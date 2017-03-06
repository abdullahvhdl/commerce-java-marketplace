'use strict';
(function() {

	angular
			.module('commerceApp')
			.controller(
					'StoresController',
					function($scope, $rootScope, $timeout, $compile,
							Store, Map) {
						var markers = [];
						$scope.stores = [];
						$scope.maximumDistance = 30;

						var latlng = new google.maps.LatLng(
								$rootScope.address.latlng.latitude,
								$rootScope.address.latlng.longitude);
						var infowindow = new google.maps.InfoWindow();
						$scope.store = {};
						var mapOptions = {
							scrollwheel : false,
							zoom : 12,
							center : latlng,
							mapTypeId : google.maps.MapTypeId.ROADMAP
						};
						var map = new google.maps.Map(document
								.getElementById("map_canvas"), mapOptions);
						$scope.showWindow = function(store) {
							for ( var index in markers) {
								var marker = markers[index];
								if (store.id == marker.store.id) {

									showContent(marker);
									break;
								}
							}
							highLightStore(store);
						};
						loadAllStores();

						$scope.$watch('maximumDistance', function() {
							loadAllStores();
							Map.setZoomLevel(map, $scope.maximumDistance);
						});
						$rootScope.$watch('address.latlng', function() {
							loadAllStores();
							latlng = new google.maps.LatLng(
									$rootScope.address.latlng.latitude,
									$rootScope.address.latlng.longitude);
							map.setCenter(latlng);

						});

						function loadAllStores() {

							Store.getStoresByLocation($rootScope.address.latlng,
									$scope.maximumDistance).then(
									function(result) {
										$scope.stores = result.data;
										Map.clearOverlays(markers);
										markers = [];
										$scope.stores.sort(function(a, b) {
											return a.distance - b.distance
										});
										addMarkersToMap();
									});

						}
						function addMarkersToMap() {
							for (var index = 0; index < $scope.stores.length; index++) {
								var store = $scope.stores[index];
								var myLatlng = new google.maps.LatLng(
										store.location.latitude,
										store.location.longitude);

								var marker = new google.maps.Marker(
										{
											icon : 'https://chart.googleapis.com/chart?chst=d_map_pin_letter&chld='
													+ (index + 1)
													+ '|FF776B|000000',
											position : myLatlng,
											map : map,
											title : store.name
										});
								marker.store = store;
								markers.push(marker);
								google.maps.event
										.addListener(
												marker,
												'click',
												function() {
													showContent(this);
													var store = this.store;
													$('.store-list-container')
															.animate(
																	{
																		scrollTop : $(
																				'#'
																						+ store.uniqueId)
																				.parent()
																				.scrollTop()
																				+ $(
																						'#'
																								+ store.uniqueId)
																						.offset().top
																				- $(
																						'#'
																								+ store.uniqueId)
																						.parent()
																						.offset().top
																	}, 'slow');
												});
							}

						}
						function showContent(marker) {
							$timeout(
									function() {
										infowindow.close();
										var store = marker.store;
										var href = store.name+'-'+store.id;
										highLightStore(store);
										var content = '<div id="infowindow_content" ng-include src="\'scripts/app/template/store-list-content.tpl.html\'"></div>';
										infowindow.setContent(content);
										infowindow.open(map, marker);
										var infowindow_content = document
												.getElementById('infowindow_content');
										var local_scope = angular.element(
												infowindow_content).scope();

										$compile(infowindow_content)(
												local_scope);
										local_scope.store = store;
										local_scope.$apply();
									}, 0);

						}
						function highLightStore(store) {
							$('.store-list-item').removeClass('active');
							$('#' + store.uniqueId).addClass('active');
						}

					});
}());
