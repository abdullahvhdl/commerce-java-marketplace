package com.respodo.commerce.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.respodo.commerce.repository.domain.QuickOrder;

/**
 * Spring Data JPA repository for the Catalog entity.
 */
public interface QuickOrderRepository extends JpaRepository<QuickOrder,Long> {



}
