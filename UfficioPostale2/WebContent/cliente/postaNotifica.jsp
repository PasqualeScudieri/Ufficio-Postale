<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="model.PacchiModel"%>
<%@page import="bean.PacchiBean"%>
<%@page import="bean.PostaBean"%>
<%@page import="model.PostaModel"%>
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
		<link type="text/css" href="/UfficioPostale2/css/visualizza.css" rel= "stylesheet">
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
		
		<style type="text/css">
			.colonnaDestra{width: 300px;}
		</style>
		
	</head>
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


		<div id="mainDiv">
			<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(cliente.getNome().charAt(0))%><%=Character.toUpperCase(cliente.getCognome().charAt(0))%> </p>
			
			<%	String codP= (String) request.getParameter("codPosta");
				System.out.println("codP "+ codP);
				int codicePosta=Integer.parseInt(codP);
				PostaModel modelPo= new PostaModel();
				PostaBean posta= modelPo.cercaByCodice(codicePosta);
			%>
				<div id="scrittaElenco">Ecco le informazioni sulla posta appena consegnata</div>
				<section id="descrizione">
			
			<% 		
				if(posta.getTipo().equals("pacchi")){
					PacchiModel modelPa= new PacchiModel();
					PacchiBean pacco= modelPa.cercaPacco(codicePosta);
					if(pacco!=null && pacco.getCodice()!=0){
			%>			<p><span>Codice:</span><span class="colonnaDestra"> <%=pacco.getCodice()%></span></p>
						<p><span>Peso:</span><span class="colonnaDestra"> <%=pacco.getPeso()%></span></p>
						<p><span>Volume:</span><span class="colonnaDestra"> <%=pacco.getVolume()%></span></p>
			<% 
					}
			%>
			<%
				}	
			%>		
		
				<p><span>Mittente:</span><span class="colonnaDestra"> <%=cliente.getNome()%> <%=cliente.getCognome()%></span></p>
				<p><span>Indirizzo mittente: </span><span class="colonnaDestra"><%=cliente.getIndirizzo()%></span></p>
				<p><span>Destinatario: </span><span class="colonnaDestra"><%=posta.getDestinatario()%></span></p>
				<p><span>Indirizzo destinatario: </span><span class="colonnaDestra"><%=posta.getIndirizzo()%></span></p>
				<p><span>Dipendente: </span><span class="colonnaDestra" ><%=posta.getDipendente()%></span></p>
				<p><span>Codice:</span><span class="colonnaDestra"><%=posta.getCodice()%></span></p>
				<% DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
				<p><span>Data di Spedizione: </span><span class="colonnaDestra"><%=dateScad.format(posta.getDataSpedizione())%></span></p>
			
			<% 	if(posta.getDataConsegna()!=null){
			%>		<p><span>Data di Consegna:</span><span class="colonnaDestra"><%=dateScad.format(posta.getDataConsegna())%></span></p>
			<%	}else{
			%>		<p><span>Data di Consegna:</span><span class="colonnaDestra"> Non ancora consegnato.</span></p>
			<%	
				}
			%>
			</section>
		</div>
		<%@ include file="../footer.html" %>	
	</body>
</html>