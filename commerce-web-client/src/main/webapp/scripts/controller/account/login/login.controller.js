'use strict';

angular.module('commerceApp')
    .controller('LoginController', function ($rootScope,$window, $scope,  $timeout, Auth) {
        $scope.user = {};
        $scope.errors = {};

        $scope.rememberMe = false;
        $timeout(function (){angular.element('[ng-model="email"]').focus();});
        $scope.login = function (event) {
            event.preventDefault();
            Auth.login({
                email: $scope.email,
                password: $scope.password,
                rememberMe: $scope.rememberMe
            }).then(function () {
                $scope.authenticationError = false;
                $window.history.back();
            }).catch(function (data) {
                $scope.authenticationError = data.data.error_description;
            });
        };
    });
