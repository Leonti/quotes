'use strict';

describe('Directive: tagsmanager', function () {
  beforeEach(module('uiApp'));

  var element;

  it('should make hidden element visible', inject(function ($rootScope, $compile) {
    element = angular.element('<tagsmanager></tagsmanager>');
    element = $compile(element)($rootScope);
    expect(element.text()).toBe('this is the tagsmanager directive');
  }));
});
