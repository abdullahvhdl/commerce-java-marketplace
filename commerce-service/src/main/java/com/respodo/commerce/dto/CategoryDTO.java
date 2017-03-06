package com.respodo.commerce.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * A Category.
 */
public class CategoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8057823392419174215L;

	private Long id;

	@NotBlank
	private String uniqueId;

	private Integer catLevel;

	@Valid
	private List<CategoryDTO> subCategories;

	@Valid
	@NotNull
	private List<CategoryDescDTO>  description;
	
	private Long parentId;

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

	public Integer getCatLevel() {
		return catLevel;
	}

	public void setCatLevel(Integer catLevel) {
		this.catLevel = catLevel;
	}

	public List<CategoryDTO> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<CategoryDTO> subCategories) {
		this.subCategories = subCategories;
	}

	public List<CategoryDescDTO> getDescription() {
		return description;
	}

	public void setDescription(List<CategoryDescDTO> description) {
		this.description = description;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", uniqueId=" + uniqueId
				+ ", catLevel=" + catLevel + ", subCategories=" + subCategories
				+ ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((catLevel == null) ? 0 : catLevel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((uniqueId == null) ? 0 : uniqueId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDTO other = (CategoryDTO) obj;
		if (catLevel == null) {
			if (other.catLevel != null)
				return false;
		} else if (!catLevel.equals(other.catLevel))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		return true;
	}
	
	

	



}
