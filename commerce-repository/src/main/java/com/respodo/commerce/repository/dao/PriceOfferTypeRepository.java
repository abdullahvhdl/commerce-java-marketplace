package com.respodo.commerce.repository.dao;

import com.respodo.commerce.repository.domain.PriceOfferType;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PriceOfferType entity.
 */
public interface PriceOfferTypeRepository extends JpaRepository<PriceOfferType,Long> {

	PriceOfferType findOneByType(String type);

}
