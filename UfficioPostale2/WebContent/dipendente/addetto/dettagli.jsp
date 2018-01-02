<%@page import="bean.postePayBean"%>
<%@page import="model.PostePayModel"%>
<%@page import="bean.BancopostaBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.BancoPostaModel"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="model.ClienteModel"%>
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
		<link type="text/css" href="/UfficioPostale2/css/perCinzia2.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/table.css" rel= "stylesheet">
		
		<link type="text/css" href="/UfficioPostale2/css/visualizza.css" rel= "stylesheet">
		
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
	</head>
	
	<body onresize="resize();">
	<%@ include file="../headerAdmin.html" %>
	<%@ include file="navAdmin.jsp" %>


	<div id="mainDiv">
		
		<%DipendentiBean dipendente=(DipendentiBean)session.getAttribute("dipendente"); %>
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(dipendente.getNome().charAt(0))%><%=Character.toUpperCase(dipendente.getCognome().charAt(0))%> </p>
	
		<%	String cf=(String) request.getParameter("cf");
			ClienteModel modelC= new ClienteModel();
			ClienteBean cliente= modelC.cercaByCliente(cf);
		%>
		<div id="scrittaElenco">Informazioni sul cliente:</div>
	
		<section id="infoSection">
		<p><span>Nome:</span> <span><%=cliente.getNome()%></span></p>
		<p><span>Cognome:</span> <span> <%=cliente.getCognome()%></span></p>
		<p><span>CF:</span> <span> <%=cliente.getCf()%></span></p>
		<p><span>Indirizzo:</span> <span><%=cliente.getIndirizzo()%></span></p>
		<p><span>Luogo di Nascita:</span> <span><%=cliente.getLuogoNascita()%></span></p>
		<% DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
		<p><span>Data Nascita:</span> <span><%=dateScad.format(cliente.getDataNascita())%></span></p>
		</section>

		<%	BancoPostaModel modelBP= new BancoPostaModel();
			ArrayList<BancopostaBean>arrayBP= modelBP.cercaPerCf(cf);
			if(!(arrayBP==null) && !arrayBP.isEmpty()){
		%>
				<table>
					<tr>
						<th>Iban</th>
						<th>Costo</th>
						<th>Tasso</th>
						<th>Carta</th>
						<th>Servizi Internet</th>
					</tr>
		<% 	
				for(BancopostaBean bancoposta: arrayBP){
		%>			<tr>
						<td><span class="large"><%=bancoposta.getIban()%></span> <span class="notLarge" title="<%=bancoposta.getIban()%>">IT76P0****<%=bancoposta.getIban().substring(24, 27)%></span></td>
						<td><%=bancoposta.getCosto()%></td>
						<td><%=bancoposta.getTasso()%></td>
						<td><%=bancoposta.getCarta()%></td>
						<td><%=bancoposta.getServInternet()%></td>
					</tr>
		<% 
				}
		%>
				</table>
		<%
			}else{
		%>
				<div id="nonCiSonoConti">Non ci sono conti BancoPosta da mostrare.</div>
		<% 		
			}
		%>
		
		<%	PostePayModel modelPP= new PostePayModel();
			ArrayList<postePayBean>arrayPP= modelPP.cercaPerCf(cf);
			if(!(arrayPP==null) && !arrayPP.isEmpty()){
		%>
				<table>
					<tr>
						<th>Iban</th>
						<th>Numero di Carta</th>
						<th>Scadenza</th>
					</tr>
		<% 	
				for(postePayBean postePay: arrayPP){
		%>			<tr>
						<td><span class="large"><%=postePay.getIban()%></span> <span class="notLarge" title="<%=postePay.getIban()%>">IT76P0****<%=postePay.getIban().substring(24, 27)%></span></td>
						<td><%=postePay.getNumCarta()%></td>
						<% DateFormat date =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
						<td><%=date.format(postePay.getScadenza())%></td>
					</tr>
		<% 			
				}
		%>
				</table>
		<%
			}else{
		%>
				<div id="nonCiSonoConti">Non ci sono postePay da mostrare.</div>
		<% 		
			}
		%>
		
		
		
		<%String tipo= (String) request.getParameter("tipo");
			if(tipo.equals("NoPP")){
		%>
				<form action="NoPostePayServlet" method="post">
					<input type="text" hidden="true" name="matricola" value="<%=((DipendentiBean)session.getAttribute("dipendente")).getMatricola()%>">
					<input type="text" value="<%=cliente.getCf()%>" hidden="true" name="cf">
					<input type="submit" class="bottone" value="invia richiesta per PostePay">
				</form>
		<%
			}else if(tipo.equals("NoBP")){
		%>
				<form action="NoBancoPostaServlet" method="post">
					<input type="text" hidden="true" name="matricola" value="<%=((DipendentiBean)session.getAttribute("dipendente")).getMatricola()%>">
					<input type="text" value="<%=cliente.getCf()%>" hidden="true" name="cf">
					<input type="submit" class="bottone" value="invia richiesta per BancoPosta">
				</form>
		<%
			}else if(tipo.equals("NoC")){
		%>
				<form action="NoCartaServlet" method="post">
					<input type="text" value="<%=(String)request.getParameter("iban")%>" hidden="true" name="iban">
					<input type="text" hidden="true" name="matricola" value="<%=((DipendentiBean)session.getAttribute("dipendente")).getMatricola()%>">
					<input type="text" value="<%=cliente.getCf()%>" hidden="true" name="cf">
					<input type="submit" class="bottone" value="invia richiesta per la carta">
				</form>
		<%
			}else if(tipo.equals("NoSI")){
		%>
				<form action="NoServiziInternetServlet" method="post">
					<input type="text" value="<%=(String)request.getParameter("iban")%>" hidden="true" name="iban">
					<input type="text" hidden="true" name="matricola" value="<%=((DipendentiBean)session.getAttribute("dipendente")).getMatricola()%>">
					<input type="text" value="<%=cliente.getCf()%>" hidden="true" name="cf">
					<input type="submit" class="bottone" value="invia richiesta per i servizi internet">
				</form>
		<%
			}
		%>
	</div>
	<div id="prova"></div>
	<%@ include file="../../footer.html" %>	
	</body>
</html>