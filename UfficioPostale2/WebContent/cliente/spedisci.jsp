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
		<script type="text/javascript">
			function pacchi(){
				var tipo= document.getElementById("tipo").value;
				console.log("tipo" + tipo);
				if(tipo=="pacchi"){
					console.log("nell if");
					document.getElementById("peso").hidden=false;
					document.getElementById("pesoLabel").hidden=false;
					document.getElementById("volLabel").hidden=false;
					document.getElementById("volume").hidden=false;
				}else{
					console.log("nell esle");
					document.getElementById("peso").hidden=true;
					document.getElementById("volume").hidden=true;
					document.getElementById("pesoLabel").hidden=true;
					document.getElementById("volLabel").hidden=true;

				}
			}
		</script>
		
		<style type="text/css">
			#etichetta{
				background: none;
				background-color: transparent;
				border: none;
				font-family: "Google font light";
				font-variant: small-caps;
				font-weight: bold;
				float:left;
				color:black;
				margin-left:-5%;
			}
			
			#error{
				margin-left:5%;
			}
		</style>
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


	<div id="mainDiv">
		
		<%ClienteBean cliente1=(ClienteBean)session.getAttribute("cliente"); %>
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(cliente1.getNome().charAt(0))%><%=Character.toUpperCase(cliente1.getCognome().charAt(0))%> </p>

		<%	String success=(String)request.getAttribute("success"); 
			if(success!=null){
		%>
				
				<div class="dialog">
					<header class="dialogTitle">Fatto!</header>
					<section><%=success%></section>
					<div class="clear"></div>
<!-- 					<form action="EtichettaServlet" method="post"> -->
<%-- 						<input name="codice" type="text" value="<%=request.getAttribute("codice")%>" hidden="true"> --%>

<!-- 						<input type="submit" id="etichetta" value="etichetta"> -->
<!-- 					</form> -->
					<a class="notificaLink" href="/UfficioPostale2/cliente/EtichettaServlet?codice=<%=request.getAttribute("codice")%>">etichetta</a>
					<a href="/UfficioPostale2/cliente/spedisci.jsp">back</a>
					<div id="sfondoDiv"></div>
				</div>
		<%
			}else{
		%>
			<div id="scrittaElenco">Spedizione della Posta</div>
			<div id="descrizione">
				Effettuare una spedizione è semplice!<br/>
				Ti basta inserire nome e cognome del destinatario, l'indirizzo  e il tipo di posta che desideri inviare. <br/>			
				In questo modo alla tua posta verrà assegnato un codice. In seguito i postini quando effettueranno la consegna<br/>
				potranno inviare una notifica sia a te che al destinatario avvisandovi dell'avvenuta consegna.<br/>
				Inoltre se stai effettuando la spedizione di un pacco ti sarà possibile inserire anche altre informazioni come il peso e il volume del pacco<br/>
			
			</div>
				<section id="spedisciSection">
	
			<% 	String destin= (String)request.getAttribute("destinatario");
				if(destin == null) destin= "";
				
				String indirizzo= (String) request.getAttribute("indirizzo");
				if(indirizzo == null) indirizzo= "";
			%>
				<form action="SpedisciServlet"method="post">
					<p><span class="label">Destinatario:</span>
					<span>	
						<input type="text" name="dest" value="<%=destin%>"  placeholder="indica il destinatario" >
					</span>
					</p>
					<p><span class="label">Indirizzo:</span>
					<span>	
						<input type="text" name="indirizzo" value="<%=indirizzo%>"  placeholder="inserisci l'indirizzo" >
					</span>
					</p>
					<p><span class="label"> Tipo:</span>
					<span>
						<select name="tipo" onchange="pacchi()" id="tipo">
							<option value="raccomandate">Raccomandate</option>
							<option value="pacchi">Pacchi</option>
							<option value="lettere" >Lettere</option>
						</select>
					</span>
					</p>
					<p><span class="label">
						<label id="pesoLabel" hidden="true">Peso: (max 15kg)</label>
					</span>
					<span>
						<input type="number" min=0.1 max=15 name="peso" id="peso" step="0.01" hidden="true">
					</span>
					</p>
					<p><span class="label">
						<label id="volLabel" hidden="true">Volume: (max 750 dm &sup3;)</label>
					</span>
					<span>
						<input type="number" min=0.1 max=750 name="volume" id="volume" step="0.01" hidden="true">
					</span>
					</p>
					
						<%		
		
			 	String error= (String)request.getAttribute("error");
				if(error != null) {
		%>			
					<div class="error" id="error"><%=error%></div>
					<p></p>	
		<% 		}else{
		%>
					<div class="error" id="error"></div>
		<%
				}
		%>
					<input type="submit" class="bottone" value="submit">
				</form>
				
			</section>
		<%} 
		%>				
		
	</div>
	<div id="prova"></div>
	<%@ include file="../footer.html" %>	
	</body>
</html>