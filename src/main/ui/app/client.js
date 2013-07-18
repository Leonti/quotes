(function(){
	var reqListener = function() {
		if (this.status == 200) {
			var quote = JSON.parse(this.responseText);
			_quote.element.innerHTML = 
			'<span class="quote"> \
				<span class="what">' + quote.what + '</span> \
				<span class="who">' + quote.who + '</span> \
				<span class="when">' + quote.when + '</span> \
			</span>';
		}
	};
	
	var oReq = new XMLHttpRequest();
	oReq.onload = reqListener;
	var baseUrl = "http://localhost:8080";
	var url = baseUrl + "/rest/quote";
	if (_quote.tags != undefined) {
		url = (_quote.excludedTags != undefined) ? 
			baseUrl + "/rest/quote/random/tags/include/" + _quote.tags :
			baseUrl + "/rest/quote/random/tags/include/" + _quote.tags + "/exclude/" + _quote.excludedTags;
	}
	
	oReq.open("get", url, true);
	oReq.send();	
}());