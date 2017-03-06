'use strict';
(function() {
	angular.module('commerceApp').controller(
			'SearchController',
			function($scope,$rootScope, $routeParams,$route, ProductSearch) {
				$scope.products = [];
				$scope.searchQuery=$routeParams.query;
				$scope.Math = window.Math;
				$scope.page=$routeParams.page;
				$scope.limit=$routeParams.limit;
				$scope.changeLimit=function(limit){
					$route.updateParams({
						limit:limit
					});	
				};
				if(!$scope.page){
					$scope.page=0;
				}
				if(!$scope.limit){
					$scope.limit=16;
				}
				ProductSearch.searchProducts($routeParams.query,$rootScope.address,$scope.page,$scope.limit).then(
						function(result) {
							$scope.products = result;
							sortStores($scope.products.content);
						});
				
				function sortStores(products){
					for(var index in products){
						var product=products[index];
						
						product.stores.sort(function(a, b){return a.distance-b.distance});
						product.store=product.stores[0];
					}
				};

			});
	
}());
