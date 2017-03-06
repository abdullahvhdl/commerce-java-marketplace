/* globals $ */
'use strict';

angular.module('commerceApp').directive('showErrors', function($timeout) {
	return {
		restrict : 'A',
		require : '^form',
		link : function(scope, el, attrs) {
			scope.$watch(attrs.name, function(newVal, oldVal) {
				
			}, true);

		}

	}
});
var compareTo = function() {
	return {
		require : "ngModel",
		scope : {
			otherModelValue : "=compareTo"
		},
		link : function(scope, element, attributes, ngModel) {

			ngModel.$validators.compareTo = function(modelValue) {
				return modelValue == scope.otherModelValue.$modelValue;
			};

			scope.$watch("otherModelValue.$modelValue", function() {
				ngModel.$validate();
			});
		}
	};
};

angular.module('commerceApp').directive("compareTo", compareTo);