<%@page import="model.ContoModel"%>
<%@page import="model.ClienteModel"%>
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
		<link type="text/css" href="/UfficioPostale2/css/formCentro.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/table.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/visualizza.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/perCinzia2.css" rel= "stylesheet">
		
		<script type="text/javascript" src="../script.js"></script>
		<script type="text/javascript" src="ibanEqual.js"></script>
		<script type="text/javascript" src="user.js"></script>
		<script type="text/javascript" src="saldo.js"></script>
		
		<script type="text/javascript">
			/*DEVI FARE  I CONTROLLI SU SE NN CE NE STANNO DI IBAN(size=0)*/
			function aggiornaIban(id, listXML){
				console.log("aggiorna iban è stata chiamata con il parametro: "+ id);
				var select=document.getElementById(id);
				while (select.hasChildNodes()) {
				    select.removeChild(select.childNodes[0]);
				}

				var size = listXML.getElementsByTagName("size")[0].firstChild.nodeValue;
				
				var i=0;
				console.log("size= " + size);
				for(i=0; i<parseInt(size);i++){
					var opt=document.createElement("option");
					
					var rdfs=listXML.getElementsByTagName("iban")[i].firstChild.nodeValue; 
					opt.innerHTML =rdfs;
					select.appendChild(opt);
					console.log("i="+i);
				}
			}
			
			function controllaImporto() {
				var saldo=document.getElementById("saldo").value;
				var importo=document.getElementById("importo").value;
				if(parseInt(importo)>parseInt(saldo)){
					document.getElementById("error").innerHTML="Importo maggiore del saldo";
					document.getElementById("submit").disabled=true;
				}else{
					document.getElementById("error").innerHTML="";
					document.getElementById("submit").disabled=false;
				}
			}
		</script>
		
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


	<div id="mainDiv">
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(cliente.getNome().charAt(0))%><%=Character.toUpperCase(cliente.getCognome().charAt(0))%> </p>

		<%/*String success=(String)request.getAttribute("success"); 
			if(success!=null){
		%>
				<div class="dialog">
					<header class="dialogTitle">Fatto!</header>
					<section><%=success%></section>
					<div class="clear"></div>
					<a href="/UfficioPostale2/cliente/Giroconto.jsp">back</a>
					<div id="sfondoDiv"></div>
				</div>
		<%
			}else*/	if((arrayBP== null || arrayBP.isEmpty()) && array!=null && array.size()==1){
		%>
				<div class="dialog">
					<header class="dialogTitle">Spiacente</header>
					<section>Non puoi effettuare giroconti</section>
					<div class="clear"></div>
					<a href="/UfficioPostale2/cliente/homeCliente.jsp">back</a>
					<div id="sfondoDiv"></div>
				</div>
		<%	
			}else if((array== null || array.isEmpty()) && arrayBP!=null && arrayBP.size()==1){
			
		%>
				<div class="dialog">
					<header class="dialogTitle">Spiacente</header>
					<section>Non puoi effettuare giroconti</section>
					<div class="clear"></div>
					<a href="/UfficioPostale2/cliente/homeCliente.jsp">back</a>
					<div id="sfondoDiv"></div>
				</div>
		<%	
			}else{
				
		%>	
			<div id="scrittaElenco">Operazione di Giroconto</div>
			<div id="descrizione">
				Un'operazione di giroconto permette di trasferire soldi da un proprio conto ad un altro.<br/>
				Effettuare un'operazione di giroconto è semplice. Basta selezionare il conto da cui prelevare i soldi e quello a cui trasferirli.<br/>
				A questo punto, basta scegliere l'importo da trasferire, con la condizione che l'importo non può essere superiore al saldo.<br/>
				Nel caso in cui l'iban del conto da cui trasferire i soldi non compaia nell'elenco, bisogna verificare che i servizi internet siano attivi.
			</div>
			
			
			<form action="GirocontoServlet" method="post">
				<section id="opSection">
					<p><span>From:</span>
					<span>
						<select name="from" id="from" onchange="ibanAjax('/UfficioPostale2/cliente/IbanServlet',aggiornaIban,document.getElementById('from').value, '<%=cliente.getCf()%>','to'); saldoAjax('/UfficioPostale2/cliente/SaldoServlet',aggiornaSaldo, document.getElementById('from').value);">
						<%
							if((arrayBP==null ||arrayBP.isEmpty()) && ( array==null || array.isEmpty())){
						%>		<option disabled selected hidden="">Non ci sono conti</option>
						<% 
							}else{
						%>
								<option disabled selected hidden="">Scegli un conto</option>
						<%
								for(int i=0;i<arrayBP.size();i++){
									if(arrayBP.get(i).getServInternet()=='y'){
						%>
										<option  value="<%=arrayBP.get(i).getIban()%>"><%=arrayBP.get(i).getIban()%></option>
						<%			}
								}
							
								for(int i=0;i<array.size();i++){
						%>
										<option value="<%=array.get(i).getIban()%>"><%=array.get(i).getIban()%></option>
						<%		}
						   }
						%>
					
						</select>
					</span>
					</p>
						
					<p><span>To:</span>
					<span>
						<select name="to" id="to" onchange="ibanAjax('/UfficioPostale2/cliente/IbanServlet',aggiornaIban,document.getElementById('to').value, '<%=cliente.getCf()%>','from');">
						<%
							if((arrayBP==null ||arrayBP.isEmpty()) && ( array==null || array.isEmpty())){
						%>		<option disabled selected hidden="">Non ci sono conti</option>
						<% 
							}else{
						%>
								<option disabled selected hidden="" >Scegli un conto</option>
						<%
								for(int i=0;i<arrayBP.size();i++){
									if(arrayBP.get(i).getServInternet()=='y'){
						%>
										<option value="<%=arrayBP.get(i).getIban()%>"><%=arrayBP.get(i).getIban()%></option>
						<%			}
								}
							
								for(int i=0;i<array.size();i++){
						%>
										<option value="<%=array.get(i).getIban()%>"><%=array.get(i).getIban()%></option>
						<%		}	
						   }
						%>
						</select>
					</span>
					</p>
				
					<p><span>Saldo:</span>
						<span><input type="number" id="saldo" value="" readonly></span>
					</p>
				
					<p><span>Importo:</span>
						<span><input min="0" onchange="controllaImporto()" value="1" type="number" step="0.01" name="importo" id="importo"></span>
					</p>
				
				
				<% 	String error= (String)request.getAttribute("error");
					if(error != null) {
				%>
						<div class="error" id="error"><%=error%></div>	
				<% }else{
				%>
					<div class="error" id="error"></div>
				<%
					}
				%>
				</section>
				<input type="submit" class="bottone" value="Submit" id="submit">
			</form>
			<%
			}
			%>
	</div>
	<%@ include file="../footer.html" %>	
	</body>
</html>