package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.ProductSearchDTO;
import com.respodo.commerce.repository.domain.Product;

@Component
public class ProductSearchMapper {

	public ProductSearchDTO toProductSearchDTO(Product product) {
		if(product==null){
			return null;
		}
		ProductSearchDTO productDTO=new ProductSearchDTO();
		productDTO.setProdType(product.getProdType());
		productDTO.setId(product.getId());
		productDTO.setUniqueId(product.getUniqueId());
		return productDTO;
	}


}
