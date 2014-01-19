'use strict';

angular.module('uiApp').controller('QuoteFormCtrl', ['$scope', '$rootScope', 'Quote', 'User', function ($scope, $rootScope, quoteService, userService) {
	
	$scope.isExpanded = false;
	
	$scope.showForm = function() {
		$scope.isExpanded = true;
	};
	
	var scopeTemplate = {
		tags: []	
	};
	
	$scope.tags = [];
	
	$scope.quote = angular.copy(scopeTemplate);
	
	$scope.addQuote = function() {
		userService.getUser().then(function(user) {
			var quote = angular.copy($scope.quote);
	  
			if ($scope.quote.when != undefined) {
				quote.when = $scope.quote.when.getTime();
			}
			quote.userId = user.id;
		  
			quoteService.create(quote).then(function(createdQuote) {
				$rootScope.$emit('quoteCreated', createdQuote);
					resetForm();
			  	});
		});		  
	};
	
	$scope.updateQuote = function() {
	
		quoteService.update($scope.quote).then(function() {
			$rootScope.$emit('quoteUpdated');
			resetForm();
		});
	};
	
	$scope.cancel = function() {
		resetForm();
	};
	
	function resetForm() {
		$scope.quote = angular.copy(scopeTemplate);
		$scope.quoteForm.$setPristine();
		$scope.isExpanded = false;
	}
	  
	$rootScope.$on('editQuote', function(event, data) {
		$scope.quote = data.quote;
		$scope.isExpanded = true;
	});
	
}]);
