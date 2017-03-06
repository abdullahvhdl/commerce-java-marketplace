package com.respodo.commerce.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class CurrencyDTO implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String code;

	@NotBlank
	private String symbol;

	@NotBlank
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CurrencyDTO [code=" + code + ", symbol=" + symbol + ", name="
				+ name + "]";
	}

}
