package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.AttributeValueDTO;
import com.respodo.commerce.repository.domain.AttributeValue;

@Component
public class AttributeValueMapper {

	public AttributeValueDTO toAttributeValueDTO(AttributeValue attribute) {
		if(attribute==null){
			return null;
		}
		AttributeValueDTO attributeValueDTO=new AttributeValueDTO();
		attributeValueDTO.setId(attribute.getId());
		attributeValueDTO.setUniqueId(attribute.getUniqueId());
		return attributeValueDTO;
	}

	public AttributeValue toAttributeValueDomain(
			AttributeValueDTO attributeValue) {
		if(attributeValue==null){
			return null;
		}
		AttributeValue attributeValueDomain=new AttributeValue();
		attributeValueDomain.setUniqueId(attributeValue.getUniqueId());
		return attributeValueDomain;
	}



}
