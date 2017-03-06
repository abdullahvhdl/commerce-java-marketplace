package com.respodo.commerce.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * A CurrencyPrice.
 */
public class CurrencyPriceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3221218379357244979L;

	private Long id;

	@NotEmpty
	private Double price;

	@NotEmpty
	private String currency;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "CurrencyPriceDTO [id=" + id + ", price=" + price
				+ ", currency=" + currency + "]";
	}

	
	
}
