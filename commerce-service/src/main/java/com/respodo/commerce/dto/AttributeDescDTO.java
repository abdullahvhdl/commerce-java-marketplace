package com.respodo.commerce.dto;

import java.io.Serializable;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A AttributeDesc.
 */
public class AttributeDescDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6573397957535887672L;

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
		return "AttributeDescDTO [id=" + id + ", name=" + name + ", language="
				+ language + "]";
	}

    
   
}
