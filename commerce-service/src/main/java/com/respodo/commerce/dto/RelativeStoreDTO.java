package com.respodo.commerce.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.respodo.commerce.repository.domain.ProductStoreRel;

/**
 * A Store.
 */
public class RelativeStoreDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5492931585107049953L;

	private Long id;

	private String uniqueId;

	@NotNull
	private String name;

	private String description;

	private ImageDTO image;

	private LocationDTO location;

	private StoreTypeDTO storeType;

	private AddressDTO address;

	private Double distance;

	private ProductDTO product;

	private List<CurrencyPriceDTO> offerPrice;

	private List<CurrencyPriceDTO> originalPrice;

	public RelativeStoreDTO() {

	}

	public RelativeStoreDTO(ProductStoreRel store) {
		if (store != null) {
			setId(store.getId());
			setUniqueId(store.getUniqueId());
			setName(store.getName());
			setDescription(store.getDescription());
		}
	}

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

	/**
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public List<CurrencyPriceDTO> getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(List<CurrencyPriceDTO> offerPrice) {
		this.offerPrice = offerPrice;
	}

	public List<CurrencyPriceDTO> getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(List<CurrencyPriceDTO> originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

}
