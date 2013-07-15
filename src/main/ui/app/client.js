(function(){
	var reqListener = function() {
		if (this.status == 200) {
			var quote = JSON.parse(this.responseText);
			_quoteElement.innerHTML = 
			'<span class="quote"> \
				<span class="what">' + quote.what + '</span> \
				<span class="who">' + quote.who + '</span> \
				<span class="when">' + quote.when + '</span> \
			</span>';
		}
	};

	var oReq = new XMLHttpRequest();
	oReq.onload = reqListener;
	oReq.open("get", "http://localhost:8080/rest/quote", true);
	oReq.send();	
}());