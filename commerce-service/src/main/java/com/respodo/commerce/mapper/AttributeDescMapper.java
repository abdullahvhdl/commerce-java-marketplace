package com.respodo.commerce.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.AttributeDescDTO;
import com.respodo.commerce.repository.domain.AttributeDesc;

@Component
public class AttributeDescMapper {

	@Autowired
	private LanguageMapper languageMapper;
	
	public AttributeDescDTO toAttributeDescDTO(
			AttributeDesc attribueDesc) {
		if(attribueDesc ==null){
			return null;
		}
		AttributeDescDTO attributeDescDTO=new AttributeDescDTO();
		attributeDescDTO.setId(attribueDesc.getId());
		attributeDescDTO.setName(attribueDesc.getName());
		attributeDescDTO.setLanguage(languageMapper.toLanguageDTO(attribueDesc.getLanguage()));
		return attributeDescDTO;
	}

	public AttributeDesc toAttributeDescDomain(AttributeDescDTO description) {
		if(description ==null){
			return null;
		}
		AttributeDesc descriptionDomain=new AttributeDesc();
		descriptionDomain.setName(description.getName());
		return descriptionDomain;
	}

}
