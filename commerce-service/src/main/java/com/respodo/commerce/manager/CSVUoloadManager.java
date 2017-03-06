package com.respodo.commerce.manager;

import org.springframework.web.multipart.MultipartFile;

public interface CSVUoloadManager {

	void uploadStoresCSV(MultipartFile file);

}
