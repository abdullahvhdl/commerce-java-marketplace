'use strict';

angular.module('commerceApp').controller('StoreMenuController',
		function($scope,Category,$location) {
			$scope.categories;
			Category.getCategories(10000).then(function(data) {
				$scope.categories = data;
			});

			$scope.$on('$domready', function() {
				initMenu();
			});

		});
