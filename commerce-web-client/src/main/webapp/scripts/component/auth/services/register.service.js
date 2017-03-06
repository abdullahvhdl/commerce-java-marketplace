'use strict';

angular.module('commerceApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


