'use strict';

angular.module('commerceAdmin').factory(
		'Tasks',
		function($http, $q) {
			var apiurl = 'api/', myData;

			return {

				getTasks : function() {
					var deferred = $q.defer();
					$http.get(apiurl + 'tasks').success(
							function(data, status, config, headers) {
								deferred.resolve(data);
							}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				getUnassignedTasks : function() {
					var deferred = $q.defer();
					$http.get(apiurl + 'unassigned').success(
							function(data, status, config, headers) {
								deferred.resolve(data);
							}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				claim : function(taskId) {
					var deferred = $q.defer();
					$http.post(apiurl + 'claim/'+taskId,{}).success(
							function(data, status, config, headers) {
								deferred.resolve();
							}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				getTask : function(taskId) {
					var deferred = $q.defer();
					$http.post(apiurl + 'task/'+taskId,{}).success(
							function(data, status, config, headers) {
								deferred.resolve(data);
							}).error(function() { // handler errors here
					});
					return deferred.promise;
				},
				submitTask : function(task) {
					var deferred = $q.defer();
					$http.post(apiurl + 'submit',task).success(
							function(data, status, config, headers) {
								deferred.resolve();
							}).error(function() { // handler errors here
					});
					return deferred.promise;
				}

			};
		});
