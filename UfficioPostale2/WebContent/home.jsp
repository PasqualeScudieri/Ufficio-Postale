<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Ufficio Postale</title> 
		<link rel="icon" href="immagini/up.png">
	 
		<link type="text/css" href="/UfficioPostale2/css/index.css" rel= "stylesheet">
		<link type="text/css" href="/UfficioPostale2/css/home.css" rel= "stylesheet">

		<script type="text/javascript" src="script.js"></script>
		<script type="text/javascript">
			function appariScompari(id){
				var x= document.getElementById(id).style.display;
				console.log("x="+x);
				if(x==null || x=="none" || x==""){
					document.getElementById(id).style.display="block";
				}else{
					document.getElementById(id).style.display="none";
				}
			}
		</script>
	</head>
	
	<body onresize="resize();">
	<%@ include file="header.html" %>
<%-- 	<%@ include file="nav.html" %> --%>

	<div id="mainDiv">
	
		<div id="bisogni">I tuoi bisogni, le nostre soluzioni.</div>
		
		<div class="alCentro">
			<img src="immagini/camion.png" class="loghi">
			<div class="affianco">
				<p class="titoloCosa">Spedisci on-line</p>
				<p class="sottotitoloCosa">Il modo più comodo per spedire, in Italia e nel mondo.</p>
				<input type="button" value="Scopri di più" onclick="appariScompari('uno')">
				<div class="appari" id="uno">
				<ul>
					<li><span>Lettere:</span><br/>
						<ul>
							<li>Inserisci le informazioni sulla spedizione</li>
							<li>Noi ti informiamo sull'esito della consegna.</li>
							<li>Spedisci in Italia e all'Estero.</li>
						</ul>
					</li>
					<li><span>Raccomandata:</span><br/>
						<ul>
							<li>Inserisci le informazioni sulla spedizione.</li>
							 <li>Invia in qualsiasi momento.</li>
							 <li>Notifica immediata dell'avvenuta consegna </li>
						</ul>
					</li>
					<li><span>Pacco:</span><br/>
						<ul>
							<li>Invia documenti e oggetti fino a un peso di 15Kg.</li>
					  		<li>Inserisci i dati on-line</li>
					  		<li>Basta code per spedire il tuo pacco.</li>
					  	</ul>	  
					</li>
				</ul>
				Per spedire online la tua corrispondenza, registrati su questo sito.
			Per spedire pacchi, puoi recarti nell'ufficio postale o prenotare il ritiro
			a domicilio gratuito. Servizio attivo 24 ore su 24. 
			</div>
			</div>					  
		</div>
		<div class="clear"></div>
		<hr noshade size="1px"/>

		<div class="alCentro">		
    		<img src="immagini/card.png" class="loghi">
			<div class="affianco">
				<p class="titoloCosa">PostePay</p>		
      			<p class="sottotitoloCosa">Le carte ricaricabili per te che vuoi evitare il contante<br/> o fare acquisti on-line.</p>
				<input type="button" value="Scopri di più" onclick="appariScompari('due')">
				<div class="appari" id="due">

		      	<ul>
		      		<li><span>Con Postepay puoi:</span>
		      			<ul>
		      				<li>Prelevare denaro in Italia o all'Estero.</li>
		      				<li>Fare acquisto in tutto il mondo.</li>
		      				<li>Tenere sotto controllo i movimenti della tua PostePay in qualsiasi momento.</li>
		      			</ul>
		      		</li>
		      		<li><span>Come richiedere?</span>
		      			<ul>
		      				<li>In Ufficio Postale.</li>
		      				<li>Registrandoti al sito, direttamente dalla tua area utente.</li>
		    			</ul>
		    		</li>
		    	</ul>
		    	</div>
    		</div>
    	</div>
<!--     	<div class="scritte"> -->
<!-- 	    </div> -->
    	<div class="clear"></div>
    	<hr noshade size="1px"/>
 
 		<div class="alCentro">
	    	<img src="immagini/bancposta.png" class="loghi">
    		<div class="affianco">
			  	<p class="titoloCosa">Conto Bancoposta</p>
      			<p class="sottotitoloCosa">Il conto flessibile per tutte le tue esigenze.</p>
		    	<input type="button" value="Scopri di più" onclick="appariScompari('tre')">
				<div class="appari" id="tre">
		    	
		    	<ul>
		      		<li><span>il tuo conto bancoposta:</span>
		      			<ul>
							<li>Gestiscilo dall'Ufficio Postale e anche on-line.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		      				<li>Accedi a tutti i prodotti e servizi accessori.</li>
		      			</ul>
		      		</li>
		      		<li><span>Apri il conto:</span>
		       			<ul>
		      				<li>Recati ppresso un qualsiasi ufficio postale e porta con te un documento d'identità e il codice fiscale.</li>
		      				<li>Crealo direttamente on-line dalla tua area utente, registrandoti al sito.</li>
		    			</ul>
		    		</li>
		    	</ul>
	    		Sei operativo sin dal momento dell'apertura del conto grazie alla carta associata e all'attivazione dei servizi internet
    		</div>
    		</div>
    	</div>
<!--     	<div class="scritte"> -->
<!--    			 <div class="clear"></div> -->
<!--      	</div> -->
     	<div class="clear"></div>
    	<hr noshade size="1px"/>
    	
    	<div class="alCentro">
	    	<img src="immagini/postagiro.png" class="loghi">
    		<div class="affianco">
	  			<p class="titoloCosa">Bonifici e Giroconto</p>
      			<p class="sottotitoloCosa">Trasferire il tuo denaro non è mai stato così facile.<p>
	   	    	<input type="button" value="Scopri di più" onclick="appariScompari('quattro')">
				<div class="appari" id="quattro">
	   	    	
	   	    	<ul>
		      		<li><span>le operazioni:</span>
		      			<ul>
							<li>Invia denaro ed effettua pagamenti in euro in Italia e all'Estero.</li>
		     				<li>Anche da pc, tablet o smartphone.</li>
		      			</ul>
		      		</li>
		      		<li><span>Come compilare?:</span>
		       			<ul>
		       				<li>In Ufficio Postale:<br>
		        				Vai in ufficio postale con la tua carta Bancoposta o una Postepay.<br>  
		      				</li>
		      				<li>On-line:<br>
		        				Direttamente da casa tramite pc, smartphone o tablet, grazie al sito di Ufficio Postale. Per effettuare un Bonifico
		        				o un Giroconto on-line, devi seguire i pochi passaggi della procedura guidata.<br>
		    				</li>
		    			</ul>
		    		</li>
		    	</ul>
		    	</div>
    		</div>
    	</div>
<!--     	<div class="scritte">	 -->
<!--     	</div> -->
    	<div class="clear"></div>
<!--     	<hr noshade size="1px"/> -->
  </div>
		
	<%@ include file="footer.html" %>	
	</body>
</html>