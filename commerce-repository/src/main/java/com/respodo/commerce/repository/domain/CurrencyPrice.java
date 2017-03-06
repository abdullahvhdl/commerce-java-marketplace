package com.respodo.commerce.repository.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A CurrencyPrice.
 */
@Entity
@Table(name = "CURRENCY_PRICE")
public class CurrencyPrice extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name="currency_price_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "price", precision=10, scale=2, nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "currency", nullable = false)
    private String currency;

    @ManyToOne
    @JoinColumn(name="price_offer_id")
    private PriceOffer priceOffer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PriceOffer getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(PriceOffer priceOffer) {
        this.priceOffer = priceOffer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrencyPrice currencyPrice = (CurrencyPrice) o;

        if ( ! Objects.equals(id, currencyPrice.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CurrencyPrice{" +
                "id=" + id +
                ", price='" + price + "'" +
                ", currency='" + currency + "'" +
                '}';
    }
}
