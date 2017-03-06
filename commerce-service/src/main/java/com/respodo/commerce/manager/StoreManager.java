package com.respodo.commerce.manager;

import java.util.List;

import org.springframework.data.domain.Page;

import com.respodo.commerce.dto.CatalogDTO;
import com.respodo.commerce.dto.StoreDTO;
import com.respodo.commerce.service.util.LocationUtil;

public interface StoreManager {
	
	Page<StoreDTO> getAllStores(Integer page, Integer limit);
	
	List<StoreDTO> getStoresByLocation(LocationUtil location);
	
	StoreDTO getStoreById(Long storeId);
	
	StoreDTO saveStore(StoreDTO store);
	
	StoreDTO updateStore(StoreDTO store,Long storeId);
	
	Boolean deleteStore(Long storeId);

	List<CatalogDTO> getStoreCatalogs(Long storeId);

	List<StoreDTO> getAllAdminStores();

	List<CatalogDTO> getStoreCatalogsWithCategories(Long storeId);

	
}
