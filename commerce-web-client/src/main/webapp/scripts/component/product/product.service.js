'use strict';

angular.module('commerceApp').factory(
		'Product',
		function($q, $http) {
			var apiurl = 'api/products/', myData;
			return {
				getProductsByCategory : function(storeId, categoryId,page,limit) {
					return $http.get(apiurl + storeId + '/byCategory/' + categoryId,{
				        params: {
				            page: page,
				            limit: limit
				        }});
				},
				getProductById : function(storeId, productId) {
					return $http.get(apiurl + storeId + '/' + productId);
				},getProductsByIds : function(productIds) {
					return $http.get(apiurl +  'getProductsByIds',{
						params: {
				            id: productIds
				        }
					});
				},
				getRelativeStores : function(storeId, productId,location,distance) {
					return $http.get(
							apiurl + "relativeStores/" + storeId + '/'
							+ productId, {
						params : {
							lat : location.latitude,
							lng : location.longitude,
							distance : distance
						}
					});
				}

			};
		});
