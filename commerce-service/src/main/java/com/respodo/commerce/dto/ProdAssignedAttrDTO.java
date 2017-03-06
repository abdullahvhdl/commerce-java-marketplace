package com.respodo.commerce.dto;

import java.io.Serializable;

import javax.validation.Valid;

/**
 * A ProdAssignedAttr.
 */
public class ProdAssignedAttrDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5114551507599291158L;

	private Long id;

	@Valid
	private AttributeDTO attribute;

	@Valid
	private AttributeValueDTO attributeValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AttributeDTO getAttribute() {
		return attribute;
	}

	public void setAttribute(AttributeDTO attribute) {
		this.attribute = attribute;
	}

	public AttributeValueDTO getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(AttributeValueDTO attributeValue) {
		this.attributeValue = attributeValue;
	}

	@Override
	public String toString() {
		return "ProdAssignedAttrDTO [id=" + id + ", attribute=" + attribute
				+ ", attributeValue=" + attributeValue + "]";
	}

}
