<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="org.apache.tomcat.jni.Local"%>
<%@page import="java.util.Date"%>
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
	
		<link type="text/css" href="../css/index.css" rel= "stylesheet">
		<link type="text/css" href="css/utente.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/navUser.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/table.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/visualizza.css" rel= "stylesheet">
		
		<link type="text/css" href="/UfficioPostale2/css/formCentro.css" rel= "stylesheet">
		
		<script type="text/javascript" src="../script.js"></script>
		<script type="text/javascript" src="user.js"></script>
		
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


	<div id="mainDiv">
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(cliente.getNome().charAt(0))%><%=Character.toUpperCase(cliente.getCognome().charAt(0))%> </p>
	
		<%postePayBean ppb=(postePayBean)request.getAttribute("postePay"); 
		if(ppb!=null && ppb.getNumCarta()!=0){
		%>	
			<div id="scrittaElenco">Informazioni sulla carta PostePay appena creata:</div>
			<section id="infoSection">
			<p><span>Iban:</span><span><%=ppb.getIban() %></span></p>
			<p><span>Numero della Carta:</span><span><%=ppb.getNumCarta() %></span></p>
			<% DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
			<%Date scadenza=ppb.getScadenza(); %>
			<p><span>Data di Scadenza:</span><span> <%=dateScad.format(ppb.getScadenza())%></span></p>
			<p><span>Codice di Sicurezza:</span><span><%=ppb.getCodSicur() %></span></p>
			</section>
		<% 
		 }else{
		%> 
		
		<div id="scrittaElenco">Crea una nuova PostePay</div>
			
			<div id="descrizione">
				Creare una nuova PostePay è semplice!<br/>
				Basta scegliere il proprio codice di sicurezza e premere invio. Verrà automaticamente creata una nuova PostePay.<br/>
				Creare una PostePay è molto conveniente; a differenza del conto BancoPosta non prevede alcun costo. Inoltre con una<br> 
				PostePay è possibile iniziare da subito ad effettuare operazioni quali giroconto e bonifici on-line, senza costi aggiuntivi.<br/>
			</div>
			
		
			<form action="NuovaPostePayServlet" method="post">
				<section>

					<p><span>Numero di Sicurezza:</span>
					<span>			
						<input type="password" name="codice" required>
					</span>
					</p>
				</section>
		<%	String error= (String)request.getAttribute("error");
			if(error != null) {
		%>
				<div class="error"><%=error %> </div>
		<%
			}
		%>
			
				
			<input type="submit"  class="bottone" value="Submit">
		</form>
		<%  
		 }
		%>
		
		
		
	</div>
	<div id="prova"></div>
	<%@ include file="../footer.html" %>	
	</body>
</html>