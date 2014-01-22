'use strict';

angular.module('uiApp').controller('WidgetsCtrl', ['$scope', '$rootScope', 'Widget', function ($scope, $rootScope, widgetService) {
	var expandedWidgetId;

	$scope.baseUrl = "http://" + window.location.hostname;
	
	$scope.configsDefaults = {
		image_width: 300,
		image_height: 50,
		iframe_width: 300,
		iframe_height: 70,		
		iframe_css: [
             '#content {',
             '    font-size: large;',
             '}',
             '#author, #when {',
             '    font-size: small;',
             '    font-style: italic;',
             '}',
             'div, span {',
             '    background: transparent;',
             '}'
             ].join('\n')
	};
	
	$scope.editConfigs = {};
	
	$scope.getIframeUrl = function(widget) {
		return '/resource/public/widget/' + widget.id + '/iframe/css/' + encodeURIComponent(widget.configs.iframe_css);
	};
	
	$scope.widgetViews = {};
	
	$scope.getWidgetView = function(type) {
		return $scope.widgetViews[type] || 'preview';
	};
	
	$scope.setWidgetView = function(type, view) {
		$scope.widgetViews[type] = view;
	};
	
	$scope.updateConfigs = function(widget) {
		
		widget.configs = angular.copy($scope.editConfigs[widget.id]);
		widgetService.update(widget);
	};
	
	$scope.toggleExpand = function(widgetId) {
		expandedWidgetId = expandedWidgetId == widgetId ? null : widgetId; 
	}; 
	
	$scope.isExpanded = function(widgetId) {
		return widgetId == expandedWidgetId;
	};
	
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
			widget: angular.copy(widget)
		});
	};
	
	function updateList() {
		widgetService.readAll().then(function(widgets) {
			
			_.each(widgets, function(widget) {
				var editorConfig = angular.copy(widget.configs) || {};
				editorConfig.image_width = editorConfig.image_width || $scope.configsDefaults.image_width;
				editorConfig.image_height = editorConfig.image_height || $scope.configsDefaults.image_height;
				editorConfig.iframe_width = editorConfig.iframe_width || $scope.configsDefaults.iframe_width;
				editorConfig.iframe_height = editorConfig.iframe_height || $scope.configsDefaults.iframe_height;				
				editorConfig.iframe_css = editorConfig.iframe_css || $scope.configsDefaults.iframe_css;
				
				$scope.editConfigs[widget.id] = editorConfig;
			});
			
			$scope.widgets = widgets;
		});		
	}
	
	updateList();
	
}]);