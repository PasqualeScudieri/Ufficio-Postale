<%@page import="bean.ContoBean"%>
<%@page import="model.ClienteModel"%>
<%@page import="model.ContoModel"%>
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
		<link type="text/css" href="/UfficioPostale2/css/perCinzia.css" rel= "stylesheet">
		
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
		
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


	<div id="mainDiv">
		
		<%ClienteBean cliente1=(ClienteBean)session.getAttribute("cliente"); %>
		<div id="benvenuto">
		<div id="inzialiNascoste"> <%=Character.toUpperCase(cliente1.getNome().charAt(0))%><%=Character.toUpperCase(cliente1.getCognome().charAt(0))%> </div>
		Benvenuto<br/>
		<%=cliente.getNome() %> <%=cliente.getCognome()%>
		</div>
		
<%-- 		sessionId:<%=session.getId()%><br/> --%>
<%-- 		logged:<%=session.getAttribute("logged") %><br> --%>
<%-- 		tempo: <%=session.getMaxInactiveInterval() %><br/> --%>
		<div id="saldoDiv">
			<span>Saldo:
			<%
				ContoModel modelC= new ContoModel();
				int saldo= modelC.cercaPerCf(cliente.getCf());		
			%>
			<%=saldo%>&euro;
			</span>
		</div>
		
		<span class="title"> Cosa desideri fare?</span>
		<div class="clear"></div>
		<section class="azioni"> 
			<header><span>Spedisci</span></header>
			<section>
				Spedisci online ciò che desideri. Il modo più comodo per spedire in Italia e nel mondo. Inserisci le informazioni per spedire pacchi, lettere o raccomandate.
			</section>
			<div class="clear"></div>	
			<a href="/UfficioPostale2/cliente/spedisci.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
		</section>
		
		<section class="azioni"> 
			<header><span>Posta inviata</span></header>
			<section>
				Visualizza i dettagli sulla posta da te inviata. Guarda il giorno e il destinatario a cui è stata inviata, la data in cui è stata consegnata, e tutti le informazioni relativi al prodotto.
			</section>
			<div class="clear"></div>
			<a href="/UfficioPostale2/cliente/postaSpedita.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
		</section>

	
		
		<section class="azioni"> 
			<header><span>Nuova PostePay</span></header>
			<section>
				Le carte ricaricabili per te che vuoi evitare il contante o fare acquisti online. &Egrave; possibile tenere sotto controllo i movimenti delle proprie PostePay in qualsiasi momento.
			</section>
			<div class="clear"></div>
			<a href="/UfficioPostale2/cliente/NuovaPostePay.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
		</section>
		
		<section class="azioni"> 
			<header><span>Nuovo Conto</span></header>
			<section>
				La comodità di un conto che consente di gestire il tuo denaro dall'ufficio postale, dal PC o dal tuo smartphone.
			</section>
			<div class="clear"></div>
			<a href="/UfficioPostale2/cliente/NuovoBancoPosta.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
		</section>
		
		<section class="azioni"> 
			<header><span>Bonifico</span></header>
			<section>
				Trasferisci il tuo denaro da uno dei tuoi conti a quello di un'altra persona, direttamente da casa tramite pc, smartphone o tablet.
			</section>
			<div class="clear"></div>
			<a href="/UfficioPostale2/cliente/Bonifico.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>
		</section>
		
		<section class="azioni"> 
			<header><span>GiroConto</span></header>
			<section>
				Trasferire il tuo denaro non è mai stato così facile. Con GiroConto puoi trasferire fondi dai tuoi conti direttamente da smartphone e tablet.
			</section>
			<div class="clear"></div>
			<a href="/UfficioPostale2/cliente/Giroconto.jsp"><img alt="go" src="/UfficioPostale2/immagini/frecciaBianca.png" class="frecciaIcon"></a>			

		</section>


	</div>
	<div id="prova"></div>
	<%@ include file="../footer.html" %>	
	</body>
</html>
