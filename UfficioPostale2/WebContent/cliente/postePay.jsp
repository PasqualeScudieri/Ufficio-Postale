<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="model.OperazioniModel"%>
<%@page import="model.ContoModel"%>
<%@page import="java.util.Date"%>
<%@page import="bean.OperazioniBean"%>
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
		<link type="text/css" href="/UfficioPostale2/css/table.css" rel= "stylesheet">
		<link type="text/css" href="css/userPostePay.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/perCinzia2.css" rel= "stylesheet">
		
		
		<script type="text/javascript" src="../script.js"></script>
		<link type="text/css" href="/UfficioPostale2/css/navUser.css" rel= "stylesheet">
		<script type="text/javascript" src="user.js"></script>
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


	<div id="mainDiv">
	
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(cliente.getNome().charAt(0))%><%=Character.toUpperCase(cliente.getCognome().charAt(0))%> </p>
		
		<%
		String iban=request.getParameter("iban");
		//System.out.println(iban);
			ContoModel modelConto= new ContoModel();
			postePayBean ppb=modelPP.cercaByIban(iban);
		%>
		
		<div id="scrittaElenco">Informazioni sulla PostePay</div>
		<div>
			<div id="ibanDiv">Iban: <%=iban%></div>
			<div id="infoDiv">
			<div>Saldo: <%=modelConto.cercaSaldo(iban)%>&euro;</div>
			Numero di carta: <%=ppb.getNumCarta() %><br/>
			<% DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
			Scadenza: <%=dateScad.format(ppb.getScadenza())%><br/>
			Codice di Sicurezza: <%=ppb.getCodSicur() %><br/>
			
			</div>
		</div>
		<br/>
		
		<%
			OperazioniModel modelOper= new OperazioniModel();
			ArrayList<OperazioniBean> operArray= modelOper.cercaOperazioni(iban);
			if(operArray==null || operArray.isEmpty()){
				%><p>Non ci sono operazioni da mostrare.</p><% 
			}else{
				%><table>
					<tr>
						<th>Importo</th>
						<th>Tipo</th>
						<th>Data</th>
					</tr>
				<% 
				for(int i=0; i<operArray.size();i++){
					Date date=operArray.get(i).getDataOper();
					%><tr>
						<td><%=operArray.get(i).getSegno() %> <%=operArray.get(i).getImporto()%></td>
						<td><%=operArray.get(i).getTipo()%></td>
						<%DateFormat dateFormat =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY); %>
						<td><%=dateFormat.format(operArray.get(i).getDataOper())%></td>
<%-- 					<td><%=operArray.get(i).getImporto() %></td> --%>
					  
					  </tr>
					<% 
				}			
				%>
				</table><%
			}
		%>	
	
	</div>
	
	<%@ include file="../footer.html" %>	
	</body>
</html>