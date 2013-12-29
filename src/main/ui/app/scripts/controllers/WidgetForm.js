'use strict';

angular.module('uiApp').controller('WidgetFormCtrl', ['$scope', '$rootScope', 'Widget', 'User', function ($scope, $rootScope, widgetService, userService) {

	var newWidget = {
		type: 'IDS',
		quoteIds: [],
		tags: []
	};  
	
	$scope.widget = angular.copy(newWidget);
	
	$scope.showQuotesPicker = function() {
		$rootScope.$emit('showQuotesPicker', {
			callback: function(selectedQuotes) {
				$scope.widget.quoteIds = selectedQuotes;
			},
			quoteIds: $scope.widget.quoteIds
		});
	};

	$scope.showTagsPicker = function() {
		$rootScope.$emit('showTagsPicker', {
			callback: function(selectedTags) {
				$scope.widget.tags = selectedTags;
			},
			tags: $scope.widget.tags
		});
	};	
	
	$scope.addWidget = function() {
		userService.getUser().then(function(user) {
			var widget = angular.copy($scope.widget);
			widget.userId = user.id;
			
			widgetService.create(widget).then(function(createdWidget) {
				$rootScope.$emit('widgetCreated', createdWidget);
				$scope.widget = angular.copy(newWidget);
				$scope.widgetForm.$setPristine();
			});
		});	  
	};
	  
}]);