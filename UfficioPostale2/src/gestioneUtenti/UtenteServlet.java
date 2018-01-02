package gestioneUtenti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClienteBean;
import bean.UtenteBean;
import model.ClienteModel;

/**
 * Servlet implementation class UtenteServlet
 */
@WebServlet("/cliente/UtenteServlet")
public class UtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UtenteBean utente=(UtenteBean)request.getAttribute("utente");
		
		ClienteModel model= new ClienteModel();
		
		ClienteBean cliente=null;
		try {
			cliente=model.cercaByCliente(utente.getCliente());
			if(cliente.getCf().equals("")) {
				//PAGINA DI ERRPREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
			}
			
		}catch(SQLException e) {
			/*
			 *PAGINA DI ERROREEEEEEEEEEEEEEEEEEEEEEEEEE 
			 * 
			 * */
		}
	
		request.getSession().setAttribute("cliente", cliente);
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/homeCliente.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
