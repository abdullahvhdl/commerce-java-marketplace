package com.respodo.commerce.service.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.respodo.commerce.constants.Metrics;

@Component
public class DistanceCalculator {

	public Double distance(double lat1, double lon1, double lat2, double lon2,
			Metrics unit) {
		Double dLat = Math.toRadians(lat2-lat1);
		Double dLon = Math.toRadians(lon2-lon1);
		Double xlat1 = Math.toRadians(lat1);
		Double xlat2 = Math.toRadians(lat2);

		Double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		        Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(xlat1) * Math.cos(xlat2); 
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		Double dist=c;
		
		if (unit == Metrics.KILOMETER) {
			dist = dist * 6371;
		} else if (unit == Metrics.MILE) {
			dist = dist * 3959;
		}
		Long factor =  (long) 100;
		dist = dist * factor;
	    long tmp = Math.round(dist);
	    dist= ((double)tmp/factor);
		return (dist);
	}

}
