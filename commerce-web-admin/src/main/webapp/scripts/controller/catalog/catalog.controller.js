"user strict";
angular
		.module("commerceAdmin")
		.controller(
				'CatalogController',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'Stores',
						'Catalog','Attributes',
						'$q',
						'Upload',
						function($scope, $rootScope, $timeout, Stores, Catalog,Attributes,$q,Upload) {
							$scope.page = 0;
							$scope.limit = 20;
							$scope.totalNumberOfPages = 0;

							$scope.stores = [];
							$scope.selectedStore = null;
							$scope.selectedCategory = null;
							$scope.view = "";
							$scope.parentCategory = {};
							$scope.displayableAttributes=[];
							$scope.buyableAttributes=[];
							$scope.productResults=[];
							var buyableAttributesModal = UIkit.modal("#buyableAttributesModal", {
								bgclose : false
							});
							var displayableAttributesModal = UIkit.modal("#displayableAttributesModal", {
								bgclose : false
							});
							var newProductModal = UIkit.modal("#newProductModal", {
								bgclose : false
							});
							Attributes.getAllBuyableAttributes().then(
									function(result) {
										$scope.buyableAttributes = result.data;
									});
							Attributes.getAllDisplayableAttributes().then(
									function(result) {
										$scope.displayableAttributes = result.data;
									});
							$scope
									.$watch(
											'selectedStore',
											function() {
												$rootScope.selectedStore = $scope.selectedStore;
											});
							$rootScope.$watch('selectedCategory', function() {
								if ($rootScope.selectedCategory) {
									$scope.view = "category";
									$scope.selectedCategory = angular
											.copy($rootScope.selectedCategory);
									Catalog.getCategoryProducts(
											$scope.selectedStore.id,
											$rootScope.selectedCategory.id)
											.then(function(data) {
												$scope.products = data;
											});
								}

							});
							$scope.setPage = function(page) {
								$scope.page = page;
							};
							$scope.getStores = function() {
								Stores.getAllStores().then(
										function(data) {
											$scope.stores = data;
											$scope.totalNumberOfPages = Math
													.ceil(data.length
															/ $scope.limit);
										});
							};
							$scope.getStores();
							$scope.getAttributeValues=function(attribute){
								Attributes.getAttributeValues(attribute.id).then(function(result){
									attribute.attributeValues=result.data;
								});
							};
							
							$scope.selectProduct = function(product) {
								$scope.view = "product";
								Catalog.getProductById($scope.selectedStore.id,
										product.id).then(
										function(data) {
											$scope.selectedProduct = data;
											console.log($scope.selectedProduct.offerPrice);
											if(!$scope.selectedProduct.offerPrice || $scope.selectedProduct.offerPrice.length==0){
												$scope.selectedProduct.offerPrice=[{currency:"USD"}];
											}
											if(!$scope.selectedProduct.originalPrice || $scope.selectedProduct.originalPrice.length==0){
												$scope.selectedProduct.originalPrice=[{currency:"USD"}];
											}
											for(var index in $scope.selectedProduct.displayableAttributes){
												var attribute=$scope.selectedProduct.displayableAttributes[index].attribute;
												$scope.getAttributeValues(attribute);
											};
											$timeout(function(){
												altair_md.inputs();
											});
										});
								

							};
							$scope.removeProduct=function(product){
								Catalog.deleteProduct($scope.selectedStore.id,$scope.selectedCategory.id,product.id).then(function(result){
									for(var index in $scope.products){
										if($scope.products[index].id==product.id){
											$scope.products.splice(index,1);
											break;
										}
									}
								});
							};
							$scope.editItem = function(product) {
								$scope.view = "item";
								Catalog.getProductById($scope.selectedStore.id,
										product.id).then(
										function(data) {
											$scope.selectedItem = data;
											if(!$scope.selectedItem.offerPrice || $scope.selectedItem.offerPrice.length==0){
												$scope.selectedItem.offerPrice=[{currency:"USD"}];
											}
											if(!$scope.selectedItem.originalPrice || $scope.selectedItem.originalPrice.length==0){
												$scope.selectedItem.originalPrice=[{currency:"USD"}];
											}
											for(var index in $scope.selectedItem.buyableAttributes){
												var attribute=$scope.selectedItem.buyableAttributes[index].attribute;
												$scope.getAttributeValues(attribute);
											};
											$timeout(function(){
												altair_md.inputs();
											});
										});
							};
							$scope.generateItems=function(){
								if($scope.selectedProduct.id){
									Catalog.generateItems($scope.selectedStore.id,$scope.selectedCategory.id,$scope.selectedProduct.id).then(function(result){
										$scope.selectProduct($scope.selectedProduct);
									});
								}
							};
							$scope.addNewItem=function(){
								$scope.view = "item";
								$scope.selectedItem=angular.copy($scope.selectedProduct);
								$scope.selectedItem.id=null;
								$scope.selectedItem.productType="item";
								for(var index in $scope.selectedItem.buyableAttributes){
									var attribute=$scope.selectedItem.buyableAttributes[index].attribute;
									$scope.getAttributeValues(attribute);
								};
								$timeout(function(){
									altair_md.inputs();
								});
							};
							$scope.saveItem=function(){
								if($scope.selectedItem.id){
									Catalog.updateItem($scope.selectedStore.id,$scope.selectedCategory.id,$scope.selectedProduct.id,$scope.selectedItem,$scope.selectedItem.id).then(function(result){
										$scope.editItem(result.data);
									});
								}
								else{
									Catalog.saveItem($scope.selectedStore.id,$scope.selectedCategory.id,$scope.selectedProduct.id,$scope.selectedItem).then(function(result){
										$scope.editItem(result.data);
									});
								}
							};
							$scope.saveProduct=function(){
								if($scope.selectedProduct.id){
									Catalog.updateProduct($scope.selectedStore.id,$scope.selectedCategory.id,$scope.selectedProduct,$scope.selectedProduct.id).then(function(result){
										$scope.selectProduct(result.data);
									});
								}
								else{
									Catalog.saveProduct($scope.selectedStore.id,$scope.selectedCategory.id,$scope.selectedProduct).then(function(result){
										$scope.selectProduct(result.data);
									});
								}
							};
							$scope.addNewProduct=function(){
								newProductModal.show();
							};
							$scope.addProductToCategory=function(productId){
								Catalog.addProductToCategory($scope.selectedStore.id,$scope.selectedCategory.id,productId).then(function(result){
									$scope.selectProduct(result.data);
									newProductModal.hide();
								});
							};
							$scope.createNewProduct=function(){
								$scope.view = "product";
								$scope.selectedProduct={};
								$scope.selectedProduct.image={};
								$scope.selectedProduct.offerPrice=[{currency:"USD"}];
								$scope.selectedProduct.originalPrice=[{currency:"USD"}];
								$scope.selectedProduct.description = [ {
									"language" : {
										"name" : "English",
										"locale" : "en",
									}
								} ];
								$scope.selectedProduct.buyableAttributes=[];
								$scope.selectedProduct.displayableAttributes=[];
								newProductModal.hide();
							};
							$scope.searchProducts=function(productName){
								$timeout(function(){
									Catalog.searchProducts(productName).then(function(result){
										$scope.productResults=result.data;
										
									});
								},300);
								
							};
							$scope.addNewAttribute=function(){
								displayableAttributesModal.show();
							};
							$scope.addNewBuaybleAttribute=function(){
								buyableAttributesModal.show();
							};
							$scope.selectDisplayableAttribute=function(attribute){
								var assignedAttr={attribute:attribute};
								$scope.getAttributeValues(attribute);
								if(!$scope.selectedProduct.displayableAttributes){
									$scope.selectedProduct.displayableAttributes=[];
								}
								$scope.selectedProduct.displayableAttributes.push(assignedAttr);
								displayableAttributesModal.hide();
							};
							$scope.selectBuyableAttribute=function(attribute){
								var assignedAttr={attribute:attribute};
								$scope.getAttributeValues(attribute);
								if(!$scope.selectedProduct.buyableAttributes){
									$scope.selectedProduct.buyableAttributes=[];
								}
								$scope.selectedProduct.buyableAttributes.push(assignedAttr);
								buyableAttributesModal.hide();
							};
							$scope.deleteDisplayableAttribute=function(assignedAttr){
								var indexof=$scope.selectedProduct.displayableAttributes.indexOf(assignedAttr);
								$scope.selectedProduct.displayableAttributes.splice(indexof,1);
							};
							$scope.deleteBuyableAttribute=function(assignedAttr){
								var indexof=$scope.selectedProduct.buyableAttributes.indexOf(assignedAttr);
								$scope.selectedProduct.buyableAttributes.splice(indexof,1);
							};
							$scope.uploadFile = function(file) {
								var uploadPath;
								if ($scope.selectedStore.name) {
									uploadPath = $scope.selectedStore.name.replace(/[^\w]/gi, '-');
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
								$scope.selectedProduct.image.image1 = null;
							};
							$scope.deleteImage2 = function() {
								$scope.selectedProduct.image.image2 = null;
							};
							$scope.deleteImage3 = function() {
								$scope.selectedProduct.image.image3 = null;
							};
							$scope.deleteImage4 = function() {
								$scope.selectedProduct.image.image4 = null;
							};
							$scope.uploadImage1 = function($files) {
								$scope.uploadFile($files[0]).then(function(data) {
									$scope.selectedProduct.image.image1 = data.fileUrl;
								});
							};
							$scope.uploadImage2 = function($files) {
								$scope.uploadFile($files[0]).then(function(data) {
									$scope.selectedProduct.image.image2 = data.fileUrl;
								});
							};
							$scope.uploadImage3 = function($files) {
								$scope.uploadFile($files[0]).then(function(data) {
									$scope.selectedProduct.image.image3 = data.fileUrl;
								});
							};
							$scope.uploadImage4 = function($files) {
								$scope.uploadFile($files[0]).then(function(data) {
									$scope.selectedProduct.image.image4 = data.fileUrl;
								});
							};

						} ]);
