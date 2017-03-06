package com.respodo.commerce.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A CategoryDesc.
 */
public class CategoryDescDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2092841684362020651L;

	private Long id;

	@NotBlank
	private String name;

	private String description;

	private Boolean active;
	
	@Valid
	@NotNull
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
		return "CategoryDescDTO [id=" + id + ", name=" + name
				+ ", description=" + description + ", active=" + active
				+ ", language=" + language + "]";
	}
	
	
	
}
