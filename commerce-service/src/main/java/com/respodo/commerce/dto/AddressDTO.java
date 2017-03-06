package com.respodo.commerce.dto;

import java.io.Serializable;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A Address.
 */
public class AddressDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6008327381314074071L;

	private Long id;

	@NotBlank
	private String address1;

	private String address2;

	@NotBlank
	private String city;

	@NotBlank
	private String state;

	@NotBlank
	private String country;

	private String addressType;

	@NotBlank
	private String zipCode;
	
	@Valid
	private AddrProfileDTO addressProfile;

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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public AddrProfileDTO getAddressProfile() {
		return addressProfile;
	}

	public void setAddressProfile(AddrProfileDTO addressProfile) {
		this.addressProfile = addressProfile;
	}

	@Override
	public String toString() {
		return "AddressDTO [id=" + id + ", address1=" + address1
				+ ", address2=" + address2 + ", city=" + city + ", state="
				+ state + ", country=" + country + ", addressType="
				+ addressType + ", zipCode=" + zipCode + ", addressProfile="
				+ addressProfile + "]";
	}



}
