<%@page import="bean.ClienteBean"%>
<%@page import="bean.NotificheBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.NotificheModel"%>
<header id="mainHeader">
	<div class="container" id="sandwich" onclick="myFunction(this); compari();">
        <div class="bar1"></div>
        <div class="bar2"></div>
    	<div class="bar3"></div>
    </div>
      
    <a id="scrittaUfficio" href="/UfficioPostale2/home.jsp"> <!--  Logo qua dentro --> Ufficio Postale</a>
	
	
	<a title="Login" href="/UfficioPostale2/login.jsp"><img src="/UfficioPostale2/immagini/omino.png" alt="Login" id="omino"> </a>
	
	<div id="nome" onclick="iniziali()" style="align:center"></div>
	<a href="/UfficioPostale2/LogoutServlet"><img src="/UfficioPostale2/immagini/logout.png" alt="logout" id="logout"></a>
	<img src="/UfficioPostale2/immagini/bell-ring.png" id="triangolo" onclick="appariMenu()">
	<%NotificheModel modelN= new NotificheModel(); 
	ArrayList<NotificheBean> notifiche= modelN.cercaByCf(((ClienteBean)session.getAttribute("cliente")).getCf());
	int n=notifiche.size();

	%>
	<%	if(!notifiche.isEmpty()){
	%>		<div id="numNotifiche" style="align:center;" onclick="appariMenu()"><%=n%></div>
	<%	} 
	%>
	<div id="menu">
		<%	//System.out.println("il cf è "+((ClienteBean)session.getAttribute("cliente")).getCf());
			
			//System.out.println("notifiche" + notifiche);
			if(notifiche==null || notifiche.isEmpty()){
		%>		<ul><li>non ci sono notifiche </li></ul>
		<%		
			}else{
		%>		
				<ul>
		<% 			
					for(NotificheBean notifica: notifiche){	
						if(notifica.getTipo().equals("aggiungiCarta") || notifica.getTipo().equals("aggiungiServiziInternet")){
		%>					<li><a href="/UfficioPostale2/cliente/notifica.jsp?matricola=<%=notifica.getMatricola()%>&cod=<%=notifica.getCodice()%>&tipo=<%=notifica.getTipo()%>&iban=<%=notifica.getIban()%>"><%=notifica.getTipo()%></a></li>
		<% 				
						}else if(notifica.getTipo().equals("postaConsegnata")){
		%>					<li><a href="/UfficioPostale2/cliente/notifica.jsp?matricola=<%=notifica.getMatricola()%>&cod=<%=notifica.getCodice()%>&tipo=<%=notifica.getTipo()%>&codPosta=<%=notifica.getCodPosta()%>"><%=notifica.getTipo()%></a></li>
		<% 				}else{
		%>					<li><a href="/UfficioPostale2/cliente/notifica.jsp?matricola=<%=notifica.getMatricola()%>&cod=<%=notifica.getCodice()%>&tipo=<%=notifica.getTipo()%>"><%=notifica.getTipo()%></a></li>
		<%				}
					}
		%>
				</ul>
		<%}
		%>
	</div>
	 
</header>