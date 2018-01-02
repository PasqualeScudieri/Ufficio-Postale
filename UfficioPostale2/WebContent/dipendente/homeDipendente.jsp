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
		<link type="text/css" href="/UfficioPostale2/css/perCinzia.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/dipendente/css/homeAdmin.css" rel= "stylesheet">

		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerAdmin.html" %>
	
	<%	String type=((DipendentiBean)session.getAttribute("dipendente")).getTipo();
	
		if(type.equals("addettoSportello")){ 
	%>
			<%@ include file="addetto/navAdmin.jsp" %>
	<%	}else if(type.equals("postino")){
	%>
			<%@ include file="postino/NavPostino.jsp" %>
	<%	
		}
	%>

	<div id="mainDiv">
		
		<%DipendentiBean dipendente=(DipendentiBean)session.getAttribute("dipendente"); %>
		
		<div id="benvenuto">
		<div id="inzialiNascoste"> <%=Character.toUpperCase(dipendente.getNome().charAt(0))%><%=Character.toUpperCase(dipendente.getCognome().charAt(0))%> </div>
		Benvenuto<br/>
		<%=dipendente.getNome() %> <%=dipendente.getCognome()%>
		</div>
		
		<div id="saldoDiv">
			<span>Ruolo:
			<%=type%>
			</span>
		</div>
		

<%-- 		sessionId:<%=session.getId()%><br/> --%>
<%-- 		logged:<%=session.getAttribute("logged") %><br> --%>
<%-- 		tempo: <%=session.getMaxInactiveInterval() %><br/> --%>
		
		<span class="title"> Cosa desideri fare?</span>
		<div class="clear"></div>
		
		<%if(type.equals("addettoSportello")){
		%>
			<section class="azioni"> 
				<header><span>PostePay</span></header>
				<section>
					Visualizza l'elenco dei clienti che non sono in possesso una PostePay. Puoi visualizzare le informazioni 
					relative a questi clienti e decidere se inviare loro una notifica, invitandoli ad attivare una PostePay.
				</section>
				<div class="clear"></div>	
				<a href="/UfficioPostale2/dipendente/addetto/NoPostePay.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
			</section>
			
			<section class="azioni"> 
				<header><span>BancoPosta</span></header>
				<section>
					Visualizza l'elenco dei clienti che non hanno un conto BancoPosta. Puoi visualizzare le informazioni 
					relative a questi clienti e decidere se inviare loro una notifica, invitandoli ad aprire un conto BancoPosta.				</section>
				<div class="clear"></div>
				<a href="/UfficioPostale2/dipendente/addetto/NoBancoPosta.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
			</section>
	
			<section class="azioni"> 
				<header><span>Servizi Internet</span></header>
				<section>
					Visualizza l'elenco dei clienti in possesso di uno o più conti Bancoposta i cui Servizi Internet non sono ancora attivati.
					Puoi visualizzare le informazioni relative a questi clienti e decidere se inviare loro una notifica, invitandoli
					ad attivare i servizi internet su quei conti.				</section>
				<div class="clear"></div>
				<a href="/UfficioPostale2/dipendente/addetto/NoServiziInternet.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
			</section>
				
			<section class="azioni"> 
				<header><span>Carta</span></header>
				<section>
					Visualizza l'elenco dei clienti in possesso di uno o più conti BancoPosta, a cui non è associata una carta.
					Puoi visualizzare le informazioni relative a questi clienti e decidere se inviare loro una notifica,
					invitandoli ad attivare una carta su quei conti.
				</section>
				<div class="clear"></div>
				<a href="/UfficioPostale2/dipendente/addetto/NoCarta.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
			</section>
		<%}else{
		%>
			<section class="azioni"> 
			<header><span>Consegna</span></header>
			<section>
				Una volta consegnata la posta, è possibile inserirne il codice. In questo modo la data di consegna 
				verrà registrata come la data odierna. Inoltre mittente e destinatario, riceveranno una notifica,
				venendo dunque informati dell'avvenuta consegna. 
				Invia subito una notifica, i nostri clienti saranno molto felici di sapere che la posta è stata consegnata.
			</section>
			<div class="clear"></div>
			<a href="/UfficioPostale2/dipendente/postino/consegna.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
		</section>

			<section class="azioni"> 
			<header><span>Visualizza</span></header>
			<section>
				&Egrave; possibile sapere se un pacco è stato già consegnato, conoscere il destinatario di una raccomandata 
				o avere semplicemente informazioni relative ad un determinato pacco, lettera o raccomandata, basterà 
				inserire il codice di quel determinato prodotto nell'apposito campo, e ottenere dunque le varie informazioni.
			</section>
			<div class="clear"></div>
			<a href="/UfficioPostale2/dipendente/postino/visualizza.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
		</section>

		<% 
		}
		%>
	</div>
	<div id="prova"></div>
	<%@ include file="../footer.html" %>	
	</body>
</html>