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
import model.ClienteModel;
import model.NotificheModel;
import model.PacchiModel;
import model.PostaModel;

/**Questa servlet riceve i dati da consegna.jsp, li processa e, nel caso risultino validi, richiede i servizi di PostaModel
 *  per indicare che la posta con quel codice è stata consegnata dal postino in una certa data.
 */
@WebServlet("/dipendente/postino/ConsegnaServlet")
public class ConsegnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsegnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**Metodo chiamato dalla jsp
	 * @param request  nella request devono essere setati i paramentri "matricola" e "codice"
	 * @param response la response che viene restituita
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mat= request.getParameter("matricola");
		String cod= request.getParameter("codice");
		int codice, matricola;
		
		try {
			matricola=Integer.parseInt(mat);
			codice=Integer.parseInt(cod);
		}catch (NumberFormatException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
	
		PostaModel modelPo= new PostaModel();
		PacchiModel modelPa= new PacchiModel();
		ClienteModel modelCl= new ClienteModel();
		PacchiBean pacco;
		PostaBean posta;
		ClienteBean mittente;
		try {
			pacco= modelPa.cercaPacco(codice);
			/*if(pacco.getCodice()==0) {
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
			    dispatcher.forward(request, response);
			    return;
			}*/
			posta=modelPo.cercaByCodice(codice);
			if(posta.getCodice()==0) {
				String error="Non esiste un pacco con questo codice";
				request.setAttribute("error",error);
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/dipendente/postino/consegna.jsp");
			    dispatcher.forward(request, response);
			    return;
			}
			
			if(posta.getDataConsegna()==null){
				//System.out.println("aaaaaaaaaaaaaaaaa null");
				modelPo.aggiornaConsegna(new Date(), codice, matricola);
				posta=modelPo.cercaByCodice(codice);
			}else{
				String error="impossibile consegnare un pacco gi&agrave; consegnato";
				request.setAttribute("error",error);
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/dipendente/postino/consegna.jsp");
			    dispatcher.forward(request, response);
			    return;
			}
			mittente= modelCl.cercaByCliente(posta.getMittente());
		}catch(SQLException e){
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;			
		}
		
		NotificheModel modelN= new NotificheModel();
		try {
			//modelN.inserisciNotifica(posta.getDestinatario(), matricola, "postaConsegnata", null, codice);
			modelN.inserisciNotifica(posta.getMittente(), matricola, "postaConsegnata", null, codice);

		}catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		String success= "consegna effettuata con successo";
		request.setAttribute("success", success);
		request.setAttribute("posta", posta);
		request.setAttribute("pacco", pacco);
		request.setAttribute("mittente", mittente);
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/dipendente/postino/visualizza.jsp");
	    dispatcher.forward(request, response);
	    return;	
	}

}
