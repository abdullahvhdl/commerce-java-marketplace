package com.respodo.commerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.respodo.commerce.dto.StoreDTO;
import com.respodo.commerce.dto.csv.StoreCSVDTO;
import com.respodo.commerce.service.util.LocationUtil;

public interface StoreService {
	StoreDTO getStoreById(Long storeId);

	List<StoreDTO> getStoresByLocation(LocationUtil location);

	Page<StoreDTO> getAllStores(Pageable pageRequest);

	List<StoreDTO> getStoresByOwner(String ownerEmail);

	StoreDTO saveStore(StoreDTO store);

	StoreDTO updateStore(StoreDTO store, Long storeId);

	Boolean deleteStore(Long storeId);

	Boolean saveStore(StoreCSVDTO storeCSV);

	StoreDTO getStoreByIdAndOwner(Long storeId, String owner);

	List<StoreDTO> getAllAdminStores();

}
