'use strict';

describe('Controller: QuoteFormCtrl', function () {

  // load the controller's module
  beforeEach(module('uiApp'));

  var QuoteFormCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    QuoteFormCtrl = $controller('QuoteFormCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
