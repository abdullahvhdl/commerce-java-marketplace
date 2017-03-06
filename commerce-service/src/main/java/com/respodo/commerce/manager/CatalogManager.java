package com.respodo.commerce.manager;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.respodo.commerce.dto.CatalogDTO;
import com.respodo.commerce.dto.CategoryDTO;
import com.respodo.commerce.dto.ProductDTO;
import com.respodo.commerce.dto.ProductSearchDTO;
import com.respodo.commerce.dto.RelativeStoreDTO;
import com.respodo.commerce.service.util.LocationUtil;

public interface CatalogManager {
	
	CatalogDTO getMasterCatalog();

	CatalogDTO getCatalogById(Long catalogId);

	CatalogDTO saveCatalog(Long storeId,CatalogDTO catalog);

	CatalogDTO updateCatalog(Long storeId,CatalogDTO catalog, Long catalogId);

	Boolean deleteCatalog(Long storeId,Long catalogId);

	List<CategoryDTO> getCatalogCategories(Long catalogId);

	List<CategoryDTO> getStoreCategories(Long storeId);

	List<CategoryDTO> getMasterCatalogCategories();

	CategoryDTO getCategoryById(Long categoryId);

	CategoryDTO saveCategory(Long storeId,Long catalogId, CategoryDTO category);

	CategoryDTO updateCategory(Long storeId,Long catalogId,CategoryDTO category, Long categoryId);

	Boolean deleteCategory(Long storeId,Long catalogId,Long categoryId);

	Page<ProductDTO> getCategoryProducts(Long categoryId, Pageable pageRequest);
	
	Page<ProductDTO> getCategoryProducts(Long storeId,Long categoryId, Pageable pageRequest);

	Page<ProductSearchDTO> searchProducts(String query, LocationUtil location, Pageable pageRequest);

	List<ProductDTO> getProductsByIds(List<Long> productIds);

	ProductDTO getProductById(Long productId);
	
	ProductDTO getProductById(Long storeId,Long productId);

	ProductDTO saveProduct(Long storeId,Long categoryId,ProductDTO product);

	ProductDTO updateProduct(Long storeId,Long categoryId,ProductDTO product, Long productId);

	Boolean deleteProduct(Long productId);

	List<ProductDTO> getProductItems(Long productId);
	
	List<ProductDTO> getProductItems(Long productId,Long storeId);
	
	List<RelativeStoreDTO> getRelativeStores(Long storeId,Long productId,LocationUtil location);

	List<ProductDTO> getAllCategoryProducts(Long storeId, Long categoryId);

	List<ProductDTO> findProducts(String query);

	ProductDTO addProductToStoreCategory(Long storeId, Long categoryId,
			Long productId);

	void generateItems(Long storeId, Long productId, Long productId2);

	ProductDTO saveItem(Long storeId, Long categoryId, Long productId, ProductDTO item);

	ProductDTO updateItem(Long storeId, Long categoryId, Long productId,
			ProductDTO item, Long itemId);

	Boolean deleteProduct(Long storeId, Long categoryId, Long productId);

}
