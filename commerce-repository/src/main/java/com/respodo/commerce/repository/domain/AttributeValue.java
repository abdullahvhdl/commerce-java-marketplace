package com.respodo.commerce.repository.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A AttributeValue.
 */
@Entity
@Table(name = "ATTRIBUTE_VALUE")
public class AttributeValue extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name="attribute_value_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "unique_id", nullable = false)
    private String uniqueId;

    @ManyToOne
    @JoinColumn(name="attribute_id")
    @JsonIgnore
    private Attribute attribute;

    @OneToMany(fetch=FetchType.EAGER,mappedBy = "attributeValue")
    private Set<AttributeValueDesc> attributeValueDescs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Set<AttributeValueDesc> getAttributeValueDescs() {
        return attributeValueDescs;
    }

    public void setAttributeValueDescs(Set<AttributeValueDesc> attributeValueDescs) {
        this.attributeValueDescs = attributeValueDescs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AttributeValue attributeValue = (AttributeValue) o;

        if ( ! Objects.equals(id, attributeValue.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AttributeValue{" +
                "id=" + id +
                ", uniqueId='" + uniqueId + "'" +
                '}';
    }
}
