package com.respodo.commerce.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.CategoryDescDTO;
import com.respodo.commerce.repository.domain.CategoryDesc;

@Component
public class CategoryDescriptionMapper {
	@Autowired
	private LanguageMapper languageMapper;
	
	public CategoryDescDTO toCategoryDescriptionDTO(CategoryDesc categoryDesc) {
		if(categoryDesc==null){
			return null;
		}
		CategoryDescDTO categoryDescDTO=new CategoryDescDTO();
		categoryDescDTO.setActive(categoryDesc.getActive());
		categoryDescDTO.setDescription(categoryDesc.getDescription());
		categoryDescDTO.setId(categoryDesc.getId());
		categoryDescDTO.setName(categoryDesc.getName());
		categoryDescDTO.setLanguage(languageMapper.toLanguageDTO(categoryDesc.getLanguage()));
		return categoryDescDTO;
	}

	public CategoryDesc toCategoryDescriptionDomain(CategoryDescDTO description) {
		CategoryDesc categoryDescDomain=new CategoryDesc();
		categoryDescDomain.setActive(description.getActive());
		categoryDescDomain.setDescription(description.getDescription());
		categoryDescDomain.setName(description.getName());
		return categoryDescDomain;
	}

}
