package com.respodo.commerce.service;

import java.util.List;

import com.respodo.commerce.dto.CatalogDTO;

public interface CatalogService {
	CatalogDTO getMasterCatalog();

	List<CatalogDTO> getCatalogsByStoreId(Long storeId);

	CatalogDTO getCatalogById(Long catalogId);

	CatalogDTO saveCatalog(Long storeId, CatalogDTO catalog);

	CatalogDTO updateCatalog(CatalogDTO catalog, Long catalogId);

	Boolean deleteCatalog(Long catalogId);

	List<CatalogDTO> getCatalogsByStoreId(Long storeId, String owner);

}
