'use strict';

angular.module('uiApp').controller('WidgetsCtrl', ['$scope', '$rootScope', 'Widget', function ($scope, $rootScope, widgetService) {

	$rootScope.$on('widgetCreated', function() {
		updateList();
	});

	$rootScope.$on('widgetUpdated', function() {
		updateList();
	});	
	
	$scope.remove = function(id) {
		widgetService.remove(id).then(function() {
			updateList();
		});
	};
	
	$scope.edit = function(widget) {
		$rootScope.$emit('editWidget', {
			widget: widget
		});
	};
	
	function updateList() {
		widgetService.readAll().then(function(widgets) {
			$scope.widgets = widgets;
		});		
	}
	
	updateList();
	
}]);