'use strict';
angular.module('commerceAdmin').controller('TaskController',
		function($rootScope, $window, $scope, $timeout, Tasks) {
			
			$scope.setTaskId=function(taskId){
				$scope.taskId=taskId;
				getTask($scope.taskId);
			}
			$scope.claim = function(task) {
				Tasks.claim(task.id).then(function(){
					getTask($scope.taskId);
				});
				
			};

			$scope.cancel=function(){
				$window.history.back();
			};
			
			$scope.submitTask=function(){
				Tasks.submitTask($scope.task);
				window.location = document.getElementById("adminBase").href
				+ "orders/assigned";
				
			};
			function getTask(taskId){
				Tasks.getTask($scope.taskId).then(function(data){
					$scope.task=data;
					$timeout(function(){
						altair_md.inputs();
					})
				});
			};
		});
