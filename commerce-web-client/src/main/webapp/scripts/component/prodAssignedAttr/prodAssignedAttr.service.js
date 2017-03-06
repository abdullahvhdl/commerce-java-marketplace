'use strict';

angular.module('commerceApp')
    .factory('ProdAssignedAttr', function ($q,$http) {
    	var apiurl = 'api/product/', myData;
    	return {
    		
			getAssignedAttrs : function(productId) {
				var deferred = $q.defer();
				$http.get(apiurl + 'getAssignedAttrs/' + productId)
						.success(function(data, status, config, headers) {
							deferred.resolve(data);
						}).error(function() { // handler errors here
						});
				
				return deferred.promise;
			}

		};
    });
