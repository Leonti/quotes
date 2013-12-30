'use strict';

angular.module('uiApp').factory('User', ['Restangular', '$http', '$q', function (Restangular, $http, $q) {  

	var currentUser = null;
	var deferredUser;	
	
	function getUser() {
		var deferred =  $q.defer();
		
		if (currentUser != null) {
			deferred.resolve(currentUser); 
		} else {
			var result = $http({
				method: 'GET',
				url: '/resource/rest/user'
			});
			
			result.success(function(data, status) {
				currentUser = data;
				deferred.resolve(data);
			});
			
			result.error(function() {
				currentUser = null;
				deferred.reject('Not logged in');
			});
		}
		
		return deferred.promise;
	}
	
	var userPromise = getUser();
	
	userPromise.then(initNavigator, initNavigator);
		
	function initNavigator(loggedInUser) {
		
		navigator.id.watch({
			loggedInUser: loggedInUser,
			onlogin: function(assertion) {
				
				var result = $http({
				    method: 'POST',
				    url: '/resource/rest/user/login',
				    data: {
				    	assertion: assertion
				    }
				});
				
				result.success(function(data, status) {
					currentUser = data;
					deferredUser.resolve(data);						
				});
				result.error(function() {
					currentUser = null;
					deferredUser.reject('Login error');	
				});
			},
			onlogout: function() {
				console.log('Logged out');
			}
		});	
	}
	
	function login() {
		deferredUser = $q.defer();
		navigator.id.request();
		
		return deferredUser.promise;
	}
	
	function logout() {
		$http({
		    method: 'GET',
		    url: '/resource/rest/user/logout'
		}).then(function() {
			navigator.id.logout();			
		});
	}
	
    return {
    	login: login,
    	logout: logout,
    	getUser: getUser
    };
  }]);