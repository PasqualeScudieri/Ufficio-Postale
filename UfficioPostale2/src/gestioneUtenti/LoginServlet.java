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

import bean.ClienteBean;
import bean.DipendentiBean;
import bean.UtenteBean;
import model.ClienteModel;
import model.DipendentiModel;
import model.UtenteModel;

/**Questa servlet si occupa di ricevere i dati dalla jsp di login 
 * elaborarli e decidere se consentire o no l’accesso all’area personale.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static UtenteModel model;
	
	static {
			model = new UtenteModel();
		}
	
	public LoginServlet() {
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
		//doGet(request, response);
		/*********************controllo parametri******************************************/
		String error= "";
		String name = request.getParameter("name");
		String password = request.getParameter("password");
	
		if(name == null || name.trim().equals("")) {
			error+= "Inserisci Username <br>"; 
		} else {
			request.setAttribute("name", name);
		}

		if(password == null || password.trim().equals("")) {
			error+= "Inserisci Password <br>";
		}

		if(error != null && !error.equals("")) {
			request.setAttribute("error", error);
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		/*boolean corretto= DatabaseServices.VerificaLogIn(name, password)*/;
		
		/***************controllo che esista un utente con quell user e password*****************************/
		UtenteBean utente=null;
		try {
			utente=model.esisteUser(name, password);
			if(utente.getUser().equals("")) {
				utente=null;
			}else {
				/*qua devo mettere il token   nella sessione*/
		
				boolean loggato=true;
				request.getSession().setAttribute("logged", loggato);
				//request.setAttribute("utente", utente);
			}
		}catch(SQLException e) {
			System.out.println("Error:" + e.getMessage());
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}
	
		//if(!corretto){
		if(utente==null) {
			error="Username o Password non corretti <br>";
			request.setAttribute("error", error);
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		HttpSession session = request.getSession();
		synchronized(session){
			//questo mi sa che nn serve----------------------------------------------------------
			String id="" + session.getId();
			request.setAttribute("id", id);
			//
			session.setAttribute("username", utente.getUser());
		}
		/********************controlli sull user o sull admin******************************/
		String tipo=utente.getTipo();
		if(tipo.equals("admin")) {
//			System.out.println("nell if dell admin");
			DipendentiBean dipendente= null;
			DipendentiModel modelD= new DipendentiModel();
			try {
				dipendente=modelD.cercaByCf(utente.getCliente());
				if(dipendente.getCf().equals("")) {
					RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
					dispatcher.forward(request, response);
				}				
			}catch (SQLException e) {
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
			request.getSession().setAttribute("dipendente", dipendente);
			response.sendRedirect("/UfficioPostale2/dipendente/homeDipendente.jsp");
			//RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/dipendente/admin.jsp");
			//dispatcher.forward(request, response);
			return;
		}else if(tipo.equals("user")){
		
			ClienteModel model= new ClienteModel();
			ClienteBean cliente=null;
			try {
				cliente=model.cercaByCliente(utente.getCliente());
				if(cliente.getCf().equals("")) {
					RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
					dispatcher.forward(request, response);
				}
			}catch(SQLException e) {
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}

			request.getSession().setAttribute("cliente", cliente);

			response.sendRedirect("/UfficioPostale2/cliente/homeCliente.jsp");
			
//			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/utente.jsp");
//			dispatcher.forward(request, response);
			return;
		}else if(tipo.equals("gestore")) {
			request.getSession().setAttribute("gestore", "true");
			request.getSession().setAttribute("username", utente.getUser());
			response.sendRedirect("/UfficioPostale2/gestore/homegestore.jsp");
			
		}
		
	}

}
