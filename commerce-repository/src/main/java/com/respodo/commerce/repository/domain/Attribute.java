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

/**
 * A Attribute.
 */
@Entity
@Table(name = "ATTRIBUTE")
public class Attribute extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name="attribute_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "unique_id", nullable = false)
    private String uniqueId;
    
    @ManyToOne
    @JoinColumn(name="attribute_type_id")
    private AttributeType attributeType;

    @OneToMany(fetch=FetchType.EAGER,mappedBy = "attribute")
    private Set<AttributeDesc> attributeDescs = new HashSet<>();

    @OneToMany(mappedBy = "attribute")
    private Set<AttributeValue> attributeValues = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Set<AttributeDesc> getAttributeDescs() {
        return attributeDescs;
    }

    public void setAttributeDescs(Set<AttributeDesc> attributeDescs) {
        this.attributeDescs = attributeDescs;
    }

    public Set<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(Set<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Attribute attribute = (Attribute) o;

        if ( ! Objects.equals(id, attribute.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", uniqueId='" + uniqueId + "'" +
                '}';
    }
}
