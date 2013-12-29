'use strict';

angular.module('uiApp').controller('TagsCtrl', ['$scope', '$rootScope', 'Quote', function ($scope, $rootScope, quoteService) {

	$scope.selected = {};
	
	$rootScope.$on('quoteCreated', function() {
		$scope.updateList();
	});

	$rootScope.$on('showTagsPicker', function(event, data) {
		$scope.onFinishPickingCallback = data.callback;
		
		_.each(data.tags, function(tag) {
			$scope.selected[tag] = true;
		});
	});
	
	$scope.finishPicking = function() {
		
		var selected = [];
		_.each($scope.tags, function(tag) {
			if ($scope.selected[tag] === true) {
				selected.push(tag);
			}
		});
		
		$scope.onFinishPickingCallback(selected);
		$scope.onFinishPickingCallback = null;
	};
	
	$scope.updateList = function() {
		quoteService.readTagsForUser().then(function(tags) {
			
			$scope.tags = _.filter(tags, function(tag) {
				
				if ($scope.filter != undefined && $scope.filter.length > 0) {
					return tag.toLowerCase().indexOf($scope.filter.toLowerCase()) !== -1;
				}
				
				return true;
			});
		});		
	}
	
	$scope.updateList();
	
}]);