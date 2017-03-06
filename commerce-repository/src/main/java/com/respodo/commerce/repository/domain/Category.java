package com.respodo.commerce.repository.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A Category.
 */
@Entity
@Table(name = "CATEGORY")
public class Category extends AbstractAuditingEntity implements Serializable {

	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "unique_id", nullable = false)
	private String uniqueId;

	@NotNull
	@Column(name = "cat_level", nullable = false)
	private Integer catLevel;
	
	@Column(name="views")
	private Integer views;

	@Column(name = "custom1")
	private String custom1;

	@Column(name = "custom2")
	private String custom2;

	@ManyToOne
	@JoinColumn(name="catalog_id")
	private Catalog catalog;

	@OneToMany(mappedBy = "parentCategory")
	private Set<Category> childCategories;

	@ManyToOne
	@JoinTable(name = "CATEGORY_REL", joinColumns = @JoinColumn(name = "category_child_id", referencedColumnName = "category_id"), inverseJoinColumns = @JoinColumn(name = "category_parent_id", referencedColumnName = "category_id"))
	private Category parentCategory;

	@OneToMany(mappedBy = "category")
	private Set<CategoryDesc> categoryDescs = new HashSet<>();

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
	
	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public String getCustom1() {
		return custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	public String getCustom2() {
		return custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public Set<CategoryDesc> getCategoryDescs() {
		return categoryDescs;
	}

	public void setCategoryDescs(Set<CategoryDesc> categoryDescs) {
		this.categoryDescs = categoryDescs;
	}

	public Set<Category> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Set<Category> childCategories) {
		this.childCategories = childCategories;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Category category = (Category) o;

		if (!Objects.equals(id, category.id))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "Category{" + "id=" + id + ", uniqueId='" + uniqueId + "'"
				+ ", catLevel='" + catLevel + "'" + '}';
	}
}
