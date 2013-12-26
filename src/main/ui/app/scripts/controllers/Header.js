'use strict';

angular.module('uiApp')
  .controller('HeaderCtrl', ['$scope', 'User', function ($scope, UserService) {
	  
	  $scope.user = UserService.getUser();

	  $scope.user.then(null, function(reason) {
		  $scope.error = reason;
	  });
	  
	  $scope.login = function() {
		  $scope.user = UserService.login();
	  };

	  $scope.logout = function() {  
		  UserService.logout();
		  $scope.user = null;
	  };	  
	  
  }]);