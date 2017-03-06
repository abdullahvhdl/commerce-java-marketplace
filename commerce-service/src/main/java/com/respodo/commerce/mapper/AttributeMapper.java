package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.AttributeDTO;
import com.respodo.commerce.dto.AttributeTypeDTO;
import com.respodo.commerce.repository.domain.Attribute;

@Component
public class AttributeMapper {

	public AttributeDTO toAttributeDTO(Attribute attribute) {
		if(attribute==null){
			return null;
		}
		AttributeDTO attributeDTO =new AttributeDTO();
		attributeDTO.setUniqueId(attribute.getUniqueId());
		attributeDTO.setId(attribute.getId());
		return attributeDTO;
	}

	public Attribute toAttributeDomain(AttributeDTO attribute) {
		if (attribute == null) {
			return null;
		}
		Attribute attributeDomain = new Attribute();
		attributeDomain.setUniqueId(attribute.getUniqueId());
		return attributeDomain;
	}

}
