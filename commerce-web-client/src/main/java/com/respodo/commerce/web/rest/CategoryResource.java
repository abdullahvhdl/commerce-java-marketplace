package com.respodo.commerce.web.rest;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.respodo.commerce.dto.CategoryDTO;
import com.respodo.commerce.manager.CatalogManager;

/**
 * REST controller for managing Category.
 */
@RestController
@RequestMapping("/api")
public class CategoryResource {

	private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

	@Autowired
	private CatalogManager catalogManager;


	/**
	 * GET /categories/byStore/{storeId} -> get all the categories For store.
	 */
	@RequestMapping(value = "categories/byStore/{storeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryDTO> getByStore(@PathVariable Long storeId) {
		log.debug("REST request to get all Categories By StoreId {}",storeId);
		
		List<CategoryDTO> categories = catalogManager.getStoreCategories(storeId);
		
		return categories;
	}

	/**
	 * GET /categories/:id -> get the "id" category.
	 */
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> get(@PathVariable Long id,Locale locale) {
		log.debug("REST request to get Category : {}, locale {}", id,locale);
		CategoryDTO category=catalogManager.getCategoryById(id);
		if(category==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else{
			return  new ResponseEntity<>(category, HttpStatus.OK);
		}
	}


}
