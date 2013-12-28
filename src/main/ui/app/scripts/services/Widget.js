'use strict';

angular.module('uiApp').factory('Widget', ['Restangular', function (Restangular) {
	  
    return {
    	readAll: function() {
    		return Restangular.all('widget').doGET();
    	},
    	
    	remove: function(id) {
    		return Restangular.one('widget', id).remove();
    	},
    	
		create: function(widget) {
			return Restangular.all('widget').post(widget);
		}
    };
  }]);