/*questa  non serve sta già nell altro*/
/*function getXmlHttpRequest() {

	var xhr = false;
	var activeXoptions = new Array("Microsoft.XmlHttp", "MSXML4.XmlHttp",
			"MSXML3.XmlHttp", "MSXML2.XmlHttp", "MSXML.XmlHttp");

	try {
		xhr = new XMLHttpRequest();
		console.log("Get XML http request");
	} catch (e) {
	}

	if (!xhr) {
		var created = false;
		for (var i = 0; i < activeXoptions.length && !created; i++) {
			try {
				xhr = new ActiveXObject(activeXoptions[i]);
				created = true;
				console.log("Get ActiveXObject XML http request");
			} catch (e) {
			}
		}
	}
	return xhr;
}
*/
function getReadyStateHandlerIBAN(req, responseXmlHandler,id) {
	return function() {
		if (req.readyState == 1) {
			console.log("Server connection IBAN");
		} else if ( req.readyState == 2 ) {
			console.log("Send request IBAN");
		} else if ( req.readyState == 3 ) {
				console.log("Receive response IBAN");
		} else if (req.readyState == 4) {
			console.log("Request finished and response is ready IBAN");
			if (req.status == 200 || req.status == 304) {
	//			console.log("in getREady id=" + id);
				responseXmlHandler(id, req.responseXML);
			} else {
				console.log("IBAN Response error "+ req.status + " " + req.statusText);
			}
		} else {
			console.log("che cosa è successo?? IBAN");
		}
	};
}

function ibanAjax(url, callback, iban , cf, id) {
	var req = getXmlHttpRequest();
	try {
//		console.log("in ibanajax id=" + id);
		req.onreadystatechange = getReadyStateHandlerIBAN(req, callback,id);
		req.open('POST', url, true);
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		console.log("Open and send request");
		req.send("iban=" + iban +"&cf="+cf);
	} catch (e1) {
		console.log("nel catch .....e mo??");
	}
}
