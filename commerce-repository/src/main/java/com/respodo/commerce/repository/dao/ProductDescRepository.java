package com.respodo.commerce.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.respodo.commerce.repository.domain.ProductDesc;

/**
 * Spring Data JPA repository for the ProductDesc entity.
 */
public interface ProductDescRepository extends JpaRepository<ProductDesc,Long> {

	ProductDesc findOneByProductIdAndLanguageLocale(Long id,
			String language);

	List<ProductDesc> findByProductId(Long productId);

}
