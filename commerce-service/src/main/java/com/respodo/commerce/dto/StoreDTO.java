package com.respodo.commerce.dto;

import java.io.Serializable;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A Store.
 */
public class StoreDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5492931585107049953L;

	private Long id;

	@NotBlank
	private String name;

	private String description;
	
	@NotBlank
	private String uniqueId;
	
	@Valid
	private ImageDTO image;

	@Valid
	private LocationDTO location;
	
	@Valid
	private StoreTypeDTO storeType;

	@Valid
	private AddressDTO address;
	
	private Double distance;
	
	private Long activeCatalogId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}

	public StoreTypeDTO getStoreType() {
		return storeType;
	}

	public void setStoreType(StoreTypeDTO storeType) {
		this.storeType = storeType;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Long getActiveCatalogId() {
		return activeCatalogId;
	}

	public void setActiveCatalogId(Long activeCatalogId) {
		this.activeCatalogId = activeCatalogId;
	}

	@Override
	public String toString() {
		return "StoreDTO [id=" + id + ", name=" + name + ", description="
				+ description + ", uniqueId=" + uniqueId + ", image=" + image
				+ ", location=" + location + ", storeType=" + storeType
				+ ", address=" + address + ", distance=" + distance
				+ ", activeCatalogId=" + activeCatalogId + "]";
	}

	

}
