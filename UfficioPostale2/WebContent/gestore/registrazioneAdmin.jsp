<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="bean.DipendentiBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, intial-scale=1">
		<title>Ufficio Postale-login</title> 
		<link rel="icon" href="/UfficioPostale2/immagini/up.png">
		
		<link type="text/css" href="/UfficioPostale2/css/login.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/registrati.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/radio.css" rel= "stylesheet">
	
	<%
		Boolean success=(Boolean)request.getAttribute("success");
		if(success!=null && success.booleanValue()==true){
	%>

			<link type="text/css" href="/UfficioPostale2/css/visualizza.css" rel= "stylesheet">
			<link type="text/css" href="/UfficioPostale2/css/formCentro.css" rel= "stylesheet">
			<link type="text/css" href="/UfficioPostale2/css/index.css" rel= "stylesheet">

			<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
			<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>

	<%} 
	%>		
		<script type="text/javascript" src="script.js"></script>
		<script type="text/javascript">
		function erroreMargin(){
			console.log("aaaaaaaaa");
			var error=document.getElementById("errore").innerHTML;
			console.log(error.length);
			if(parseInt(error.length)>130){
				console.log("nel primo if");
				var height=document.getElementById("registraDivAdmin").offsetHeight;
				console.log(height);
				document.getElementById("registraDivAdmin").style.height=(height+200) +"px";
			}else if(parseInt(error.length)>90){
				console.log("nel secondo if");
				var height=document.getElementById("registraDivAdmin").offsetHeight;
				console.log(height);
				document.getElementById("registraDivAdmin").style.height=(height+150) +"px";
			}else if(parseInt(error.length)>1){
				console.log("nel terzp if");
				var height=document.getElementById("registraDivAdmin").offsetHeight;
				console.log(height);
				document.getElementById("registraDivAdmin").style.height=(height+100) +"px";
			} 
		}
		window.onload=erroreMargin;
		</script>
		
	</head>
	
	<body onresize="resize();">            
	<%
		if(success!=null && success.booleanValue()==true){
			DipendentiBean dip= (DipendentiBean)request.getAttribute("nuovoDip");
	%>	
		<%@ include file="../dipendente/headerAdmin.html" %>
		<%@ include file="navGestore.jsp" %>
	
		<div id="mainDiv">
			<div id="scrittaElenco">Informazioni sul dipendente appena inserito:</div>
			<section id="infoSection">
			<p><span>Nome:</span><span><%=dip.getNome()%></span></p>
			<p><span>Cognome:</span><span><%=dip.getCognome() %></span></p>
			<p><span>CF:</span><span><%=dip.getCf() %></span></p>
			<p><span>Indirizzo:</span><span><%=dip.getIndirizzo() %></span></p>
			<p><span>Luogo di Nascita:</span><span><%=dip.getLuogonascita() %></span></p>
			<% DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
			<p><span>Data di Nascita:</span><span> <%=dateScad.format(dip.getDataNascita() )%></span></p>
			<p><span>Tipo:</span><span><%=dip.getTipo() %></span></p>
			<p><span>Matricola:</span><span><%=dip.getMatricola() %></span></p>
			</section>
		</div>
	<%
		}else{
	%>
	
		<div id="registraDivAdmin">
		
			<img id="LogoUfficiale" src="/UfficioPostale2/immagini/LogoUfficiale.png" alt="LogoUfficiale">
			
			<%
				String nome= (String)request.getAttribute("nome");
				if(nome == null) nome= "";
				
				String cognome= (String) request.getAttribute("cognome");
				if(cognome == null) cognome= "";

				String cf= (String) request.getAttribute("cf");
				if(cf == null) cf= "";

				String indirizzo= (String) request.getAttribute("indirizzo");
				if(indirizzo == null) indirizzo= "";
				
				String luogoNascita= (String) request.getAttribute("luogoNascita");
				if(luogoNascita == null) luogoNascita= "";
				
				String dataNascita= (String) request.getAttribute("dataNascita");
				if(dataNascita == null) dataNascita= "";
				
				String tipo= (String) request.getAttribute("tipo");
				if(tipo == null) tipo= "";
				
				String matricola= (String) request.getAttribute("matricola");
				if(matricola== null) matricola= "";
				
				String email= (String) request.getAttribute("email");
				if(email == null) email= "";
				
		 		String error= (String)request.getAttribute("error");
				if(error != null) {
			%>
					<div class="error" id="errore"><%=error %> </div>	<!-- problema del maggiore e minore -->
			<%
				}
			%>
			
			
			<form action="RegistrazioneAdminServlet" method="post" id="loginForm" >
				<table>
					<tr>
						<td class="label">nome</td>
						<td>
							<input class="input" type="text" name="nome" placeholder="Nome" value="<%=nome%>">
						</td>
					</tr>
					<tr>
						<td class="label">cognome</td>
						<td>
							<input class="input" type="text" name="cognome" placeholder="Cognome" value="<%=cognome%>">
						</td>
					</tr>
					<tr>
						<td class="label">CF</td>
						<td>
							<input class="input" type="text" name="cf" placeholder="codice fiscale" value="<%=cf%>">
						</td>
					</tr>
					<tr>
						<td class="label">indirizzo</td>
						<td>
							<input class="input" type="text" name="indirizzo" placeholder="Indirizzo" value="<%=indirizzo%>">
						</td>
					</tr>
					<tr>
						<td class="label">luogo di nascita</td>
						<td>
							<input class="input" type="text" name="luogoNascita" placeholder="Luogo di nascita" value="<%=luogoNascita%>">
						</td>
					</tr>
					<tr>
						<td class="label">data di nascita</td>
						<td>
							<input class="input" type="date" name="dataNascita" placeholder="yyyy-mm-dd" value="<%=dataNascita%>">
						</td>
					</tr>

					<tr>
						<td class="label">tipo</td>
						<td>
							<input class="input radioRegAd" type="radio" name="tipo" value="addettoSportello" <%=tipo.equals("addettoSportello")|| tipo.equals("")? "checked" :""%>><div class="Radiolabel">addetto allo sportello</div> 
							<input class="input radioRegAd" type="radio" name="tipo" value="postino" <%=tipo.equals("postino")? "checked" :""%>><div class="Radiolabel">postino</div> 
						</td>
					</tr>

					<tr>
						<td class="label">matricola</td>
						<td>
							<input class="input" type="text" name="matricola" placeholder="matricola" value="<%=matricola%>">
						</td>
					</tr>

					<tr>
						<td class="label">email</td>
						<td>
							<input class="input" type="email" name="email" placeholder="email" value="<%=email%>">
						</td>
					</tr>

				</table>
				<input id="bottone" type="submit" value="Submit"><br>
			
			</form>
			
		</div>
	<%} %>
	</body>
</html>