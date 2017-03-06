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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A Store.
 */
@Entity
@Table(name = "STORE")
public class Store extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name="store_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="unique_id",nullable=false)
    private String uniqueId;
    
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
    
    @Column(name="views")
	private Integer views;

	@Column(name = "custom1")
	private String custom1;

	@Column(name = "custom2")
	private String custom2;

    @OneToOne
    @JoinColumn(name="image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name="store_type_id")
    private StoreType storeType;

    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name="owner_id")
    private User owner;
    
    @ManyToOne
    @JoinColumn(name="active_catalog_id")
    private Catalog activeCatalog;
    
    @ManyToMany
    @JoinTable(name = "STORE_CATALOG",
               joinColumns = @JoinColumn(name="store_id", referencedColumnName="store_id"),
               inverseJoinColumns = @JoinColumn(name="catalog_id", referencedColumnName="catalog_id"))
    private Set<Catalog> catalogs = new HashSet<>();

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

	public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public StoreType getStoreType() {
        return storeType;
    }

    public void setStoreType(StoreType storeType) {
        this.storeType = storeType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

	public Catalog getActiveCatalog() {
		return activeCatalog;
	}

	public void setActiveCatalog(Catalog activeCatalog) {
		this.activeCatalog = activeCatalog;
	}

	public Set<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(Set<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Store store = (Store) o;

        if ( ! Objects.equals(id, store.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", description='" + description + "'" +
                '}';
    }
}
