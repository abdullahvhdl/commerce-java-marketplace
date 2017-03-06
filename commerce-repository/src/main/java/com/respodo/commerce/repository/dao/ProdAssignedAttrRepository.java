package com.respodo.commerce.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.respodo.commerce.repository.domain.ProdAssignedAttr;

/**
 * Spring Data JPA repository for the ProdAssignedAttr entity.
 */
public interface ProdAssignedAttrRepository extends JpaRepository<ProdAssignedAttr,Long> {

	List<ProdAssignedAttr> findByProductIdAndAttribute_AttributeType_Type(
			Long productId,String type);


}
