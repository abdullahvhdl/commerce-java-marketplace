package com.respodo.commerce.service;

import java.util.List;

import com.respodo.commerce.dto.AttributeDTO;
import com.respodo.commerce.dto.AttributeDescDTO;
import com.respodo.commerce.dto.AttributeValueDTO;
import com.respodo.commerce.dto.AttributeValueDescDTO;
import com.respodo.commerce.dto.ProdAssignedAttrDTO;

public interface AttributeService {

	List<ProdAssignedAttrDTO> getProductBuyableAttributes(Long productId);

	List<ProdAssignedAttrDTO> getProductDisplayableAttributes(Long productId);

	List<AttributeDescDTO> getAttributeDescription(Long attributeId);

	List<AttributeValueDescDTO> getAttributeValueDescription(
			Long attributeValueId);

	List<AttributeDTO> getAttributes(String attributeType);

	AttributeDTO getAttributeById(Long attributeId);

	AttributeDTO saveAttribute(AttributeDTO attribute, String attributeType);

	AttributeDTO updateAttribute(AttributeDTO attribute, Long attributeId);

	Boolean deleteAttribute(Long attributeId);

	List<AttributeValueDTO> getAttributeValues(Long attributeId);

	AttributeValueDTO getAttributeValue(Long attributeId, Long attributeValueId);

	AttributeValueDTO saveAttributeValue(Long attributeId,
			AttributeValueDTO attributeValue);

	AttributeValueDTO updateAttributeValue(Long attributeId,
			AttributeValueDTO attributeValue, Long attributeValueId);

	Boolean deleteAttributeValue(Long attributeId, Long attributeValueId);

	ProdAssignedAttrDTO updateProductAssignedAttr(Long productId, ProdAssignedAttrDTO assignedAttr);

	void saveProductAssignedAttr(Long id, ProdAssignedAttrDTO assignedAttr);
}
