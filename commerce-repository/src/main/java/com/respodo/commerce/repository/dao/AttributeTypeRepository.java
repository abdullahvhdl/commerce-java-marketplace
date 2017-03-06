package com.respodo.commerce.repository.dao;

import com.respodo.commerce.repository.domain.AttributeType;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AttributeType entity.
 */
public interface AttributeTypeRepository extends JpaRepository<AttributeType,Long> {

	AttributeType findOneByType(String type);

}
