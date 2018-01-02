package operazioni;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OperazioniModel;

/**
 * Servlet implementation class OperazioneServlet
 */
@WebServlet("/cliente/GirocontoServlet")
public class GirocontoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GirocontoServlet() {
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
		
		String ibanFrom= request.getParameter("from");
		String ibanTo=request.getParameter("to");
		
		String imp= request.getParameter("importo");
		double importo;
		try {
			importo=Double.parseDouble(imp);
		}catch (NumberFormatException e) {
			String error="importo non valido";
		    request.setAttribute("error",error);
		    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Giroconto.jsp");
		    dispatcher.forward(request, response);
		    return;
		}

		
		/*
		if(ibanFrom==null || ibanFrom.equals("")) {	
			 String error="metti iban from valido";
		      request.setAttribute("error",error);
		      RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Giroconto.jsp");
		      dispatcher.forward(request, response);
		      return;
		}
		if(ibanTo==null || ibanTo.equals("")) {	
			 String error="metti iban to valido";
		      request.setAttribute("error",error);
		      RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Giroconto.jsp");
		      dispatcher.forward(request, response);
		      return;
		}
		if(ibanFrom.equals(ibanTo)) {
			  String error="metti iban diversi";
		      request.setAttribute("error",error);
		      RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Giroconto.jsp");
		      dispatcher.forward(request, response);
		      return;
		}
		
		String imp= request.getParameter("importo");
		double importo;
		try {
			importo=Double.parseDouble(imp);
		}catch (NumberFormatException e) {
			String error="importo non valido";
		    request.setAttribute("error",error);
		    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Giroconto.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		int saldo;
		try {
			ContoModel modelC= new ContoModel();
			saldo=modelC.cercaSaldo(ibanFrom);
		}catch(SQLException e) {
			e.printStackTrace();
		    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		if(importo>saldo) {
			String error="importo superiore al saldo";
		    request.setAttribute("error",error);
		    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Giroconto.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		if(importo<=0) {
			String error="importo non valido";
		    request.setAttribute("error",error);
		    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Giroconto.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		try {
			
			//ho deciso che nn faccio il controllo sul bancoposta to perchè è il from che mi interessa
			BancoPostaModel modelBP= new BancoPostaModel();
			BancopostaBean bancoposta= modelBP.cercaByIban(ibanFrom);
			if(bancoposta!=null && !bancoposta.getIban().equals("") && bancoposta.getServInternet()!='y') {
				String error="bancoposta  from senza servizi internet";
			    request.setAttribute("error",error);
			    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Giroconto.jsp");
			    dispatcher.forward(request, response);
			    return;
			}
			*//*
			bancoposta=modelBP.cercaByIban(ibanTo);
			if(bancoposta!=null && !bancoposta.getIban().equals("") && bancoposta.getServInternet()!='y') {
				String error="bancoposta to senza servizi internet";
			    request.setAttribute("error",error);
			    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Giroconto.jsp");
			    dispatcher.forward(request, response);
			    return;
			}
			*//*
		}catch(SQLException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		*/
		String s= ControlloIbanFromTo.controlloIban(request, response);
		if(s.equals("page")) {
		    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Giroconto.jsp");
		    dispatcher.forward(request, response);
		    return;
		}else if(s.equals("error")) {
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		OperazioniModel modelOp= new OperazioniModel();
		try {
			modelOp.Operazione(ibanFrom, ibanTo, importo);
			String success="operazione effettuata con successo";
		    request.setAttribute("success",success);
		    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/success.jsp");
		    dispatcher.forward(request, response);
		    return;
			
		}catch(SQLException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
	}

}
