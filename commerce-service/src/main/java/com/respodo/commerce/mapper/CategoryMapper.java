package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.CategoryDTO;
import com.respodo.commerce.repository.domain.Category;

@Component
public class CategoryMapper {

	public CategoryDTO toCategoryDTO(Category category) {
		if(category==null){
			return null;
		}
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setCatLevel(category.getCatLevel());
		categoryDTO.setUniqueId(category.getUniqueId());
		return categoryDTO;
	}

	public Category toCategoryDomain(CategoryDTO category) {
		Category categoryDomain = new Category();
		categoryDomain.setCatLevel(category.getCatLevel());
		categoryDomain.setUniqueId(category.getUniqueId());
		return categoryDomain;
	}

}
