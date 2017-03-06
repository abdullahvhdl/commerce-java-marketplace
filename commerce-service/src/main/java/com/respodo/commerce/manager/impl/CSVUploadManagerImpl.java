package com.respodo.commerce.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.respodo.commerce.dto.csv.StoreCSVDTO;
import com.respodo.commerce.manager.CSVUoloadManager;
import com.respodo.commerce.service.LocationService;
import com.respodo.commerce.service.ParserService;
import com.respodo.commerce.service.StoreService;

@Service
public class CSVUploadManagerImpl implements CSVUoloadManager {
	
	@Autowired
	private ParserService parserService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private LocationService locationService;

	@Override
	public void uploadStoresCSV(MultipartFile file) {
		List<StoreCSVDTO> stores=parserService.parseCSVFile(file,StoreCSVDTO.class);
		stores.forEach(storeCSV->{
			storeService.saveStore(storeCSV);
		});
		locationService.updateStoresLocation();
		
	}

}
