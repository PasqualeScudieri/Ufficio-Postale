package gestioneUtenti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**Questa servlet si occupa di invalidare la sessione per consentire il logut
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**Metodo chiamato dalla jsp
	 * @param request  la requwest che viene passata come parametro
	 * @param response la response che viene restituita
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession sessione=request.getSession();
		
		
		synchronized (sessione) {
			sessione.removeAttribute("id");
			sessione.removeAttribute("logged");
			sessione.setAttribute("logged", "falso");
			System.out.println("logged= "+sessione.getAttribute("logged"));
			sessione.invalidate();
		}
		
		/*RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/blank.jsp");
		dispatcher.forward(request, response);
	*/
		response.sendRedirect("home.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
