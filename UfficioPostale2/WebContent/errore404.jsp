<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Errore</title> 
		<link rel="icon" href="/UfficioPostale2/immagini/up.png">
		<link type="text/css" href="/UfficioPostale2/css/index.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/perCinzia2.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/errore.css" rel= "stylesheet">


		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
	</head>
	
	<body onresize="resize();">
	<%@ include file="header.html" %>
	<%@ include file="nav.html" %>

	<div id="mainDiv">
		
		<div class="dialog">
          <header class="dialogTitle">Errore 404</header>
            <section>Ops! Si è verificato un errore!</section>
            <div class="clear"></div>
            <a href="/UfficioPostale2/home.jsp"><img src="/UfficioPostale2/immagini/errore.png" alt="Errore" id="casa_errore"></a>
            <div id="sfondoDiv"></div>
        </div>
        
	</div>

	<%@ include file="footer.html" %>	
	</body>
</html>