package com.respodo.commerce.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.respodo.commerce.repository.domain.Catalog;

/**
 * Spring Data JPA repository for the Catalog entity.
 */
public interface CatalogRepository extends JpaRepository<Catalog,Long> {

    @Query("select catalog from Catalog catalog where catalog.id =:id")
    Catalog findOneWithEagerRelationships(@Param("id") Long id);

	Optional<Catalog> findOneByUniqueId(
			String uniqueId);

	Catalog findOneByIdAndStores_Id(Long id, Long storeId);

	List<Catalog> findByStores_Id(Long storeId);

}
