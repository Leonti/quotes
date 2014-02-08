'use strict';

angular.module('uiApp')
  .controller('HeaderCtrl', ['$rootScope', '$scope', 'User', function ($rootScope, $scope, UserService) {
	  
	  UserService.getUser().then(onUserLoad, onUserError);
	  
	  $scope.login = function() {
		  UserService.login().then(onUserLoad, onUserError);
	  };

	  $scope.logout = function() {  
		  UserService.logout();
		  $scope.user = null;
	  };
	  
	  function onUserLoad(user) {
		  $scope.user = user;
		  $rootScope.$broadcast('userLoggedIn', user);
	  }
	  
	  function onUserError(reason) {
		  $scope.error = reason;
	  }
	  
  }]);