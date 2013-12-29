'use strict';

angular.module('uiApp').directive('tagsmanager', function () {
    return {
      restrict: 'A',
           
      link: function postLink(scope, element, attrs) {
        
        $(element).attr('name', 'tags');
        $(element).tagsManager({
  		  tagClass: 'tm-tag-info'
  	  });
      }
    };
  });
