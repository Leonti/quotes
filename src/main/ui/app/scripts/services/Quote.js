'use strict';

angular.module('uiApp')
  .factory('Quote', ['Restangular', function (Restangular) {  
	  
    return {
    	random: function() {
    		return Restangular.all('quote').doGET();
    	},
    	
		create: function(quote) {
			return Restangular.all('quote').post(quote);
		}
    };
  }]);
