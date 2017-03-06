package com.respodo.commerce.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * A Product.
 */
public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7695263575607420895L;

	private Long id;

	@NotEmpty
	private String uniqueId;

	@NotEmpty
	private String productType;

	@Valid
	private ImageDTO image;

	private List<ProductDescDTO> description;

	private List<CurrencyPriceDTO> originalPrice;

	private List<CurrencyPriceDTO> offerPrice;
	
	private String brand;
	
	private Integer views;
	
	private Boolean deleted;

	@Valid
	private List<ProductDTO> items;

	@Valid
	private List<ProdAssignedAttrDTO> buyableAttributes;
	
	@Valid
	private List<ProdAssignedAttrDTO> displayableAttributes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

	public List<ProductDescDTO> getDescription() {
		return description;
	}

	public void setDescription(List<ProductDescDTO> description) {
		this.description = description;
	}

	public List<CurrencyPriceDTO> getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(List<CurrencyPriceDTO> originalPrice) {
		this.originalPrice = originalPrice;
	}

	public List<CurrencyPriceDTO> getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(List<CurrencyPriceDTO> offerPrice) {
		this.offerPrice = offerPrice;
	}

	public List<ProductDTO> getItems() {
		return items;
	}

	public void setItems(List<ProductDTO> items) {
		this.items = items;
	}

	public List<ProdAssignedAttrDTO> getBuyableAttributes() {
		return buyableAttributes;
	}

	public void setBuyableAttributes(List<ProdAssignedAttrDTO> buyableAttributes) {
		this.buyableAttributes = buyableAttributes;
	}

	public List<ProdAssignedAttrDTO> getDisplayableAttributes() {
		return displayableAttributes;
	}

	public void setDisplayableAttributes(
			List<ProdAssignedAttrDTO> displayableAttributes) {
		this.displayableAttributes = displayableAttributes;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", uniqueId=" + uniqueId
				+ ", productType=" + productType + ", image=" + image
				+ ", description=" + description + ", originalPrice="
				+ originalPrice + ", offerPrice=" + offerPrice + ", brand="
				+ brand + ", views=" + views + ", deleted=" + deleted
				+ ", items=" + items + ", buyableAttributes="
				+ buyableAttributes + ", displayableAttributes="
				+ displayableAttributes + "]";
	}

	


}
