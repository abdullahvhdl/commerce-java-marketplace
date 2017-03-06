'use strict';
angular.module('commerceAdmin').controller(
		'AttributesController',
		function($rootScope, $scope, Stores, Attributes) {
			$scope.d_page = 0;
			$scope.d_limit = 10;
			$scope.d_totalNumberOfPages = 0;
			$scope.d_setPage = function(page) {
				$scope.d_page = page;
			};
			$scope.b_page = 0;
			$scope.b_limit = 10;
			$scope.b_totalNumberOfPages = 0;
			$scope.b_setPage = function(page) {
				$scope.b_page = page;
			};
			$scope.newBuyableAttribute = {};
			$scope.newDisplayableAttribute = {};
			var buyableAttributeModal = UIkit.modal("#buyableAttributeModal", {
				bgclose : false
			});
			var displayableAttributeModal = UIkit.modal(
					"#displayableAttributeModal", {
						bgclose : false
					});
			$scope.addNewBuyableAttribute = function() {
				$scope.newBuyableAttribute = {
					description : [ {
						"language" : {
							"name" : "English",
							"locale" : "en",
						}
					} ]
				};
				buyableAttributeModal.show();
			};
			$scope.saveBuyableAttribute = function() {
				Attributes.saveBuyableAttribute($scope.newBuyableAttribute)
						.then(function(result) {
							$scope.buyableAttributes.push(result.data);
							buyableAttributeModal.hide();
						});
			};
			$scope.cancelBuyableAttribute = function() {
				$scope.newBuyableAttribute = {};
				buyableAttributeModal.hide();
			};
			$scope.addNewDisplayableAttribute = function() {
				$scope.newDisplayableAttribute = {
					description : [ {
						"language" : {
							"name" : "English",
							"locale" : "en",
						}
					} ]
				};
				displayableAttributeModal.show();
			};
			$scope.saveDisplayableAttribute = function() {
				Attributes.saveDisplayableAttribute(
						$scope.newDisplayableAttribute).then(function(result) {
					$scope.displayableAttributes.push(result.data);
					displayableAttributeModal.hide();
				});
			};
			$scope.cancelDisplayableAttribute = function() {
				$scope.newDisplayableAttribute = {};
				displayableAttributeModal.hide();
			};
			Attributes.getAllBuyableAttributes().then(
					function(result) {
						$scope.buyableAttributes = result.data;
						$scope.d_totalNumberOfPages = Math
								.ceil(result.data.length / $scope.d_limit);

					});
			Attributes.getAllDisplayableAttributes().then(
					function(result) {
						$scope.displayableAttributes = result.data;
						$scope.b_totalNumberOfPages = Math
								.ceil(result.data.length / $scope.b_limit);

					});

		});

angular
		.module('commerceAdmin')
		.controller(
				'EditAttributeController',
				function($rootScope, $scope, $timeout, Stores, Attributes) {
					$scope.page = 0;
					$scope.limit = 10;
					$scope.totalNumberOfPages = 0;
					$scope.setPage = function(page) {
						$scope.page = page;
					};
					$scope.selectedValue = {};
					var valueModal = UIkit.modal("#attriubuteValueModal", {
						bgclose : false
					});
					$scope.initAttribute = function(attributeId) {
						Attributes
								.getAttributeById(attributeId)
								.then(
										function(result) {
											$scope.attribute = result.data;
											$scope.totalNumberOfPages = Math
													.ceil(result.data.attributeValues.length
															/ $scope.limit);
											$timeout(function() {
												altair_md.inputs();
											});
										});
					};
					$scope.saveAttribute = function() {
						var attribute = angular.copy($scope.attribute);
						delete attribute.attributeValues;
						Attributes
								.updateAttribute(attribute, attribute.id)
								.then(
										function(result) {
											attribute = result.data;
											attribute.attributeValues = $scope.attribute.attributeValues;
											$scope.attribute = attribute;
											$timeout(function() {
												altair_md.inputs();
											});
										});
					};
					$scope.addNewValue = function() {
						$scope.selectedValue = {
							description : [ {
								"language" : {
									"name" : "English",
									"locale" : "en",
								}
							} ]
						};
						valueModal.show();
					};
					$scope.editAttributeValue = function(value) {
						$scope.selectedValue = angular.copy(value);
						valueModal.show();
						$timeout(function() {
							altair_md.inputs();
						});
					};
					$scope.cancelAttributeValue = function() {
						valueModal.hide();
					};
					$scope.saveAttributeValue=function(){
						if($scope.selectedValue.id){
							
						}
						else{
							Attributes.saveAttributeValue($scope.attribute.id,$scope.selectedValue).then(function(result){
								if($scope.attribute.attributeValues){
									$scope.attribute.attributeValues.push(result.data);
								}
								else{
									$scope.attribute.attributeValues=[result.data];
								}
								valueModal.hide();
							});
						}
					};

				});
