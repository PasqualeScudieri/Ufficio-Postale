package operazioni;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ContoModel;
import model.OperazioniModel;

/**Questa servlet riceve i dati da Bonifico.jso, li processa 
 * e nel caso i valori risultino ammissibili utilizza i servizi di OperazioniModel per effettare l’inserimento.
 */
@WebServlet("/cliente/BonificoServlet")
public class BonificoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BonificoServlet() {
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
	 * @param request  nella request devono essere setati i paramentri "ibanFrom" , "ibanTo", "importo"
	 * @param response la response che viene restituita
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String s= ControlloIbanFromTo.controlloIban(request, response);
		if(s.equals("page")) {
		    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Bonifico.jsp");
		    dispatcher.forward(request, response);
		    return;
		}else if(s.equals("error")) {
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
	
		String ibanFrom=(String)request.getParameter("from");
		String ibanTo=(String)request.getParameter("to");
		
		try {
			ContoModel modelC=new ContoModel();
			if(!modelC.cercaConto(ibanTo)) {
				String error="iban to non valido";
				request.setAttribute("error", error);
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Bonifico.jsp");
			    dispatcher.forward(request, response);
			    return;
			}
		}catch (SQLException e) {
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
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
		    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/Bonifico.jsp");
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
