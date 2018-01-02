<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, intial-scale=1">
		<title>Ufficio Postale-login</title> 
		<link rel="icon" href="immagini/up.png">
		<link type="text/css" href="css/login.css" rel= "stylesheet">
		<link type="text/css" href="css/registrati.css" rel= "stylesheet">
		
		<script type="text/javascript" src="script.js"></script>
		<script type="text/javascript">
			function erroreMargin(){
				console.log("aaaaaaaaa");
				var error=document.getElementById("errore").innerHTML;
				console.log(error.length);
				if(parseInt(error.length)>95){
					console.log("nel primo if");
					var height=document.getElementById("registraDiv").offsetHeight;
					console.log(height);
					document.getElementById("registraDiv").style.height=(height+150) +"px";
				}else if(parseInt(error.length)>1){
					console.log("nel secondo if");
					var height=document.getElementById("registraDiv").offsetHeight;
					console.log(height);
					document.getElementById("registraDiv").style.height=(height+100) +"px";
				}
			}
			window.onload=erroreMargin;
		</script>
		
	</head>
	
	<body onresize="resize();">            
	
		<div id="registraDiv">
		
			<img id="LogoUfficiale" src="immagini/LogoUfficiale.png" alt="LogoUfficiale">
			
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
				
				
				String username= (String) request.getAttribute("username");
				if(username == null) username= "";
				
				
		 		String error= (String)request.getAttribute("error");
				if(error != null) {
			%>
					<div class="error" id="errore"><%=error %> </div>	<!-- problema del maggiore e minore -->
			<%
				}
			%>
			
			
			<form action="RegistratiServlet" method="post" id="loginForm" >
				<table>
					<tr>
						<td id="label">Nome</td>
						<td>
							<input class="input" type="text" name="nome" placeholder="Nome" value="<%=nome%>">
						</td>
					</tr>
					<tr>
						<td id="label">Cognome</td>
						<td>
							<input class="input" type="text" name="cognome" placeholder="Cognome" value="<%=cognome%>">
						</td>
					</tr>
					<tr>
						<td id="label">CF</td>
						<td>
							<input class="input" type="text" name="cf" placeholder="codice fiscale" value="<%=cf%>">
						</td>
					</tr>
					<tr>
						<td id="label">Indirizzo</td>
						<td>
							<input class="input" type="text" name="indirizzo" placeholder="Indirizzo" value="<%=indirizzo%>">
						</td>
					</tr>
					<tr>
						<td id="label">Luogo di nascita</td>
						<td>
							<input class="input" type="text" name="luogoNascita" placeholder="Luogo di nascita" value="<%=luogoNascita%>">
						</td>
					</tr>
					<tr>
						<td id="label">Data di nascita</td>
						<td>
							<input class="input" type="date" name="dataNascita" placeholder="yyyy-mm-dd" value="<%=dataNascita%>">
						</td>
					</tr>
					<tr>
						<td id="label">Username</td>
						<td>
							<input class="input" type="text" name="username" placeholder="username" value="<%=username%>">
						</td>
					</tr>					
					<tr>
						<td id="label">Password</td>
						<td>
							<input class="input" type="password" name="password" placeholder="password">
						</td>
					</tr>
				</table>
				<input id="bottone" type="submit" value="Submit"><br>
<!-- 				Vuoi registrarti come admin? <a href="/UfficioPostale2/registrazioneAdmin.jsp">Clicca qui</a> -->
			</form>
			
		</div>

	</body>
</html>