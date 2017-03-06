package com.respodo.commerce.repository.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.respodo.commerce.repository.domain.Store;

/**
 * Spring Data JPA repository for the Store entity.
 */
public interface StoreRepository extends JpaRepository<Store, Long> {

	Store findOneByUniqueId(String uniqueId);

	List<Store> findByLocationIsNull();
	
	@Query("SELECT s From Store s where (6371 * acos( cos(radians(:latitude))   * cos(radians(s.location.latitude)) * cos(radians(s.location.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.location.latitude)))) <=:distance ")
	List<Store> findByLocation(@Param("latitude") BigDecimal latitude,
			@Param("longitude") BigDecimal longitude,
			@Param("distance") Double distance);

	List<Store> findByOwner_Login(String ownerEmail);

	Store findOneByIdAndOwner_Login(Long storeId, String owner);

	Page<Store> findByOwner_Login(String owner, Pageable pageRequest);

}
