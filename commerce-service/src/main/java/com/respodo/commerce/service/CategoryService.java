package com.respodo.commerce.service;

import java.util.List;

import com.respodo.commerce.dto.CategoryDTO;
import com.respodo.commerce.dto.CategoryDescDTO;

public interface CategoryService {

	CategoryDTO getCategoryById(Long id);

	List<CategoryDTO> getStoreCategories(Long storeId);

	List<CategoryDTO> getCatalogCategories(Long catalogId);

	List<CategoryDTO> getSubCategories(Long categoryId);

	List<CategoryDescDTO> getCategoryDescription(Long categoryId);

	CategoryDTO saveCategory(Long catalogId, CategoryDTO category);

	CategoryDTO updateCategory(CategoryDTO category, Long categoryId);

	CategoryDTO getCategoryByCatalogIdAndCategoryId(Long catalogId,
			Long categoryId);

}
