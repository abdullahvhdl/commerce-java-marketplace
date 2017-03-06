package com.respodo.commerce.admin.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.respodo.commerce.dto.CatalogDTO;
import com.respodo.commerce.dto.StoreDTO;
import com.respodo.commerce.manager.StoreManager;

@RestController
@RequestMapping("/api")
public class StoresResource {
	@Autowired
	private StoreManager storesManager;
	
	@RequestMapping(value="/stores",method=RequestMethod.GET)
	public List<StoreDTO> getStores(){
		return storesManager.getAllAdminStores();
		
	}
	
	@RequestMapping(value="/stores/{storeId}",method=RequestMethod.GET)
	public ResponseEntity<?> getStoreById(@PathVariable Long storeId){
		
		StoreDTO store=storesManager.getStoreById(storeId);
		if(store==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StoreDTO>(store,HttpStatus.OK);
	}
	
	@RequestMapping(value="/stores",method=RequestMethod.POST)
	public ResponseEntity<?> saveStore(@RequestBody StoreDTO store){
		
		StoreDTO result=storesManager.saveStore(store);
		if(result==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<StoreDTO>(result,HttpStatus.OK);
	}
	
	@RequestMapping(value="/stores/{storeId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateStore(@PathVariable Long storeId,@RequestBody StoreDTO store){
		
		StoreDTO result=storesManager.updateStore(store,storeId);
		if(result==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<StoreDTO>(result,HttpStatus.OK);
	}
	
	@RequestMapping(value="/stores/{storeId}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteStore(@PathVariable Long storeId){
		
		Boolean status=storesManager.deleteStore(storeId);
		if(status==false){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/stores/{storeId}/catalogs",method=RequestMethod.GET)
	public List<CatalogDTO> getStoreCatalogs(@PathVariable Long storeId){
		
		List<CatalogDTO> catalogs=storesManager.getStoreCatalogs(storeId);
		
		
		return catalogs;
	}
	@RequestMapping(value="/stores/{storeId}/catalogs-categories",method=RequestMethod.GET)
	public List<CatalogDTO> getStoreCatalogsWithCategories(@PathVariable Long storeId){
		
		List<CatalogDTO> catalogs=storesManager.getStoreCatalogsWithCategories(storeId);
		
		return catalogs;
	}
}
