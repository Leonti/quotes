'use strict';

angular.module('uiApp')
  .controller('QuoteFormCtrl', function ($scope) {
    
	  $scope.addQuote = function() {
		  console.log($scope.quote);
		  console.log($scope.quote.when.getTime() / 1000);
	  };
  });
