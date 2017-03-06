package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.ProductDTO;
import com.respodo.commerce.repository.domain.Product;

@Component
public class ProductMapper {

	public ProductDTO toProductDTO(Product product) {
		if(product==null){
			return null;
		}
		ProductDTO productDTO=new ProductDTO();
		productDTO.setProductType(product.getProdType());
		productDTO.setId(product.getId());
		productDTO.setUniqueId(product.getUniqueId());
		productDTO.setBrand(product.getBrand());
		productDTO.setViews(product.getViews());
		return productDTO;
	}

	public Product toProductDomain(ProductDTO product) {
		if(product==null){
			return null;
		}
		Product productDomain=new Product();
		productDomain.setProdType(product.getProductType());
		productDomain.setId(product.getId());
		productDomain.setUniqueId(product.getUniqueId());
		productDomain.setBrand(product.getBrand());
		productDomain.setViews(product.getViews());
		return productDomain;
	}

}
