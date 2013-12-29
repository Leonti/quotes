'use strict';

angular.module('uiApp').controller('QuotesCtrl', ['$scope', '$rootScope', 'Quote', function ($scope, $rootScope, quoteService) {

	$scope.selected = {};
	
	$scope.formatDate = function(timestamp) {
		if (timestamp) {
			return moment(timestamp).format("MMM Do YYYY");			
		}
	};
	
	$rootScope.$on('quoteCreated', function() {
		$scope.updateList();
	});

	$rootScope.$on('quoteUpdated', function() {
		$scope.updateList();
	});	
	
	$rootScope.$on('showQuotesPicker', function(event, data) {
		$scope.onFinishPickingCallback = data.callback;
		
		_.each(data.quoteIds, function(id) {
			$scope.selected[id] = true;
		});
	});
	
	$scope.remove = function(id) {
		quoteService.remove(id).then(function() {
			$scope.updateList();
		});
	};

	$scope.edit = function(quote) {
		$rootScope.$emit('editQuote', {
			quote: angular.copy(quote)
		});
	};	
	
	$scope.finishPicking = function() {
		
		var selected = [];
		_.each($scope.quotes, function(quote) {
			if ($scope.selected[quote.id] === true) {
				selected.push(quote.id);
			}
		});
		
		$scope.onFinishPickingCallback(selected);
		$scope.onFinishPickingCallback = null;
	};
	
	$scope.updateList = function() {
		quoteService.readAll().then(function(quotes) {
			
			$scope.quotes = _.filter(quotes, function(quote) {
				
				if ($scope.filter != undefined && $scope.filter.length > 0) {
					return quote.who.toLowerCase().indexOf($scope.filter.toLowerCase()) !== -1
						|| quote.what.toLowerCase().indexOf($scope.filter.toLowerCase()) !== -1;
				}
				
				return true;
			});
		});		
	}
	
	$scope.updateList();
	
}]);