package com.respodo.commerce.repository.dao;

import com.respodo.commerce.repository.domain.Attribute;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Attribute entity.
 */
public interface AttributeRepository extends JpaRepository<Attribute,Long> {

	Optional<Attribute> findOneByUniqueId(String uniqueId);

	List<Attribute> findByAttributeType_Type(String type);

}
