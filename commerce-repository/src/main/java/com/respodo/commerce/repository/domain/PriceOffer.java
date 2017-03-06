package com.respodo.commerce.repository.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A PriceOffer.
 */
@Entity
@Table(name = "PRICE_OFFER")
public class PriceOffer extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name="price_offer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="price_offer_type_id")
    private PriceOfferType priceOfferType;

    @OneToMany(mappedBy = "priceOffer")
    private Set<CurrencyPrice> currencyPrices = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product;
    
    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PriceOfferType getPriceOfferType() {
        return priceOfferType;
    }

    public void setPriceOfferType(PriceOfferType priceOfferType) {
        this.priceOfferType = priceOfferType;
    }

    public Set<CurrencyPrice> getCurrencyPrices() {
        return currencyPrices;
    }

    public void setCurrencyPrices(Set<CurrencyPrice> currencyPrices) {
        this.currencyPrices = currencyPrices;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PriceOffer priceOffer = (PriceOffer) o;

        if ( ! Objects.equals(id, priceOffer.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PriceOffer{" +
                "id=" + id +
                '}';
    }
}
