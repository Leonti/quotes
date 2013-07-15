'use strict';

angular.module('uiApp', ['$strap.directives'])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/quote-form.html',
        controller: 'QuoteFormCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
