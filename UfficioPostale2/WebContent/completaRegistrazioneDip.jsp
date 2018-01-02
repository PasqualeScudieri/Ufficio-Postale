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
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		
	</head>
	
	<body onresize="resize();">            
	
		<div id="loginDiv">
		
			<img id="LogoUfficiale" src="/UfficioPostale2/immagini/LogoUfficiale.png" alt="LogoUfficiale">
			
			<form action="/UfficioPostale2/CompletaRegistrazione" method="post" id="loginForm" >
				
					<%					
						String name= (String)request.getParameter("nome");
						
						if(name == null ){
							
							name=(String)request.getAttribute("user");
							System.out.println("pasquale");
						}
				
				 		String error= (String)request.getAttribute("error");
						if(error != null) {
					%>
							<div class="error"><%=error %> </div>	<!-- problema del maggiore e minore -->
					<%
				 		}
					%>
					
					<input id="inputUser" class="input" type="text" name="name" readonly placeholder="Username" value="<%=name%>"><br>
 					<input class="input" type="password" name="password" placeholder="Password"><br>
					<input id="bottone" type= "submit" value="Registrati"><br>
					
			</form>
			
		</div>

	</body>
</html>