package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.LanguageDTO;
import com.respodo.commerce.repository.domain.Language;

@Component
public class LanguageMapper {

	public LanguageDTO toLanguageDTO(Language language){
		if(language ==null){
			return null;
		}
		LanguageDTO languageDTO=new LanguageDTO();
		languageDTO.setId(language.getId());
		languageDTO.setName(language.getName());
		languageDTO.setLocale(language.getLocale());
		return languageDTO;
	}
}
