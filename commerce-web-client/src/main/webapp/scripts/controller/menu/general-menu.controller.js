'use strict';

angular.module('commerceApp').controller('GeneralMenuController',
		function($scope, $state) {
			$scope.$on('$viewContentLoaded', function(event) {
				 Journal.setupMenuGeneral();
			});
			
		});
