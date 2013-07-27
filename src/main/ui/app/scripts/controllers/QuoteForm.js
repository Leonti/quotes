'use strict';

angular.module('uiApp')
  .controller('QuoteFormCtrl', ['$scope', 'Quote', function ($scope, quoteService) {
	  
	  $scope.addQuote = function() {
		  		  
		  var quote = angular.copy($scope.quote);
		  quote.tags = $('input[name=hidden-tags]').val().split(',');
		  quote.when = $scope.quote.when.getTime() / 1000;
		  
		  quoteService.create(quote).then(function(createdQuote) {
			  $scope.$emit('quoteCreated', createdQuote);
		  });
		  
	  };
	  
  }]);
