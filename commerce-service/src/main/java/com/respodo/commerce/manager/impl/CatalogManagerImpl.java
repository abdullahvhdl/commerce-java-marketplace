package com.respodo.commerce.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.respodo.commerce.common.AttributeTypeConstants;
import com.respodo.commerce.dto.CatalogDTO;
import com.respodo.commerce.dto.CategoryDTO;
import com.respodo.commerce.dto.CurrencyPriceDTO;
import com.respodo.commerce.dto.LocationDTO;
import com.respodo.commerce.dto.ProdAssignedAttrDTO;
import com.respodo.commerce.dto.ProductDTO;
import com.respodo.commerce.dto.ProductSearchDTO;
import com.respodo.commerce.dto.RelativeStoreDTO;
import com.respodo.commerce.dto.StoreDTO;
import com.respodo.commerce.dto.csv.ProductAssignedAttrsCSVDTO;
import com.respodo.commerce.manager.CatalogManager;
import com.respodo.commerce.repository.config.SecurityUtils;
import com.respodo.commerce.service.CatalogService;
import com.respodo.commerce.service.CategoryService;
import com.respodo.commerce.service.AttributeService;
import com.respodo.commerce.service.ProductService;
import com.respodo.commerce.service.StoreService;
import com.respodo.commerce.service.util.DistanceCalculator;
import com.respodo.commerce.service.util.LocationUtil;

@Service
public class CatalogManagerImpl implements CatalogManager {

	@Autowired
	private StoreService storeService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private AttributeService prodAssignedAttrService;

	@Autowired
	private DistanceCalculator distanceCalculator;

	@Override
	public CatalogDTO getMasterCatalog() {
		CatalogDTO catalog = catalogService.getMasterCatalog();
		return catalog;
	}

	@Override
	public CatalogDTO getCatalogById(Long catalogId) {
		CatalogDTO catalog = catalogService.getCatalogById(catalogId);
		return catalog;
	}

	@Override
	public CatalogDTO saveCatalog(Long storeId, CatalogDTO catalog) {
		String owner = SecurityUtils.getCurrentLogin();
		StoreDTO store = storeService.getStoreByIdAndOwner(storeId, owner);
		if (store == null) {
			return null;
		}
		CatalogDTO result = catalogService.saveCatalog(storeId, catalog);

		return result;
	}

	@Override
	public CatalogDTO updateCatalog(Long storeId, CatalogDTO catalog,
			Long catalogId) {
		String owner = SecurityUtils.getCurrentLogin();
		StoreDTO store = storeService.getStoreByIdAndOwner(storeId, owner);
		if (store == null) {
			return null;
		}
		CatalogDTO dbCatalog = catalogService.getCatalogById(catalogId);
		List<CatalogDTO> storeCatalogs = catalogService
				.getCatalogsByStoreId(storeId);
		if (storeCatalogs.contains(dbCatalog)) {
			CatalogDTO result = catalogService
					.updateCatalog(catalog, catalogId);

			return result;
		}

		return null;
	}

	@Override
	public Boolean deleteCatalog(Long storeId, Long catalogId) {
		String owner = SecurityUtils.getCurrentLogin();
		StoreDTO store = storeService.getStoreByIdAndOwner(storeId, owner);
		if (store == null) {
			return null;
		}
		CatalogDTO dbCatalog = catalogService.getCatalogById(catalogId);
		List<CatalogDTO> storeCatalogs = catalogService
				.getCatalogsByStoreId(storeId);
		if (storeCatalogs.contains(dbCatalog)) {
			Boolean status = catalogService.deleteCatalog(catalogId);
			return status;
		}

		return null;
	}

	@Override
	public List<CategoryDTO> getCatalogCategories(Long catalogId) {
		List<CategoryDTO> categories = categoryService
				.getCatalogCategories(catalogId);
		return categories;
	}

	@Override
	public List<CategoryDTO> getStoreCategories(Long storeId) {
		List<CategoryDTO> categories = categoryService
				.getStoreCategories(storeId);

		return categories;
	}

	@Override
	public List<CategoryDTO> getMasterCatalogCategories() {
		CatalogDTO masterCatalog = getMasterCatalog();
		if (masterCatalog != null) {
			categoryService.getCatalogCategories(masterCatalog.getId());
		}
		return null;
	}

	@Override
	public CategoryDTO getCategoryById(Long categoryId) {
		CategoryDTO category = categoryService.getCategoryById(categoryId);
		return category;
	}

	@Override
	public CategoryDTO saveCategory(Long storeId, Long catalogId,
			CategoryDTO category) {
		String owner = SecurityUtils.getCurrentLogin();
		StoreDTO store = storeService.getStoreByIdAndOwner(storeId, owner);
		if (store == null) {
			return null;
		}
		CatalogDTO dbCatalog = catalogService.getCatalogById(catalogId);
		List<CatalogDTO> storeCatalogs = catalogService
				.getCatalogsByStoreId(storeId);
		if (storeCatalogs.contains(dbCatalog)) {
			CategoryDTO status = categoryService.saveCategory(catalogId,
					category);
			return status;
		}
		return null;
	}

	@Override
	public CategoryDTO updateCategory(Long storeId, Long catalogId,
			CategoryDTO category, Long categoryId) {
		String owner = SecurityUtils.getCurrentLogin();
		StoreDTO store = storeService.getStoreByIdAndOwner(storeId, owner);
		if (store == null) {
			return null;
		}
		CatalogDTO dbCatalog = catalogService.getCatalogById(catalogId);
		List<CatalogDTO> storeCatalogs = catalogService
				.getCatalogsByStoreId(storeId);
		CategoryDTO dbCategory = categoryService
				.getCategoryByCatalogIdAndCategoryId(catalogId, categoryId);
		if (storeCatalogs.contains(dbCatalog) && dbCategory != null) {
			CategoryDTO result = categoryService.updateCategory(category,
					categoryId);
			return result;
		}
		return null;
	}

	@Override
	public Boolean deleteCategory(Long storeId, Long catalogId, Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductDTO> getCategoryProducts(Long categoryId,
			Pageable pageRequest) {
		Page<ProductDTO> products = productService.getCategoryProducts(
				categoryId, pageRequest);
		return products;
	}

	@Override
	public Page<ProductDTO> getCategoryProducts(Long storeId, Long categoryId,
			Pageable pageRequest) {
		Page<ProductDTO> products = productService.getCategoryProducts(
				categoryId, pageRequest);
		products.forEach(product -> {
			List<CurrencyPriceDTO> offerPrices = productService
					.getProductOfferPrices(product.getId(), storeId);
			product.setOfferPrice(offerPrices);
			List<CurrencyPriceDTO> originalPrices = productService
					.getProductOriginalPrices(product.getId(), storeId);
			product.setOriginalPrice(originalPrices);
		});
		return products;
	}

	@Override
	public Page<ProductSearchDTO> searchProducts(String query,
			LocationUtil location, Pageable pageRequest) {
		Page<ProductSearchDTO> products = productService.searchProducts(query,
				pageRequest);
		products.forEach(product -> {
			List<RelativeStoreDTO> stores = productService
					.getProductStores(product.getId());
			stores.forEach(store -> {
				LocationDTO locationDTO = store.getLocation();
				if (locationDTO != null) {
					Double storeDistance = distanceCalculator.distance(location
							.getLat(), location.getLng(), locationDTO
							.getLatitude().doubleValue(), locationDTO
							.getLongitude().doubleValue(), location.getMetric());
					store.setDistance(storeDistance);
				}

				List<CurrencyPriceDTO> offerPrices = productService
						.getProductOfferPrices(product.getId(), store.getId());
				store.setOfferPrice(offerPrices);
				List<CurrencyPriceDTO> originalPrices = productService
						.getProductOriginalPrices(product.getId(),
								store.getId());
				store.setOriginalPrice(originalPrices);
			});
			product.setStores(stores);

		});
		return products;
	}

	@Override
	public List<ProductDTO> getProductsByIds(List<Long> productIds) {
		List<ProductDTO> products = productService.getProductsByIds(productIds);
		return products;
	}

	@Override
	public ProductDTO getProductById(Long productId) {
		ProductDTO product = productService.getProductById(productId);
		return product;
	}

	@Override
	public ProductDTO getProductById(Long storeId, Long productId) {
		ProductDTO product = productService.getProductById(productId);
		if (product != null) {
			List<CurrencyPriceDTO> offerPrices = productService
					.getProductOfferPrices(product.getId(), storeId);
			product.setOfferPrice(offerPrices);
			List<CurrencyPriceDTO> originalPrices = productService
					.getProductOriginalPrices(product.getId(), storeId);
			product.setOriginalPrice(originalPrices);
			List<ProdAssignedAttrDTO> buyableAttrs = prodAssignedAttrService
					.getProductBuyableAttributes(productId);
			product.setBuyableAttributes(buyableAttrs);
			List<ProdAssignedAttrDTO> displayableAttrs = prodAssignedAttrService
					.getProductDisplayableAttributes(productId);
			product.setDisplayableAttributes(displayableAttrs);
			List<ProductDTO> productItems = getProductItems(productId, storeId);
			product.setItems(productItems);
		}
		return product;
	}

	@Override
	public ProductDTO saveProduct(Long storeId, Long categoryId,
			ProductDTO product) {
		ProductDTO savedProduct = productService.saveProduct(storeId,
				categoryId, product);
		if(savedProduct !=null){
			for(ProdAssignedAttrDTO assignedAttr:product.getDisplayableAttributes()){
				prodAssignedAttrService.saveProductAssignedAttr(savedProduct.getId(),assignedAttr);
			}
			for(ProdAssignedAttrDTO assignedAttr:product.getBuyableAttributes()){
				prodAssignedAttrService.saveProductAssignedAttr(savedProduct.getId(),assignedAttr);
			}
		}
		return savedProduct;
	}

	@Override
	public ProductDTO updateProduct(Long storeId, Long categoryId,
			ProductDTO product, Long productId) {
		ProductDTO savedProduct = productService.updateProduct(storeId,
				categoryId, product, productId);
		if(savedProduct !=null){
			for(ProdAssignedAttrDTO assignedAttr:product.getDisplayableAttributes()){
				prodAssignedAttrService.updateProductAssignedAttr(savedProduct.getId(),assignedAttr);
			}
			for(ProdAssignedAttrDTO assignedAttr:product.getBuyableAttributes()){
				prodAssignedAttrService.updateProductAssignedAttr(savedProduct.getId(),assignedAttr);
			}
		}
		return savedProduct;
	}

	@Override
	public Boolean deleteProduct(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> getProductItems(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> getProductItems(Long productId, Long storeId) {
		List<ProductDTO> items = productService.getProductItems(productId,
				storeId);
		items.forEach(item -> {
			List<CurrencyPriceDTO> offerPrices = productService
					.getProductOfferPrices(item.getId(), storeId);
			item.setOfferPrice(offerPrices);
			List<CurrencyPriceDTO> originalPrices = productService
					.getProductOriginalPrices(item.getId(), storeId);
			item.setOriginalPrice(originalPrices);
			List<ProdAssignedAttrDTO> buyableAttrs = prodAssignedAttrService
					.getProductBuyableAttributes(item.getId());
			item.setBuyableAttributes(buyableAttrs);
		});
		return items;
	}

	@Override
	public List<RelativeStoreDTO> getRelativeStores(Long storeId,
			Long productId, LocationUtil location) {
		List<RelativeStoreDTO> stores = productService.getRelativeStores(
				storeId, productId, location);
		stores.forEach(store -> {
			ProductDTO product = store.getProduct();
			if (product != null) {
				List<CurrencyPriceDTO> offerPrices = productService
						.getProductOfferPrices(productId, storeId);
				product.setOfferPrice(offerPrices);
				List<CurrencyPriceDTO> originalPrices = productService
						.getProductOriginalPrices(productId, storeId);
				product.setOriginalPrice(originalPrices);
			}
		});
		return stores;
	}

	@Override
	public List<ProductDTO> getAllCategoryProducts(Long storeId, Long categoryId) {
		List<ProductDTO> products = productService
				.getAllCategoryProducts(categoryId);
		products.forEach(product -> {
			List<CurrencyPriceDTO> offerPrices = productService
					.getProductOfferPrices(product.getId(), storeId);
			product.setOfferPrice(offerPrices);
			List<CurrencyPriceDTO> originalPrices = productService
					.getProductOriginalPrices(product.getId(), storeId);
			product.setOriginalPrice(originalPrices);
		});
		return products;
	}

	@Override
	public List<ProductDTO> findProducts(String query) {
		List<ProductDTO> products = productService.findProducts(query);
		return products;
	}

	@Override
	public ProductDTO addProductToStoreCategory(Long storeId, Long categoryId,
			Long productId) {
		ProductDTO product = productService.addProductToStoreCategory(storeId,
				categoryId, productId);
		// if(product !=null){
		// List<CurrencyPriceDTO> offerPrices = productService
		// .getProductOfferPrices(product.getId(), storeId);
		// product.setOfferPrice(offerPrices);
		// List<CurrencyPriceDTO> originalPrices = productService
		// .getProductOriginalPrices(product.getId(), storeId);
		// product.setOriginalPrice(originalPrices);
		// List<ProdAssignedAttrDTO> buyableAttrs = prodAssignedAttrService
		// .getProductBuyableAttributes(productId);
		// product.setBuyableAttributes(buyableAttrs);
		// List<ProdAssignedAttrDTO> displayableAttrs = prodAssignedAttrService
		// .getProductDisplayableAttributes(productId);
		// product.setDisplayableAttributes(displayableAttrs);
		// List<ProductDTO> productItems = getProductItems(productId, storeId);
		// product.setItems(productItems);
		// }
		return product;
	}

	@Override
	public void generateItems(Long storeId,Long categoryId, Long productId) {
		productService.generateItems(storeId,categoryId,productId);
		
	}

	@Override
	public ProductDTO saveItem(Long storeId, Long categoryId, Long productId,
			ProductDTO item) {
		ProductDTO savedItem=productService.saveItem(storeId,categoryId,productId,item);
		if(savedItem !=null){
			for(ProdAssignedAttrDTO assignedAttr:item.getDisplayableAttributes()){
				prodAssignedAttrService.saveProductAssignedAttr(savedItem.getId(),assignedAttr);
			}
			for(ProdAssignedAttrDTO assignedAttr:item.getBuyableAttributes()){
				prodAssignedAttrService.saveProductAssignedAttr(savedItem.getId(),assignedAttr);
			}
		}
		return savedItem;
	}

	@Override
	public ProductDTO updateItem(Long storeId, Long categoryId, Long productId,
			ProductDTO item, Long itemId) {
		ProductDTO savedItem=productService.updateItem(storeId,categoryId,productId,item,itemId);
		if(savedItem !=null){
			for(ProdAssignedAttrDTO assignedAttr:item.getDisplayableAttributes()){
				prodAssignedAttrService.updateProductAssignedAttr(savedItem.getId(),assignedAttr);
			}
			for(ProdAssignedAttrDTO assignedAttr:item.getBuyableAttributes()){
				prodAssignedAttrService.updateProductAssignedAttr(savedItem.getId(),assignedAttr);
			}
		}
		return savedItem;
	}

	@Override
	public Boolean deleteProduct(Long storeId, Long categoryId, Long productId) {
		return productService.romoveProduct(storeId,categoryId,productId);
	}

}
