package com.respodo.commerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.respodo.commerce.common.CatalogTypeConstants;
import com.respodo.commerce.dto.CatalogDTO;
import com.respodo.commerce.mapper.CatalogMapper;
import com.respodo.commerce.repository.dao.CatalogRepository;
import com.respodo.commerce.repository.dao.StoreRepository;
import com.respodo.commerce.repository.domain.Catalog;
import com.respodo.commerce.repository.domain.Store;
import com.respodo.commerce.service.CatalogService;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private CatalogRepository catalogRepository;
	
	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private CatalogMapper catalogMapper;

	@Override
	public List<CatalogDTO> getCatalogsByStoreId(Long storeId) {
		List<Catalog> catalogs = catalogRepository.findByStores_Id(storeId);
		List<CatalogDTO> catalogDTOs = new ArrayList<CatalogDTO>();
		catalogs.forEach(catalog -> {
			CatalogDTO catalogDTO = catalogMapper.toCatalogDTO(catalog);
			catalogDTOs.add(catalogDTO);
		});
		return catalogDTOs;
	}

	@Override
	public CatalogDTO getMasterCatalog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatalogDTO getCatalogById(Long catalogId) {
		Catalog catalog=catalogRepository.getOne(catalogId);
		CatalogDTO catalogDTO=catalogMapper.toCatalogDTO(catalog);
		return catalogDTO;
	}

	@Override
	public CatalogDTO saveCatalog(Long storeId,CatalogDTO catalog) {
		Catalog catalogDomain=catalogMapper.toCatalogDomain(catalog);
		Store store=storeRepository.findOne(storeId);
		catalogDomain.setType(CatalogTypeConstants.SALE_CATALOG);
		catalogRepository.save(catalogDomain);
		store.getCatalogs().add(catalogDomain);
		storeRepository.save(store);
		CatalogDTO result=catalogMapper.toCatalogDTO(catalogDomain);
		return result;
	}

	@Override
	public CatalogDTO updateCatalog(CatalogDTO catalog, Long catalogId) {
		Catalog catalogDomain=catalogRepository.getOne(catalogId);
		if(catalogDomain ==null){
			return null;
		}
		catalogDomain.setUniqueId(catalog.getUniqueId());
		catalogDomain.setName(catalog.getName());
		catalogRepository.save(catalogDomain);
		return catalog;
	}

	@Override
	public Boolean deleteCatalog(Long catalogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatalogDTO> getCatalogsByStoreId(Long storeId, String owner) {
		List<CatalogDTO> catalogDTOs = new ArrayList<CatalogDTO>();
		Store store=storeRepository.findOneByIdAndOwner_Login(storeId, owner);
		if(store !=null){
			List<Catalog> catalogs = catalogRepository.findByStores_Id(storeId);
			catalogs.forEach(catalog -> {
				CatalogDTO catalogDTO = catalogMapper.toCatalogDTO(catalog);
				catalogDTOs.add(catalogDTO);
			});
		}
		return catalogDTOs;
	}

}
