package com.respodo.commerce.repository.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A ProdAssignedAttr.
 */
@Entity
@Table(name = "PROD_ASSIGNED_ATTR")
public class ProdAssignedAttr extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name="prod_assigned_attr_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="attribute_id")
    private Attribute attribute;

    @ManyToOne
    @JoinColumn(name="attribute_value_id")
    private AttributeValue attributeValue;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public AttributeValue getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(AttributeValue attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProdAssignedAttr prodAssignedAttr = (ProdAssignedAttr) o;

        if ( ! Objects.equals(id, prodAssignedAttr.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProdAssignedAttr{" +
                "id=" + id +
                '}';
    }
}
