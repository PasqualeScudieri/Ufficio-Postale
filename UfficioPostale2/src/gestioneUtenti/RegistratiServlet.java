package gestioneUtenti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClienteBean;
import bean.UtenteBean;
import model.ClienteModel;
import model.UtenteModel;

/**Questa servlet riceve di dati da Registrazione.jsp e li elabora. Se i dati sono corretti utilizza i servizi di UtenteModel
 *  e ClienteModel per effettuare la registrazione, altrimenti passa alla jsp un messaggio d’errore.
 */
@WebServlet("/RegistratiServlet")
public class RegistratiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistratiServlet() {
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
	 * @param request  nella request devono essere setati i paramentri "nome", "cognome", "cf","indirizzo", "luogoNascita", "dataNascita", "username" e "password"
	 * @param response la response che viene restituita
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errore="";
		String nome= request.getParameter("nome");
		
		if(nome == null || nome.trim().equals("")) {
			errore+= "Inserisci nome <br>"; 
		} else if(nome.length()<2 || nome.length()>50){
			errore+= "Luneghezza nome non valida <br>";
		}else {
			int i;
			for(i=0; i< nome.length(); i++) {
				char ch=nome.charAt(i);
				if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
					errore+= "formato nome non valido<br>";
					break;
				}
			}
			if(i==nome.length()) {
				request.setAttribute("nome", nome);			
			}
		}
		
		String cognome=request.getParameter("cognome");
		if(cognome == null || cognome.trim().equals("")) {
			errore+= "Inserisci cognome <br>"; 
		}else if(cognome.length()<2 || cognome.length()>50){
			errore+= "Luneghezza cognome non valida <br>";
		}else {
			int i;
			for(i=0; i< cognome.length(); i++) {
				char ch=cognome.charAt(i);
				if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
					errore+= "formato cognome non valido<br>";
					break;
				}
			}
			if(i==cognome.length()) {
				request.setAttribute("cognome", cognome);			
			}
		}
		
		String cf=request.getParameter("cf");
		if(cf == null || cf.trim().equals("")) {
			errore+= "Inserisci cf <br>"; 
		} else {
			if(cf.length()!=16) {
				errore+="formato del cf non valido <br>";
			}else {
				request.setAttribute("cf", cf);			
			}
		}

		
		String indirizzo=request.getParameter("indirizzo");
		if(indirizzo == null || indirizzo.trim().equals("")) {
			errore+= "Inserisci indirizzo <br>"; 
		}else if(indirizzo.length()<2 || indirizzo.length()>100){
			errore+= "Luneghezza indirizzo non valida <br>";
		}else {
			int i;
			for(i=0; i< indirizzo.length(); i++) {
				char ch=indirizzo.charAt(i);
				if(!Character.isLetterOrDigit(ch) && !Character.isSpaceChar(ch)) {
					errore+= "formato indirizzo non valido<br>";
					break;
				}
			}
			if(i==indirizzo.length()) {
				request.setAttribute("indirizzo", indirizzo);
			}
		}
		
		String luogoNascita=request.getParameter("luogoNascita");
		if(luogoNascita == null || luogoNascita.trim().equals("")) {
			errore+= "Inserisci luogo nascita <br>"; 
		}else if(luogoNascita.length()<2 || luogoNascita.length()>50){
			errore+= "Luneghezza luogo nascita non valida <br>";
		}else {
			int i;
			for(i=0; i< luogoNascita.length(); i++) {
				char ch=luogoNascita.charAt(i);
				if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
					errore+= "formato luogo nascita non valido<br>";
					break;
				}
			}
			if(i==luogoNascita.length()) {
				request.setAttribute("luogoNascita", luogoNascita);
			}
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		String dataNascita=request.getParameter("dataNascita");
		if(dataNascita == null || dataNascita.trim().equals("")) {
			errore+= "Inserisci data nascita <br>"; 
		} else {
			if(dataNascita.length()!=10	||!Character.isDigit(dataNascita.charAt(0)) || !Character.isDigit(dataNascita.charAt(1)) ||
			   !Character.isDigit(dataNascita.charAt(2)) || !Character.isDigit(dataNascita.charAt(3))
				|| !(dataNascita.charAt(4)=='-') || !Character.isDigit(dataNascita.charAt(5)) || !Character.isDigit(dataNascita.charAt(6))
				|| !(dataNascita.charAt(7)=='-')|| !Character.isDigit(dataNascita.charAt(8)) || !Character.isDigit(dataNascita.charAt(9))
				)
			{
				errore+= "formato della data errato <br>";
			}else {
				boolean buono= true;
				String yy= dataNascita.substring(0, 4);
				String mm= dataNascita.substring(5, 7);
				String dd= dataNascita.substring(8, 10);
				try {
					int ddNum=Integer.parseInt(dd);
					if(ddNum<1 || ddNum>31) {
						errore+= "formato della data errato";											
						buono=false;
					}
					int mmNum=Integer.parseInt(mm);
					if(mmNum<1 || mmNum>12) {
						errore+= "formato della data errato";											
						buono=false;
					}
					int yyNum=Integer.parseInt(yy);
					if(yyNum<1900|| yyNum>2025) {
						errore+= "formato della data errato";											
						buono=false;
					}
				}catch(NumberFormatException e) {
					errore+= "formato della data errato";					
					buono=false;
				}
				if(buono) {
					request.setAttribute("dataNascita", dataNascita);					
				}
			}
			
			//System.out.println(dataNascita);
		}
		
		String user= request.getParameter("username");
		if(user == null || user.trim().equals("")) {
			errore+= "Inserisci Username <br>"; 
		}else if(user.length()<2 || user.length()>50){
			errore+= "Luneghezza username non valida <br>";
		}else {
			int i;
			for(i=0; i< user.length(); i++) {
				char ch=user.charAt(i);
				if(!Character.isLetterOrDigit(ch) && !Character.isSpaceChar(ch)) {
					errore+= "formato username non valido<br>";
					break;
				}
			}
			if(i==user.length()) {
				request.setAttribute("username", user);			}
		}

		String password= request.getParameter("password");
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
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/registrazione.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		UtenteModel modelU= new UtenteModel();
		ClienteModel modelC= new ClienteModel();
		try{
			UtenteBean utente=modelU.doRetrieveByUser(user);
			if(!utente.getUser().equals("")) {
				//System.out.println("sono nell if :" +utente.getUser());
				errore+="username non disponibile <br>";
				request.setAttribute("error", errore);
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/registrazione.jsp");
				dispatcher.forward(request, response);
				return;
			}
			if(modelU.esisteCf(cf)) {
				errore+="sei già registrato con questo cf <br>";
				request.setAttribute("error", errore);
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/registrazione.jsp");
				dispatcher.forward(request, response);
				return;
			}
			ClienteBean cliente=modelC.cercaByCliente(cf);
			if(!cliente.getCf().equals("")) {
				errore+="è già presente un cliente con questo cf <br>";
				request.setAttribute("error", errore);
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/registrazione.jsp");
				dispatcher.forward(request, response);
				return;
			}
		
		}catch(SQLException e) {
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}
		
		ClienteBean nuovoCliente= new ClienteBean();
		nuovoCliente.setNome(nome);
		nuovoCliente.setCognome(cognome);
		nuovoCliente.setIndirizzo(indirizzo);
		nuovoCliente.setLuogoNascita(luogoNascita);
		nuovoCliente.setCf(cf);
		int year= Integer.parseInt(dataNascita.substring(0, 4));
		int month= Integer.parseInt(dataNascita.substring(5, 7));
		int day= Integer.parseInt(dataNascita.substring(8, 10));
		GregorianCalendar cal= new GregorianCalendar(year, month, day);
		Date data= new Date(cal.getTimeInMillis());
		nuovoCliente.setDataNascita(data);
		
		try {
			modelC.doSave(nuovoCliente);
		}catch (SQLException e) {
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}
		
		UtenteBean nuovoUtente= new UtenteBean();
		nuovoUtente.setCliente(cf);
		nuovoUtente.setUser(user);
		nuovoUtente.setPassword(password);
		
		try {
			modelU.doSave(nuovoUtente);
		}catch (SQLException e) {
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}
		
		boolean loggato=true;
		HttpSession session= request.getSession();
		
		synchronized (session) {
			session.setAttribute("logged", loggato);
			session.setAttribute("cliente", nuovoCliente);
			session.setAttribute("username", nuovoUtente.getUser());			
		}
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/homeCliente.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
