package posta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClienteBean;
import bean.PacchiBean;
import bean.PostaBean;
import model.PacchiModel;
import model.PostaModel;

/**Questa servlet riceve i dati da spedisci.jsp e nel caso siano 
 * validi ne effettua l’inserimento usando i servizi di PostaModel e pacchiModel.
 */
@WebServlet("/cliente/SpedisciServlet")
public class SpedisciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpedisciServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}
	
	/**Metodo chiamato dalla jsp
	 * @param request  nella request devono essere setati i paramentri "cf", "destinatario", "indirizzo" e "tipo"
	 * @param response la response che viene restituita
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cf=((ClienteBean)request.getSession().getAttribute("cliente")).getCf();
		String dest= request.getParameter("dest");
		String indirizzo= request.getParameter("indirizzo");

		System.out.println("sssssssssssssssssssss");
		
		String error="";
		if(dest==null || dest.equals("") ) {
			 error += "metti un destinatario valido <br>";
		} else if(dest.length()<2 || dest.length()>100){
			error += "Luneghezza destinatario non valida <br>";
		}else {
			int i;
			for(i=0; i< dest.length(); i++) {
				char ch=dest.charAt(i);
				if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
					error+= "formato destinatario non valido<br>";
					break;
				}
			}
			if(i==dest.length()) {
				request.setAttribute("destinatario", dest);			
			}
		}
		
		if(indirizzo==null || indirizzo.equals("") ) {
			error+= "metti un indirizzo <br>";
		} else if(indirizzo.length()<2 || indirizzo.length()>100){
			error += "Luneghezza indirizzo non valida <p>";
		}else {
			int i;
			for(i=0; i< indirizzo.length(); i++) {
				char ch=indirizzo.charAt(i);
				if(!Character.isLetterOrDigit(ch) && !Character.isSpaceChar(ch)) {
					error+= "formato indirizzo non valido<br>";
					break;
				}
			}
			if(i==indirizzo.length()) {
				request.setAttribute("indirizzo", indirizzo);			
			}
		}
		
		String tipo= request.getParameter("tipo");
		System.out.println("tipo: " + tipo);
		if(!tipo.equals("raccomandate") && !tipo.equals("pacchi") && !tipo.equals("lettere")) {
			error+= "inserisci un tipo valido";
			request.setAttribute("error",error);
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/spedisci.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		PostaModel modelP= new PostaModel();
		PostaBean posta= new PostaBean();
		posta.setDataSpedizione(new Date());
		posta.setDestinatario(dest);
		posta.setMittente(cf);
		posta.setIndirizzo(indirizzo);
		posta.setTipo(tipo);
		
		double peso=-1, volume=-1;
		if(tipo.equals("pacchi")) {
			String pes= request.getParameter("peso");
			String vol=request.getParameter("volume");
			
			try {
				peso=Double.parseDouble(pes);
				volume=Double.parseDouble(vol);
			}catch (NumberFormatException e) {
				error+= "inserisci numeri validi <br>";
			}
			
			if(peso<=0 || peso>15){
				error+= "impossibile spedire un pacco con questo peso <br>";
			}
			if(volume<=0 || volume>750){
				error+= "impossibile spedire un pacco con questo volume <br>";
			}
		}
		if(error != null && !error.equals("")) {
			request.setAttribute("error", error);
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/spedisci.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		if(tipo.equals("pacchi")) {
			PacchiModel modelpa= new PacchiModel();
			PacchiBean pacco= new PacchiBean();
			pacco.setPeso(peso);
			pacco.setVolume(volume);
			try {	
				int cod=modelP.inserisciInPo(posta);
				pacco.setCodice(cod);
				modelpa.inserisciInPa(pacco);
				request.setAttribute("codice", cod);
			}catch (SQLException e) {
				e.printStackTrace();
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
			    dispatcher.forward(request, response);
			    return;
			}
		}else {
			try {	
				int cod=modelP.inserisciInPo(posta);
				request.setAttribute("codice", cod);
			}catch (SQLException e) {
				e.printStackTrace();
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
			    dispatcher.forward(request, response);
			    return;
			}
		}
		
		String success="inserimento effettuato con successo. <br/> Ora stampa l'etichetta e applicala sulla busta.";
		request.setAttribute("success",success);
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/spedisci.jsp");
	    dispatcher.forward(request, response);
	    return;
	}

}
