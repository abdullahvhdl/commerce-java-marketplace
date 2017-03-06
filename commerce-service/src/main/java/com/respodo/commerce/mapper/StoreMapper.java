package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.StoreDTO;
import com.respodo.commerce.repository.domain.Store;

@Component
public class StoreMapper {

	public StoreDTO toStoreDTO(Store store) {
		if(store==null){
			return null;
		}
		StoreDTO storeDTO=new StoreDTO();
		storeDTO.setId(store.getId());
		storeDTO.setName(store.getName());
		storeDTO.setDescription(store.getDescription());
		storeDTO.setUniqueId(store.getUniqueId());
		return storeDTO;
	}

	public Store toStoreDomain(StoreDTO store) {
		if(store==null){
			return null;
		}
		Store domainStore=new Store();
		domainStore.setName(store.getName());
		domainStore.setDescription(store.getDescription());
		domainStore.setUniqueId(store.getUniqueId());
		return domainStore;
	}

}
