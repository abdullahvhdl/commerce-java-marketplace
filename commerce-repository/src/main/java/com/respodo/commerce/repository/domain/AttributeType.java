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
 * A AttributeType.
 */
@Entity
@Table(name = "ATTRIBUTE_TYPE")
public class AttributeType implements Serializable {

    @Id
    @Column(name="attribute_type_id")
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

    public void setType(String attributeType) {
        this.type = attributeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AttributeType attributeType = (AttributeType) o;

        if ( ! Objects.equals(id, attributeType.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AttributeType{" +
                "id=" + id +
                ", attributeType='" + type + "'" +
                '}';
    }
}
