'use strict';

angular.module('uiApp').controller('QuotesCtrl', ['$scope', '$rootScope', 'Quote', function ($scope, $rootScope, quoteService) {

	$rootScope.$on('quoteCreated', function() {
		updateList();
	});
	
	$scope.remove = function(id) {
		quoteService.remove(id).then(function() {
			updateList();
		});
	};
	
	function updateList() {
		quoteService.readAll().then(function(quotes) {
			$scope.quotes = quotes;
		});		
	}
	
	updateList();
	
}]);