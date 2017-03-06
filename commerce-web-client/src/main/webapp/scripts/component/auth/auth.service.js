'use strict';

angular.module('commerceApp')
    .factory('Auth', function Auth($rootScope, $q, Principal, AuthServerProvider, Account, Register, Activate, Password, PasswordResetInit, PasswordResetFinish) {
        return {
            login: function (credentials, callback) {
                var cb = callback || angular.noop;
                var deferred = $q.defer();

                AuthServerProvider.login(credentials).then(function (data) {
                    // retrieve the logged account information
                    Principal.identity(true).then(function(account) {
                        deferred.resolve(data);
                    });
                    return cb();
                }).catch(function (err) {
                    this.logout();
                    deferred.reject(err);
                    return cb(err);
                }.bind(this));

                return deferred.promise;
            },

            logout: function () {
                AuthServerProvider.logout();
                Principal.authenticate(null);
            },

            authorize: function(force) {
                return Principal.identity(force)
                    .then(function() {
                        var isAuthenticated = Principal.isAuthenticated();

                    
                    });
            },
            createAccount: function (account, callback) {
                var cb = callback || angular.noop;

                return Register.save(account,
                    function () {
                        return cb(account);
                    },
                    function (err) {
                        this.logout();
                        return cb(err);
                    }.bind(this)).$promise;
            },

            updateAccount: function (account, callback) {
                var cb = callback || angular.noop;

                return Account.save(account,
                    function () {
                        return cb(account);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },

            activateAccount: function (key, callback) {
                var cb = callback || angular.noop;

                return Activate.get(key,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },

            changePassword: function (newPassword, callback) {
                var cb = callback || angular.noop;

                return Password.save(newPassword, function () {
                    return cb();
                }, function (err) {
                    return cb(err);
                }).$promise;
            },

            resetPasswordInit: function (mail, callback) {
                var cb = callback || angular.noop;

                return PasswordResetInit.save(mail, function() {
                    return cb();
                }, function (err) {
                    return cb(err);
                }).$promise;
            },

            resetPasswordFinish: function(key, newPassword, callback) {
                var cb = callback || angular.noop;

                return PasswordResetFinish.save(key, newPassword, function () {
                    return cb();
                }, function (err) {
                    return cb(err);
                }).$promise;
            }
        };
    });
