'use strict';

angular.module('commerceApp').factory(
		'Order',
		function($q,$http) {
			var apiurl = 'api', myData;
			
			return {
				registerOrder : function(orderData) {
					var deferred = $q.defer();
					$http.post(apiurl + '/order',orderData)
							.success(function(data, status, config, headers) {
								deferred.resolve(data);
							}).error(function() { // handler errors here
							});
					
					return deferred.promise;
				},
				quickOrder : function(orderData) {
					var deferred = $q.defer();
					$http.post(apiurl + '/quickOrder',orderData)
							.success(function(data, status, config, headers) {
								deferred.resolve(data);
							}).error(function() { // handler errors here
							});
					
					return deferred.promise;
				}

			};
		});
