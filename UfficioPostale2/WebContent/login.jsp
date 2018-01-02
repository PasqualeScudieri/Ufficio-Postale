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
			
			<form action="/UfficioPostale2/LoginServlet" method="post" id="loginForm" >
				
					<%	Object loggato= session.getAttribute("logged");
						if(loggato!=null){
							System.out.println("logged non è null");
							
							boolean logged= (Boolean) loggato;
							if(logged){
								System.out.println("sei già loggato");
								if(session.getAttribute("cliente")!=null){
									System.out.println("sono un cliente");
									//response.sendRedirect("/UfficioPostale2/user/utente.jsp");
									RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/homeCliente.jsp");
									dispatcher.forward(request, response);
									return;
								}
								if(session.getAttribute("dipendente")!=null){
									System.out.println("sono un dipendente");
									RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/dipendente/homeDipendente.jsp");
									dispatcher.forward(request, response);
									return;
								}

							}

						}else{
							System.out.println("logged è null");
						}
						
					
						String name= (String)request.getAttribute("name");
						if(name == null) name= "";
				
				 		String error= (String)request.getAttribute("error");
						if(error != null) {
					%>
							<div class="error"><%=error %> </div>	<!-- problema del maggiore e minore -->
					<%
				 		}
					%>
					
					<input id="inputUser" class="input" type="text" name="name" placeholder="Username" value="<%=name%>"><br>
 					<input class="input" type="password" name="password" placeholder="Password"><br>
					<input id="bottone" type= "submit" value="Login"><br>
					
					Non sei registrato? <a href="registrazione.jsp" id="registrati">Registrati</a>
			</form>
			
		</div>

	</body>
</html>