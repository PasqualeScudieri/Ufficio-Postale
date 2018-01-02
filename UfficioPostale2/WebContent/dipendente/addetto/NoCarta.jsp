<%@page import="bean.ContoCliente"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.ClienteModel"%>
<%@page import="java.util.ArrayList"%>
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
	
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>		
		<style type="text/css">
			@media screen and (max-width: 850px){
				#footerSopra{
					padding-right:240px;
				}
				
				#footerSotto{
					padding-right:240px;
				}
				
				footer{
					padding-right:240px;
				}
			}
		</style>
		
		
	</head>
	
	<body onresize="resize();">
	<%@ include file="../headerAdmin.html" %>
	<%@ include file="navAdmin.jsp" %>


	<div id="mainDiv">
		
		<%DipendentiBean dipendente=(DipendentiBean)session.getAttribute("dipendente"); %>
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(dipendente.getNome().charAt(0))%><%=Character.toUpperCase(dipendente.getCognome().charAt(0))%> </p>

		<%	String success=(String)request.getAttribute("success");
			if(success!=null && !success.equals("")){
		%>		<div class="dialog">
					<header class="dialogTitle">Fatto!</header>
						<section><%=success%></section>
						<div class="clear"></div>
						<a href="/UfficioPostale2/dipendente/addetto/NoCarta.jsp">back</a>
						<div id="sfondoDiv"></div>
				</div>
		<%		
			}else{
				ClienteModel modelC=new ClienteModel();
				ArrayList<ContoCliente> array= modelC.cercaNoCarta();
				if(array==null || array.isEmpty()){
		%>
					<div class="dialog">
						<header class="dialogTitle">Spiacente!</header>
						<section>Non ci sono clienti da mostrare!</section>
						<div class="clear"></div>
						<a href="/UfficioPostale2/dipendente/homeDipendente.jsp">Back</a>
						<div id="sfondoDiv"></div>
					</div>
		<%	
				}else{		
				
						
		%>				<div id="scrittaElenco">Elenco dei clienti con conto sprovvisto di Carta</div>
						<div id="descrizione">
							In questa sezione è possibile visualizzare un elenco dei clienti i cui conti sono privi di una Carta.
							&Egrave; possibile invitare un cliente ad associare una carta ai propri conti<br> 
							BancoPosta, attraverso l'azione "invia richiesta".
							&Egrave; possibile visualizzare ulteriori dettagli riguardo il cliente, selezionando la voce "visualizza".<br>
						</div>	
						<table>
							<tr>
								<th>Nome</th>
								<th>Cognome</th>
								<th class="large">Indirizzo</th>
								<th class="large">Data di nascita</th>
								<th>Conto</th>
								<th colspan="2">Azioni</th>
							</tr>
		<% 
					for (ContoCliente cc: array){
						ClienteBean cliente= cc.getCliente();
		%>				<tr>
							<td><%=cliente.getNome()%></td>
							<td><%=cliente.getCognome()%></td>
							<td class="large"><%=cliente.getIndirizzo()%></td>
								<%DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
							<td class="large"><%=dateScad.format(cliente.getDataNascita())%></td>
							<td><span class="large"><%=cc.getIban()%></span> <span class="notLarge">IT76P0****<%=cc.getIban().substring(24, 27)%></span></td>
							<td>
								<form action="NoCartaServlet" method="post">
									<input type="text" value="<%=cc.getIban()%>" hidden="true" name="iban">
									<input type="text" value="<%=cliente.getCf()%>" hidden="true" name="cf">
									<input type="text" hidden="true" name="matricola" value="<%=((DipendentiBean)session.getAttribute("dipendente")).getMatricola()%>">
									<input type="submit"  value="invia richiesta">
								</form>
							</td>
							<td>
								<form action="dettagli.jsp?tipo=NoC" method="post">
									<input type="text" value="<%=cc.getIban()%>" hidden="true" name="iban">
									<input type="text" value="<%=cliente.getCf()%>" hidden="true" name="cf">
									<input type="submit"  value="visualizza">
								</form>
							</td>			
						</tr>		
		<% 					
					}
		%>			</table>
		
		<% 		}
			}
		%>

	</div>
	<div id="prova"></div>
	<%@ include file="../../footer.html" %>	
	</body>
</html>