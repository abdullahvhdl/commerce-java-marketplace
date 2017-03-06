package com.respodo.commerce.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.respodo.commerce.repository.domain.PriceOffer;
import com.respodo.commerce.repository.domain.PriceOfferType;
import com.respodo.commerce.repository.domain.Product;
import com.respodo.commerce.repository.domain.Store;

/**
 * Spring Data JPA repository for the PriceOffer entity.
 */
public interface PriceOfferRepository extends JpaRepository<PriceOffer, Long> {

	PriceOffer findOneByProductAndStoreAndPriceOfferType(
			Product product, Store store, PriceOfferType type);

}
