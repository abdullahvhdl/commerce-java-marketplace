'use strict';

angular
		.module(
				'commerceApp',
				[ 'ngRoute','ngResource', 'ngMessages', 'LocalStorageModule', 'ngCookies',
						'ngCacheBuster' ])

		.run(
				function($rootScope, $location, $window, $http, Map, Auth,
						Principal) {
					$rootScope.language = "en";
					$rootScope.currency = "USD";
					$rootScope.getLocaledValue = function(list) {
						for ( var index in list) {
							var value = list[index];
							if (value.language.locale == $rootScope.language) {
								return value;
							}
						}
					};
					$rootScope.getPrice = function(prices) {
						for ( var index in prices) {
							var price = prices[index];
							if (price.currency == $rootScope.currency) {
								return price;
							}
						}
					};
					$rootScope.isAuthenticated = function() {
						return Principal.isAuthenticated();
					};
					$rootScope.address = Map.getStoredAddress();
					$rootScope.getAccount = function() {
						return Principal.identity(true);
					};
					$rootScope.getAccount();
					$rootScope.locateMe = function() {
						$http
								.get('http://www.telize.com/geoip')
								.success(
										function(location) {
											$rootScope.address = {};
											$rootScope.address.latlng = {};
											$rootScope.address.latlng.latitude = location.latitude;
											$rootScope.address.latlng.longitude = location.longitude;
											var latlng = new google.maps.LatLng(
													$rootScope.address.latlng.latitude,
													$rootScope.address.latlng.longitude);
											getAddress(latlng);
										});
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
						$rootScope.address = {
							"latlng" : {
								"latitude" : 37.09024,
								"longitude" : -95.71289100000001
							},
							"formatted_address" : "United States"
						}

						$rootScope.locateMe();
					}
					function getAddress(latlng) {
						Map
								.getAddress(latlng)
								.then(
										function(data) {
											if (data) {
												$rootScope.address.formatted_address = data[0].formatted_address;
												Map
														.saveAddress($rootScope.address);
											} else {
												// TODO alert address cannot
												// found
											}
										});
					}

				})
		.factory(
				'authInterceptor',
				function($rootScope, $q, $location, localStorageService) {
					return {
						// Add authorization token to headers
						request : function(config) {
							config.headers = config.headers || {};
							var token = localStorageService.get('token');

							if (token && token.expires_at
									&& token.expires_at > new Date().getTime()) {
								config.headers.Authorization = 'Bearer '
										+ token.access_token;
							}

							return config;
						}
					};
				})
		.factory(
				'authExpiredInterceptor',
				function($rootScope, $q, $injector, localStorageService) {
					return {
						responseError : function(response) {
							// token has expired
							if (response.status === 401
									&& (response.data.error == 'invalid_token' || response.data.error == 'Unauthorized')) {
								localStorageService.remove('token');
								var Principal = $injector.get('Principal');
								if (Principal.isAuthenticated()) {
									var Auth = $injector.get('Auth');
									Auth.authorize(true);
								}
							}
							return $q.reject(response);
						}
					};
				})
		.config(
				function($httpProvider, $locationProvider,
						localStorageServiceProvider,
						httpRequestInterceptorCacheBusterProvider,
						$routeProvider) {
					localStorageServiceProvider
							.setStorageType('sessionStorage');
					// Cache everything except rest api requests
					httpRequestInterceptorCacheBusterProvider.setMatchlist([
							/.*api.*/, /.*protected.*/ ], true);
					$httpProvider.interceptors.push('authInterceptor');
					$httpProvider.interceptors.push('authExpiredInterceptor');
					// Initialize angular-translate
					// $routeProvider.html5Mode(true);
					$routeProvider
							.when('/', {
								templateUrl : 'home',
							})
							.when('/stores', {
								templateUrl : 'stores',
							})
							.when('/search', {
								templateUrl : 'search',
							})
							.when('/login', {
								templateUrl : 'login',
							})
							.when('/register', {
								templateUrl : 'register',
							})
							.when('/activate', {
								templateUrl : 'activate',
							})
							.when('/account', {
								templateUrl : 'account',
							})
							.when(
									'/:storeName-:storeId',
									{
										templateUrl : function(urlattr) {
											return urlattr.storeName + '-'
													+ urlattr.storeId;
										},
									})
							.when(
									'/:storeName-:storeId/category/:catName-:catId',
									{
										templateUrl : function(urlattr) {
											return urlattr.storeName + '-'
													+ urlattr.storeId
													+ '/category/'
													+ urlattr.catName + '-'
													+ urlattr.catId;
										},
									})
							.when(
									'/:storeName-:storeId/product/:productName-:productId',
									{
										templateUrl : function(urlattr) {
											return urlattr.storeName + '-'
													+ urlattr.storeId
													+ '/product/'
													+ urlattr.productName + '-'
													+ urlattr.productId;
										},
									}).otherwise({
								redirectTo : '/'
							});
				}).filter('cut', function() {
			return function(value, wordwise, max, tail) {
				if (!value)
					return '';

				max = parseInt(max, 10);
				if (!max)
					return value;
				if (value.length <= max)
					return value;

				value = value.substr(0, max);
				if (wordwise) {
					var lastspace = value.lastIndexOf(' ');
					if (lastspace != -1) {
						value = value.substr(0, lastspace);
					}
				}

				return value + (tail || ' …');
			};
		});
