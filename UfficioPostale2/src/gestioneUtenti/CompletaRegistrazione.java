package gestioneUtenti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DipendentiBean;
import model.DipendentiModel;
import model.UtenteModel;

/**
 * Servlet implementation class CompletaRegistrazione
 */
@WebServlet("/CompletaRegistrazione")
public class CompletaRegistrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompletaRegistrazione() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String user= (String)request.getParameter("name");
		System.out.println("nella servlet user= "+ user);
		String password= (String)request.getParameter("password");
		
		String errore="";
		if(password == null || password.trim().equals("")) {
			errore+= "Inserisci password <br>";
		}else if(password.length()<2 || password.length()>50){
			errore+= "Luneghezza password non valida <br>";
		}else {
			int i;
			for(i=0; i< password.length(); i++) {
				char ch=password.charAt(i);
				if(!Character.isLetterOrDigit(ch) && !Character.isSpaceChar(ch)) {
					errore+= "formato password non valido<br>";
					break;
				}
			}
		}
		

		if(errore != null && !errore.equals("")) {
			request.setAttribute("error", errore);
			request.setAttribute("user", user);
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/completaRegistrazioneDip.jsp");
			dispatcher.forward(request, response);
			return;
		}

		try {
			UtenteModel modelU= new UtenteModel();
			modelU.setPasswordDip(user, password);
			DipendentiModel modelD= new DipendentiModel();
			int matricola=Integer.parseInt(user);
			
			DipendentiBean dip=modelD.cercaByMatricola(matricola);
			boolean loggato=true;
			HttpSession session= request.getSession();
			
			synchronized (session) {
				session.setAttribute("logged", loggato);
				session.setAttribute("dipendente", dip);
				session.setAttribute("username", user);			
			}
			
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/dipendente/homeDipendente.jsp");
			dispatcher.forward(request, response);
			return;
		}catch(SQLException |NumberFormatException e) {
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
	
		
		
		
	}

}
