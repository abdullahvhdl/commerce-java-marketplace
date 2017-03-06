'use strict';

angular
		.module('commerceApp')
		.controller(
				'NavbarController',
				function($scope, $rootScope, Map, $timeout, $location, Auth,
						Principal) {
					$scope.currency = {
						allCurrencies : [ {
							name : "US Dollar",
							code : "USD",
							symbol : "$"
						} ],
						currentCurrency : {
							name : "US Dollar",
							code : "USD",
							symbol : "$"
						}
					};
					$scope.language = {
						allLanguages : [ {
							name : "English",
							code : "en",
							flag : "assets/images/flags/us.png"
						} ],
						currentLanguage : {
							name : "English",
							code : "en",
							flag : "assets/images/flags/us.png"
						}
					};
					$scope.searchQuery = "";
					$scope.serachProducts = function() {
						$location.url('search?query='+$scope.searchQuery);
					};
					$scope.address = $rootScope.address.formatted_address;
					$scope.locationSuggestions = [];
					$scope.clearSuggestions = function() {
						$timeout(function() {
							$scope.locationSuggestions = [];
						}, 500);

					};
					$rootScope.$watch('address.formatted_address', function() {
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
													.getLatLng($scope.address)
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
					$scope.logout = function() {
						Auth.logout();
						$location.url("/");
					};
					$scope.$on('$routeChangeSuccess', function(event, current) {
						$timeout(function() {
							initMenu();
						}, 300);

					});

				});
function initMenu() {
	var $outer = $('<div>').css({
		visibility : 'hidden',
		width : 100,
		overflow : 'scroll'
	}).appendTo('body'), widthWithScroll = $('<div>').css({
		width : '100%'
	}).appendTo($outer).outerWidth();
	$outer.remove();
	var scrollWidth = 100 - widthWithScroll;

	var last = null, current = null;

	if ($('html').hasClass('responsive-layout')) {
		/* add resize event */
		var width = $(window).width() + scrollWidth;
		if (width <= 760) {
			current = 'mobile';
		} else if (width <= 980) {
			current = 'tablet';
		} else {
			current = 'desktop';
		}
		if (last !== current) {
			last = current;
			switch (current) {
			case 'mobile':
				Journal.onMobile();
				break;
			case 'tablet':
				Journal.onTablet();
				break;
			case 'desktop':
				Journal.onDesktop();
				break;
			}
		}
	} else {
		Journal.onDesktop();
	}
	$('.journal-menu .drop-down').each(function() {
		$('ul', this).css('min-width', $(this).width());
	});
}