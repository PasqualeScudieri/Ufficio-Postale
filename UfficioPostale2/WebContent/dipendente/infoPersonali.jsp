<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
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
		<link type="text/css" href="/UfficioPostale2/user/navUser.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/visualizza.css" rel= "stylesheet">
		
		
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerAdmin.html" %>
	<%	String type=((DipendentiBean)session.getAttribute("dipendente")).getTipo();
	
		if(type.equals("addettoSportello")){ 
	%>
			<%@ include file="addetto/navAdmin.jsp" %>
	<%	}else if(type.equals("postino")){
	%>
			<%@ include file="postino/NavPostino.jsp" %>
	<%	
		}
	%>


	<div id="mainDiv">
		
		<%DipendentiBean dipendente=(DipendentiBean)session.getAttribute("dipendente"); %>
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(dipendente.getNome().charAt(0))%><%=Character.toUpperCase(dipendente.getCognome().charAt(0))%> </p>
		<div id="scrittaElenco">Informazioni su di te</div>
		<div id="descrizione">
			In questa pagina è possibile visualizzare le proprie informazioni.
		</div>
		<section id="infoSection">
		<p><span>Nome:</span><span><%=dipendente.getNome() %></span>
		<p><span>Cognome:</span><span><%=dipendente.getCognome() %></span>
		<p><span>CF:</span><span><%=dipendente.getCf() %></span>
		<p><span>Indrizzo:</span><span><%=dipendente.getIndirizzo() %></span>
		<p><span>Luogo di Nascita:</span><span><%=dipendente.getLuogonascita() %></span>
		<p><span>Matricola:</span><span><%=dipendente.getMatricola() %></span>
		<p><span>Tipo:</span><span><%=dipendente.getTipo() %></span>
		<% DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
		<p><span>Data di Nascita:</span><span><%=dateScad.format(dipendente.getDataNascita())%></span>
		
		<p><span>Username: </span><span><%=session.getAttribute("username")%></span>
		</section>
	</div>
	<div id="prova"></div>
	<%@ include file="../footer.html" %>	
	</body>
</html>