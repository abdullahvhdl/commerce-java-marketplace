package com.respodo.commerce.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.respodo.commerce.repository.domain.AttributeDesc;

/**
 * Spring Data JPA repository for the AttributeDesc entity.
 */
public interface AttributeDescRepository extends
		JpaRepository<AttributeDesc, Long> {

	AttributeDesc findOneByAttributeIdAndLanguageLocale(Long id,
			String string);

	List<AttributeDesc> findByAttributeId(Long attributeId);

}
