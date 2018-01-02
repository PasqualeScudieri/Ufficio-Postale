package conti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClienteBean;
import bean.postePayBean;
import model.ContoModel;
import model.PostePayModel;

/**
 * Servlet implementation class NuovaPostePayServlet
 */
@WebServlet("/cliente/NuovaPostePayServlet")
public class NuovaPostePayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuovaPostePayServlet() {
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
		doGet(request, response);
		
		String numCodice=(String)request.getParameter("codice");
		//System.out.println("numcodice"+numCodice);
		int numSicur;
		
	    try {
	      numSicur=Integer.parseInt(numCodice);
	      if(numSicur<=9999 || numSicur>=99999) {
	    	  String error="metti un nummero a 5 cifre";
		      
		      request.setAttribute("error",error);
		      RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/NuovaPostePay.jsp");
		      dispatcher.forward(request, response);
		      
		      return;
	      }
	    }
	      catch (NumberFormatException e) {
	      
	      String error="metti un nummero valido";
	      
	      request.setAttribute("error",error);
	      RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/NuovaPostePay.jsp");
	      dispatcher.forward(request, response);
	      
	      return;
	    }
	    
	    ContoModel modelC= new ContoModel();
	    PostePayModel modelPP= new PostePayModel();
	    ClienteBean cliente= (ClienteBean) request.getSession().getAttribute("cliente");
	    
	    try {
			String iban= modelC.creaConto(cliente.getCf());
			modelPP.creaPostePay(iban, numSicur);
			//String error="Postepay creata";
		
			postePayBean ppb= modelPP.cercaByIban(iban);
			
			request.setAttribute("postePay",ppb);
		    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/NuovaPostePay.jsp");
		    dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		}
	    
	  }
	

}
