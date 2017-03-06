package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.LocationDTO;
import com.respodo.commerce.repository.domain.Location;

@Component
public class LocationMapper {

	public LocationDTO toLocationDTO(Location location) {
		if(location==null){
			return null;
		}
		LocationDTO locationDTO=new LocationDTO();
		locationDTO.setLatitude(location.getLatitude());
		locationDTO.setLongitude(location.getLongitude());
		return locationDTO;
	}

}
