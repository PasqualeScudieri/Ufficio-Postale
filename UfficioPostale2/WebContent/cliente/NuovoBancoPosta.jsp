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
		<link type="text/css" href="/UfficioPostale2/cliente/css/userPostePay.css" rel= "stylesheet">
	
		<link type="text/css" href="../css/index.css" rel= "stylesheet">
		<link type="text/css" href="css/utente.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/navUser.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/radio.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/table.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/visualizza.css" rel= "stylesheet">
		
		<link type="text/css" href="/UfficioPostale2/css/formCentro.css" rel= "stylesheet">
		
		<script type="text/javascript" src="../script.js"></script>
		<script type="text/javascript" src="user.js"></script>
		<script type="text/javascript">
			function calcolaCosto(){
				var r1= document.forms.bpform.carta;
				var r2= document.forms.bpform.servInternet;
				var carta;
				var servInternet;
				
				for(var i=0;i<r1.length;i++){
					if(r1[i].checked){
						carta=r1[i].value;
						break;
					}
				}
				
				for(var i=0;i<r2.length;i++){
					if(r2[i].checked){
						servInternet=r2[i].value;
						break;
					}
				}

				if(carta=='y' && servInternet=='y'){
					document.getElementById("costo").value=5;
				}else if(carta=='y' || servInternet=='y'){
					document.getElementById("costo").value=7;
				}else{
					document.getElementById("costo").value=10;
				}
			}
		</script>
		
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


	<div id="mainDiv">
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(cliente.getNome().charAt(0))%><%=Character.toUpperCase(cliente.getCognome().charAt(0))%> </p>
	
		<%BancopostaBean bpb=(BancopostaBean)request.getAttribute("bancoPosta"); 
		if(bpb!=null && bpb.getCosto()!=0){
		%>	
			<div id="scrittaElenco">Informazioni sul conto BancoPosta appena creato:</div>
			<section id="infoSection">
				<p><span>Iban:</span><span><%=bpb.getIban() %></span></p>
				<p><span>Tasso:</span><span><%=bpb.getTasso() %></span></p>
				<p><span>Carta:</span><span><%=bpb.getCarta() %></span></p>
				<p><span>Costo:</span><span><%=bpb.getCosto() %>&euro;</span></p>
				<p><span>Servizi Internet:</span><span><%=bpb.getServInternet()%></span></p>
			</section>
		<% 
		 }else{
		%> 
			<div id="scrittaElenco">Crea un nuovo conto BancoPosta</div>
			
			<div id="descrizione">
				Creare un nuovo conto BancoPosta è semplice!<br/>
				Basta scegliere se associare una carta e se si desidera attivare i servizi internet, per usufruire delle funzionalità del sito.
				Maggiore è il numero <br>di servizi che si sceglie di attivare, minore sarà il costo.
				Inoltre senza servizi internet non è possibile effettuare operazioni di giroconto o bonifici.<br/>
				*Il tasso è fissato ad un valore di 0.20.			
			</div>
			
			<form action="NuovoBancoPostaServlet" method="post" name="bpform" id="nuovoBP">
				<!-- tasso ha detto mamma HA HAHAHAHAHHA metti0.20 e l agg trattat	 -->
				<section>

				<p><span>Carta:</span>
				<span>
					<input type="radio" class="radioBP" name="carta" value="y" onclick="calcolaCosto()">y
					<input type="radio" class="radioBP" name="carta" value="n" onclick="calcolaCosto()" checked>n
				</span>
				</p>

				<p><span>Servizi Internet:</span>
				<span>
					<input type="radio" class="radioBP" name="servInternet"  value="y" onclick="calcolaCosto()"> y
					<input type="radio" class="radioBP" name="servInternet"  value="n" onclick="calcolaCosto()" checked> n
				</span>
				</p>
				<p><span>Costo:</span>
				<span id="costoDisabled"><input type="number" value="10" name="costo" id="costo" readonly>€
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
			
			<input type="submit" id="SubmitBP"class="bottone"value="Submit">
		</form>
		<%  
		 }
		%>
		
		
		
	</div>
	<div id="prova"></div>
	<%@ include file="../footer.html" %>	
	</body>
</html>