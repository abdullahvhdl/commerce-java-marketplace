package com.respodo.commerce.repository.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A AddrProfile.
 */
@Entity
@Table(name = "ADDR_PROFILE")
public class AddrProfile extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name="addr_profile_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "mid_name")
    private String midName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "email1", nullable = false)
    private String email1;

    @Column(name = "email2")
    private String email2;

    @NotNull
    @Column(name = "phone1", nullable = false)
    private String phone1;

    @Column(name = "phone2")
    private String phone2;

    @OneToOne(mappedBy = "addrProfile")
    @JsonIgnore
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddrProfile addrProfile = (AddrProfile) o;

        if ( ! Objects.equals(id, addrProfile.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AddrProfile{" +
                "id=" + id +
                ", firstName='" + firstName + "'" +
                ", midName='" + midName + "'" +
                ", lastName='" + lastName + "'" +
                ", email1='" + email1 + "'" +
                ", email2='" + email2 + "'" +
                ", phone1='" + phone1 + "'" +
                ", phone2='" + phone2 + "'" +
                '}';
    }
}
