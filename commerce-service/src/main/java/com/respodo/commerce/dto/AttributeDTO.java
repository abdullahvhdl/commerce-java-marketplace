package com.respodo.commerce.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A Attribute.
 */
public class AttributeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5789840990674020582L;

	private Long id;

	@NotBlank
	private String uniqueId;

	@Valid
	private List<AttributeDescDTO> description;
	
	@Valid
	private AttributeTypeDTO attributeType;
	
	@Valid
	private List<AttributeValueDTO> attributeValues;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public List<AttributeDescDTO> getDescription() {
		return description;
	}

	public void setDescription(List<AttributeDescDTO> description) {
		this.description = description;
	}

	public AttributeTypeDTO getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeTypeDTO attributeType) {
		this.attributeType = attributeType;
	}
	

	public List<AttributeValueDTO> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(List<AttributeValueDTO> attributeValues) {
		this.attributeValues = attributeValues;
	}

	@Override
	public String toString() {
		return "AttributeDTO [id=" + id + ", uniqueId=" + uniqueId
				+ ", description=" + description + ", attributeType="
				+ attributeType + "]";
	}
	
	

}
