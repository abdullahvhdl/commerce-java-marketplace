package com.respodo.commerce.dto;

import java.io.Serializable;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A AttributeValueDesc.
 */
public class AttributeValueDescDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1812005295046333152L;

	private Long id;

    @NotBlank
    private String name;

    @Valid
    private LanguageDTO language;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LanguageDTO getLanguage() {
		return language;
	}

	public void setLanguage(LanguageDTO language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "AttributeValueDescDTO [id=" + id + ", name=" + name
				+ ", language=" + language + "]";
	}
    
    

}
