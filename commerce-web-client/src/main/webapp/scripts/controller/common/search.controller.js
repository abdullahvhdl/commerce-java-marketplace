'use strict';

angular
		.module('commerceApp')
		.controller(
				'SearchInputController',
				function($scope) {
					$scope.isAuthenticated = Principal.isAuthenticated;
					$scope.searchQuery = "";
					$scope.serachProducts = function() {
						
					};
					
				});
