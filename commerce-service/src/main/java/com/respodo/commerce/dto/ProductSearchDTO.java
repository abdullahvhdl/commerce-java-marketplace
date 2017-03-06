package com.respodo.commerce.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A Product.
 */
public class ProductSearchDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7695263575607420895L;

	private Long id;

	@NotBlank
	private String uniqueId;

	@NotBlank
	private String prodType;

	@Valid
	private ImageDTO image;

	@Valid
	private List<ProductDescDTO> description;

	List<RelativeStoreDTO> stores;

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

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
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

	public List<RelativeStoreDTO> getStores() {
		return stores;
	}

	public void setStores(List<RelativeStoreDTO> stores) {
		this.stores = stores;
	}

	@Override
	public String toString() {
		return "ProductSearchDTO [id=" + id + ", uniqueId=" + uniqueId
				+ ", prodType=" + prodType + ", image=" + image
				+ ", description=" + description + ", stores=" + stores + "]";
	}

	

}
