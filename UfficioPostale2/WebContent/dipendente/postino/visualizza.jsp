<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="bean.PostaBean"%>
<%@page import="bean.PacchiBean"%>
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
		<link type="text/css" href="/UfficioPostale2/css/table.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/visualizza.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/formCentro.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/perCinzia2.css" rel= "stylesheet">
		
		<style type="text/css">
			.colonnaDestra{width: 300px;}
		</style>
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
	</head>
	
	<body onresize="resize();">
	<%@ include file="../headerAdmin.html" %>
	
	<%@ include file="NavPostino.jsp" %>
	<div id="mainDiv">
		
		<%DipendentiBean dipendente=(DipendentiBean)session.getAttribute("dipendente"); %>
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(dipendente.getNome().charAt(0))%><%=Character.toUpperCase(dipendente.getCognome().charAt(0))%> </p>
			
		<%	String success= (String)request.getAttribute("success");

			String error=(String) request.getAttribute("error");
			if(error!=null && !error.equals("")){
		%> 	
				<div class="dialog">
					<header class="dialogTitle">Spiacente!</header>
					<section><%=error%></section>
					<div class="clear"></div>
					<a href="/UfficioPostale2/dipendente/postino/visualizza.jsp">back</a>
					<div id="sfondoDiv"></div>
				</div>
		<%
			}else if(success!=null && !success.equals("")){
				PacchiBean pacco= (PacchiBean) request.getAttribute("pacco");
				PostaBean posta=(PostaBean) request.getAttribute("posta");
				ClienteBean mittnete=(ClienteBean) request.getAttribute("mittente");
		%>
				<div id="scrittaElenco"> <%=success%></div>
				<section id="descrizione">
		<% 		//System.out.println("cod= " +pacco.getCodice());
				if(pacco!=null && pacco.getCodice()!=0){
			%>		<p><span>Codice:</span><span class="colonnaDestra"> <%=pacco.getCodice()%></span></p>
					<p><span>Peso:</span><span class="colonnaDestra"> <%=pacco.getPeso()%></span></p>
					<p><span>Volume:</span><span class="colonnaDestra"> <%=pacco.getVolume()%></span></p>
	
			<% 
				}
			%>
				<p><span>Mittente:</span><span class="colonnaDestra"> <%=mittnete.getNome()%> <%=mittnete.getCognome()%></span></p>
				<p><span>Indirizzo mittente: </span><span class="colonnaDestra"><%=mittnete.getIndirizzo()%></span></p>
				<p><span>Destinatario: </span><span class="colonnaDestra"><%=posta.getDestinatario()%></span></p>
				<p><span>Indirizzo destinatario: </span><span class="colonnaDestra"><%=posta.getIndirizzo()%></span></p>
				<p><span>Dipendente: </span><span class="colonnaDestra" ><%=posta.getDipendente()%></span></p>
				<p><span>Codice:</span><span class="colonnaDestra"><%=posta.getCodice()%></span></p>
				<% DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
				<%//System.out.println("postaaa:"+posta.getDataSpedizione()); %>
				<p><span>Data di Spedizione: </span><span class="colonnaDestra"><%=dateScad.format(posta.getDataSpedizione())%></span></p>
			<% 	if(posta.getDataConsegna()!=null){
			%>
					<p><span>Data di Consegna:</span><span class="colonnaDestra"><%=dateScad.format(posta.getDataConsegna())%></span></p>
			<%	}else{
			%>
					<p><span>Data di Consegna:</span><span class="colonnaDestra"> Non ancora consegnato.</span></p>
			<%	
			}
			%>
			</section>
		<%
			}else{
		%>
			
				<div id="scrittaElenco">Visualizzazione dettagli relativi alla posta</div>
						<div id="descrizione">
						&Egrave; possibilite ottenere informazioni riguardo lettere, pacchi e raccomandate.<br/>
						Per visualizzare i dettagli, basta inserire il codice relativo all'articolo che si sta cercando.<br/>
						</div>	
				<form action="VisualizzaPostaServlet" method="post">
					<input type="text" value="<%=dipendente.getMatricola()%>" name="matricola" hidden="true">
						
					<section>
						<p align="center"><span>Codice:</span>
						<span>
						<input type="number" placeholder="codice pacco" name="codice" required>
						</span>
						</p>
					</section>
					<input type="submit" class="bottone"  value="visualizza">
				</form>
		<%}
		%>		
	</div>
	<div id="prova"></div>
	<%@ include file="../../footer.html" %>	
	</body>
</html>
