'use strict';

angular.module('commerceApp').controller(
		'StoreNavbarController',
		function($scope, $location,$rootScope,$timeout, $state, $stateParams, Auth, Principal,
				Category,Map) {
			$rootScope.address = Map.getStoredAddress();

			//$rootScope.address = $rootScope.address;
			$scope.locationSuggestions = [];
			$scope.locateMe = function() {
				Map
						.locateMe()
						.then(
								function(position) {
									$rootScope.address = {};
									$rootScope.address.latlng = {};
									$rootScope.address.latlng.latitude = position.coords.latitude;
									$rootScope.address.latlng.longitude = position.coords.longitude;
									var latlng = new google.maps.LatLng(
											$rootScope.address.latlng.latitude,
											$rootScope.address.latlng.longitude);
									getAddress(latlng);

								});
			};
			if (!$rootScope.address) {
				$rootScope.address={"latlng":{"latitude":37.09024,"longitude":-95.71289100000001},"formatted_address":"United States"}
				$scope.locateMe();
			}
			$scope.clearSuggestions = function() {
				$timeout(function() {
					$scope.locationSuggestions = [];
				}, 500);

			};
			$scope.setAddress = function(location) {
				$rootScope.address.formatted_address = location.formatted_address;
				$rootScope.address.latlng = {};
				$rootScope.address.latlng.latitude = location.geometry.location
						.lat();
				$rootScope.address.latlng.longitude = location.geometry.location
						.lng();
				Map.saveAddress($rootScope.address);
			};
			$scope
					.$watch(
							'address.formatted_address',
							function() {
								Map
										.getLatLng(
												$rootScope.address.formatted_address)
										.then(
												function(data) {
													if (data) {
														if (data.length == 1
																&& data[0].formatted_address == $rootScope.address.formatted_address) {
															$scope.locationSuggestions = [];
															return;
														}
														$scope.locationSuggestions = data;

													} else {
														$scope.locationSuggestions = [];
														// TODO alert
														// address
														// cannot found
													}
												});
							});

			function getAddress(latlng) {
				Map
						.getAddress(latlng)
						.then(
								function(data) {
									if (data) {
										$rootScope.address.formatted_address = data[0].formatted_address;
										Map.saveAddress($rootScope.address);
									} else {
										// TODO alert address cannot
										// found
									}
									$scope.locationSuggestions = [];
								});
			}
			$scope.isAuthenticated = Principal.isAuthenticated;
			$scope.$state = $state;
			$scope.categories;
			$scope.searchQuery = "";
			
			if ($stateParams.storeId) {
				// TODO check if the store ID exists
				Category.getCategories($stateParams.storeId).then(
						function(data) {
							$scope.categories = data;
						});
			}
			
			$scope.logout = function() {
				Auth.logout();
				$state.go('home');
			};


			$scope.serachProducts = function() {
				
				$state.go('search',{query:$scope.searchQuery});
			};

			// $scope.$watch('categories',function(){
			//				
			//				
			// });
			 $scope.$on('$domready', function () {
				 Journal.setupMenuGeneral();
			 });

			$scope.$on('$viewContentLoaded', function(event) {
				//$(window).resize();
				//Journal.init();
			});

		});
