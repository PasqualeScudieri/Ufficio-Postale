<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="bean.OperazioniBean"%>
<%@page import="model.OperazioniModel"%>
<%@page import="model.ContoModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Ufficio Postale</title> 
		<link rel="icon" href="/UfficioPostale2/immagini/up.png">
		
		<link type="text/css" href="/UfficioPostale2/css/perCinzia2.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/table.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/index.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/cliente/css/utente.css" rel= "stylesheet">
	
		<link type="text/css" href="css/userPostePay.css" rel= "stylesheet">
		
		<script type="text/javascript" src="/UfficioPostale2/script.js"></script>
		<link type="text/css" href="/UfficioPostale2/css/navUser.css" rel= "stylesheet">
		<script type="text/javascript" src="/UfficioPostale2/cliente/user.js"></script>
	</head>
	
	<body onresize="resize();">
	<%@ include file="headerUser.jsp" %>
	<%@ include file="navUser.jsp" %>


	<div id="mainDiv">
		<p id="inzialiNascoste" style="display: none;"> <%=Character.toUpperCase(cliente.getNome().charAt(0))%><%=Character.toUpperCase(cliente.getCognome().charAt(0))%> </p>

	<%	
		String success=(String)request.getAttribute("success");
		if(success!= null && !success.equals("")){
	%>
			<div><%=success%></div>
	<% 	
		}else{
			String iban=request.getParameter("iban");
			ContoModel modelConto= new ContoModel();
			BancopostaBean bpb=modelBP.cercaByIban(iban);
	%>
			<div id="scrittaElenco">Informazioni sul conto BancoPosta</div>
			<div>
				<div id="ibanDiv">Iban: <%=iban%></div>
				<div id="infoDiv">
				Saldo: <%=modelConto.cercaSaldo(iban)%>&euro;<br/>
				Tasso: <%=bpb.getTasso()%><br/>
				<%char carta=bpb.getCarta();%>
				Carta: <%=carta%><br/>
				Costo: <%=bpb.getCosto()%><br/>
				<%char servInternet=bpb.getServInternet();%>
				Servizi Internet: <%=servInternet%><br/>
				</div>
			
	<%		if(carta=='n' || servInternet=='n'){
	%>			<form action="AttivaCarta" method="post">
					<input type="text" value="<%=bpb.getIban()%>" hidden="true" name="iban">
	<%				if(carta=='n'){
	%> 					<input type="submit" class="bottone" value="Attiva Carta" name="carta">
	<% 				}
					if(servInternet=='n'){
	%>					<input type="submit"  class="bottone" value="Attiva Servizi Internet" name="serviziInternet">
	<%				}
	%>			</form>
	<%		
			}
	%>
			</div>

	<% 
		/*	if(servInternet=='n'){
	%>			<form action="AttivaServiziInternetServlet" method="post">
					<input type="text" value="<%=bpb.getIban()%>" hidden="true">
					
				</form>
	<%		
			}*/
	%>
	
	<%
			OperazioniModel modelOper= new OperazioniModel();
			ArrayList<OperazioniBean> operArray= modelOper.cercaOperazioni(iban);
			if(operArray==null || operArray.isEmpty()){
				%><div class="clear"></div>
				<div>Non ci sono operazioni da mostrare</div><% 
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
						<td><%=operArray.get(i).getSegno() %><%=operArray.get(i).getImporto()%></td>
						<td><%=operArray.get(i).getTipo()%></td>
						<% DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);%>
						<td><%=dateScad.format(operArray.get(i).getDataOper())%></td>
<%-- 						<td><%=operArray.get(i).getImporto() %></td> --%>
					  </tr>
				<% 
				}
			%>
				</table><%
			}
		}
	%>	

	</div>
	
	<%@ include file="../footer.html" %>	
	</body>
</html>