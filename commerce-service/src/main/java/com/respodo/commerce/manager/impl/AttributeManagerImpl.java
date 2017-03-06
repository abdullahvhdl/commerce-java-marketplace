package com.respodo.commerce.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.respodo.commerce.dto.AttributeDTO;
import com.respodo.commerce.dto.AttributeValueDTO;
import com.respodo.commerce.manager.AttributeManager;
import com.respodo.commerce.service.AttributeService;

@Service
public class AttributeManagerImpl implements AttributeManager {

	@Autowired
	private AttributeService attributeService;

	@Override
	public List<AttributeDTO> getAttributes(String attributeType) {
		List<AttributeDTO> attributes = attributeService.getAttributes(attributeType);

		return attributes;
	}

	@Override
	public AttributeDTO getAttributeById(Long attributeId) {
		AttributeDTO attribute=attributeService.getAttributeById(attributeId);
		if(attribute!=null){
			List<AttributeValueDTO> values=attributeService.getAttributeValues(attributeId);
			attribute.setAttributeValues(values);
		}
		return attribute;
	}

	@Override
	public AttributeDTO saveAttribute(AttributeDTO attribute,String attributeType) {
		AttributeDTO saveAttribute=attributeService.saveAttribute(attribute,attributeType);
		return saveAttribute;
	}

	@Override
	public AttributeDTO updateAttribute(AttributeDTO attribute, Long attributeId) {
		AttributeDTO saveAttribute=attributeService.updateAttribute(attribute,attributeId);
		return saveAttribute;
	}

	@Override
	public Boolean deleteAttribute(Long attributeId) {
		Boolean result=attributeService.deleteAttribute(attributeId);
		return result;
	}

	@Override
	public List<AttributeValueDTO> getAttributeValues(Long attributeId) {
		List<AttributeValueDTO> values=attributeService.getAttributeValues(attributeId);
		return values;
	}

	@Override
	public AttributeValueDTO getAttributeValue(Long attributeId,
			Long attributeValueId) {
		AttributeValueDTO value=attributeService.getAttributeValue(attributeId, attributeValueId);
		return value;
	}

	@Override
	public AttributeValueDTO saveAttributeValue(Long attributeId,
			AttributeValueDTO attributeValue) {
		AttributeValueDTO savedValue=attributeService.saveAttributeValue(attributeId, attributeValue);
		return savedValue;
	}

	@Override
	public AttributeValueDTO updateAttributeValue(Long attributeId,
			AttributeValueDTO attributeValue, Long attributeValueId) {
		AttributeValueDTO savedValue=attributeService.updateAttributeValue(attributeId, attributeValue,attributeValueId);
		return savedValue;
	}

	@Override
	public Boolean deleteAttributeValue(Long attributeId, Long attributeValueId) {
		
		Boolean result=attributeService.deleteAttributeValue(attributeId,attributeId);
		return result;
	}

}
