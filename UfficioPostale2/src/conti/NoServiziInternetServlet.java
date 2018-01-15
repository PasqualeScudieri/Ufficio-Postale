package conti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NotificheModel;

/**Questa servlet viene ha il compito di inviare una notifica ad un cliente 
 * che non ha i servizi internet attivi su un conto BancoPosta
 */
@WebServlet("/dipendente/addetto/NoServiziInternetServlet")
public class NoServiziInternetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoServiziInternetServlet() {
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
	 * @param request  nella request devono essere setati i paramentri "cf" , "matricola", "iban"
	 * @param response la response che viene restituita
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cf= request.getParameter("cf");
		String mat=request.getParameter("matricola"); 
		String iban=request.getParameter("iban");
		int matricola;
		try {
			matricola=Integer.parseInt(mat);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		NotificheModel modelN= new NotificheModel();
		
		try {
			modelN.inserisciNotifica(cf, matricola, "aggiungiServiziInternet",iban,0);
		}catch(SQLException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		String success="Notifica mandata con successo!";
		request.setAttribute("success", success);
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/dipendente/addetto/NoServiziInternet.jsp");
	    dispatcher.forward(request, response);
	    return;
	}

}
