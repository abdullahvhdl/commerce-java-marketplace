'use strict';

angular.module('commerceAdmin').factory('Attributes', function($http, $q) {
	var apiurl = 'api/', myData;

	return {

		getAllBuyableAttributes : function() {
			return $http.get(apiurl + 'attributes/buyable');
		},
		getAllDisplayableAttributes : function() {
			return $http.get(apiurl + 'attributes/displayable');
		},
		getAttributeById : function(attributeId) {
			return $http.get(apiurl + 'attributes/'+attributeId);
		},
		saveBuyableAttribute:function(attribute){
			return $http.post(apiurl+'attributes/buyable',attribute);
		},
		saveDisplayableAttribute:function(attribute){
			return $http.post(apiurl+'attributes/displayable',attribute);
		},
		updateAttribute:function(attribute,attributeId){
			return $http.put(apiurl+'attributes/'+attributeId,attribute);
		},
		saveAttributeValue:function(attributeId,attributeValue){
			return $http.post(apiurl+'attributes/'+attributeId+'/values',attributeValue);
		},
		getAttributeValues:function(attributeId){
			return $http.get(apiurl + 'attributes/'+attributeId+'/values');
		}
	};
});
