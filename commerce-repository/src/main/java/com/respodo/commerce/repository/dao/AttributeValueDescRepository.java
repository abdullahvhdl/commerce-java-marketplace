package com.respodo.commerce.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.respodo.commerce.repository.domain.AttributeValueDesc;

/**
 * Spring Data JPA repository for the AttributeValueDesc entity.
 */
public interface AttributeValueDescRepository extends JpaRepository<AttributeValueDesc,Long> {

	Optional<AttributeValueDesc> findOneByAttributeValueIdAndLanguageLocale(Long id, String string);

	List<AttributeValueDesc> findByAttributeValueId(Long attributeValueId);

}
