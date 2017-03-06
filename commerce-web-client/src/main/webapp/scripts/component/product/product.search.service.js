'use strict';

angular.module('commerceApp')
    .factory('ProductSearch', function ($q,$http) {
    	var apiurl = 'api/_search/products', myData;
		
		return {
			searchProducts : function(query,address,page,limit) {
				var deferred = $q.defer();
				$http.get(apiurl +'?query='+  query+'&lat='+address.latlng.latitude+'&lng='+address.latlng.longitude+'&page='+page+'&limit='+limit)
						.success(function(data, status, config, headers) {
							deferred.resolve(data);
						}).error(function() { // handler errors here
						});
				
				return deferred.promise;
			}

		};
    });
