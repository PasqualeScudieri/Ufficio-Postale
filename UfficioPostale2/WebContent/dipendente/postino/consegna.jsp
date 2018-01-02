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
		
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
	</head>
	
	<body onresize="resize();">
	<%@ include file="../headerAdmin.html" %>
	
	<%@ include file="NavPostino.jsp" %>
	<div id="mainDiv">
		
		<%DipendentiBean dipendente=(DipendentiBean)session.getAttribute("dipendente"); %>
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(dipendente.getNome().charAt(0))%><%=Character.toUpperCase(dipendente.getCognome().charAt(0))%> </p>
		
		
		<%	String error=(String) request.getAttribute("error");
			if(error!=null && !error.equals("")){
		%> 	
				<div class="dialog">
					<header class="dialogTitle">Spiacente!</header>
					<section><%=error%></section>
					<div class="clear"></div>
					<a href="/UfficioPostale2/dipendente/postino/consegna.jsp">back</a>
					<div id="sfondoDiv"></div>
				</div>
		<%
			}else{
		%>		<div id="scrittaElenco">Consegna della Posta</div>
			
				<div id="descrizione">
					Consegnare la posta è semplice.<br/>
					Per consegnare un pacco, una lettera o una raccomandata, basta inserire nell'apposito campo posto in questa pagina<br/>
					il codice della posta. Automaticamente la data di consegna verrà registrata alla data di oggi.<br/>
					Inoltre, mittente e destinatario riceveranno una notifica, venendo così avvisati dell'avvenuta consegna<br/>
				</div>
					
				<form action="ConsegnaServlet" method="post">
					<input type="text" value="<%=dipendente.getMatricola()%>" name="matricola" hidden="true">

				<section>
					<p align="center"><span>Codice:</span>
					<span>
						<input type="number" placeholder="codice posta" name="codice" required="required">
					</span>
					</p>
				</section>
					<input type="submit" class="bottone" value="consegna">
				</form>
		<%}
		%>		
	</div>
	<div id="prova"></div>
	<%@ include file="../../footer.html" %>	
	</body>
</html>