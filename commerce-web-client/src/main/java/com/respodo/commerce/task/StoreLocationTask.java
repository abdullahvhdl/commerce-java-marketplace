package com.respodo.commerce.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.respodo.commerce.service.LocationService;

@Controller
public class StoreLocationTask {

	@Autowired
	private LocationService locationService;

	@Scheduled(cron = "0 0 */1 * * ?")
	public void updateStoresLocation() {
		locationService.updateStoresLocation();

	}
}
