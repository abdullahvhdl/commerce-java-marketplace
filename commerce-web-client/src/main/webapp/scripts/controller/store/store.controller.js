'use strict';

angular.module('commerceApp').controller('StoreController',
		function($scope, Principal, Category) {
			Principal.identity().then(function(account) {
				$scope.account = account;
				$scope.isAuthenticated = Principal.isAuthenticated;

			});
		});
