<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
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
		<link type="text/css" href="css/utente.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/navUser.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/perCinzia.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/visualizza.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/table.css" rel= "stylesheet">	
		
		<script type="text/javascript" src="../script.js"></script>
		<script type="text/javascript" src="user.js"></script>
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


	<div id="mainDiv">
		
		<%ClienteBean cliente3=(ClienteBean)session.getAttribute("cliente"); %>
		
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(cliente.getNome().charAt(0))%><%=Character.toUpperCase(cliente.getCognome().charAt(0))%> </p>
		<div id="scrittaElenco">Informazioni su di te</div>
		<div id="descrizione">
			In questa pagina è possibile visualizzare le proprie informazioni.
		</div>
		<section id="infoSection">
		
		<p><span>Nome:</span><span><%=cliente.getNome()%></span></p>
		<p><span>Cognome:</span><span> <%=cliente.getCognome()%></span></p>
		<p><span>CF: </span><span><%=cliente.getCf()%></span></p>
		<p><span>Indirizzo:</span><span><%=cliente.getIndirizzo()%></span></p>
		<p><span>Luogo di Nascita:</span><span><%=cliente.getLuogoNascita()%></span></p>
		<% DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
		<p><span>Data di Nascita:</span><span><%=dateScad.format(cliente.getDataNascita())%></span></p>
		
		<p><span>Username:</span><span> <%=session.getAttribute("username")%></span></p>
		</section>
		
		
		<section id="contiSection">
		<%	if(!(arrayBP==null) && !arrayBP.isEmpty()){
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
		
		<%	ArrayList<postePayBean>arrayPP= modelPP.cercaPerCf(cf);
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
		
		
		
		</section>
		
	</div>
	<div id="prova"></div>
	<%@ include file="../footer.html" %>	
	</body>
</html>