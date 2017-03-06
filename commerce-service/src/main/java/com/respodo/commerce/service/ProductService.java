package com.respodo.commerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.respodo.commerce.dto.CurrencyPriceDTO;
import com.respodo.commerce.dto.ProductDTO;
import com.respodo.commerce.dto.ProductDescDTO;
import com.respodo.commerce.dto.ProductSearchDTO;
import com.respodo.commerce.dto.RelativeStoreDTO;
import com.respodo.commerce.service.util.LocationUtil;

public interface ProductService {

	ProductDTO getProductById(Long productId);

	Page<ProductDTO> getCategoryProducts(Long categoryId, Pageable pageRequest);

	Page<ProductSearchDTO> searchProducts(String query, Pageable pageRequest);

	List<ProductDTO> getProductsByIds(List<Long> productIds);

	List<ProductDTO> getProductItems(Long productId);

	List<ProductDTO> getProductItems(Long productId, Long storeId);

	List<CurrencyPriceDTO> getProductOfferPrices(Long productId, Long storeId);

	List<CurrencyPriceDTO> getProductOriginalPrices(Long productId, Long storeId);

	List<ProductDescDTO> getProductDescription(Long productId);

	List<RelativeStoreDTO> getRelativeStores(Long storeId, Long productId,
			LocationUtil location);

	List<RelativeStoreDTO> getProductStores(Long productId);

	List<ProductDTO> getAllCategoryProducts(Long categoryId);

	List<ProductDTO> findProducts(String query);

	ProductDTO addProductToStoreCategory(Long storeId, Long categoryId,
			Long productId);

	ProductDTO updateProduct(Long storeId,Long categoryId,ProductDTO product, Long productId);

	void generateItems(Long storeId, Long categoryId, Long productId);

	ProductDTO updateItem(Long storeId, Long categoryId, Long productId,
			ProductDTO item, Long itemId);

	ProductDTO saveItem(Long storeId, Long categoryId, Long productId,
			ProductDTO item);

	ProductDTO saveProduct(Long storeId, Long categoryId, ProductDTO product);

	Boolean romoveProduct(Long storeId, Long categoryId, Long productId);

}
