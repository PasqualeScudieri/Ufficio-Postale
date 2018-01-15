<%@page import="bean.DipendentiBean"%>
<%@page import="bean.ClienteBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Ufficio Postale</title> 
		<link rel="icon" href="/UfficioPostale2/immagini/up.png">
	
		<link type="text/css" href="/UfficioPostale2/css/index.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/navUser.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/perCinzia.css" rel= "stylesheet">
<!-- 		<link type="text/css" href="/UfficioPostale2/dipendente/css/homeAdmin.css" rel= "stylesheet"> -->
<style type="text/css"> 

 .azioni{ 
 	width: 270px; 
 	height:350px; 
 } 
 .azioni section{ 
 	height:220px; 
 } 
 
 #nome{
 	text-align:center;
 }
 </style>

		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
	</head>
	
	<body onresize="resize();">
	<%@ include file="../dipendente/headerAdmin.html" %>
	
<%-- 	<%	String type=((DipendentiBean)session.getAttribute("dipendente")).getTipo(); --%>
<%-- 	%> --%>
	<%String nome= (String)session.getAttribute("username");  %>
	<%@ include file="navGestore.jsp" %>
	
	<div id="mainDiv">
		
		<div id="benvenuto">
		<div id="inzialiNascoste"><%=nome.charAt(0)%> </div>
		Benvenuto<br/>
		<%=nome %>
		</div>
		
		<div id="saldoDiv">
			<span>Ruolo:
			gestore
			</span>
		</div>

		<span class="title"> Cosa desideri fare?</span>
		<div class="clear"></div>
		
			<section class="azioni"> 
				<header><span>Inserisci </span></header>
				<section>
				Questa funzionalità ti permette di inserire un nuovo dipendente dell' Ufficio Postale. Questa funzione consente la creazione di un nuovo account 
				da cui poi il dipendente avrà la possibilità di lavorare. Si può inserire sia un postino che un addetto allo sportello. Cliccando sulla freccia verde qui sotto 
				puoi effettuare l'inserimento					
				</section>
				<div class="clear"></div>	
				<a href="/UfficioPostale2/gestore/registrazioneAdmin.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
			</section>
			
	</div>
	<div id="prova"></div>
	<%@ include file="../footer.html" %>	
	</body>
</html>