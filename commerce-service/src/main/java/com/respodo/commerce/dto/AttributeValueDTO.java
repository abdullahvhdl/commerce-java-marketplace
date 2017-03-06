package com.respodo.commerce.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A AttributeValue.
 */
public class AttributeValueDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7182596017604183871L;

	private Long id;

	@NotBlank
	private String uniqueId;

	@Valid
	private List<AttributeValueDescDTO> description;

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

	public List<AttributeValueDescDTO> getDescription() {
		return description;
	}

	public void setDescription(List<AttributeValueDescDTO> description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AttributeValueDTO [id=" + id + ", uniqueId=" + uniqueId
				+ ", description=" + description + "]";
	}


}
