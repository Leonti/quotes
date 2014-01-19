'use strict';

angular.module('uiApp').controller('WidgetFormCtrl', ['$scope', '$rootScope', 'Widget', 'User', function ($scope, $rootScope, widgetService, userService) {

	$scope.isExpanded = false;
	
	$scope.showForm = function() {
		$scope.isExpanded = true;
	};
	
	var widgetTemplate = {
		type: 'IDS',
		quoteIds: [],
		tags: []
	};  
	
	$scope.widget = angular.copy(widgetTemplate);
	
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
	
	$rootScope.$on('editWidget', function(event, data) {
		$scope.widget = data.widget;
		$scope.isExpanded = true;
	});	
	
	$scope.updateWidget = function() {
		widgetService.update($scope.widget).then(function() {
			$rootScope.$emit('widgetUpdated');
			resetForm();
		});
	};	
	
	$scope.addWidget = function() {
		userService.getUser().then(function(user) {
			$scope.widget.userId = user.id;
			
			widgetService.create($scope.widget).then(function() {
				$rootScope.$emit('widgetCreated');
				resetForm();
			});
		});	  
	};
	
	$scope.cancel = function() {
		resetForm();
	};
	
	function resetForm() {
		$scope.widget = angular.copy(widgetTemplate);
		$scope.widgetForm.$setPristine();
		$scope.isExpanded = false;
	}
	  
}]);