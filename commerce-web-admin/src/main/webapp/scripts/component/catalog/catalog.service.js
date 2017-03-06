'use strict';

angular.module('commerceAdmin').factory(
		'Catalog',
		function($http, $q) {
			var apiurl = 'api/', myData;

			return {
				saveCatalog : function(storeId, catalog) {
					var deferred = $q.defer();
					$http.post(apiurl +'stores/'+storeId+'/catalog',catalog).success(function(data, status, config, headers) {
						deferred.resolve(data);
					}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				updateCatalog : function(storeId, catalog) {
					var deferred = $q.defer();
					$http.put(apiurl +'stores/'+storeId+'/catalog/'+catalog.id,catalog).success(function(data, status, config, headers) {
						deferred.resolve(data);
					}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				deleteCatalog : function(storeId, catalogId) {
					var deferred = $q.defer();
					$http.delete(apiurl +'stores/'+storeId+'/catalog/'+catalogId).success(function(data, status, config, headers) {
						deferred.resolve(data);
					}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				saveCategory : function(storeId, catalogId,category) {
					var deferred = $q.defer();
					$http.post(apiurl +'stores/'+storeId+'/catalog/'+catalogId+'/category',category).success(function(data, status, config, headers) {
						deferred.resolve(data);
					}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				updateCategory : function(storeId,catalogId, category) {
					var deferred = $q.defer();
					$http.put(apiurl +'stores/'+storeId+'/catalog/'+catalogId+'/category/'+category.id,category).success(function(data, status, config, headers) {
						deferred.resolve(data);
					}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				deleteCatalog : function(storeId, catalogId,categoryId) {
					var deferred = $q.defer();
					$http.delete(apiurl +'stores/'+storeId+'/catalog/'+catalogI+'/category/'+categoryId).success(function(data, status, config, headers) {
						deferred.resolve(data);
					}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				getCategoryProducts : function(storeId, categoryId) {
					var deferred = $q.defer();
					$http.get(apiurl +'stores/'+storeId+'/categories/'+categoryId+'/products').success(function(data, status, config, headers) {
						deferred.resolve(data);
					}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				getProductById:function(storeId,productId){
					var deferred = $q.defer();
					$http.get(apiurl +'stores/'+storeId+'/products/'+productId).success(function(data, status, config, headers) {
						deferred.resolve(data);
					}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				searchProducts:function(query){
					return $http.get(apiurl+'searchProduct',{params:{query:query}});
				},
				addProductToCategory:function(storeId,categoryId,productId){
					return $http.post(apiurl+'stores/'+storeId+'/categories/'+categoryId+'/addProduct/'+productId,{});
				},
				updateProduct:function(storeId,categoryId,product,productId){
					return $http.put(apiurl+'stores/'+storeId+'/categories/'+categoryId+"/products/"+productId,product);
				},
				saveProduct:function(storeId,categoryId,product){
					return $http.post(apiurl+'stores/'+storeId+'/categories/'+categoryId+"/products",product);
				},
				deleteProduct:function(storeId,categoryId,productId){
					return $http.delete(apiurl+'stores/'+storeId+'/categories/'+categoryId+"/products/"+productId,{});
				},
				generateItems:function(storeId,categoryId,productId){
					return $http.post(apiurl+'stores/'+storeId+'/categories/'+categoryId+"/products/"+productId+"/generateItems",{});
				},
				saveItem:function(storeId,categoryId,productId,item){
					return $http.post(apiurl+'stores/'+storeId+'/categories/'+categoryId+"/products/"+productId+"/saveItem",item);
				},
				updateItem:function(storeId,categoryId,productId,item,itemId){
					return $http.put(apiurl+'stores/'+storeId+'/categories/'+categoryId+"/products/"+productId+"/updateItem/"+itemId,item);
				}
			};
		});
