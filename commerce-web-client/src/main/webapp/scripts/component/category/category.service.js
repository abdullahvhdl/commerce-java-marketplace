'use strict';

angular.module('commerceApp').factory(
		'Category',
		function($http, $q) {
			var apiurl = 'api/categories/', myData;
			return {
				getCategories : function(storeId) {
					return $http.get(apiurl + 'byStore/' + storeId);
				},
				getCategoryById : function(categoryId) {
					return $http.get(apiurl  + categoryId);
				}
			};
		});
