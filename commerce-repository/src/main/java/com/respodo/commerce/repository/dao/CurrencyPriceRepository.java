package com.respodo.commerce.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.respodo.commerce.repository.domain.CurrencyPrice;
import com.respodo.commerce.repository.domain.PriceOffer;

/**
 * Spring Data JPA repository for the CurrencyPrice entity.
 */
public interface CurrencyPriceRepository extends
		JpaRepository<CurrencyPrice, Long> {

	@Query("SELECT cp from CurrencyPrice cp INNER JOIN cp.priceOffer po where po.store.id =:storeId  AND  po.product.id =:productId  AND cp.currency =:currencyCode AND  po.priceOfferType.type =:type")
	Optional<CurrencyPrice> findProductPrice(
			@Param("productId") Long productId, @Param("storeId") Long storeId,
			@Param("type") String type,
			@Param("currencyCode") String currencyCode);

	CurrencyPrice findOneByPriceOfferAndCurrency(PriceOffer priceOffer,
			String currency);

	@Query("SELECT cp from CurrencyPrice cp INNER JOIN cp.priceOffer po where po.store.id =:storeId  AND  po.product.id =:productId AND  po.priceOfferType.type =:type")
	List<CurrencyPrice> findProductPrice(@Param("productId") Long productId,
			@Param("storeId") Long storeId, @Param("type") String type);
}
