package com.respodo.commerce.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.respodo.commerce.dto.CatalogDTO;
import com.respodo.commerce.dto.CategoryDTO;
import com.respodo.commerce.dto.StoreDTO;
import com.respodo.commerce.manager.StoreManager;
import com.respodo.commerce.repository.config.SecurityUtils;
import com.respodo.commerce.service.CatalogService;
import com.respodo.commerce.service.CategoryService;
import com.respodo.commerce.service.LocationService;
import com.respodo.commerce.service.StoreService;
import com.respodo.commerce.service.util.LocationUtil;


@Service
public class StoreManagerImpl implements StoreManager {
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public Page<StoreDTO> getAllStores(Integer page, Integer limit) {
		Pageable pageRequest= new PageRequest(page, limit);
		Page<StoreDTO> storeDTOs=storeService.getAllStores(pageRequest);
		
		return storeDTOs;
	}
	

	@Override
	public List<StoreDTO> getStoresByLocation(LocationUtil location) {
		List<StoreDTO> stores=storeService.getStoresByLocation(location);
		return stores;
	}

	@Override
	public StoreDTO getStoreById(Long storeId) {
		String owner=SecurityUtils.getCurrentLogin();
		StoreDTO store=storeService.getStoreByIdAndOwner(storeId,owner);
		return store;
	}

	@Override
	public  StoreDTO saveStore(StoreDTO store) {
		StoreDTO result=storeService.saveStore(store);
		locationService.updateStoresLocation();
		return result;
	}

	@Override
	public StoreDTO updateStore(StoreDTO store, Long storeId) {
		StoreDTO result=storeService.updateStore(store, storeId);
		locationService.updateStoreLocation(storeId);
		return result;
	}

	@Override
	public Boolean deleteStore(Long storeId) {
		Boolean status=storeService.deleteStore(storeId);
		
		return status;
	}


	@Override
	public List<CatalogDTO> getStoreCatalogs(Long storeId) {
		String owner=SecurityUtils.getCurrentLogin();
		List<CatalogDTO> domainCatalogs=catalogService.getCatalogsByStoreId(storeId,owner);
		return domainCatalogs;
	}


	@Override
	public List<StoreDTO> getAllAdminStores() {
		List<StoreDTO> storeDTOs=storeService.getAllAdminStores();
		
		return storeDTOs;
	}


	@Override
	public List<CatalogDTO> getStoreCatalogsWithCategories(Long storeId) {
		String owner=SecurityUtils.getCurrentLogin();
		List<CatalogDTO> domainCatalogs=catalogService.getCatalogsByStoreId(storeId,owner);
		domainCatalogs.forEach(catalog->{
			List<CategoryDTO> categories=categoryService.getCatalogCategories(catalog.getId());
			catalog.setCategories(categories);
		});
		return domainCatalogs;
	}


}
