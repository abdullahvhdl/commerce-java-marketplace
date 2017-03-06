package com.respodo.commerce.dto;

import java.io.Serializable;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A ProductDesc.
 */
public class ProductDescDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1735076891459975739L;

	private Long id;

    @NotBlank
    private String name;

    private String description;

    private Boolean active;
    
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LanguageDTO getLanguage() {
		return language;
	}

	public void setLanguage(LanguageDTO language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "ProductDescDTO [id=" + id + ", name=" + name + ", description="
				+ description + ", active=" + active + ", language=" + language
				+ "]";
	}
    
}
