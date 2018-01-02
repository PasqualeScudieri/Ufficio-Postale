<%@page import="bean.PacchiBean"%>
<%@page import="model.PacchiModel"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="bean.PostaBean"%>
<%@page import="model.PostaModel"%>
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
		<link type="text/css" href="/UfficioPostale2/css/visualizza.css" rel= "stylesheet">
		
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
		
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


	<div id="mainDiv">
		<%ClienteBean cliente1=(ClienteBean)session.getAttribute("cliente"); %>
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(cliente1.getNome().charAt(0))%><%=Character.toUpperCase(cliente1.getCognome().charAt(0))%> </p>
		<div id="scrittaElenco">Informazioni sulla posta inviata</div>
		<div id="descrizione">
			In questa pagina è possibile visualizzare tutte le informazioni sulla posta che hai inviato.
		</div>


		<%	PostaModel modelPo=new PostaModel();
			PacchiModel modelPa=new PacchiModel();
			ArrayList<PostaBean> arrayP=modelPo.cercaPerCfMit(cliente.getCf());
			for(PostaBean posta: arrayP){
		%>		<div id="postadiv">
				<section class="PostaSection">
<!-- 					<p><span>Mittente:</span> -->
<%-- 					<span><%=posta.getMittente()%></span> --%>
<!-- 					</p> -->
					<p><span>Destinatario:</span>
					<span><%=posta.getDestinatario()%></span>
				 	</p>
				 	<p><span>Indirizzo:</span>
					<span><%=posta.getIndirizzo()%></span>
				 	</p>
				 	<p><span>Codice:</span>
					<span><%=posta.getCodice()%></span>
					</p>
				</section>
				<section class="PostaSection">
				 	<p><span>Dipendente:</span>
				 	<span><%=posta.getDipendente()==0? "Non consegnato" : posta.getDipendente()%></span>
				 	</p>
					<% DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
					<%//System.out.println("postaaa:"+posta.getDataSpedizione()); %>
					<p><span>Data Spedizione:</span>
					<span><%=dateScad.format(posta.getDataSpedizione())%></span>
				 	</p>
		<% 		if(posta.getDataConsegna()!=null){
		%>			<p><span>Data Consegna:</span>
					<span><%=dateScad.format(posta.getDataConsegna())%></span>
					</p>
		<%		}else{
		%>			<p><span>Data Consegna:</span>
					<span>Non ancora consegnato.</span>
					</p>
		<%	
				}
		%>
				</section>
		<%		PacchiBean pacco= modelPa.cercaPacco(posta.getCodice());
				if(pacco!=null && pacco.getCodice()!=0){
		%>
<%-- 					Codice: <%=pacco.getCodice()%><br/> --%>
					<section class="PacchiSection">
						<p><span>Peso:</span>
						<span><%=pacco.getPeso()%></span>
						</p>
						<p><span>Volume:</span>
						<span><%=pacco.getVolume()%></span>
						</p>
					</section>
		<% 
				}
		%>
				<div class="clear"></div>
				<hr noshade size="1px"/>
			</div>
			
		<%
			}
		%>
				
	</div>

	<%@ include file="../footer.html" %>	
	</body>
</html>