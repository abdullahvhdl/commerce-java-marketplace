package com.respodo.commerce.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.respodo.commerce.repository.domain.Catalog;
import com.respodo.commerce.repository.domain.Product;

/**
 * Spring Data JPA repository for the Product entity.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p INNER JOIN p.productDescs d where p.prodType=:type AND d.name like %:query% OR d.description like %:query% ")
	Page<Product> searchProducts(@Param("query") String query,@Param("type") String type, Pageable pageRequest);

	List<Product> findByProductParentIdAndCategories_catalog(Long productId,
			Catalog activeCatalog);

	Page<Product> findByCategoriesIdAndProdType(Long categoryId, String product, Pageable pageable);

	List<Product> findByProductParentIdAndProdTypeAndCategories_catalog(
			Long productId, String item, Catalog activeCatalog);

	Optional<Product> findOneByUniqueId(String uniqueId);


	List<Product> findByProductParentId(Long productId);
	
	List<Product> findByIdIn(List<Long> id);

	List<Product> findByProductParentIdAndProdType(Long productId, String item);

	List<Product> findByCategoriesIdAndProdType(Long categoryId, String product);

	@Query("SELECT p FROM Product p INNER JOIN p.productDescs d where p.prodType=:type AND d.name like %:query% OR d.description like %:query% ")
	List<Product> searchProducts(@Param("query") String query,@Param("type") String type);

}
