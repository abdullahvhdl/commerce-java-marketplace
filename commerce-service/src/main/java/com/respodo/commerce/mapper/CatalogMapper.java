package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.CatalogDTO;
import com.respodo.commerce.repository.domain.Catalog;

@Component
public class CatalogMapper {

	public CatalogDTO toCatalogDTO(Catalog catalog){
		if(catalog==null){
			return  null;
		}
		CatalogDTO catalogDTO=new CatalogDTO();
		catalogDTO.setId(catalog.getId());
		catalogDTO.setUniqueId(catalog.getUniqueId());
		catalogDTO.setName(catalog.getName());
		catalogDTO.setType(catalog.getType());
		return catalogDTO;
	}

	public Catalog toCatalogDomain(CatalogDTO catalog) {
		if(catalog==null){
			return null;
		}
		
		Catalog catalogDomain=new Catalog();
		catalogDomain.setUniqueId(catalog.getUniqueId());
		catalogDomain.setName(catalog.getName());
		catalogDomain.setType(catalog.getType());
		
		return catalogDomain;
	}
}
