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

/**Questa servlet viene invocata da un addetto allo sportello quando questi decide 
 * di mandare una notifica ad un cliente che non possiede una PostePay.
 */
@WebServlet("/dipendente/addetto/NoPostePayServlet")
public class NoPostePayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoPostePayServlet() {
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
	 * @param request  nella request devono essere setati i paramentri "cf" e "matricola"
	 * @param response la response che viene restituita
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cf= request.getParameter("cf");
		//System.out.println("cf: " + cf);
		String mat=request.getParameter("matricola");
		int matricola;
		//System.out.println("matricola"+mat);
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
			modelN.inserisciNotifica(cf, matricola, "nuovaPostePay",null,0);
		}catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		String success="notifica mandata con successo";
		request.setAttribute("success", success);
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/dipendente/addetto/NoPostePay.jsp");
	    dispatcher.forward(request, response);
	    return;

	}

}
