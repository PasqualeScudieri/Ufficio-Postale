package posta;

import java.io.IOException;
import java.sql.SQLException;

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
import model.PacchiModel;
import model.PostaModel;

/**Questa servlet riceve i dati visualizza.jsp, li processa e, 
 * nel caso risultino ammissibili, recupera i dati desiderati e li passa a visualizza.jsp
 */
@WebServlet("/dipendente/postino/VisualizzaPostaServlet")
public class VisualizzaPostaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaPostaServlet() {
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
	 * @param request  nella request devono essere setati i paramentri "codice" 
	 * @param response la response che viene restituita
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod= request.getParameter("codice");
		request.removeAttribute("error");
		int codice;
		
		try {
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
			
			posta=modelPo.cercaByCodice(codice);
			
			if(posta.getCodice()==0) {
				String error="Non esiste un pacco con questo codice";
				request.setAttribute("error",error);
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/dipendente/postino/visualizza.jsp");
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
		String success= "Ecco i dettagli";
		request.setAttribute("success", success);
		request.setAttribute("posta", posta);
		request.setAttribute("pacco", pacco);
		request.setAttribute("mittente", mittente);
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/dipendente/postino/visualizza.jsp");
	    dispatcher.forward(request, response);
	    return;	
	}

}
