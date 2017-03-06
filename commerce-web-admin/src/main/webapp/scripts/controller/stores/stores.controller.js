'use strict';
angular.module('commerceAdmin').controller(
		'StoresController',
		function($rootScope, $scope, Stores) {
			$scope.page=0;
			$scope.limit=20;
			$scope.totalNumberOfPages=0;
			$scope.stores = [];
			
			$scope.getStores = function() {
				Stores.getAllStores().then(
						function(data) {
							$scope.stores = data;
						});
			};
			$scope.setPage = function(page) {
				$scope.page = page;
			};
			$scope.getStores();

		});
angular.module('commerceAdmin').controller(
		'EditStoreController',
		function($rootScope, $scope, $q, $timeout, Stores, Upload) {

			$scope.store = {};
			$scope.store.image = {};
			$scope.storeId;
			$scope.catalogs = [];
			$scope.getStore = function() {
				Stores.getStore($scope.storeId).then(function(data) {
					$scope.store = data;
					
					$timeout(function() {
						altair_md.inputs();
					});
				});
			};
			$scope.getCatalogs = function() {
				Stores.getCatalogs($scope.storeId).then(function(data) {
					$scope.catalogs = data;
				});
			};
			$scope.init = function(storeId) {
				$scope.storeId = storeId;
				if ($scope.storeId) {
					$scope.getStore();
					$scope.getCatalogs();
				}
			};
			$scope.saveStore = function($event) {

				if ($scope.storeId) {
					Stores.updateStore($scope.store, $scope.storeId).then(
							function(data) {
							});
				} else{
					Stores.saveStore($scope.store).then(function(data) {
					});
				}
				window.location = document.getElementById("adminBase").href
						+ "stores";

			};
			$scope.uploadFile = function(file) {
				var uploadPath;
				if ($scope.store.name) {
					uploadPath = $scope.store.name.replace(/[^\w]/gi, '-');
				}

				var deferred = $q.defer();
				Upload.upload({
					url : 'upload',
					fields : {
						'path' : uploadPath
					}, // additional data to send
					file : file
				}).success(function(data, status, headers, config) {
					deferred.resolve(data);
				});
				return deferred.promise;
			};
			$scope.deleteImage1 = function() {
				$scope.store.image.image1 = null;
			};
			$scope.deleteImage2 = function() {
				$scope.store.image.image2 = null;
			};
			$scope.deleteImage3 = function() {
				$scope.store.image.image3 = null;
			};
			$scope.deleteImage4 = function() {
				$scope.store.image.image4 = null;
			};
			$scope.uploadImage1 = function($files) {
				$scope.uploadFile($files[0]).then(function(data) {
					$scope.store.image.image1 = data.fileUrl;
				});
			};
			$scope.uploadImage2 = function($files) {
				$scope.uploadFile($files[0]).then(function(data) {
					$scope.store.image.image2 = data.fileUrl;
				});
			};
			$scope.uploadImage3 = function($files) {
				$scope.uploadFile($files[0]).then(function(data) {
					$scope.store.image.image3 = data.fileUrl;
				});
			};
			$scope.uploadImage4 = function($files) {
				$scope.uploadFile($files[0]).then(function(data) {
					$scope.store.image.image4 = data.fileUrl;
				});
			};
		});