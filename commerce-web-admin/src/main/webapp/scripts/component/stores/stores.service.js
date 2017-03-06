'use strict';

angular.module('commerceAdmin').factory(
		'Stores',
		function($http, $q) {
			var apiurl = 'api/', myData;

			return {

				getAllStores : function() {
					var deferred = $q.defer();
					$http.get(apiurl + 'stores').success(function(data, status, config, headers) {
						deferred.resolve(data);
					}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				getStore : function(storeId) {
					var deferred = $q.defer();
					$http.get(apiurl + 'stores/' + storeId).success(
							function(data, status, config, headers) {
								deferred.resolve(data);
							}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				saveStore : function(store) {
					var deferred = $q.defer();
					$http.post(apiurl + 'stores', store).success(
							function(data, status, config, headers) {
								deferred.resolve();
							}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				updateStore : function(store,storeId) {
					var deferred = $q.defer();
					$http.put(apiurl + 'stores/'+storeId, store).success(
							function(data, status, config, headers) {
								deferred.resolve();
							}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				deleteStore : function(storeId) {
					var deferred = $q.defer();
					$http.delete(apiurl + 'stores/'+storeId).success(
							function(data, status, config, headers) {
								deferred.resolve();
							}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				getCatalogs : function(storeId) {
					var deferred = $q.defer();
					$http.get(apiurl + 'stores/' + storeId+'/catalogs').success(
							function(data, status, config, headers) {
								deferred.resolve(data);
							}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				getCatalogsWithCategories : function(storeId) {
					var deferred = $q.defer();
					$http.get(apiurl + 'stores/' + storeId+'/catalogs-categories').success(
							function(data, status, config, headers) {
								deferred.resolve(data);
							}).error(function() { // handler errors here
					});
					return deferred.promise;
				}
			};
		});
