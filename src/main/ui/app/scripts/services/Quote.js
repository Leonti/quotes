'use strict';

angular.module('uiApp').factory('Quote', ['Restangular', function (Restangular) {  
	  
    return {
    	readAll: function() {
    		return Restangular.all('quote').doGET();
    	},
  
    	remove: function(id) {
    		return Restangular.one('quote', id).remove();
    	},    	
    	
		create: function(quote) {
			return Restangular.all('quote').post(quote);
		}
    };
  }]);
