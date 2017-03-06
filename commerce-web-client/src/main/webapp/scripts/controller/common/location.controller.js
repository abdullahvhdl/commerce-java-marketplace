'use strict';

angular
		.module('commerceApp')
		.controller(
				'LocationController',
				function($scope, $timeout, $location, $rootScope,  Auth,
						Principal, Map) {
					
					$scope.address = $rootScope.address.formatted_address;
					$scope.locationSuggestions = [];
					$scope.clearSuggestions = function() {
						$timeout(function() {
							$scope.locationSuggestions = [];
						}, 500);

					};
					$rootScope.$watch('address.formatted_address',function(){
						$scope.address = $rootScope.address.formatted_address;
					});
					$scope.setAddress = function(location) {
						$rootScope.address.formatted_address = location.formatted_address;
						$rootScope.address.latlng = {};
						$rootScope.address.latlng.latitude = location.geometry.location
								.lat();
						$rootScope.address.latlng.longitude = location.geometry.location
								.lng();
						Map.saveAddress($rootScope.address);
						$scope.address = $rootScope.address.formatted_address;
					};
					$scope
							.$watch(
									'address',
									function() {
										if ($rootScope.address.formatted_address != $scope.address) {
											Map
													.getLatLng(
															$scope.address)
													.then(
															function(data) {
																if (data) {
																	
																	$scope.locationSuggestions = data;

																} else {
																	$scope.locationSuggestions = [];
																	// TODO
																	// alert
																	// address
																	// cannot
																	// found
																}
															});
										}

									});

					
					$scope.isAuthenticated = Principal.isAuthenticated;
				});
