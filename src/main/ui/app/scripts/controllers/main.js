'use strict';

angular.module('uiApp').controller('MainCtrl', ['$scope', '$window', function ($scope, $window) {
	$scope.baseUrl = $window.location.hostname + ($window.location.port ? ':' + $window.location.port : '');
	
	$scope.view = 'quotes';
	$scope.setView = function(view) {
		$scope.view = view;
	};
	
	$scope.$on('quoteCreated', function(event, quote) {
		$scope.quote = quote;
		
		if ($scope.quote.tags.length > 0) {
			$scope.tagsAsString = '';
			
			_.each($scope.quote.tags, function(tag) {
				$scope.tagsAsString += tag + ', ';					
			});
			
			$scope.tagsAsString = $scope.tagsAsString.substring(0, $scope.tagsAsString.length - 2);
		}
		
		console.log($scope.quote);
	});
	
}]);
