'use strict';

angular.module('uiApp', ['ngRoute', 'restangular', '$strap.directives']).config(function ($routeProvider, RestangularProvider) {
	$routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
    
    RestangularProvider.setBaseUrl(window.location.protocol + '//' + window.location.hostname + ":8080/rest");
});
