package com.respodo.commerce.repository.dao;

import com.respodo.commerce.repository.domain.AddrProfile;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AddrProfile entity.
 */
public interface AddrProfileRepository extends JpaRepository<AddrProfile,Long> {

}
