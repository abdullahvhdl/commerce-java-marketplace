package com.respodo.commerce.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.respodo.commerce.constants.Metrics;
import com.respodo.commerce.dto.StoreDTO;
import com.respodo.commerce.manager.StoreManager;
import com.respodo.commerce.service.util.LocationUtil;

/**
 * REST controller for managing Store.
 */
@RestController
@RequestMapping("/api")
public class StoreResource {

	private final Logger log = LoggerFactory.getLogger(StoreResource.class);

	@Autowired
	private StoreManager storeManager;

	/**
	 * GET /stores -> get all the stores.
	 */
	@RequestMapping(value = "/stores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StoreDTO> getAllStores(@RequestParam Double lat,
			@RequestParam Double lng, @RequestParam Integer distance) {
		log.debug("REST request to get all Stores ");
		LocationUtil location=new LocationUtil();
		location.setLat(lat);
		location.setLng(lng);
		location.setDistance(distance);
		location.setMetric(Metrics.MILE);
		
		return storeManager.getStoresByLocation(location);
	}

	/**
	 * GET /stores/:id -> get the "id" store.
	 */
	@RequestMapping(value = "/stores/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StoreDTO> getStore(@PathVariable Long id) {
		StoreDTO storeDTO=storeManager.getStoreById(id);
		if(storeDTO == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(storeDTO, HttpStatus.OK);
	}

}
