package com.respodo.commerce.manager;

import java.util.List;

import com.respodo.commerce.dto.AttributeDTO;
import com.respodo.commerce.dto.AttributeValueDTO;

public interface AttributeManager {

	List<AttributeDTO> getAttributes(String attributeType);

	AttributeDTO getAttributeById(Long attributeId);

	AttributeDTO saveAttribute(AttributeDTO attribute,String attributeType);
	
	AttributeDTO updateAttribute(AttributeDTO attribute,Long attributeId);
	
	Boolean deleteAttribute(Long attributeId);
	
	List<AttributeValueDTO> getAttributeValues(Long attributeId);
	
	AttributeValueDTO getAttributeValue(Long attributeId,Long attributeValueId);
	
	AttributeValueDTO saveAttributeValue(Long attributeId,AttributeValueDTO attributeValue);
	
	AttributeValueDTO updateAttributeValue(Long attributeId,AttributeValueDTO attributeValue,Long attributeValueId);
	
	Boolean deleteAttributeValue(Long attributeId,Long attributeValueId);
	

}
