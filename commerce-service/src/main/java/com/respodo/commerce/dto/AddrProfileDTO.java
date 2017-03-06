package com.respodo.commerce.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A AddrProfile.
 */
public class AddrProfileDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3597722200975804408L;

	private Long id;

	@NotBlank
	private String firstName;

	private String midName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String email1;

	private String email2;

	@NotBlank
	private String phone1;

	private String phone2;

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

	@Override
	public String toString() {
		return "AddrProfileDTO [id=" + id + ", firstName=" + firstName
				+ ", midName=" + midName + ", lastName=" + lastName
				+ ", email1=" + email1 + ", email2=" + email2 + ", phone1="
				+ phone1 + ", phone2=" + phone2 + "]";
	}

	
}
