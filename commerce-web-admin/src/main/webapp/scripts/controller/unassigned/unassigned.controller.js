'use strict';
angular.module('commerceAdmin').controller('UnassignedTasksController',
		function($rootScope, $window, $scope, $timeout, Tasks) {
			getUnassignedTasks();
			
			$scope.claim = function(task) {
				Tasks.claim(task.id).then(function(){
					getUnassignedTasks();
				});
				
			};

			function getUnassignedTasks() {
				Tasks.getUnassignedTasks().then(function(data) {
					$scope.tasks = data;
				});
			}
		});
