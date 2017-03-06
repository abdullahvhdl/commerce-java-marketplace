'use strict';
angular.module('commerceAdmin').controller('TasksController',
		function($rootScope, $window, $scope, $timeout, Tasks) {
			Tasks.getTasks().then(function(data){
				$scope.tasks=data;
			});
		});
