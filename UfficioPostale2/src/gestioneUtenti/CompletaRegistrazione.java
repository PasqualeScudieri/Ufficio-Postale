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
import bean.UtenteBean;
import model.DipendentiModel;
import model.UtenteModel;

/**Questa servlet riceve i dati da completaRegistrazioneDip.jsp 
 * e utilizza i servizi di utente model e dipendente model per completare la registrazione.
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


	/**Metodo chiamato dalla jsp
	 * @param request  nella request devono essere setati i paramentri "username" e "password"
	 * @param response la response che viene restituita
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String user= (String)request.getParameter("name");
		System.out.println("nella servlet user= "+ user);
		String password= (String)request.getParameter("password");
		
		String errore="";
		
		UtenteModel modelU= new UtenteModel();
		DipendentiModel modelD= new DipendentiModel();
		DipendentiBean dip=null;
		int matricola;
		try {
			matricola=Integer.parseInt(user);
			dip=modelD.cercaByMatricola(matricola);
			if(dip.getNome()=="" ) {
				System.out.println("MENTE LA PRIMA");
				errore+="username non valido <br>";
			}else {
				UtenteBean utente= modelU.doRetrieveByUser(user);
				if (utente.getPassword() != null && !utente.getPassword().trim().equals("") ) {
					System.out.println("MENTE LA SECONDA");
					System.out.println("_"+utente.getPassword()+"_");
					errore+="username non valido <br>";					
				}
			}
		
		} catch (NumberFormatException | NullPointerException |SQLException e ) {
			System.out.println("MENTE L?ECCEZUOINE");
			errore+="username non valido <br>";
		}
		
		
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
			//UtenteModel modelU= new UtenteModel();
			modelU.setPasswordDip(user, password);
			//DipendentiModel modelD= new DipendentiModel();
//			int matricola=Integer.parseInt(user);
//			
//			DipendentiBean dip=modelD.cercaByMatricola(matricola);
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
