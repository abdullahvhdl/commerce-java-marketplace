package com.respodo.commerce.service.impl;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.respodo.commerce.dto.google.GLocation;
import com.respodo.commerce.dto.google.GoogleResponse;
import com.respodo.commerce.repository.dao.LocationRepository;
import com.respodo.commerce.repository.dao.StoreRepository;
import com.respodo.commerce.repository.domain.Address;
import com.respodo.commerce.repository.domain.Location;
import com.respodo.commerce.repository.domain.Store;
import com.respodo.commerce.service.LocationService;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private LocationRepository locationRepository;

	private static final String URL = "http://maps.googleapis.com";
	private static final String PATH = "/maps/api/geocode/json";

	@Override
	public void updateStoresLocation() {
		List<Store> newStores = storeRepository.findByLocationIsNull();
		for (Store store : newStores) {
			updateLocation(store);
		}
	}

	@Override
	public void updateStoreLocation(Long storeId) {
		Store store=storeRepository.findOne(storeId);
		if(store !=null){
			updateLocation(store);
		}
		
	}
	
	private void updateLocation(Store store){
		RestTemplate restTemplate = new RestTemplate();
		Address address = store.getAddress();
		String addressString = address.getAddress1() + " "
				+ address.getAddress2() + ", " + address.getCity() + ", "
				+ address.getState() + ", " + address.getZipCode() + ", "
				+ address.getCountry();
		URI targetUrl = UriComponentsBuilder.fromUriString(URL).path(PATH)
				.queryParam("address", addressString).build().toUri();
		GoogleResponse response = restTemplate.getForObject(targetUrl,
				GoogleResponse.class);
		Location location = new Location();
		if (response.getResults().length > 0
				&& response.getResults()[0] !=null  &&response.getResults()[0].getGeometry() != null
				&& response.getResults()[0].getGeometry().getLocation() != null) {
			GLocation gLocation = response.getResults()[0].getGeometry().getLocation();
			location.setLatitude(gLocation.getLat());
			location.setLongitude(gLocation.getLng());
			store.setLocation(location);
			locationRepository.save(location);
			storeRepository.save(store);
			
			
		}
	}
}
