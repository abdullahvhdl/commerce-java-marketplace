package com.respodo.commerce.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.ProductDescDTO;
import com.respodo.commerce.repository.domain.ProductDesc;

@Component
public class ProductDescriptionMapper {
	@Autowired
	private LanguageMapper languageMapper;

	public ProductDescDTO toProductDescriptionDTO(ProductDesc productDesc) {
		if (productDesc == null) {
			return null;
		}
		ProductDescDTO productDescDTO = new ProductDescDTO();
		productDescDTO.setId(productDesc.getId());
		productDescDTO.setActive(productDesc.getActive());
		productDescDTO.setDescription(productDesc.getDescription());
		productDescDTO.setName(productDesc.getName());
		productDescDTO.setLanguage(languageMapper.toLanguageDTO(productDesc.getLanguage()));
		return productDescDTO;
	}

	public ProductDesc toProductDescDomain(ProductDescDTO description) {
		if (description == null) {
			return null;
		}
		ProductDesc productDescDomain = new ProductDesc();
		productDescDomain.setActive(description.getActive());
		productDescDomain.setDescription(description.getDescription());
		productDescDomain.setName(description.getName());
		return productDescDomain;
	}

}
