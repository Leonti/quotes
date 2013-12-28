'use strict';

angular.module('uiApp').controller('QuoteFormCtrl', ['$scope', '$rootScope', 'Quote', 'User', function ($scope, $rootScope, quoteService, userService) {
	  
	  $scope.addQuote = function() {

		  userService.getUser().then(function(user) {
			  var quote = angular.copy($scope.quote);
			  quote.tags = $('input[name=tags]').val().split(',');
			  if ($scope.quote.when != undefined) {
				  quote.when = $scope.quote.when.getTime();
			  }
			  quote.userId = user.id;
			  
			  quoteService.create(quote).then(function(createdQuote) {
				  $rootScope.$emit('quoteCreated', createdQuote);
				  $scope.quote = {};
				  console.log($scope.quoteForm);
				  $scope.quoteForm.$setPristine();
			  });
		  });		  
	  };
	  
  }]);
