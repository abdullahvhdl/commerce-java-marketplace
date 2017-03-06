package com.respodo.commerce.web.rest;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.respodo.commerce.constants.Metrics;
import com.respodo.commerce.dto.ProductDTO;
import com.respodo.commerce.dto.ProductSearchDTO;
import com.respodo.commerce.dto.RelativeStoreDTO;
import com.respodo.commerce.manager.CatalogManager;
import com.respodo.commerce.service.AttributeService;
import com.respodo.commerce.service.util.LocationUtil;

/**
 * REST controller for managing Product.
 */
@RestController
@RequestMapping("/api")
public class ProductResource {

	private final Logger log = LoggerFactory.getLogger(ProductResource.class);

	@Autowired
	private CatalogManager catalogManager;

	@Autowired
	private AttributeService prodAssignedAttrService;

	/**
	 * GET /products -> get products by categoryId.
	 */
	@RequestMapping(value = "/products/{storeId}/byCategory/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ProductDTO> getByCategory(@PathVariable Long storeId,
			@PathVariable Long categoryId,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "15") Integer limit, Locale locale) {
		log.debug("REST request to get all Products");
		Pageable pageRequest = new PageRequest(page, limit);
		return catalogManager.getCategoryProducts(storeId, categoryId,
				pageRequest);
	}

	/**
	 * GET /products/:id -> get the "id" product.
	 */
	@RequestMapping(value = "/products/{storeId}/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Long storeId,
			@PathVariable Long productId) {
		log.debug("REST request to get Product : {}", productId);
		ProductDTO product = catalogManager.getProductById(storeId, productId);
		if (product == null) {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	/**
	 * GET /products/:id -> get the "id" product.
	 */
	@RequestMapping(value = "/products/getProductsByIds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductsByIds(@RequestParam List<Long> id,
			Locale locale) {
		log.debug("REST request to get Products by ids : {}", id);
		return catalogManager.getProductsByIds(id);
	}

	@RequestMapping(value = "/products/relativeStores/{storeId}/{productId}", method = RequestMethod.GET)
	public List<RelativeStoreDTO> getAlternateStores(
			@PathVariable Long storeId, @PathVariable Long productId,
			@RequestParam Double lat, @RequestParam Double lng,
			@RequestParam Integer distance) {
		LocationUtil location = new LocationUtil();
		location.setLat(lat);
		location.setLng(lng);
		location.setDistance(distance);
		location.setMetric(Metrics.MILE);
		return catalogManager.getRelativeStores(storeId, productId, location);

	}

	@RequestMapping("/serachComplete")
	private List<ProductSearchDTO> autoComplete(
			@RequestParam String searchQuery, Locale locale) {
		Pageable pageRequest = new PageRequest(0, 15);
		return null;
	}

	/**
	 * SEARCH /_search/products/:query -> search for the product corresponding
	 * to the query.
	 */
	@RequestMapping(value = "/_search/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ProductSearchDTO> search(@RequestParam String query,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "15") Integer limit,
			@RequestParam Double lat, @RequestParam Double lng, Locale locale) {
		Pageable pageRequest = new PageRequest(page, limit);
		LocationUtil location = new LocationUtil();
		location.setLat(lat);
		location.setLng(lng);
		location.setMetric(Metrics.MILE);
		return catalogManager.searchProducts(query,location, pageRequest);
	}
}
