package com.respodo.commerce.repository.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A Catalog.
 */
@Entity
@Table(name = "QUICK_ORDER")
public class QuickOrder extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name="quick_order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    
    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;
    
    @Column(name= "currency")
    private String currency;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name="status", nullable=false)
    private String status;
    
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
    

    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
    

}
