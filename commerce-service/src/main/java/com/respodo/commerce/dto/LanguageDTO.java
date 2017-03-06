package com.respodo.commerce.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A Language.
 */
public class LanguageDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1140226085556695457L;

	private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String locale;
    
    private String flag;

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

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
    
}
