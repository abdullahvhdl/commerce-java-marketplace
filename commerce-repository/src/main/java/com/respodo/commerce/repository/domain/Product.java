package com.respodo.commerce.repository.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Product.
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "unique_id", nullable = false)
    private String uniqueId;

    @NotNull
    @Column(name = "product_type", nullable = false)
    private String prodType;
    
    @Column(name="brand")
    private String brand;
    
    @Column(name="views")
	private Integer views;

	@Column(name = "custom1")
	private String custom1;

	@Column(name = "custom2")
	private String custom2;

    @ManyToOne
    @JoinColumn(name="product_parent_id")
    @JsonIgnore
    private Product productParent;
    
    @OneToMany(mappedBy="productParent")
    private Set<Product> items= new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<PriceOffer> priceOffers = new HashSet<>();

    @OneToMany(fetch=FetchType.EAGER,mappedBy = "product")
    private Set<ProductDesc> productDescs = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProdAssignedAttr> prodAssignedAttrs = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "PRODUCT_CATEGORY",
               joinColumns = @JoinColumn(name="product_id", referencedColumnName="product_id"),
               inverseJoinColumns = @JoinColumn(name="category_id", referencedColumnName="category_id"))
    @JsonIgnore
    private Set<Category> categories = new HashSet<>();
    
    @OneToMany(mappedBy="product")
    private List<ProductStoreRel> relativeStores;
    
    @OneToOne
    @JoinColumn(name="image_id")
    private Image image;
    

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

	public Product getProductParent() {
        return productParent;
    }

    public void setProductParent(Product product) {
        this.productParent = product;
    }

    public Set<PriceOffer> getPriceOffers() {
        return priceOffers;
    }

    public void setPriceOffers(Set<PriceOffer> priceOffers) {
        this.priceOffers = priceOffers;
    }

    public Set<ProductDesc> getProductDescs() {
        return productDescs;
    }

    public void setProductDescs(Set<ProductDesc> productDescs) {
        this.productDescs = productDescs;
    }

    public Set<ProdAssignedAttr> getProdAssignedAttrs() {
        return prodAssignedAttrs;
    }

    public void setProdAssignedAttrs(Set<ProdAssignedAttr> prodAssignedAttrs) {
        this.prodAssignedAttrs = prodAssignedAttrs;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public List<ProductStoreRel> getRelativeStores() {
		return relativeStores;
	}

	public void setRelativeStores(List<ProductStoreRel> relativeStores) {
		this.relativeStores = relativeStores;
	}

	public Set<Product> getItems() {
		return items;
	}

	public void setItems(Set<Product> items) {
		this.items = items;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        if ( ! Objects.equals(id, product.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", uniqueId='" + uniqueId + "'" +
                ", prodType='" + prodType + "'" +
                '}';
    }
}
