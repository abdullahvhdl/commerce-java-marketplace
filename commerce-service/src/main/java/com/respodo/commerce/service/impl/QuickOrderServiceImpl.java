package com.respodo.commerce.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.respodo.commerce.common.PriceOfferTypeConstants;
import com.respodo.commerce.common.QuickOrderStatus;
import com.respodo.commerce.dto.AddressDTO;
import com.respodo.commerce.dto.ProductDTO;
import com.respodo.commerce.dto.ProductDescDTO;
import com.respodo.commerce.dto.QuantityPrice;
import com.respodo.commerce.dto.RegisterdOrderDTO;
import com.respodo.commerce.dto.StoreDTO;
import com.respodo.commerce.dto.UserDTO;
import com.respodo.commerce.mapper.AddressMapper;
import com.respodo.commerce.mapper.ProductDescriptionMapper;
import com.respodo.commerce.mapper.ProductMapper;
import com.respodo.commerce.mapper.StoreMapper;
import com.respodo.commerce.mapper.UserMapper;
import com.respodo.commerce.repository.dao.AddressRepository;
import com.respodo.commerce.repository.dao.CurrencyPriceRepository;
import com.respodo.commerce.repository.dao.ProductRepository;
import com.respodo.commerce.repository.dao.QuickOrderRepository;
import com.respodo.commerce.repository.dao.StoreRepository;
import com.respodo.commerce.repository.dao.UserRepository;
import com.respodo.commerce.repository.domain.Address;
import com.respodo.commerce.repository.domain.CurrencyPrice;
import com.respodo.commerce.repository.domain.Product;
import com.respodo.commerce.repository.domain.ProductDesc;
import com.respodo.commerce.repository.domain.QuickOrder;
import com.respodo.commerce.repository.domain.Store;
import com.respodo.commerce.repository.domain.User;
import com.respodo.commerce.service.QuickOrderService;

@Service(value = "quickOrderService")
@Transactional
public class QuickOrderServiceImpl implements QuickOrderService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private QuickOrderRepository quickOrderRepository;

	@Autowired
	private CurrencyPriceRepository currencyPriceRepository;
	
	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private ProductDescriptionMapper productDescriptionMapper;

	@Override
	public Optional<QuickOrder> orderItem(RegisterdOrderDTO registeredOrderDTO) {
		Product product = productRepository.findOne(registeredOrderDTO
				.getProductId());
		Store store = storeRepository.findOne(registeredOrderDTO.getStoreId());
		User user = userRepository
				.findOneByLogin(registeredOrderDTO.getLogin());
		if (product != null && store != null && user != null) {
			QuickOrder quickOrder = new QuickOrder();
			quickOrder.setProduct(product);
			quickOrder.setStore(store);
			quickOrder.setUser(user);
			quickOrder.setQuantity(registeredOrderDTO.getQuantity());
			CurrencyPrice currencyPrice = getPrice(product.getId(),
					store.getId(), "USD");
			quickOrder.setProductPrice(currencyPrice.getPrice());
			quickOrder.setTotalPrice(currencyPrice.getPrice().multiply(new BigDecimal(registeredOrderDTO.getQuantity())));
			quickOrder.setCurrency(currencyPrice.getCurrency());
			quickOrder.setStatus(QuickOrderStatus.SUBMITTED.name());
			quickOrderRepository.save(quickOrder);
			return Optional.ofNullable(quickOrder);
		} else {
			return Optional.empty();
		}

	}

	protected CurrencyPrice getPrice(Long productId, Long storeId, String currencyCode) {
		CurrencyPrice productPrice = new CurrencyPrice();
		Optional<CurrencyPrice> cp = currencyPriceRepository
				.findProductPrice(productId, storeId,
						PriceOfferTypeConstants.OFFER, currencyCode);
		cp.ifPresent(currencyPrice -> {
			productPrice.setCurrency(currencyPrice.getCurrency());
			productPrice.setPrice(currencyPrice.getPrice());
		});
		return productPrice;
	}

	@Override
	public Optional<QuickOrder> getOrder(Long orderId) {
		QuickOrder order = quickOrderRepository.getOne(orderId);
		if (order != null) {

		}
		return Optional.ofNullable(order);
	}

	public void getOrderDetails(DelegateExecution execution) {
		Long orderId = execution.getVariable("orderId", Long.class);
		QuickOrder order = quickOrderRepository.getOne(orderId);
		if (order != null) {
			UserDTO user = userMapper.toUserDTO(order.getUser());

			// user.s
			StoreDTO store = storeMapper.toStoreDTO(order.getStore());
			AddressDTO storeAddress = addressMapper.toAddressDTO(order.getStore()
					.getAddress());
			store.setAddress(storeAddress);
			ProductDTO prodcut =productMapper.toProductDTO(order.getProduct());
			List <ProductDescDTO> description = new ArrayList<ProductDescDTO>();
			for (ProductDesc desc : order.getProduct().getProductDescs()) {
				ProductDescDTO descreptionDTO = productDescriptionMapper.toProductDescriptionDTO(desc);
				description.add(descreptionDTO);
				break;
			}
			prodcut.setDescription(description);
			execution.setVariable("user", user);
			execution.setVariable("store", store);
			execution.setVariable("product", prodcut);
			QuantityPrice quantityPrice = new QuantityPrice();
			quantityPrice.setPrice(order.getProductPrice());
			quantityPrice.setQuantity(order.getQuantity().longValue());
			execution.setVariable("quantityPrice", quantityPrice);
		}
	}

	public void updateOrder(DelegateExecution execution) {
		Long orderId = execution.getVariable("orderId", Long.class);
		QuickOrder order = quickOrderRepository.getOne(orderId);
		String productAvailable = execution.getVariable("productAvailable",
				String.class);
		UserDTO user = execution.getVariable("user", UserDTO.class);
		User userDomain = userRepository.findOneByLogin(user.getEmail());
		Address address = userDomain.getAddress();
		address.setAddress1(user.getAddress1());
		address.setAddress2(user.getAddress2());
		address.setCity(user.getCity());
		address.setCountry(user.getCountry());
		address.setState(user.getState());
		address.setZipCode(user.getZipCode());
		addressRepository.save(address);
		String userConfirm = execution.getVariable("customerConfirm",
				String.class);
		QuantityPrice quantityPrice = execution.getVariable("quantityPrice",
				QuantityPrice.class);
		if (order != null) {
			if (productAvailable.equals("true") && userConfirm.equals("true")) {
				order.setQuantity(quantityPrice.getQuantity().intValue());
				order.setTotalPrice(order.getProductPrice().multiply(new BigDecimal(quantityPrice.getQuantity())));
				order.setStatus(QuickOrderStatus.APPROVED.name());
			} else {
				order.setStatus(QuickOrderStatus.CANCELLED.name());
			}
			quickOrderRepository.save(order);
		}
	}
}
