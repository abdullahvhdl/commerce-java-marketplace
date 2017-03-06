'use strict';

angular.module('commerceApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
