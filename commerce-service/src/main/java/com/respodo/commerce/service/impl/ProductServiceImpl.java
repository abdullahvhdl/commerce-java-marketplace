package com.respodo.commerce.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.respodo.commerce.common.AttributeTypeConstants;
import com.respodo.commerce.common.PriceOfferTypeConstants;
import com.respodo.commerce.common.ProductTypeConstants;
import com.respodo.commerce.constants.Metrics;
import com.respodo.commerce.dto.AddressDTO;
import com.respodo.commerce.dto.CurrencyPriceDTO;
import com.respodo.commerce.dto.ImageDTO;
import com.respodo.commerce.dto.LanguageDTO;
import com.respodo.commerce.dto.LocationDTO;
import com.respodo.commerce.dto.ProdAssignedAttrDTO;
import com.respodo.commerce.dto.ProductDTO;
import com.respodo.commerce.dto.ProductDescDTO;
import com.respodo.commerce.dto.ProductSearchDTO;
import com.respodo.commerce.dto.RelativeStoreDTO;
import com.respodo.commerce.mapper.AddressMapper;
import com.respodo.commerce.mapper.CurrencyPriceMapper;
import com.respodo.commerce.mapper.ImageMapper;
import com.respodo.commerce.mapper.LocationMapper;
import com.respodo.commerce.mapper.ProductDescriptionMapper;
import com.respodo.commerce.mapper.ProductMapper;
import com.respodo.commerce.mapper.ProductSearchMapper;
import com.respodo.commerce.repository.config.SecurityUtils;
import com.respodo.commerce.repository.dao.CategoryRepository;
import com.respodo.commerce.repository.dao.CurrencyPriceRepository;
import com.respodo.commerce.repository.dao.ImageRepository;
import com.respodo.commerce.repository.dao.LanguageRepository;
import com.respodo.commerce.repository.dao.PriceOfferRepository;
import com.respodo.commerce.repository.dao.PriceOfferTypeRepository;
import com.respodo.commerce.repository.dao.ProdAssignedAttrRepository;
import com.respodo.commerce.repository.dao.ProductDescRepository;
import com.respodo.commerce.repository.dao.ProductRepository;
import com.respodo.commerce.repository.dao.ProductStoreRelRepository;
import com.respodo.commerce.repository.dao.StoreRepository;
import com.respodo.commerce.repository.domain.AttributeType;
import com.respodo.commerce.repository.domain.AttributeValue;
import com.respodo.commerce.repository.domain.AttributeValueDesc;
import com.respodo.commerce.repository.domain.Catalog;
import com.respodo.commerce.repository.domain.Category;
import com.respodo.commerce.repository.domain.CurrencyPrice;
import com.respodo.commerce.repository.domain.Image;
import com.respodo.commerce.repository.domain.PriceOffer;
import com.respodo.commerce.repository.domain.PriceOfferType;
import com.respodo.commerce.repository.domain.ProdAssignedAttr;
import com.respodo.commerce.repository.domain.Product;
import com.respodo.commerce.repository.domain.ProductDesc;
import com.respodo.commerce.repository.domain.ProductStoreRel;
import com.respodo.commerce.repository.domain.Store;
import com.respodo.commerce.service.ProductService;
import com.respodo.commerce.service.util.DistanceCalculator;
import com.respodo.commerce.service.util.LocationUtil;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final Logger log = LoggerFactory
			.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private ProductDescRepository productDescRepository;

	@Autowired
	private CurrencyPriceRepository currencyPriceRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private DistanceCalculator distanceCalculator;

	@Autowired
	private ProductStoreRelRepository productStoreRelRepository;

	@Autowired
	private PriceOfferRepository priceOfferRepository;

	@Autowired
	private PriceOfferTypeRepository priceOfferTypeRepository;

	@Autowired
	private ProdAssignedAttrRepository prodAssignedAttrRepository;

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductSearchMapper productSearchMapper;

	@Autowired
	private ProductDescriptionMapper productDescriptionMapper;

	@Autowired
	private ImageMapper imageMapper;

	@Autowired
	private CurrencyPriceMapper currencyPriceMapper;

	@Autowired
	private LocationMapper locationMapper;

	@Autowired
	private AddressMapper addressMapper;

	@Override
	public ProductDTO getProductById(Long productId) {
		Product product = productRepository.getOne(productId);
		if (product == null) {
			return null;
		}
		ProductDTO productDTO = productMapper.toProductDTO(product);
		ImageDTO imageDTO = imageMapper.toImageDTO(product.getImage());
		productDTO.setImage(imageDTO);
		List<ProductDescDTO> description = getProductDescription(product
				.getId());
		productDTO.setDescription(description);

		return productDTO;
	}

	@Override
	public Page<ProductDTO> getCategoryProducts(Long categoryId,
			Pageable pageRequest) {
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		Page<Product> pr = productRepository.findByCategoriesIdAndProdType(
				categoryId, ProductTypeConstants.PRODUCT, pageRequest);

		List<Product> products = pr.getContent();

		products.forEach(product -> {
			ProductDTO productDTO = productMapper.toProductDTO(product);
			ImageDTO imageDTO = imageMapper.toImageDTO(product.getImage());
			productDTO.setImage(imageDTO);
			List<ProductDescDTO> description = getProductDescription(product
					.getId());
			productDTO.setDescription(description);
			productDTOs.add(productDTO);
		});

		Page<ProductDTO> pageableProducts = new PageImpl<ProductDTO>(
				productDTOs, pageRequest, pr.getTotalElements());
		return pageableProducts;
	}

	@Override
	public Page<ProductSearchDTO> searchProducts(String query,
			Pageable pageRequest) {
		List<ProductSearchDTO> productDTOs = new ArrayList<ProductSearchDTO>();
		Page<Product> products = productRepository.searchProducts(query,
				ProductTypeConstants.PRODUCT, pageRequest);
		products.getContent()
				.forEach(
						product -> {
							ProductSearchDTO productDTO = productSearchMapper
									.toProductSearchDTO(product);
							ImageDTO imageDTO = imageMapper.toImageDTO(product
									.getImage());
							productDTO.setImage(imageDTO);
							List<ProductDescDTO> description = getProductDescription(product
									.getId());
							productDTO.setDescription(description);

							productDTOs.add(productDTO);

						});
		Page<ProductSearchDTO> pageableProducts = new PageImpl<ProductSearchDTO>(
				productDTOs, pageRequest, products.getTotalElements());
		return pageableProducts;
	}

	@Override
	public List<ProductDTO> getProductsByIds(List<Long> productIds) {
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		List<Product> products = productRepository.findByIdIn(productIds);

		products.forEach(product -> {
			ProductDTO productDTO = productMapper.toProductDTO(product);
			ImageDTO imageDTO = imageMapper.toImageDTO(product.getImage());
			productDTO.setImage(imageDTO);
			List<ProductDescDTO> description = getProductDescription(product
					.getId());
			productDTO.setDescription(description);
			productDTOs.add(productDTO);
		});
		return productDTOs;
	}

	@Override
	public List<ProductDTO> getProductItems(Long productId, Long storeId) {
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		Store store = storeRepository.findOne(storeId);
		if (store != null) {
			Catalog activeCatalog = store.getActiveCatalog();
			if (activeCatalog != null) {
				List<Product> products = productRepository
						.findByProductParentIdAndProdTypeAndCategories_catalog(
								productId, ProductTypeConstants.ITEM,
								activeCatalog);
				log.debug("getProductItems porductId {}, storeId {}",
						productId, storeId);
				products.forEach(product -> {
					ProductDTO productDTO = productMapper.toProductDTO(product);
					ImageDTO imageDTO = imageMapper.toImageDTO(product
							.getImage());
					productDTO.setImage(imageDTO);
					List<ProductDescDTO> description = getProductDescription(product
							.getId());
					productDTO.setDescription(description);
					productDTOs.add(productDTO);
				});
			}
		}

		return productDTOs;
	}

	@Override
	public List<ProductDTO> getProductItems(Long productId) {
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		List<Product> products = productRepository
				.findByProductParentIdAndProdType(productId,
						ProductTypeConstants.ITEM);
		log.debug("getProductItems porductId {}", productId);
		products.forEach(product -> {
			ProductDTO productDTO = productMapper.toProductDTO(product);
			ImageDTO imageDTO = imageMapper.toImageDTO(product.getImage());
			productDTO.setImage(imageDTO);
			List<ProductDescDTO> description = getProductDescription(product
					.getId());
			productDTO.setDescription(description);
			productDTOs.add(productDTO);
		});

		return productDTOs;
	}

	@Override
	public List<CurrencyPriceDTO> getProductOfferPrices(Long productId,
			Long storeId) {
		List<CurrencyPrice> prices = currencyPriceRepository.findProductPrice(
				productId, storeId, PriceOfferTypeConstants.OFFER);
		List<CurrencyPriceDTO> priceDTOs = new ArrayList<CurrencyPriceDTO>();
		prices.forEach(price -> {
			CurrencyPriceDTO priceDTO = currencyPriceMapper
					.toCurrencyPriceDTO(price);
			priceDTOs.add(priceDTO);
		});
		return priceDTOs;
	}

	@Override
	public List<CurrencyPriceDTO> getProductOriginalPrices(Long productId,
			Long storeId) {
		List<CurrencyPrice> prices = currencyPriceRepository.findProductPrice(
				productId, storeId, PriceOfferTypeConstants.ORIGINAL);
		List<CurrencyPriceDTO> priceDTOs = new ArrayList<CurrencyPriceDTO>();
		prices.forEach(price -> {
			CurrencyPriceDTO priceDTO = currencyPriceMapper
					.toCurrencyPriceDTO(price);
			priceDTOs.add(priceDTO);
		});
		return priceDTOs;
	}

	@Override
	public List<ProductDescDTO> getProductDescription(Long productId) {
		List<ProductDesc> prodcutDescDomain = productDescRepository
				.findByProductId(productId);
		List<ProductDescDTO> productDescDTOs = new ArrayList<ProductDescDTO>();
		prodcutDescDomain.forEach(description -> {
			ProductDescDTO productDescDTO = productDescriptionMapper
					.toProductDescriptionDTO(description);
			productDescDTOs.add(productDescDTO);
		});
		return productDescDTOs;
	}

	@Override
	public List<RelativeStoreDTO> getRelativeStores(Long storeId,
			Long productId, LocationUtil location) {
		Integer distance;
		if (location.getMetric() == Metrics.MILE) {
			distance = (int) (location.getDistance() * 1.609344);
		} else {
			distance = location.getDistance();
		}
		List<ProductStoreRel> relativeStores = productStoreRelRepository
				.findByProductIdAndLocation(productId,
						new BigDecimal(location.getLat()), new BigDecimal(
								location.getLng()), distance.doubleValue());
		List<RelativeStoreDTO> relativeStoreDTOs = new ArrayList<RelativeStoreDTO>();
		relativeStores
				.forEach(prodStore -> {
					if (!prodStore.getId().equals(storeId)) {
						ProductDTO product = getProductById(productId);
						RelativeStoreDTO relativeStoreDTO = new RelativeStoreDTO(
								prodStore);
						ImageDTO imageDTO = imageMapper.toImageDTO(prodStore
								.getImage());
						LocationDTO locationDTO = locationMapper
								.toLocationDTO(prodStore.getLocation());
						AddressDTO addressDTO = addressMapper
								.toAddressDTO(prodStore.getAddress());
						Double storeDistance = distanceCalculator.distance(
								location.getLat(), location.getLng(),
								locationDTO.getLatitude().doubleValue(),
								locationDTO.getLongitude().doubleValue(),
								location.getMetric());
						relativeStoreDTO.setAddress(addressDTO);
						relativeStoreDTO.setLocation(locationDTO);
						relativeStoreDTO.setImage(imageDTO);
						relativeStoreDTO.setDistance(storeDistance);
						relativeStoreDTO.setProduct(product);
						relativeStoreDTOs.add(relativeStoreDTO);
					}

				});
		return relativeStoreDTOs;
	}

	@Override
	public List<RelativeStoreDTO> getProductStores(Long productId) {
		List<ProductStoreRel> relativeStores = productStoreRelRepository
				.findByProductId(productId);
		List<RelativeStoreDTO> relativeStoreDTOs = new ArrayList<RelativeStoreDTO>();
		relativeStores
				.forEach(prodStore -> {
					RelativeStoreDTO relativeStoreDTO = new RelativeStoreDTO(
							prodStore);
					ImageDTO imageDTO = imageMapper.toImageDTO(prodStore
							.getImage());
					LocationDTO locationDTO = locationMapper
							.toLocationDTO(prodStore.getLocation());
					AddressDTO addressDTO = addressMapper
							.toAddressDTO(prodStore.getAddress());
					relativeStoreDTO.setAddress(addressDTO);
					relativeStoreDTO.setLocation(locationDTO);
					relativeStoreDTO.setImage(imageDTO);
					relativeStoreDTOs.add(relativeStoreDTO);
				});
		return relativeStoreDTOs;
	}

	@Override
	public List<ProductDTO> getAllCategoryProducts(Long categoryId) {
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

		List<Product> products = productRepository
				.findByCategoriesIdAndProdType(categoryId,
						ProductTypeConstants.PRODUCT);

		products.forEach(product -> {
			ProductDTO productDTO = productMapper.toProductDTO(product);
			ImageDTO imageDTO = imageMapper.toImageDTO(product.getImage());
			productDTO.setImage(imageDTO);
			List<ProductDescDTO> description = getProductDescription(product
					.getId());
			productDTO.setDescription(description);
			productDTOs.add(productDTO);
		});

		return productDTOs;
	}

	@Override
	public List<ProductDTO> findProducts(String query) {
		List<Product> products = productRepository.searchProducts(query,
				ProductTypeConstants.PRODUCT);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		products.forEach(product -> {
			ProductDTO productDTO = productMapper.toProductDTO(product);
			ImageDTO imageDTO = imageMapper.toImageDTO(product.getImage());
			productDTO.setImage(imageDTO);
			List<ProductDescDTO> description = getProductDescription(product
					.getId());
			productDTO.setDescription(description);
			productDTOs.add(productDTO);
		});
		return productDTOs;
	}

	@Override
	public ProductDTO addProductToStoreCategory(Long storeId, Long categoryId,
			Long productId) {
		Product product = productRepository.findOne(productId);
		Category category = categoryRepository.findOne(categoryId);
		if (product == null || category == null) {
			return null;
		}
		product.getCategories().add(category);
		productRepository.save(product);
		for (Product item : product.getItems()) {
			item.getCategories().add(category);
			productRepository.save(item);
		}

		return getProductById(productId);
	}

	@Override
	public ProductDTO updateProduct(Long storeId, Long categoryId,
			ProductDTO product, Long productId) {
		Product dbProduct = productRepository.findOne(productId);
		Store dbStore = storeRepository.findOne(storeId);
		if (dbProduct == null || dbStore == null) {
			return null;
		}
		dbProduct.setBrand(product.getBrand());
		dbProduct.setUniqueId(product.getUniqueId());
		ImageDTO image = product.getImage();
		if (image != null) {
			Image dbImage = dbProduct.getImage();
			if (dbImage == null) {
				dbImage = new Image();
			}
			dbImage.setImage1(image.getImage1());
			dbImage.setImage2(image.getImage2());
			dbImage.setImage3(image.getImage3());
			dbImage.setImage4(image.getImage4());
			dbImage.setThumbnail(image.getThumbnail());
			imageRepository.save(dbImage);
			dbProduct.setImage(dbImage);

		}
		productRepository.save(dbProduct);
		for (ProductDescDTO description : product.getDescription()) {
			LanguageDTO language = description.getLanguage();
			if (language != null) {
				ProductDesc dbDescription = productDescRepository
						.findOneByProductIdAndLanguageLocale(productId,
								language.getLocale());
				if (dbDescription == null) {
					dbDescription = new ProductDesc();
				}
				dbDescription.setLanguage(languageRepository
						.findOneByLocale(language.getLocale()));
				dbDescription.setActive(true);
				dbDescription.setDescription(description.getDescription());
				dbDescription.setName(description.getName());
				dbDescription.setProduct(dbProduct);
				productDescRepository.save(dbDescription);
			}

		}
		PriceOfferType offerType = priceOfferTypeRepository
				.findOneByType(PriceOfferTypeConstants.OFFER);
		PriceOffer offerPrice = priceOfferRepository
				.findOneByProductAndStoreAndPriceOfferType(dbProduct, dbStore,
						offerType);
		if (offerPrice == null) {
			offerPrice = new PriceOffer();
			offerPrice.setProduct(dbProduct);
			offerPrice.setStore(dbStore);
			offerPrice.setPriceOfferType(offerType);
			priceOfferRepository.save(offerPrice);
		}

		PriceOfferType originalType = priceOfferTypeRepository
				.findOneByType(PriceOfferTypeConstants.ORIGINAL);
		PriceOffer originalPrice = priceOfferRepository
				.findOneByProductAndStoreAndPriceOfferType(dbProduct, dbStore,
						originalType);
		if (originalPrice == null) {
			originalPrice = new PriceOffer();
			originalPrice.setProduct(dbProduct);
			originalPrice.setStore(dbStore);
			originalPrice.setPriceOfferType(originalType);
			priceOfferRepository.save(originalPrice);
		}

		for (CurrencyPriceDTO price : product.getOfferPrice()) {
			CurrencyPrice priceDomain = currencyPriceRepository
					.findOneByPriceOfferAndCurrency(offerPrice,
							price.getCurrency());
			if (priceDomain == null) {
				priceDomain = new CurrencyPrice();
				priceDomain.setPriceOffer(offerPrice);
				priceDomain.setCurrency(price.getCurrency());
				if(price.getPrice()!=null){
					priceDomain.setPrice(new BigDecimal(price.getPrice()));
					currencyPriceRepository.save(priceDomain);
				}
				
			} else {
				if(price.getPrice()!=null){
					priceDomain.setPrice(new BigDecimal(price.getPrice()));
					currencyPriceRepository.save(priceDomain);
				}
				else{
					priceDomain.setPrice(null);
					currencyPriceRepository.delete(priceDomain);
				}
			}
			

		}
		for (CurrencyPriceDTO price : product.getOriginalPrice()) {
			CurrencyPrice priceDomain = currencyPriceRepository
					.findOneByPriceOfferAndCurrency(originalPrice,
							price.getCurrency());
			if (priceDomain == null) {
				priceDomain = new CurrencyPrice();
				priceDomain.setPriceOffer(originalPrice);
				priceDomain.setCurrency(price.getCurrency());
				if(price.getPrice()!=null){
					priceDomain.setPrice(new BigDecimal(price.getPrice()));
					currencyPriceRepository.save(priceDomain);
				}
				
			} else {
				if(price.getPrice()!=null){
					priceDomain.setPrice(new BigDecimal(price.getPrice()));
					currencyPriceRepository.save(priceDomain);
				}
				else{
					priceDomain.setPrice(null);
					currencyPriceRepository.delete(priceDomain);
				}
			}

		}

		return getProductById(productId);
	}

	@Override
	public void generateItems(Long storeId, Long categoryId, Long productId) {
		Product product = productRepository.findOne(productId);
		String owner=SecurityUtils.getCurrentLogin();
		Store store=storeRepository.findOneByIdAndOwner_Login(storeId, owner);
		if (product == null || store ==null) {
			return;
		}
		int itemsNO = 1;
		for (ProdAssignedAttr attribute : product.getProdAssignedAttrs()) {
			if (attribute.getAttribute() != null) {
				AttributeType type = attribute.getAttribute()
						.getAttributeType();
				if (type != null
						&& type.getType()
								.equals(AttributeTypeConstants.BUYABLE)) {
					itemsNO *= attribute.getAttribute().getAttributeValues()
							.size();
				}
			}

		}
		if (product.getItems().size() >= itemsNO) {
			return;
		}
		List<Product> productItems = new ArrayList<Product>();
		int[] combination = new int[product.getProdAssignedAttrs().size()];
		ProdAssignedAttr[] assignedAttrs = new ProdAssignedAttr[product
				.getProdAssignedAttrs().size()];
		assignedAttrs = product.getProdAssignedAttrs().toArray(assignedAttrs);
		for (int i = 0; i < itemsNO; i++) {
			Product item = new Product();
			item.setProductParent(product);
			item.getCategories().add(categoryRepository.findOne(categoryId));
			item.setBrand(product.getBrand());
			item.setProdType(ProductTypeConstants.ITEM);
			item.setViews(0);
			Image productImage=product.getImage();
			Image itemImage=new Image();
			itemImage.setImage1(productImage.getImage1());
			itemImage.setImage2(productImage.getImage2());
			itemImage.setImage3(productImage.getImage3());
			itemImage.setImage4(productImage.getImage4());
			itemImage.setThumbnail(productImage.getThumbnail());
			imageRepository.save(itemImage);
			item.setImage(itemImage);

			String uniqueId = product.getUniqueId();
			String nameSuffex=" (";

			for (int j = 0; j < assignedAttrs.length; j++) {
				ProdAssignedAttr assignedAttr = assignedAttrs[j];
				if (assignedAttr.getAttribute() != null) {
					AttributeType type = assignedAttr.getAttribute()
							.getAttributeType();
					AttributeValue[] values = new AttributeValue[assignedAttr
							.getAttribute().getAttributeValues().size()];
					values = assignedAttr.getAttribute().getAttributeValues()
							.toArray(values);
					if (type != null
							&& type.getType().equals(
									AttributeTypeConstants.BUYABLE)) {
						ProdAssignedAttr itemAssignedAttr = new ProdAssignedAttr();
						itemAssignedAttr.setAttribute(assignedAttr
								.getAttribute());
						AttributeValue value = values[combination[j]];
						uniqueId += value.getUniqueId();
						for(AttributeValueDesc valueDesc:value.getAttributeValueDescs()){
							nameSuffex+=valueDesc.getName();
						}
						if (j != assignedAttrs.length - 1) {
							uniqueId += "-";
							nameSuffex+=", ";
						}
						else{
							nameSuffex+=")";
						}
						itemAssignedAttr.setAttributeValue(value);
						itemAssignedAttr.setProduct(item);
						prodAssignedAttrRepository.save(itemAssignedAttr);
					}
				}
			}
			item.setUniqueId(uniqueId);
			
			productRepository.save(item);
			ProductDesc productDesc=productDescRepository.findOneByProductIdAndLanguageLocale(productId, "en");
			if(productDesc !=null){
				ProductDesc itemDesc=new ProductDesc();
				itemDesc.setActive(productDesc.getActive());
				itemDesc.setDescription(productDesc.getDescription());
				itemDesc.setName(productDesc.getName() +nameSuffex);
				itemDesc.setProduct(item);
				itemDesc.setLanguage(productDesc.getLanguage());
				productDescRepository.save(itemDesc);
			}
			
			PriceOffer originalPrice=new PriceOffer();
			originalPrice.setPriceOfferType(priceOfferTypeRepository.findOneByType(PriceOfferTypeConstants.ORIGINAL));
			originalPrice.setProduct(item);
			originalPrice.setStore(store);
			priceOfferRepository.save(originalPrice);
			PriceOffer offerPrice=new PriceOffer();
			offerPrice.setPriceOfferType(priceOfferTypeRepository.findOneByType(PriceOfferTypeConstants.OFFER));
			offerPrice.setProduct(item);
			offerPrice.setStore(store);
			priceOfferRepository.save(offerPrice);
			List<CurrencyPriceDTO> originalPrices=getProductOriginalPrices(productId, storeId);
			originalPrices.forEach(price->{
				CurrencyPrice priceDomain = new CurrencyPrice();
				priceDomain.setPriceOffer(originalPrice);
				priceDomain.setCurrency(price.getCurrency());
				if(price.getPrice()!=null){
					priceDomain.setPrice(new BigDecimal(price.getPrice()));
					currencyPriceRepository.save(priceDomain);
				}
			});
			List<CurrencyPriceDTO> offerPrices=getProductOfferPrices(productId, storeId);
			offerPrices.forEach(price->{
				CurrencyPrice priceDomain = new CurrencyPrice();
				priceDomain.setPriceOffer(offerPrice);
				priceDomain.setCurrency(price.getCurrency());
				if(price.getPrice()!=null){
					priceDomain.setPrice(new BigDecimal(price.getPrice()));
					currencyPriceRepository.save(priceDomain);
				}
			});
			for (int j = assignedAttrs.length - 1; j >= 0; j--) {
				ProdAssignedAttr assignedAttr = assignedAttrs[j];

				if (assignedAttr.getAttribute() != null) {
					AttributeType type = assignedAttr.getAttribute()
							.getAttributeType();
					AttributeValue[] values = new AttributeValue[assignedAttr
							.getAttribute().getAttributeValues().size()];
					values = assignedAttr.getAttribute().getAttributeValues()
							.toArray(values);
					if (type != null
							&& type.getType().equals(
									AttributeTypeConstants.BUYABLE)) {
						int n = ++combination[j];
						if (n >= values.length) {
							// "digit" exceeded valid range -> back to 0 and
							// continue incrementing
							combination[j] = 0;
						} else {
							// "digit" still in valid range -> stop
							break;
						}
					}
				}

			}
			productItems.add(item);

		}
		if (product.getProdAssignedAttrs().size() > 1) {

		}
	}

	@Override
	public ProductDTO updateItem(Long storeId, Long categoryId, Long productId,
			ProductDTO item, Long itemId) {
		Product dbItem = productRepository.findOne(itemId);
		Store dbStore = storeRepository.findOne(storeId);
		if (dbItem == null || dbStore == null) {
			return null;
		}
		dbItem.setBrand(item.getBrand());
		dbItem.setUniqueId(item.getUniqueId());
		ImageDTO image = item.getImage();
		if (image != null) {
			Image dbImage = dbItem.getImage();
			if (dbImage == null) {
				dbImage = new Image();
			}
			dbImage.setImage1(image.getImage1());
			dbImage.setImage2(image.getImage2());
			dbImage.setImage3(image.getImage3());
			dbImage.setImage4(image.getImage4());
			dbImage.setThumbnail(image.getThumbnail());
			imageRepository.save(dbImage);
			dbItem.setImage(dbImage);

		}
		productRepository.save(dbItem);
		for (ProductDescDTO description : item.getDescription()) {
			LanguageDTO language = description.getLanguage();
			if (language != null) {
				ProductDesc dbDescription = productDescRepository
						.findOneByProductIdAndLanguageLocale(itemId,
								language.getLocale());
				if (dbDescription == null) {
					dbDescription = new ProductDesc();
				}
				dbDescription.setLanguage(languageRepository
						.findOneByLocale(language.getLocale()));
				dbDescription.setActive(true);
				dbDescription.setDescription(description.getDescription());
				dbDescription.setName(description.getName());
				dbDescription.setProduct(dbItem);
				productDescRepository.save(dbDescription);
			}

		}
		PriceOfferType offerType = priceOfferTypeRepository
				.findOneByType(PriceOfferTypeConstants.OFFER);
		PriceOffer offerPrice = priceOfferRepository
				.findOneByProductAndStoreAndPriceOfferType(dbItem, dbStore,
						offerType);
		if (offerPrice == null) {
			offerPrice = new PriceOffer();
			offerPrice.setProduct(dbItem);
			offerPrice.setStore(dbStore);
			offerPrice.setPriceOfferType(offerType);
			priceOfferRepository.save(offerPrice);
		}

		PriceOfferType originalType = priceOfferTypeRepository
				.findOneByType(PriceOfferTypeConstants.ORIGINAL);
		PriceOffer originalPrice = priceOfferRepository
				.findOneByProductAndStoreAndPriceOfferType(dbItem, dbStore,
						originalType);
		if (originalPrice == null) {
			originalPrice = new PriceOffer();
			originalPrice.setProduct(dbItem);
			originalPrice.setStore(dbStore);
			originalPrice.setPriceOfferType(originalType);
			priceOfferRepository.save(originalPrice);
		}

		for (CurrencyPriceDTO price : item.getOfferPrice()) {
			CurrencyPrice priceDomain = currencyPriceRepository
					.findOneByPriceOfferAndCurrency(offerPrice,
							price.getCurrency());
			if (priceDomain == null) {
				priceDomain = new CurrencyPrice();
				priceDomain.setPriceOffer(offerPrice);
				priceDomain.setCurrency(price.getCurrency());
				if(price.getPrice()!=null){
					priceDomain.setPrice(new BigDecimal(price.getPrice()));
					currencyPriceRepository.save(priceDomain);
				}
				
			} else {
				if(price.getPrice()!=null){
					priceDomain.setPrice(new BigDecimal(price.getPrice()));
					currencyPriceRepository.save(priceDomain);
				}
				else{
					priceDomain.setPrice(null);
					currencyPriceRepository.delete(priceDomain);
				}
			}
			

		}
		for (CurrencyPriceDTO price : item.getOriginalPrice()) {
			CurrencyPrice priceDomain = currencyPriceRepository
					.findOneByPriceOfferAndCurrency(originalPrice,
							price.getCurrency());
			if (priceDomain == null) {
				priceDomain = new CurrencyPrice();
				priceDomain.setPriceOffer(originalPrice);
				priceDomain.setCurrency(price.getCurrency());
				if(price.getPrice()!=null){
					priceDomain.setPrice(new BigDecimal(price.getPrice()));
					currencyPriceRepository.save(priceDomain);
				}
				
			} else {
				if(price.getPrice()!=null){
					priceDomain.setPrice(new BigDecimal(price.getPrice()));
					currencyPriceRepository.save(priceDomain);
				}
				else{
					priceDomain.setPrice(null);
					currencyPriceRepository.delete(priceDomain);
				}
			}

		}

		return getProductById(itemId);
	}

	@Override
	public ProductDTO saveItem(Long storeId, Long categoryId, Long productId,
			ProductDTO item) {
		Store store=storeRepository.findOne(storeId);
		Category category=categoryRepository.findOne(categoryId);
		Product product=productRepository.findOne(productId);
		Product itemDomain=productMapper.toProductDomain(item);
		if(item==null||store==null || category==null || product==null){
			return null;
		}
		itemDomain.setProdType(ProductTypeConstants.ITEM);
		itemDomain.setProductParent(product);
		Image image=imageMapper.toImageDomain(item.getImage());
		if(image !=null){
			imageRepository.save(image);
			itemDomain.setImage(image);
		}
		itemDomain.getCategories().add(category);
		productRepository.save(itemDomain);
		for(ProductDescDTO description:item.getDescription()){
			ProductDesc descriptionDomain=productDescriptionMapper.toProductDescDomain(description);
			if(description.getLanguage() !=null){
				descriptionDomain.setLanguage(languageRepository.findOneByLocale(description.getLanguage().getLocale()));
				descriptionDomain.setProduct(itemDomain);
			}
			productDescRepository.save(descriptionDomain);
		}
		
		PriceOffer originalPrice=new PriceOffer();
		originalPrice.setPriceOfferType(priceOfferTypeRepository.findOneByType(PriceOfferTypeConstants.ORIGINAL));
		originalPrice.setProduct(itemDomain);
		originalPrice.setStore(store);
		priceOfferRepository.save(originalPrice);
		PriceOffer offerPrice=new PriceOffer();
		offerPrice.setPriceOfferType(priceOfferTypeRepository.findOneByType(PriceOfferTypeConstants.OFFER));
		offerPrice.setProduct(itemDomain);
		offerPrice.setStore(store);
		priceOfferRepository.save(offerPrice);
		for(CurrencyPriceDTO price:item.getOfferPrice()){
			CurrencyPrice priceDomain = new CurrencyPrice();
			priceDomain.setPriceOffer(offerPrice);
			priceDomain.setCurrency(price.getCurrency());
			if(price.getPrice()!=null){
				priceDomain.setPrice(new BigDecimal(price.getPrice()));
				currencyPriceRepository.save(priceDomain);
			}
		}
		for(CurrencyPriceDTO price:item.getOriginalPrice()){
			CurrencyPrice priceDomain = new CurrencyPrice();
			priceDomain.setPriceOffer(originalPrice);
			priceDomain.setCurrency(price.getCurrency());
			if(price.getPrice()!=null){
				priceDomain.setPrice(new BigDecimal(price.getPrice()));
				currencyPriceRepository.save(priceDomain);
			}
		}
		
		return getProductById(itemDomain.getId());
	}

	@Override
	public ProductDTO saveProduct(Long storeId, Long categoryId,
			ProductDTO product) {
		Store store=storeRepository.findOne(storeId);
		Category category=categoryRepository.findOne(categoryId);
		Product productDomain=productMapper.toProductDomain(product);
		if(product==null||store==null || category==null){
			return null;
		}
		productDomain.setProdType(ProductTypeConstants.PRODUCT);
		productDomain.getCategories().add(category);
		Image image=imageMapper.toImageDomain(product.getImage());
		if(image !=null){
			imageRepository.save(image);
			productDomain.setImage(image);
		}
		productRepository.save(productDomain);
		for(ProductDescDTO description:product.getDescription()){
			ProductDesc descriptionDomain=productDescriptionMapper.toProductDescDomain(description);
			if(description.getLanguage() !=null){
				descriptionDomain.setLanguage(languageRepository.findOneByLocale(description.getLanguage().getLocale()));
				descriptionDomain.setProduct(productDomain);
			}
			productDescRepository.save(descriptionDomain);
		}
		
		PriceOffer originalPrice=new PriceOffer();
		originalPrice.setPriceOfferType(priceOfferTypeRepository.findOneByType(PriceOfferTypeConstants.ORIGINAL));
		originalPrice.setProduct(productDomain);
		originalPrice.setStore(store);
		priceOfferRepository.save(originalPrice);
		PriceOffer offerPrice=new PriceOffer();
		offerPrice.setPriceOfferType(priceOfferTypeRepository.findOneByType(PriceOfferTypeConstants.OFFER));
		offerPrice.setProduct(productDomain);
		offerPrice.setStore(store);
		priceOfferRepository.save(offerPrice);
		for(CurrencyPriceDTO price:product.getOfferPrice()){
			CurrencyPrice priceDomain = new CurrencyPrice();
			priceDomain.setPriceOffer(offerPrice);
			priceDomain.setCurrency(price.getCurrency());
			if(price.getPrice()!=null){
				priceDomain.setPrice(new BigDecimal(price.getPrice()));
				currencyPriceRepository.save(priceDomain);
			}
		}
		for(CurrencyPriceDTO price:product.getOriginalPrice()){
			CurrencyPrice priceDomain = new CurrencyPrice();
			priceDomain.setPriceOffer(originalPrice);
			priceDomain.setCurrency(price.getCurrency());
			if(price.getPrice()!=null){
				priceDomain.setPrice(new BigDecimal(price.getPrice()));
				currencyPriceRepository.save(priceDomain);
			}
		}
		
		return getProductById(productDomain.getId());
	}

	@Override
	public Boolean romoveProduct(Long storeId, Long categoryId, Long productId) {
		Category category=categoryRepository.findOne(categoryId);
		Product product=productRepository.findOne(productId);
		if(category ==null || product==null){
			return false;
		}
		product.getCategories().remove(category);
		for(Product item:product.getItems()){
			item.getCategories().remove(category);
		}
		
		return true;
	}
}
