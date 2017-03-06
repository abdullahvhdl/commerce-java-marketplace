package com.respodo.commerce.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ParserService {

	void parseCSVFile(MultipartFile file);

	void parseCSVFileForStore(Long storeId, MultipartFile file);

	void parseCSVFileForCatalog(Long storeId, Long catalogId, MultipartFile file);
	
	<T> List<T> parseCSVFile(MultipartFile file,Class<T> type);

}
