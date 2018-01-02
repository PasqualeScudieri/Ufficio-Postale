package conti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BancopostaBean;
import bean.ClienteBean;
import model.BancoPostaModel;
import model.ContoModel;

/**
 * Servlet implementation class NuovoBancoPostaServlet
 */
@WebServlet("/cliente/NuovoBancoPostaServlet")
public class NuovoBancoPostaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuovoBancoPostaServlet() {
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
		double tasso=0.20;
		char carta = request.getParameter("carta") .charAt(0);
		//System.out.println("carta"+carta);
		char servInternet= request.getParameter("servInternet").charAt(0);
		int costo;
		request.removeAttribute("errore");
		try {
		//	System.out.println("il costo è:.. "+request.getParameter("costo"));
			costo=Integer.parseInt(request.getParameter("costo"));
		}catch(NumberFormatException e) {
		      String error="costo non valido1";
		      request.setAttribute("error",error);
		      RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/NuovoBancoPosta.jsp");
		      dispatcher.forward(request, response);
		      return;
		}
		if(carta!='y' && carta!='n') {
			 String error="inserisci un valore valido per Carta";
		      request.setAttribute("error",error);
		      RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/NuovoBancoPosta.jsp");
		      dispatcher.forward(request, response);
		      return;
		}
		
		if(servInternet!='y' && servInternet!='n') {
			 String error="inserisci un valore valido per Servizi Internet";
		      request.setAttribute("error",error);
		      RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/NuovoBancoPosta.jsp");
		      dispatcher.forward(request, response);
		      return;
		}
		
		if(carta=='y' && servInternet=='y' && costo!=5){
		      String error="costo non valido2";
		      request.setAttribute("error",error);
		      RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/NuovoBancoPosta.jsp");
		      dispatcher.forward(request, response);
		      return;
		}else if(carta=='y' ^ servInternet=='y' && costo!=7){
			 String error="costo non valido3";
		      request.setAttribute("error",error);
		      RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/NuovoBancoPosta.jsp");
		      dispatcher.forward(request, response);
		      return;
		}
		
		
			    
	    ContoModel modelC= new ContoModel();
	    BancoPostaModel modelBP= new BancoPostaModel();
	    ClienteBean cliente= (ClienteBean) request.getSession().getAttribute("cliente");
	    
	    try {
			String iban= modelC.creaConto(cliente.getCf());
			modelBP.creaBancoPosta(iban, tasso, costo, carta, servInternet);
			//String error="Postepay creata";
		
			BancopostaBean bpb= modelBP.cercaByIban(iban);
			
			request.setAttribute("bancoPosta",bpb);
		    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/NuovoBancoPosta.jsp");
		    dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		}
	
	}

}
