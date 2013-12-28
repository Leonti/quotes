'use strict';

angular.module('uiApp')
  .controller('HeaderCtrl', ['$scope', 'User', function ($scope, UserService) {
	  
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
	  }
	  
	  function onUserError(reason) {
		  $scope.error = reason;
	  }
	  
  }]);