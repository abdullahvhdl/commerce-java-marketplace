package com.respodo.commerce.dto;

import java.io.Serializable;

/**
 * A StoreType.
 */
public class StoreTypeDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String type;

    private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "StoreTypeDTO [id=" + id + ", type=" + type + ", description="
				+ description + "]";
	}
    
    

}
