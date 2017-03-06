package com.respodo.commerce.admin.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.respodo.commerce.dto.CatalogDTO;
import com.respodo.commerce.dto.CategoryDTO;
import com.respodo.commerce.dto.ProductDTO;
import com.respodo.commerce.manager.CatalogManager;

@RestController
@RequestMapping("/api")
public class CatalogResource {
	@Autowired
	private CatalogManager catalogManager;

	@RequestMapping(value = "/stores/{storeId}/catalog", method = RequestMethod.POST)
	public ResponseEntity<?> saveCatalog(@PathVariable Long storeId,
			@RequestBody CatalogDTO catalog) {

		CatalogDTO result = catalogManager.saveCatalog(storeId, catalog);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CatalogDTO>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/stores/{storeId}/catalog/{catalogId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCatalog(@PathVariable Long storeId,
			@PathVariable Long catalogId, @RequestBody CatalogDTO catalog) {

		CatalogDTO result = catalogManager.updateCatalog(storeId,catalog, catalogId);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CatalogDTO>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stores/{storeId}/catalog/{catalogId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCatalog(@PathVariable Long storeId,
			@PathVariable Long catalogId, @RequestBody CatalogDTO catalog) {

		Boolean result = catalogManager.deleteCatalog(storeId, catalogId);
		if (!result) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stores/{storeId}/catalog/{catalogId}/category", method = RequestMethod.POST)
	public ResponseEntity<?> saveCategory(@PathVariable Long storeId,
			@PathVariable Long catalogId, @RequestBody CategoryDTO category) {

		CategoryDTO result = catalogManager.saveCategory(storeId,catalogId,category);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CategoryDTO>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stores/{storeId}/catalog/{catalogId}/category/{categoryId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCategory(@PathVariable Long storeId,
			@PathVariable Long catalogId,@PathVariable Long categoryId, @RequestBody CategoryDTO category) {

		CategoryDTO result = catalogManager.updateCategory(storeId,catalogId,category, categoryId);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CategoryDTO>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stores/{storeId}/catalog/{catalogId}/category/{categoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCategory(@PathVariable Long storeId,
			@PathVariable Long catalogId,@PathVariable Long categoryId) {

		Boolean result = catalogManager.deleteCategory(storeId,catalogId, categoryId);
		if (!result) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>( HttpStatus.OK);
	}

	@RequestMapping(value="/stores/{storeId}/categories/{categoryId}/products",method=RequestMethod.GET)
	public List<ProductDTO> getCategoryProducts(@PathVariable Long storeId,
			@PathVariable Long categoryId) {

		List<ProductDTO> products = catalogManager.getAllCategoryProducts(
				storeId, categoryId);
		return products;
	}
	
	@RequestMapping(value="/stores/{storeId}/categories/{categoryId}/products/{productId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@PathVariable Long storeId,
			@PathVariable Long categoryId,@PathVariable Long productId,@RequestBody ProductDTO product) {

		ProductDTO savedProduct = catalogManager.updateProduct(storeId,categoryId,product, productId);
		if(savedProduct==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ProductDTO>(savedProduct,HttpStatus.OK);
	}
	
	@RequestMapping(value="/stores/{storeId}/categories/{categoryId}/products",method=RequestMethod.POST)
	public ResponseEntity<?> saveProduct(@PathVariable Long storeId,
			@PathVariable Long categoryId,@RequestBody ProductDTO product) {

		ProductDTO savedProduct = catalogManager.saveProduct(storeId,categoryId,product);
		if(savedProduct==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ProductDTO>(savedProduct,HttpStatus.OK);
	}
	
	@RequestMapping(value="/stores/{storeId}/categories/{categoryId}/products/{productId}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable Long storeId,
			@PathVariable Long categoryId,@PathVariable Long productId) {

		Boolean status = catalogManager.deleteProduct(storeId,categoryId, productId);
		if(!status){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/stores/{storeId}/categories/{categoryId}/addProduct/{productId}",method=RequestMethod.POST)
	public ResponseEntity<?> addProductToStoreCategory(@PathVariable Long storeId,
			@PathVariable Long categoryId,@PathVariable Long productId){
		ProductDTO product=catalogManager.addProductToStoreCategory(storeId,categoryId,productId);
		if(product==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ProductDTO>(product,HttpStatus.OK);
	}

	@RequestMapping("/stores/{storeId}/products/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable Long storeId,
			@PathVariable Long productId) {

		ProductDTO product = catalogManager.getProductById(storeId, productId);
		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value="/stores/{storeId}/categories/{categoryId}/products/{productId}/generateItems",method=RequestMethod.POST)
	public ResponseEntity<?> generateItems(@PathVariable Long storeId,@PathVariable Long categoryId,
			@PathVariable Long productId) {

		catalogManager.generateItems(storeId,categoryId, productId);

		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@RequestMapping(value="/stores/{storeId}/categories/{categoryId}/products/{productId}/saveItem",method=RequestMethod.POST)
	public ResponseEntity<?> saveItem(@PathVariable Long storeId,@PathVariable Long categoryId,
			@PathVariable Long productId,@RequestBody ProductDTO item) {

		ProductDTO savedItem=catalogManager.saveItem(storeId,categoryId, productId,item);
		if (savedItem == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductDTO>(savedItem, HttpStatus.OK);
	}
	
	@RequestMapping(value="/stores/{storeId}/categories/{categoryId}/products/{productId}/updateItem/{itemId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateItem(@PathVariable Long storeId,@PathVariable Long categoryId,
			@PathVariable Long productId,@PathVariable Long itemId,@RequestBody ProductDTO item) {

		ProductDTO savedItem=catalogManager.updateItem(storeId,categoryId, productId,item,itemId);
		if (savedItem == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductDTO>(savedItem, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="searchProduct",method=RequestMethod.GET)
	public List<ProductDTO> searchProducts(@RequestParam String query){
		List<ProductDTO> products=catalogManager.findProducts(query);
		
		return products;
		
	}
	
	
}
