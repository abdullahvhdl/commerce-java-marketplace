package com.respodo.commerce.service.util;

import com.respodo.commerce.constants.Metrics;

public class LocationUtil {
	
	private Double lat;
	
	private Double lng;
	
	private Integer distance;
	
	private Metrics metric;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Metrics getMetric() {
		return metric;
	}

	public void setMetric(Metrics metric) {
		this.metric = metric;
	}

	
}
