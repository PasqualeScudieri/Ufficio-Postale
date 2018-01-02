<%@page import="model.BancoPostaModel"%>
<%@page import="bean.BancopostaBean"%>
<%@page import="model.PostePayModel"%>
<%@page import="bean.postePayBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.ClienteBean"%>
<div id="sfondoOmbra" onclick="scompari();"></div>    
<nav id="navLaterale">
	<ul>
		<li><a href="/UfficioPostale2/cliente/homeCliente.jsp"><img class="loghiBarra" src="/UfficioPostale2/immagini/home.png">Home</a></li>
	</ul>
		<hr noshade size="1px"/>	
	<ul>
		<li><img class="loghiBarra" src="/UfficioPostale2/immagini/conto2.png">Conti
			<%	ClienteBean cliente=(ClienteBean)session.getAttribute("cliente");
			String cf=cliente.getCf();
			BancoPostaModel modelBP= new BancoPostaModel();
			
			ArrayList<BancopostaBean> arrayBP= modelBP.cercaPerCf(cf);

			//System.out.println(arrayBP.size());
			%><ul>
			<%
			if(!(arrayBP==null) && !arrayBP.isEmpty()){
				
				for(int i=0;i<arrayBP.size();i++){
					String ibanCompleto=arrayBP.get(i).getIban();
					String iban=ibanCompleto.replace("0760103400100", "***");
				%><li><a href="/UfficioPostale2/cliente/BancoPosta.jsp?iban=<%=ibanCompleto%>"><%=iban%></a></li><%
				
				}
			}
				%><li> <a href="/UfficioPostale2/cliente/NuovoBancoPosta.jsp"><img class="loghiBarra" src="/UfficioPostale2/immagini/add.png">Nuovo conto</a></li></ul>
		</li>
	
		<li><img  class="loghiBarra" src="/UfficioPostale2/immagini/postepay.png">PostePay
		<%	
			PostePayModel modelPP= new PostePayModel();
			
			ArrayList<postePayBean> array= modelPP.cercaPerCf(cf);

			//System.out.println(array.size());
			%><ul>
			<%
			if(!(array==null) && !array.isEmpty()){
				
				for(int i=0;i<array.size();i++){
					String ibanCompleto=array.get(i).getIban();
					String iban=ibanCompleto.replace("0760103400100", "***");
				%><li><a href="/UfficioPostale2/cliente/postePay.jsp?iban=<%=ibanCompleto%>"><%=iban%></a></li><%
				
				}
			}
				%>
				<li><a href="/UfficioPostale2/cliente/NuovaPostePay.jsp"><img class="loghiBarra" src="/UfficioPostale2/immagini/add.png">Nuova postepay</a><li></ul>
				
		</li>
		<li><img class="loghiBarra" src="/UfficioPostale2/immagini/op.png">Operazioni
			
			<ul>
				<li><a href="/UfficioPostale2/cliente/Giroconto.jsp"><img class="loghiBarra" src="/UfficioPostale2/immagini/giroconto.png">Giroconto</a></li>
				<li><a href="/UfficioPostale2/cliente/Bonifico.jsp"><img class="loghiBarra" src="/UfficioPostale2/immagini/bonifico.png">Bonifico</a></li>
			</ul>
		
		</li>
		
	</ul>	
		<hr noshade size="1px"/>	
	<ul>
		<li><img class="loghiBarra" src="/UfficioPostale2/immagini/lettera.png">Posta
			<ul>
				<li><a href="/UfficioPostale2/cliente/postaSpedita.jsp"><img class="loghiBarra" src="/UfficioPostale2/immagini/inviata.png">Inviata</a></li>
<!-- 				<li><a href="/UfficioPostale2/cliente/destinatario.jsp"><img class="loghiBarra" src="/UfficioPostale2/immagini/ricevuta.png">Ricevuta</a></li> -->
				<li><a href="/UfficioPostale2/cliente/spedisci.jsp"><img class="loghiBarra" src="/UfficioPostale2/immagini/spedisci.png">Spedisci</a></li>
			</ul>
		
		</li>
	</ul>
		<hr noshade size="1px"/>
	<ul>
		<li><a href="/UfficioPostale2/cliente/infoPersonali.jsp"><img class="loghiBarra" src="/UfficioPostale2/immagini/info.png">Info</a></li>
	</ul>
</nav>