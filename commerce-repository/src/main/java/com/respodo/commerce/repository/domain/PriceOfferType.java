package com.respodo.commerce.repository.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A PriceOfferType.
 */
@Entity
@Table(name = "PRICE_OFFER_TYPE")
public class PriceOfferType implements Serializable {

    @Id
    @Column(name="price_offer_type_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String priceOfferType) {
        this.type = priceOfferType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PriceOfferType priceOfferType = (PriceOfferType) o;

        if ( ! Objects.equals(id, priceOfferType.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PriceOfferType{" +
                "id=" + id +
                ", priceOfferType='" + type + "'" +
                '}';
    }
}
