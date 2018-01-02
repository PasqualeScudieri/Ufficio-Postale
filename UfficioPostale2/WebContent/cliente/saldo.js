/*questa si copia*/
function getXmlHttpRequest() {

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

function getReadyStateHandler(req, responseXmlHandler) {
	return function() {
		if (req.readyState == 1) {
			console.log("Server connection");
		} else if ( req.readyState == 2 ) {
			console.log("Send request");
		} else if ( req.readyState == 3 ) {
				console.log("Receive response");
		} else if (req.readyState == 4) {
			console.log("Request finished and response is ready");
			if (req.status == 200 || req.status == 304) {
				responseXmlHandler(req.responseXML);
			} else {
				console.log("Response error "+ req.status + " " + req.statusText);
			}
		} else {
			console.log("che cosa è successo??");
		}
	};
}

function aggiornaSaldo(listXML){
	//console.log("aggiornaSaldo è stata chiamata");
	var rdfs = listXML.getElementsByTagName("saldo")[0].firstChild.nodeValue;
	//console.log("rdfs vale: "+ rdfs);
	document.getElementById("saldo").value=rdfs;
}


function saldoAjax(url, callback, parameter) {
	var req = getXmlHttpRequest();
	try {

		req.onreadystatechange = getReadyStateHandler(req, callback);
		req.open('POST', url, true);
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		console.log("Open and send request");
		req.send("iban=" + parameter);
	} catch (e1) {
		console.log("nel catch .....e mo??");
	}
}
