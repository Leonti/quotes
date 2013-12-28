'use strict';

angular.module('uiApp').controller('WidgetFormCtrl', ['$scope', '$rootScope', 'Widget', 'User', function ($scope, $rootScope, widgetService, userService) {

	  
	$scope.widget = {
		type: 'IDS'	
	}; 
	
	$scope.addWidget = function() {
		userService.getUser().then(function(user) {
			var widget = angular.copy($scope.widget);
			widget.userId = user.id;
			
			widget.quoteIds = [1,2,3,4];
			widget.tags = ['tag1','tag2','tag3'];
			
			widgetService.create(widget).then(function(createdWidget) {
				$rootScope.$emit('widgetCreated', createdWidget);
			});
		});	  
	  
	};
	  
}]);