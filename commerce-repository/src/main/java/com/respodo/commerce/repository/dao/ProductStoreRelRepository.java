package com.respodo.commerce.repository.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.respodo.commerce.repository.domain.Product;
import com.respodo.commerce.repository.domain.ProductStoreRel;

/**
 * Spring Data JPA repository for the Product entity.
 */
public interface ProductStoreRelRepository extends
		JpaRepository<ProductStoreRel, Long> {

	@Query("SELECT s From ProductStoreRel s where s.product.id=:productId AND (6371 * acos( cos(radians(:latitude))   * cos(radians(s.location.latitude)) * cos(radians(s.location.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.location.latitude)))) <=:distance ")
	public List<ProductStoreRel> findByProductIdAndLocation(
			@Param("productId") Long productId,
			@Param("latitude") BigDecimal latitude,
			@Param("longitude") BigDecimal longitude,
			@Param("distance") Double distance);

	public List<ProductStoreRel> findByProductId(Long id);
}
