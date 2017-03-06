'use strict';

angular.module('commerceApp')
    .controller('ActivationController', function ($scope, $routeParams, Auth) {
        Auth.activateAccount({key: $routeParams.key}).then(function () {
            $scope.error = null;
            $scope.success = 'OK';
        }).catch(function () {
            $scope.success = null;
            $scope.error = 'ERROR';
        });
    });

