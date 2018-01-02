<%@page import="bean.DipendentiBean"%>
<%@page import="model.DipendentiModel"%>
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
		<link type="text/css" href="/UfficioPostale2/cliente/css/utente.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/navUser.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/perCinzia2.css" rel= "stylesheet">
		
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
		<style type="text/css">
			#backButton{
				font-variant: small-caps;
				font-weight: bold;
				float: right;
				color: black;
				text-decoration: none;
				z-index: 3;
				font-size: 14;
				border-style: none;
				background: white;
			}
		
		
		</style>
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


	<div id="mainDiv">
		
		<%	ClienteBean cliente1=(ClienteBean)session.getAttribute("cliente"); %>
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(cliente1.getNome().charAt(0))%><%=Character.toUpperCase(cliente1.getCognome().charAt(0))%> </p>
		
			<div class="dialog">
				<header class="dialogTitle">Notifica!</header>
				<section>
		
		
		<%	String cod=(String) request.getParameter("cod");
			int codice=Integer.parseInt(cod);
			//modelN.cancellaNotifica(codice);
		%>
		<%	String tipo= (String) request.getParameter("tipo");
		%>
			Hei ci siamo accorti che 
		<%	if(tipo.equals("nuovoBancoPosta")){
		%>
				nn hai un BancoPosta
				 
		<% 	}else if(tipo.equals("nuovaPostePay")){
		%>		
				non hai una postePay
		<%
			}else if(tipo.equals("aggiungiCarta")){
		%>		puoi aggiungere una carta al conto <%=(String)request.getParameter("iban")%>
		<% 		
			}else if(tipo.equals("aggiungiServiziInternet")){
		%>		puoi attivare i servizi internet sul conto <%=(String)request.getParameter("iban")%>
		<%
			}else if(tipo.equals("postaConsegnata")){
		%>		è la posta è stata consegnata
		<%
			}
		%>		
		<br/>
		<%DipendentiModel modelD= new DipendentiModel();
			String mat=(String) request.getParameter("matricola");
			int matricola;
			matricola=Integer.parseInt(mat);
			DipendentiBean dipendente= modelD.cercaByMatricola(matricola);
		%>
		<%=dipendente.getNome() %> <%=dipendente.getCognome() %>  
		<%	if(tipo.equals("nuovoBancoPosta")){
		%>		ti ha invitato ad aprire un conto BancoPosta <br/>
				Crea un nuovo conto ed inizia ad usufruire dei nostri servizi<br/>
				</section>
				<div class="clear"></div>
				<a class="notificaLink" href="/UfficioPostale2/cliente/EliminaNotifica?backFrom=<%=codice%>&goTo=NuovoBancoPosta">crea ora!</a>
		<% 
				}else if(tipo.equals("nuovaPostePay")){
		%>		
				ti ha invitato ad aprire una nuova PostePay<br/>
				Crea una nuova postePay ed inizia ad usufruire dei nostri servizi<br/>
				</section>
				<div class="clear"></div>
				<a class="notificaLink" href="/UfficioPostale2/cliente/EliminaNotifica?backFrom=<%=codice%>&goTo=NuovaPostePay">crea ora!</a>
		<%
			}else if(tipo.equals("aggiungiCarta")){
		%>		ti ha invitato  ad aggiungere una carta<br/>
				Aggiungi una carta al tuo conto bancoPosta e si ridurrà anche il costo!<br/>
				</section>
				<div class="clear"></div>
				<a class="notificaLink" href="/UfficioPostale2/cliente/EliminaNotifica?backFrom=<%=codice%>&goTo=Carta&iban=<%=(String)request.getParameter("iban")%>">attiva ora!</a>
		<% 		
			}else if(tipo.equals("aggiungiServiziInternet")){
		%>		ti ha invitato ad attivare i servizi Internet<br/>
				Attiva i servizi internet del tuo conto e si ridurrà anche il costo!<br/>
				</section>
				<div class="clear"></div>
				<a class="notificaLink" href="/UfficioPostale2/cliente/EliminaNotifica?backFrom=<%=codice%>&goTo=ServiziInternet&iban=<%=(String)request.getParameter("iban")%>">attiva ora!</a>
		<%
			} else if(tipo.equals("postaConsegnata")){
		%>		ha cosegnato la posta con codice <%=request.getParameter("codPosta")%>
				Visualizza dettagli
				</section>
				<div class="clear"></div>
				<a class="notificaLink" href="/UfficioPostale2/cliente/EliminaNotifica?backFrom=<%=codice%>&goTo=PostaNotifica&codPosta=<%=(String)request.getParameter("codPosta")%>">info</a>
		<%
			}
		%>	
		
			<form action="EliminaNotifica">
				<input type="text" value="<%=codice%>" name="backFrom" hidden="true">
				<input type="submit" value="back" id="backButton">
			</form>
			<div id="sfondoDiv"></div>		
		</div>
		
	
		
		

	</div>
	<div id="prova"></div>
	<%@ include file="../footer.html" %>	
	</body>
</html>
