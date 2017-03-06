package com.respodo.commerce.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A AttributeType.
 */
public class AttributeTypeDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5583835653096487960L;

	private Long id;

    @NotBlank
    private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AttributeTypeDTO [id=" + id + ", type=" + type + "]";
	}

    
}
