package com.respodo.commerce.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.respodo.commerce.common.AddressTypeConstants;
import com.respodo.commerce.common.StoreTypeConstants;
import com.respodo.commerce.constants.Metrics;
import com.respodo.commerce.dto.AddressDTO;
import com.respodo.commerce.dto.ImageDTO;
import com.respodo.commerce.dto.LocationDTO;
import com.respodo.commerce.dto.StoreDTO;
import com.respodo.commerce.dto.csv.StoreCSVDTO;
import com.respodo.commerce.mapper.AddressMapper;
import com.respodo.commerce.mapper.ImageMapper;
import com.respodo.commerce.mapper.LocationMapper;
import com.respodo.commerce.mapper.StoreMapper;
import com.respodo.commerce.repository.config.SecurityUtils;
import com.respodo.commerce.repository.dao.AddressRepository;
import com.respodo.commerce.repository.dao.CatalogRepository;
import com.respodo.commerce.repository.dao.ImageRepository;
import com.respodo.commerce.repository.dao.StoreRepository;
import com.respodo.commerce.repository.dao.StoreTypeRepository;
import com.respodo.commerce.repository.dao.UserRepository;
import com.respodo.commerce.repository.domain.Address;
import com.respodo.commerce.repository.domain.Catalog;
import com.respodo.commerce.repository.domain.Image;
import com.respodo.commerce.repository.domain.Store;
import com.respodo.commerce.repository.domain.StoreType;
import com.respodo.commerce.repository.domain.User;
import com.respodo.commerce.service.StoreService;
import com.respodo.commerce.service.util.DistanceCalculator;
import com.respodo.commerce.service.util.LocationUtil;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

	private final Logger log = LoggerFactory.getLogger(StoreServiceImpl.class);

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private StoreTypeRepository storeTypeRepository;

	@Autowired
	private CatalogRepository catalogRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private ImageMapper imageMapper;

	@Autowired
	private LocationMapper locationMapper;

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private DistanceCalculator distanceCalculator;

	@Override
	public List<StoreDTO> getStoresByLocation(LocationUtil location) {
		log.debug("enter getStores");
		Integer distance;
		if (location.getMetric() == Metrics.MILE) {
			distance = (int) (location.getDistance() * 1.609344);
		} else {
			distance = location.getDistance();
		}

		List<Store> allStores = storeRepository.findByLocation(new BigDecimal(
				location.getLat()), new BigDecimal(location.getLng()), distance
				.doubleValue());
		List<StoreDTO> storeDTOs = new ArrayList<StoreDTO>();
		allStores.forEach(store -> {
			StoreDTO storeDTO = storeMapper.toStoreDTO(store);
			ImageDTO imageDTO = imageMapper.toImageDTO(store.getImage());
			LocationDTO locationDTO = locationMapper.toLocationDTO(store
					.getLocation());
			AddressDTO addressDTO = addressMapper.toAddressDTO(store
					.getAddress());
			Double storeDistance = distanceCalculator.distance(location
					.getLat(), location.getLng(), locationDTO.getLatitude()
					.doubleValue(), locationDTO.getLongitude().doubleValue(),
					location.getMetric());
			storeDTO.setAddress(addressDTO);
			storeDTO.setImage(imageDTO);
			storeDTO.setLocation(locationDTO);
			storeDTO.setDistance(storeDistance);
			storeDTOs.add(storeDTO);
		});
		log.debug("return all stores {}", allStores);

		return storeDTOs;
	}

	@Override
	public StoreDTO getStoreById(Long id) {
		Store store = storeRepository.findOne(id);
		if(store==null){
			return null;
		}
		StoreDTO storeDTO = storeMapper.toStoreDTO(store);
		ImageDTO imageDTO = imageMapper.toImageDTO(store.getImage());
		LocationDTO locationDTO = locationMapper.toLocationDTO(store
				.getLocation());
		AddressDTO addressDTO = addressMapper.toAddressDTO(store.getAddress());
		storeDTO.setAddress(addressDTO);
		storeDTO.setImage(imageDTO);
		storeDTO.setLocation(locationDTO);
		Catalog activeCatalog = store.getActiveCatalog();
		if (activeCatalog != null) {
			storeDTO.setActiveCatalogId(activeCatalog.getId());
		}
		return storeDTO;

	}

	@Override
	public Page<StoreDTO> getAllStores(Pageable pageRequest) {

		Page<Store> domainStoresPage = storeRepository.findAll(pageRequest);
		List<Store> domainStores = domainStoresPage.getContent();
		List<StoreDTO> storeDTOs = new ArrayList<StoreDTO>();
		domainStores.forEach(domainStore -> {
			StoreDTO storeDTO = storeMapper.toStoreDTO(domainStore);
			ImageDTO imageDTO = imageMapper.toImageDTO(domainStore.getImage());
			LocationDTO locationDTO = locationMapper.toLocationDTO(domainStore
					.getLocation());
			AddressDTO addressDTO = addressMapper.toAddressDTO(domainStore
					.getAddress());
			storeDTO.setAddress(addressDTO);
			storeDTO.setImage(imageDTO);
			storeDTO.setLocation(locationDTO);
			storeDTOs.add(storeDTO);
		});
		Page<StoreDTO> page = new PageImpl<StoreDTO>(storeDTOs, pageRequest,
				domainStoresPage.getTotalElements());
		return page;
	}

	@Override
	public List<StoreDTO> getStoresByOwner(String ownerEmail) {
		List<Store> allStores = storeRepository.findByOwner_Login(ownerEmail);
		List<StoreDTO> storeDTOs = new ArrayList<StoreDTO>();
		allStores.forEach(store -> {
			StoreDTO storeDTO = storeMapper.toStoreDTO(store);
			ImageDTO imageDTO = imageMapper.toImageDTO(store.getImage());
			LocationDTO locationDTO = locationMapper.toLocationDTO(store
					.getLocation());
			AddressDTO addressDTO = addressMapper.toAddressDTO(store
					.getAddress());
			storeDTO.setAddress(addressDTO);
			storeDTO.setImage(imageDTO);
			storeDTO.setLocation(locationDTO);
			storeDTOs.add(storeDTO);
		});
		log.debug("return all stores {}", allStores);

		return storeDTOs;
	}

	@Override
	public StoreDTO saveStore(StoreDTO store) {
		if (store == null || store.getAddress() == null) {
			return null;
		}
		String owner = SecurityUtils.getCurrentLogin();
		Store domainStore = storeMapper.toStoreDomain(store);
		StoreType storeType = storeTypeRepository
				.findOneByType(StoreTypeConstants.RETAIL);
		domainStore.setStoreType(storeType);
		Address domainStoreAddress = addressMapper.toAddressDomain(store
				.getAddress());
		Image domainImage = imageMapper.toImageDomain(store.getImage());
		imageRepository.save(domainImage);
		domainStore.setImage(domainImage);
		domainStoreAddress.setAddressType(AddressTypeConstants.LOCATION);
		User user = userRepository.findOneByLogin(owner);
		domainStore.setOwner(user);
		addressRepository.save(domainStoreAddress);
		domainStore.setAddress(domainStoreAddress);
		storeRepository.save(domainStore);
		StoreDTO result=storeMapper.toStoreDTO(domainStore);
		return result;
	}

	@Override
	public StoreDTO updateStore(StoreDTO store, Long storeId) {
		if (store == null || store.getAddress() == null) {
			return null;
		}
		Store dbStore = storeRepository.findOne(storeId);
		String owner = SecurityUtils.getCurrentLogin();
		if (dbStore == null || dbStore.getOwner() == null
				|| !dbStore.getOwner().getLogin().equals(owner)) {
			return null;
		}
		Catalog activeCatalog = null;
		if (store.getActiveCatalogId() != null) {
			activeCatalog = catalogRepository.findOne(store
					.getActiveCatalogId());
		}

		dbStore.setActiveCatalog(activeCatalog);
		Address dbAddress = dbStore.getAddress();
		dbStore.setName(store.getName());
		dbStore.setDescription(store.getDescription());
		AddressDTO addressDTO = store.getAddress();
		dbAddress.setAddress1(addressDTO.getAddress1());
		dbAddress.setAddress2(addressDTO.getAddress2());
		dbAddress.setCity(addressDTO.getCity());
		dbAddress.setCountry(addressDTO.getCountry());
		dbAddress.setState(addressDTO.getState());
		dbAddress.setZipCode(addressDTO.getZipCode());
		Image dbImage = dbStore.getImage();
		Image domainImage = imageMapper.toImageDomain(store.getImage());
		if (dbImage == null) {
			imageRepository.save(domainImage);
			dbStore.setImage(domainImage);
		} else {
			dbImage.setImage1(domainImage.getImage1());
			dbImage.setImage2(domainImage.getImage2());
			dbImage.setImage3(domainImage.getImage3());
			dbImage.setImage4(domainImage.getImage4());
			dbImage.setThumbnail(domainImage.getThumbnail());
			imageRepository.save(dbImage);
		}
		addressRepository.save(dbAddress);
		storeRepository.save(dbStore);
		StoreDTO result=storeMapper.toStoreDTO(dbStore);
		return result;
	}

	@Override
	public Boolean deleteStore(Long storeId) {
		if (storeId == null) {
			return false;
		}
		String owner = SecurityUtils.getCurrentLogin();
		Store store = storeRepository.findOne(storeId);
		if (store == null || store.getOwner() == null
				|| !store.getOwner().getLogin().equals(owner)) {
			return false;
		}
		if (store.getAddress() != null) {
			addressRepository.delete(store.getAddress());
		}
		storeRepository.delete(store);
		return true;
	}

	@Override
	public Boolean saveStore(StoreCSVDTO storeCSV) {
		String owner = SecurityUtils.getCurrentLogin();
		Store store = storeRepository.findOneByUniqueId(storeCSV.getUniqueId());
		if (store == null) {
			store = new Store();
			store.setDescription(storeCSV.getDescription());
			store.setName(storeCSV.getName());
			store.setUniqueId(storeCSV.getUniqueId());

			StoreType storeType = storeTypeRepository.findOneByType(storeCSV
					.getStoreType());
			store.setStoreType(storeType);

			Address address = new Address();
			address.setAddress1(storeCSV.getAddress1());
			address.setAddress2(storeCSV.getAddress2());
			address.setAddressType(storeCSV.getAddressType());
			address.setCity(storeCSV.getCity());
			address.setZipCode(storeCSV.getZipCode());
			address.setCountry(storeCSV.getCountry());
			address.setState(storeCSV.getState());
			store.setAddress(address);

			Image image = new Image();
			image.setImage1(storeCSV.getImage1());
			image.setImage2(storeCSV.getImage2());
			image.setImage3(storeCSV.getImage3());
			image.setImage4(storeCSV.getImage4());
			image.setOtherImages(storeCSV.getOtherImages());
			image.setThumbnail(storeCSV.getThumbnail());
			store.setImage(image);
			User user = userRepository.findOneByLogin(owner);
			store.setOwner(user);
			addressRepository.save(address);
			imageRepository.save(image);
			storeRepository.save(store);

		} else if (store.getOwner() != null
				&& store.getOwner().getLogin().equals(owner)) {

			Address address = store.getAddress();
			Image image = store.getImage();
			if (address == null) {
				address = new Address();
			}
			if (image == null) {
				image = new Image();
			}
			if (!StringUtils.isEmpty(storeCSV.getName())) {
				store.setName(storeCSV.getName());
			}
			if (!StringUtils.isEmpty(storeCSV.getDescription())) {
				store.setDescription(storeCSV.getDescription());
			}
			if (!StringUtils.isEmpty(storeCSV.getStoreType())) {
				StoreType storeType = storeTypeRepository
						.findOneByType(storeCSV.getStoreType());
				store.setStoreType(storeType);
			}
			if (!StringUtils.isEmpty(storeCSV.getAddress1())) {
				address.setAddress1(storeCSV.getAddress1());

			}
			if (!StringUtils.isEmpty(storeCSV.getAddress2())) {
				address.setAddress2(storeCSV.getAddress2());

			}
			if (!StringUtils.isEmpty(storeCSV.getAddressType())) {
				address.setAddressType(storeCSV.getAddressType());

			}
			if (!StringUtils.isEmpty(storeCSV.getCity())) {
				address.setCity(storeCSV.getCity());
			}
			if (!StringUtils.isEmpty(storeCSV.getZipCode())) {
				address.setZipCode(storeCSV.getZipCode());

			}
			if (!StringUtils.isEmpty(storeCSV.getCountry())) {
				address.setCountry(storeCSV.getCountry());

			}
			if (!StringUtils.isEmpty(storeCSV.getState())) {
				address.setState(storeCSV.getState());

			}
			if (!StringUtils.isEmpty(storeCSV.getImage1())) {
				image.setImage1(storeCSV.getImage1());

			}
			if (!StringUtils.isEmpty(storeCSV.getImage2())) {
				image.setImage2(storeCSV.getImage2());

			}
			if (!StringUtils.isEmpty(storeCSV.getImage3())) {
				image.setImage3(storeCSV.getImage3());

			}
			if (!StringUtils.isEmpty(storeCSV.getImage4())) {
				image.setImage4(storeCSV.getImage4());
			}
			if (!StringUtils.isEmpty(storeCSV.getOtherImages())) {
				image.setThumbnail(storeCSV.getThumbnail());
			}
			if (!StringUtils.isEmpty(storeCSV.getThumbnail())) {
				store.setImage(image);
			}
			store.setAddress(address);
			store.setImage(image);

			addressRepository.save(address);
			imageRepository.save(image);
			storeRepository.save(store);
		} else {
			return false;
		}
		return true;
	}

	@Override
	public StoreDTO getStoreByIdAndOwner(Long storeId, String owner) {
		Store store = storeRepository.findOneByIdAndOwner_Login(storeId, owner);
		if(store==null){
			return null;
		}
		StoreDTO storeDTO = storeMapper.toStoreDTO(store);
		ImageDTO imageDTO = imageMapper.toImageDTO(store.getImage());
		LocationDTO locationDTO = locationMapper.toLocationDTO(store
				.getLocation());
		AddressDTO addressDTO = addressMapper.toAddressDTO(store.getAddress());
		storeDTO.setAddress(addressDTO);
		storeDTO.setImage(imageDTO);
		storeDTO.setLocation(locationDTO);
		Catalog activeCatalog = store.getActiveCatalog();
		if (activeCatalog != null) {
			storeDTO.setActiveCatalogId(activeCatalog.getId());
		}
		return storeDTO;
	}

	@Override
	public List<StoreDTO> getAllAdminStores() {
		String owner=SecurityUtils.getCurrentLogin();
		List<Store> domainStores = storeRepository.findByOwner_Login(owner);
		List<StoreDTO> storeDTOs = new ArrayList<StoreDTO>();
		domainStores.forEach(domainStore -> {
			StoreDTO storeDTO = storeMapper.toStoreDTO(domainStore);
			ImageDTO imageDTO = imageMapper.toImageDTO(domainStore.getImage());
			LocationDTO locationDTO = locationMapper.toLocationDTO(domainStore
					.getLocation());
			AddressDTO addressDTO = addressMapper.toAddressDTO(domainStore
					.getAddress());
			storeDTO.setAddress(addressDTO);
			storeDTO.setImage(imageDTO);
			storeDTO.setLocation(locationDTO);
			storeDTOs.add(storeDTO);
		});
		return storeDTOs;
	}

}
