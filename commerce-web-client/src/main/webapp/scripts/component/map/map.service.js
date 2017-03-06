angular
		.module('commerceApp')
		.factory(
				'Map',
				function($q,localStorageService) {
					var geocoder = new google.maps.Geocoder();
					return {
						setZoomLevel : function(map, distance) {
							if (distance == 30) {
								map.setZoom(10);
							} else if (distance == 50) {
								map.setZoom(9);
							} else if (distance == 100) {
								map.setZoom(8);
							} else if (distance == 250) {
								map.setZoom(7);
							}

						},
						clearOverlays : function(markers) {
							for ( var index in markers) {
								var marker = markers[index];
								marker.setMap(null);
							}
						},
						getAddress : function(latlng) {
							var deferred = $q.defer();
							var location = {
								location : latlng
							};
							geocoder
									.geocode(
											location,
											function(results, status) {
												if (status == google.maps.GeocoderStatus.OK) {
													deferred
															.resolve(results);
												} else {
													deferred.resolve(null);
												}
											});
							return deferred.promise;

						},
						getLatLng : function(address) {
							var deferred = $q.defer();
							var pos = {
								address : address
							};
							geocoder
									.geocode(
											pos,
											function(results, status) {

												if (status == google.maps.GeocoderStatus.OK) {
													deferred
															.resolve(results);
												} else {
													deferred.resolve(null);
												}
											});
							return deferred.promise;
						},
						locateMe : function() {
							var deferred = $q.defer();
							if (navigator.geolocation) {
								navigator.geolocation
										.getCurrentPosition(function(position) {
											deferred.resolve(position);
										});
							}
							return deferred.promise;
						},
						saveAddress :function(address){
							localStorageService.set('current-address',address);
						},
						getStoredAddress :function(){
							return localStorageService.get('current-address');
						}
					};
				});
