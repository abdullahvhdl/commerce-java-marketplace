package com.respodo.commerce.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class QuantityPrice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4394094546593966905L;
	private Long quantity;
	private BigDecimal price;
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	

}
