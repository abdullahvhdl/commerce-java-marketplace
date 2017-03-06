'use strict';
(function() {

	angular
			.module('commerceApp')
			.controller(
					'ProductController',
					function($scope, $rootScope,$routeParams,  $compile,
							$timeout,  Product, Category, ProductSearch,
							ProdAssignedAttr, Map, Principal, Order) {
						$scope.storeId=$routeParams.storeId;
						$scope.categoryId=$routeParams.catId;
						$scope.productId=$routeParams.productId;
						$scope.success = null;
						$scope.error = null;
						$scope.account = {};
						$scope.product = {
							buyableAttrs : [],
							displayableAttrs : []
						};
						$scope.cartProduct = {
							quantity : 1
						};
						$scope.relativeStores = [];
						$scope.Math = window.Math;
						$scope.selectedProduct = {};
						$scope.Math = window.Math;
						$scope.increaseQty = function() {
							$scope.cartProduct.quantity++;
						};
						$scope.decreaseQty = function() {
							$scope.cartProduct.quantity--;
							if ($scope.cartProduct.quantity < 1) {
								$scope.cartProduct.quantity = 1;
							}
						};
						$scope.addToCart = function() {
							if (Principal.isAuthenticated()) {
								$scope.getAccount().then(function(data) {
									$scope.account = data;
								});
							}
							if($scope.product.buyableAttributes.length==0){
								$scope.selectedProduct=$scope.product.items[0];
							}
							$.magnificPopup.open({
								alignTop : true,
								removalDelay : 200,
								closeOnBgClick : false,
								items : {
									src : '#cart-popup',
									type : 'inline',

								},
								callbacks : {
									close : function() {
										$scope.account = {};
										$scope.success = null;
										$scope.error = null;
									}
								}
							});

						};
						$scope.registerOrder = function() {
							var order = {};
							order.quantity = $scope.cartProduct.quantity;
							order.productId = $scope.selectedProduct.id;
							order.storeId = $scope.storeId;
							order.phoneNumber = $scope.account.phoneNumber;
							Order.registerOrder(order).then(function(data) {
								$scope.success = true;
							});
						};
						$scope.quickOrderSubmit = function() {
							var order = {};
							order.quantity = $scope.cartProduct.quantity;
							order.productId = $scope.selectedProduct.id;
							order.storeId = $scope.storeId;
							order.phoneNumber = $scope.account.phoneNumber;
							order.firstName = $scope.account.firstName;
							order.lastName = $scope.account.lastName;
							order.email = $scope.account.email;
							Order.quickOrder(order).then(function(data) {
								$scope.success = true;
							});
						};
						$scope.cancelOrder = function() {
							$.magnificPopup.close();
						};
						$scope.findSelectedItem = function() {
							var allItems = $scope.product.items;
							// for(var index in $scope.product.items){
							// allItems.push($scope.product.items[index].id);
							// }
							for ( var index in $scope.product.buyableAttributes) {
								var prodAssignedAttr = $scope.product.buyableAttributes[index];

								if (prodAssignedAttr.selectedValue) {
									var valueItems = JSON
											.parse(prodAssignedAttr.selectedValue).items;
									allItems = allItems.filter(function(el) {
										return valueItems.indexOf(el.id) != -1;
									});
								}

							}
							if (allItems.length == 1) {
								$scope.selectedProduct = allItems[0];
							} else {
								$scope.selectedProduct = {};
							}
						};
						if ($scope.productId) {
							Product.getProductById($scope.storeId,
									$scope.productId).then(
									function(result) {
										$scope.product = result.data;
										fetchAssignedAttrs($scope.product);
										$timeout(function(){
												var opts = {
													itemsCustom : [ [ 0, parseInt('4', 10) ],
															[ 470, parseInt('4', 10) ],
															[ 760, parseInt('4', 10) ],
															[ 980, parseInt('4', 10) ],
															[ 1100, parseInt('4', 10) ] ],
													navigation : true,
													scrollPerPage : true,
													navigationText : false,
													stopOnHover : true,
													cssAnimation : false,
													paginationSpeed : 300,
													margin : parseInt('15', 10)
												};
												opts.autoPlay = parseInt('3000', 10);
												opts.stopOnHover = true;
												jQuery("#product-gallery").owlCarousel(opts);
												$('#product-gallery .owl-buttons').addClass(
														'side-buttons');
												Journal.productPage();
												Journal.productPageGallery();
										},300);
										// addMarkersToMap();
									});

						}


						/*
						 * releated products script
						 */

						var markers = [];
						$scope.maximumDistance = 30;

						var latlng = new google.maps.LatLng(
								$rootScope.address.latlng.latitude,
								$rootScope.address.latlng.longitude);
						var infowindow = new google.maps.InfoWindow();
						var mapOptions = {
							scrollwheel : false,
							zoom : 12,
							center : latlng,
							mapTypeId : google.maps.MapTypeId.ROADMAP
						};
						var map = new google.maps.Map(document
								.getElementById("map_canvas"), mapOptions);
						function loadRelativeStores() {
							if ($scope.productId) {
								Product.getRelativeStores($scope.storeId,
										$scope.productId,
										$rootScope.address.latlng,
										$scope.maximumDistance).then(
										function(result) {
											$scope.relativeStores = result.data;
											$scope.relativeStores.sort(function(a, b) {
												return a.distance - b.distance
											});
											addMarkersToMap();
										});
							}
						}
						loadRelativeStores();
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
						$scope.$watch('maximumDistance', function() {
							loadRelativeStores();
							Map.setZoomLevel(map, $scope.maximumDistance);
						});
						$rootScope.$watch('address.latlng', function() {
							loadRelativeStores();
							latlng = new google.maps.LatLng(
									$rootScope.address.latlng.latitude,
									$rootScope.address.latlng.longitude);
							map.setCenter(latlng);

						});
						function addMarkersToMap() {
							for (var index = 0; index < $scope.relativeStores.length; index++) {
								var store = $scope.relativeStores[index];
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
								google.maps.event.addListener(marker, 'click',
										function() {
											showContent(this);
										});
							}

						}
						function showContent(marker) {
							$timeout(
									function() {
										infowindow.close();
										var store = marker.store;
										var href ='#';
										highLightStore(store);
										var content = '<div id="infowindow_content" ng-include src="\'scripts/app/template/relative-store-list-content.tpl.html\'"></div>';
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
	function indexOf(array, object) {
		for ( var index in array) {
			if (array[index].id == object.id) {
				return index;
			}
		}
		return -1;
	}
	function getAssignedAttribute(attrs, id) {
		for ( var index in attrs) {
			if (attrs[index].attribute.id == id) {
				return attrs[index];
			}
		}

	}
	function fetchAssignedAttrs(product) {
		for ( var index in product.items) {
			var item = product.items[index];
			for ( var i in item.buyableAttributes) {

				var productAttribute = item.buyableAttributes[i];
				productAttribute.attributeValue.items = [];
				var buyableAttribute = getAssignedAttribute(
						product.buyableAttributes,
						productAttribute.attribute.id);
				if (!buyableAttribute.attributeValues) {
					buyableAttribute.attributeValues = [];
				}
				var valueIndex = indexOf(buyableAttribute.attributeValues,
						productAttribute.attributeValue);
				if (valueIndex == -1) {
					productAttribute.attributeValue.items = [];
					productAttribute.attributeValue.items.push(item.id);
					buyableAttribute.attributeValues
							.push(productAttribute.attributeValue);
				} else {
					buyableAttribute.attributeValues[valueIndex].items
							.push(item.id);
				}

			}
		}
	}

}());
