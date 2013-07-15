'use strict';

describe('Service: Quote', function () {

  // load the service's module
  beforeEach(module('uiApp'));

  // instantiate service
  var Quote;
  beforeEach(inject(function (_Quote_) {
    Quote = _Quote_;
  }));

  it('should do something', function () {
    expect(!!Quote).toBe(true);
  });

});
