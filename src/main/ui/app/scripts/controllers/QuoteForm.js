'use strict';

angular.module('uiApp').controller('QuoteFormCtrl', ['$scope', '$rootScope', 'Quote', 'User', function ($scope, $rootScope, quoteService, userService) {
	  
	  $scope.addQuote = function() {

		  userService.getUser().then(function(user) {
			  var quote = angular.copy($scope.quote);
			  quote.tags = [];
			  var tags = $('input[name=hidden-tags]').val().split(',');
			  console.log(tags);
		  	  _.each(tags, function(tag) {
		  		  if (tag.length > 0) {
		  			quote.tags.push(tag);
		  		  }
		  	  });
		  
			  if ($scope.quote.when != undefined) {
				  quote.when = $scope.quote.when.getTime();
			  }
			  quote.userId = user.id;
			  
			  quoteService.create(quote).then(function(createdQuote) {
				  $rootScope.$emit('quoteCreated', createdQuote);
				  $scope.quote = {};
				  $scope.quoteForm.$setPristine();
			  });
		  });		  
	  };
	  
  }]);
