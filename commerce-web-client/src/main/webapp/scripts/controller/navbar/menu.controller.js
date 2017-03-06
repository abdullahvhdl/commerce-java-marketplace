'use strict';

angular.module('commerceApp').controller(
		'MenuController',
		function($scope,$routeParams, Category) {
			$scope.categories;
			$scope.storeName=$routeParams.storeName;
			$scope.storeId=$routeParams.storeId;
			if ($routeParams.storeId) {
				// TODO check if the store ID exists
				Category.getCategories($routeParams.storeId).then(
						function(result) {
							$scope.categories = result.data;
						});
			}

		});
