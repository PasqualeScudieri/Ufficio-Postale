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
		<link type="text/css" href="/UfficioPostale2/cliente/css/utente.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/navUser.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/formCentro.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/table.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/visualizza.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/perCinzia2.css" rel= "stylesheet">
		
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
		<style type="text/css">
			a:HOVER{
				font-size: 18px;
			}
			
			a{
				color:#00897b;
				text-decoration: none;
			}
		</style>
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


	<div id="mainDiv">
		
		<%ClienteBean cliente1=(ClienteBean)session.getAttribute("cliente"); %>
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(cliente1.getNome().charAt(0))%><%=Character.toUpperCase(cliente1.getCognome().charAt(0))%> </p>

			<div id="scrittaElenco">Cerca i dati del destinatario</div>
			<div id="descrizione">
				Effettuare una ricerca è semplice!<br/>
				Ti basta inserire nome, cognome e indirizzo del destinatario e premere su cerca.<br/>
				Se la persona che stai cercando è tra i nostri clienti allora ti comparirà il suo codice fiscale.<br/>
				A questo punto ti basta copiarlo e inserirlo nella form per la spedizione<br/> 
			</div>
				<section id="spedisciSection">
	
		
				<form action="CercaDestinatarioServlet"method="post">
					<p><span class="label">Nome:</span>
					<span>	
						<input type="text" name="nome" placeholder="inserisci il nome ">
					</span>
					</p>
					<p><span class="label">Cognome:</span>
					<span>	
						<input type="text" name="cognome" placeholder="inserisci il cognome ">
					</span>
					</p>
					<p><span class="label">Indirizzo:</span>
					<span>	
						<input type="text" name="nome" placeholder="inserisci l'indirizzo ">
					</span>
					</p>
						<%		
		
			 	String error= (String)request.getAttribute("error");
				if(error != null) {
		%>			
					<label class="error" id="error" style="padding-left: 5%"><%=error%></label>
					<p></p>	
		<% 		}else{
		%>
					<div class="error" id="error"></div>
		<%
				}
		%>
					<input type="submit" class="bottone" value="Cerca">
				</form>
				
			</section>
			
		<%	
		ArrayList<ClienteBean> risultati=null;
		try{
			risultati=(ArrayList<ClienteBean>)request.getAttribute("risultatoRicerca");
		}catch(ClassCastException e){
			e.printStackTrace();
		}
			//System.out.println(risultati.toString());
			if(risultati!=null && risultati.size()==0){
		%>
				<label class="error" id="error">La ricerca non ha prodotto alcun risultato :(</label>
				<p></p>	
		<%
			}else if(risultati!=null){
		%>
				<table>
					<tr>
						<th>Nome</th>
						<th>Cognome</th>
						<th>Cf</th>
						<th>Indirizzo</th>
						<th>Data di Nascita</th>
						
					</tr>
		<%
				for(ClienteBean c: risultati){
		%>
					<tr>
						<td><%=c.getNome()%></td>
						<td><%=c.getCognome() %></td>
						<td class="cfLink"><a href="/UfficioPostale2/cliente/spedisci.jsp?cod=<%=c.getCf()%>" title="spedisci a questo cliente"><%=c.getCf() %></a></td>
						<td><%=c.getIndirizzo() %></td>
						<%DateFormat dateFormat =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY); %>
						<td><%=dateFormat.format(c.getDataNascita())%></td>
					</tr>
		<%
				}			
		%>
				</table>
		
		<% 
			}
		%>
		
	</div>
	<div id="prova"></div>
	<%@ include file="../footer.html" %>	
	</body>
</html>