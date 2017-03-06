package com.respodo.commerce.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -793163344609537141L;

	@NotNull
    @Size(min = 5, max = 100)
    private String password;

    @NotNull
    @Size(max = 50)
    private String firstName;
    
    @Size(max = 50)
    private String middleName;

    @NotNull
    @Size(max = 50)
    private String lastName;

    @NotNull
    @Email
    @Size(min = 5, max = 100)
    private String email;

    @NotNull
    private String address1;
    
    private String address2;
    
    @NotNull
    private String city;
    
    @NotNull
    private String zipCode;
    
    @NotNull
    private String country;
    
    @NotNull
    private String state;
    
    @NotNull
    private String phoneNumber;
    

    public String getPhoneNumber() {
		return phoneNumber;
	}

	private List<String> roles;


    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMiddleName() {
		return middleName;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCountry() {
		return country;
	}

    public List<String> getRoles() {
        return roles;
    }

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserDTO [password=" + password
				+ ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", email=" + email + ", address1=" + address1 + ", address2=" + address2
				+ ", city=" + city + ", zipCode=" + zipCode + ", country="
				+ country + ", roles=" + roles + "]";
	}

    
}
