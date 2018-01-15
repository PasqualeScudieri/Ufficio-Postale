package notifica;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NotificheModel;

/**Questa servlet si occupa di portare a termine l’eliminazione di una notifica una volta che questa 
 * è stata visualizzata da un cliente. Per fare questo utilizza i servizi offerti dalla classe NotificaModel.
 */
@WebServlet("/cliente/EliminaNotifica")
public class EliminaNotifica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaNotifica() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**Metodo chiamato dalla jsp
	 * @param request  nella request deve essere settato il parametro "codice"
	 * @param response la response che viene restituita
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod= request.getParameter("backFrom");
		String pagina= request.getParameter("goTo");
		NotificheModel modelN= new NotificheModel();
		try{
			int codice= Integer.parseInt(cod);
			modelN.cancellaNotifica(codice);
		}catch(NumberFormatException | SQLException e){
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		if(pagina!=null && pagina!="") {
			RequestDispatcher dispatcher;
			request.removeAttribute("backFrom");
			request.removeAttribute("goTo");
			switch(pagina) {
				case "NuovaPostePay":		
					dispatcher= getServletContext().getRequestDispatcher("/cliente/NuovaPostePay.jsp");
					break;
				case "NuovoBancoPosta":
					dispatcher= getServletContext().getRequestDispatcher("/cliente/NuovoBancoPosta.jsp");
					break;
				case "Carta":
				case "ServiziInternet":
					dispatcher= getServletContext().getRequestDispatcher("/cliente/BancoPosta.jsp");
					break;
				case "PostaNotifica":
					System.out.println("sto facendo il dispatch");
					dispatcher= getServletContext().getRequestDispatcher("/cliente/postaNotifica.jsp");
					break;
				default: 
					dispatcher= getServletContext().getRequestDispatcher("/error.jsp");

			}
		    dispatcher.forward(request, response);
		    return;
		}
		
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/cliente/homeCliente.jsp");
		    dispatcher.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	    return;	
	}

}
