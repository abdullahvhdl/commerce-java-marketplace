package com.respodo.commerce.admin.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.respodo.commerce.common.AttributeTypeConstants;
import com.respodo.commerce.dto.AttributeDTO;
import com.respodo.commerce.dto.AttributeValueDTO;
import com.respodo.commerce.manager.AttributeManager;

@RestController
@RequestMapping("/api")
public class AttributesResource {
	
	@Autowired
	private AttributeManager attributeManager;

	@RequestMapping(value = "/attributes/buyable", method = RequestMethod.GET)
	public List<AttributeDTO> getBuyableAttributes() {
		List<AttributeDTO> attributeValues=attributeManager.getAttributes(AttributeTypeConstants.BUYABLE);
		return attributeValues;
	}
	
	@RequestMapping(value = "/attributes/displayable", method = RequestMethod.GET)
	public List<AttributeDTO> getDisplayableAttributes() {
		List<AttributeDTO> attributeValues=attributeManager.getAttributes(AttributeTypeConstants.DISPLAYABLE);
		return attributeValues;
	}

	@RequestMapping(value = "/attributes/{attributeId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAttribute(@PathVariable Long attributeId) {
		AttributeDTO attribute=attributeManager.getAttributeById(attributeId);
		if(attribute==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AttributeDTO>(attribute,HttpStatus.OK);
	}

	@RequestMapping(value = "/attributes/buyable", method = RequestMethod.POST)
	public ResponseEntity<?> saveBuyableAttribute(@RequestBody AttributeDTO attribute) {
		AttributeDTO savedAttribute=attributeManager.saveAttribute(attribute,AttributeTypeConstants.BUYABLE);
		if(savedAttribute==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AttributeDTO>(savedAttribute,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/attributes/displayable", method = RequestMethod.POST)
	public ResponseEntity<?> saveDisplayableAttribute(@RequestBody AttributeDTO attribute) {
		AttributeDTO savedAttribute=attributeManager.saveAttribute(attribute,AttributeTypeConstants.DISPLAYABLE);
		if(savedAttribute==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AttributeDTO>(savedAttribute,HttpStatus.OK);
	}

	@RequestMapping(value = "/attributes/{attributeId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBuyableAttribute(@PathVariable Long attributeId,
			@RequestBody AttributeDTO attribute) {
		
		AttributeDTO savedAttribute=attributeManager.updateAttribute(attribute,attributeId);
		if(savedAttribute==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AttributeDTO>(savedAttribute,HttpStatus.OK);
	}

	@RequestMapping(value = "/attributes/{attributeId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAttribute(@PathVariable Long attributeId) {
		Boolean status=attributeManager.deleteAttribute(attributeId);
		if(!status){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/attributes/{attributeId}/values", method = RequestMethod.GET)
	public List<AttributeValueDTO> getAttributeValues(
			@PathVariable Long attributeId) {
		List<AttributeValueDTO> attributeValues=attributeManager.getAttributeValues(attributeId);
		return attributeValues;
	}

	@RequestMapping(value = "/attributes/{attributeId}/values/{attributeValueId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAttributeValue(@PathVariable Long attributeId,
			@PathVariable Long attributeValueId) {
		AttributeValueDTO attributeValue=attributeManager.getAttributeValue(attributeId, attributeValueId);
		if(attributeValue==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AttributeValueDTO>(attributeValue,HttpStatus.OK);
	}

	@RequestMapping(value = "/attributes/{attributeId}/values", method = RequestMethod.POST)
	public ResponseEntity<?> saveAttributeValue(@PathVariable Long attributeId,
			@RequestBody AttributeValueDTO attributeValue) {
		AttributeValueDTO savedAttributeValue=attributeManager.saveAttributeValue(attributeId, attributeValue);
		if(savedAttributeValue==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<AttributeValueDTO>(savedAttributeValue,HttpStatus.OK);
	}

	@RequestMapping(value = "/attributes/{attributeId}/values/{attributeValueId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAttributeValue(
			@PathVariable Long attributeId,
			@PathVariable Long attributeValueId,
			@RequestBody AttributeValueDTO attributeValue) {

		AttributeValueDTO savedAttributeValue=attributeManager.updateAttributeValue(attributeId, attributeValue,attributeValueId);
		if(savedAttributeValue==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<AttributeValueDTO>(savedAttributeValue,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/attributes/{attributeId}/values/{attributeValueId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAttributeValue(@PathVariable Long attributeId,
			@PathVariable Long attributeValueId) {
		
		Boolean status=attributeManager.deleteAttributeValue(attributeId,attributeValueId);
		if(!status){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
