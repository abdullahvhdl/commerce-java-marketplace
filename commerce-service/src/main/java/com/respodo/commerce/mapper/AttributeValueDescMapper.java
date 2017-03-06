package com.respodo.commerce.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.AttributeValueDescDTO;
import com.respodo.commerce.repository.domain.AttributeDesc;
import com.respodo.commerce.repository.domain.AttributeValueDesc;

@Component
public class AttributeValueDescMapper {
	
	@Autowired
	private LanguageMapper languageMapper;
	
	public AttributeValueDescDTO toAttributeValueDescDTO(AttributeValueDesc description){
		if(description ==null){
			return null;
		}
		
		AttributeValueDescDTO descriptionDTO=new AttributeValueDescDTO();
		descriptionDTO.setId(description.getId());
		descriptionDTO.setName(description.getName());
		descriptionDTO.setLanguage(languageMapper.toLanguageDTO(description.getLanguage()));
		return descriptionDTO;
		
	}

	public AttributeValueDesc toAttributeValueDescDomain(
			AttributeValueDescDTO description) {
		AttributeValueDesc descriptionDomain=new AttributeValueDesc();
		descriptionDomain.setName(description.getName());
		return descriptionDomain;
	}

}
