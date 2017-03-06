package com.respodo.commerce.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.respodo.commerce.repository.domain.AttributeValue;

/**
 * Spring Data JPA repository for the AttributeValue entity.
 */
public interface AttributeValueRepository extends JpaRepository<AttributeValue,Long> {

	Optional<AttributeValue> findOneByUniqueId(String uniqueId);

	List<AttributeValue> findByAttributeId(Long attributeId);

}
