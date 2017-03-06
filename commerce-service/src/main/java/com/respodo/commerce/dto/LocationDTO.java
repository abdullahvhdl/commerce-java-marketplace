package com.respodo.commerce.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A Location.
 */
public class LocationDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5400679372546032835L;

    @NotBlank
    private BigDecimal longitude;

    @NotBlank
    private BigDecimal latitude;
    
   
	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "LocationDTO [longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}

	
}
