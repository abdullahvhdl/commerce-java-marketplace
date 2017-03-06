'use strict';

angular
		.module('commerceApp')
		.controller(
				'CategoryController',
				function($scope, $routeParams,$route, $timeout, Category, Product) {
					$scope.storeName = $routeParams.storeName;
					$scope.storeId = $routeParams.storeId;
					$scope.category = {};
					$scope.products = [];
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
					if ($routeParams.catId) {
						Category
								.getCategoryById($routeParams.catId)
								.then(
										function(result) {
											$scope.category = result.data;
											$timeout(
													function() {
														var opts = $
																.parseJSON('[[0,3],[470,4],[760,4],[980,6],[1100,7]]');
														jQuery("#refine-images")
																.owlCarousel(
																		{
																			itemsCustom : opts,
																			autoPlay : false,
																			touchDrag : false,
																			stopOnHover : false,
																			navigation : true,
																			scrollPerPage : true,
																			navigationText : false,
																			paginationSpeed : 400,
																			margin : 13
																		});
														Journal
																.equalHeight(
																		$("#refine-images .refine-image"),
																		'.refine-category-name');
													}, 200);
										});
						Product.getProductsByCategory($routeParams.storeId,
								$routeParams.catId,$scope.page,$scope.limit).then(function(result) {
								
							$scope.products = result.data;
						});

					}
				});
