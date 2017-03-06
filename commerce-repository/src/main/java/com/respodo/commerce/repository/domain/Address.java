package com.respodo.commerce.repository.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A Address.
 */
@Entity
@Table(name = "ADDRESS")
public class Address extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name="address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "address1", nullable = false)
    private String address1;

    @Column(name = "address2")
    private String address2;

    @NotNull
    @Column(name = "city", nullable = false)
    private String city;
    
    @NotNull
    @Column(name = "zipcode", nullable = false)
    private String zipCode;

    @NotNull
    @Column(name = "state", nullable = false)
    private String state;

    @NotNull
    @Column(name = "country", nullable = false)
    private String country;

    @NotNull
    @Column(name = "address_type", nullable = false)
    private String addressType;

    @OneToOne
    @JoinColumn(name="addr_profile_id")
    private AddrProfile addrProfile;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public AddrProfile getAddrProfile() {
        return addrProfile;
    }

    public void setAddrProfile(AddrProfile addrProfile) {
        this.addrProfile = addrProfile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address address = (Address) o;

        if ( ! Objects.equals(id, address.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address1='" + address1 + "'" +
                ", address2='" + address2 + "'" +
                ", city='" + city + "'" +
                ", state='" + state + "'" +
                ", country='" + country + "'" +
                ", addressType='" + addressType + "'" +
                '}';
    }
}
