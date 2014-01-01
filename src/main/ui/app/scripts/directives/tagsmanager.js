'use strict';

angular.module('uiApp').directive('tagsmanager', ['$timeout', function ($timeout) {
	return {
		restrict: 'A',
		scope: { 
		      tags:'=tags',
		},
		
		link: function postLink(scope, element, attrs) {
        
			var ignoreWatch = false;
			scope.$watch('tags', function(newTags, oldTags) {
				if (ignoreWatch === true) {
					ignoreWatch = false;
					return;
				}
				element.tagsManager('empty');
				_.each(newTags, function(tag) {
					element.tagsManager('pushTag', tag);					
				});
			});
    	  
	        element.attr('name', 'tags');
	        element.tagsManager({
	        	tagClass: 'tm-tag-info'
	  	  	});
	        
	        element.parent().find('input[name="hidden-tags"]').on('change', function() {
	        	ignoreWatch = true;
	        	
	        	$timeout(function() {
	        		scope.tags = element.tagsManager('tags');
	        	})
	        });
		}
    };
}]);
