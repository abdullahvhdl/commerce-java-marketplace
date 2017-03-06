package com.respodo.commerce.repository.dao;

import com.respodo.commerce.repository.domain.Language;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Language entity.
 */
public interface LanguageRepository extends JpaRepository<Language,Long> {

	Language findOneByLocale(String language);

}
