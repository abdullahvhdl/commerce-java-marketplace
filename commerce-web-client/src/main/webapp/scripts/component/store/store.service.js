'use strict';

angular.module('commerceApp').factory('Store', function($http, $q) {
	var apiurl = 'api/stores/', myData;

	return {
		getStoresByLocation : function(location, maximumDistance) {
			return $http.get(apiurl, {
				params : {
					lat : location.latitude,
					lng : location.longitude,
					distance : maximumDistance
				}
			});
		},
		getStoreById : function(storeId) {
			return $http.get(apiurl + storeId);
		}
	};
});
