package com.respodo.commerce.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.respodo.commerce.repository.domain.CategoryDesc;

/**
 * Spring Data JPA repository for the CategoryDesc entity.
 */
public interface CategoryDescRepository extends JpaRepository<CategoryDesc,Long> {

	List<CategoryDesc> findByCategoryId(Long categoryId);

	CategoryDesc findOneByCategoryIdAndLanguageLocale(Long id, String language);

}
