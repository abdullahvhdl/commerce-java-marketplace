package com.respodo.commerce.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.respodo.commerce.repository.domain.Category;

/**
 * Spring Data JPA repository for the Category entity.
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {

	public List<Category> findByCatLevel(Integer catLevel);

	public List<Category> findByCatalogIdAndCatLevel(Long id, int catLevel);

	public Category findOneByUniqueId(String uniqueId);

	public List<Category> findByParentCategoryId(Long categoryId);

	public Category findOneByCatalogIdAndId(Long catalogId, Long categoryId);


}
