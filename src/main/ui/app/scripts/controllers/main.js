'use strict';

angular.module('uiApp').controller('MainCtrl', ['$scope', '$window', function ($scope, $window) {
	$scope.baseUrl = $window.location.hostname + ($window.location.port ? ':' + $window.location.port : '');
	
//	$scope.quote = {
//		id: 21	
//	};
	
	$scope.$on('quoteCreated', function(event, quote) {
		$scope.quote = quote;
		console.log($scope.quote);
	});
	
}]);
