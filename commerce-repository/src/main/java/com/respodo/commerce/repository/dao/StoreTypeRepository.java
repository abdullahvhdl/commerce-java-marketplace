package com.respodo.commerce.repository.dao;

import com.respodo.commerce.repository.domain.StoreType;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the StoreType entity.
 */
public interface StoreTypeRepository extends JpaRepository<StoreType,Long> {

	StoreType findOneByType(String storeType);

}
