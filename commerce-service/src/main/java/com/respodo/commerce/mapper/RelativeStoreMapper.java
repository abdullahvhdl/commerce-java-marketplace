package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.RelativeStoreDTO;
import com.respodo.commerce.repository.domain.ProductStoreRel;

@Component
public class RelativeStoreMapper {

	public RelativeStoreDTO toRelativeStoreDTO(ProductStoreRel store) {
		if(store==null){
			return null;
		}
		RelativeStoreDTO storeDTO=new RelativeStoreDTO();
		storeDTO.setId(store.getId());
		storeDTO.setUniqueId(store.getUniqueId());
		storeDTO.setName(store.getName());
		storeDTO.setDescription(store.getDescription());
		return storeDTO;
	}



}
