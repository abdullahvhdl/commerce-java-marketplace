package com.respodo.commerce.service;

import org.springframework.scheduling.annotation.Async;

public interface LocationService {

	@Async
	void updateStoresLocation();

	@Async
	void updateStoreLocation(Long storeId);

}
