package com.respodo.commerce.web.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.respodo.commerce.dto.QuickOrderDTO;
import com.respodo.commerce.dto.RegisterdOrderDTO;
import com.respodo.commerce.manager.QuickOrderManager;

/**
 * REST controller for managing Store.
 */
@RestController
@RequestMapping("/api")
public class QuickOrderResource {

	private final Logger log = LoggerFactory
			.getLogger(QuickOrderResource.class);

	@Autowired
	private QuickOrderManager quickOrderManager;
	
	/**
	 * POST /quickOrder -> order an Item for not registered users.
	 */
	@RequestMapping(value = "/quickOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> quickOrder(
			@Valid @RequestBody QuickOrderDTO quickOrderDTO) {
		log.debug("REST request to order an Item ");
		return quickOrderManager
				.quickOrder(quickOrderDTO)
				.map(quickOrder -> new ResponseEntity<>(HttpStatus.OK))
				.orElse(new ResponseEntity<Object>(
						HttpStatus.INTERNAL_SERVER_ERROR));
		
	}
	
	/**
	 * POST /quickOrder -> order an Item for  registered users.
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> orderItem(
			@Valid @RequestBody RegisterdOrderDTO registeredOrderDTO) {
		log.debug("REST request to order an Item ");
		return quickOrderManager
				.orderItem(registeredOrderDTO)
				.map(quickOrder -> new ResponseEntity<>(HttpStatus.OK))
				.orElse(new ResponseEntity<Object>(
						HttpStatus.INTERNAL_SERVER_ERROR));
		
	}
	
}
