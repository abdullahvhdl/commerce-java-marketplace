/* globals $ */
'use strict';

angular.module('commerceApp')
    .directive('ngPagination', function() {
        return {
            templateUrl: 'scripts/component/form/pagination.html',
            scope: {
                pageable: '='
             },
            controller:function($scope,$route,$routeParams){
            	$scope.range = function(min, max, step){
				    step = step || 1;
				    var input = [];
				    for (var i = min; i < max; i += step) input.push(i);
				    return input;
				 };
            	$scope.page=$routeParams.page;
            	if(!$scope.page){
					$scope.page=0;
				}
            	$scope.loadPage=function(page){
            		$route.updateParams({
						page:page
					});
            	};
            }
        };
    });
