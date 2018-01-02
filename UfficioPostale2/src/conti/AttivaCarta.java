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
import model.BancoPostaModel;

/**
 * Servlet implementation class AttivaCarta
 */
@WebServlet("/cliente/AttivaCarta")
public class AttivaCarta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttivaCarta() {
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
		String attivaC=request.getParameter("carta");
		String attivaSI=request.getParameter("serviziInternet");
		String iban=request.getParameter("iban");
		
//		System.out.println("attivaCarta= " +attivaC);
//		System.out.println("attivaSI= "+ attivaSI);
//		System.out.println("iban= "+ iban);
		BancoPostaModel	modelBP= new BancoPostaModel();
		try {
			if(attivaC!=null) {
//				System.out.println("chiamo model si carta");
				modelBP.siCarta(iban);
			}else if(attivaSI!=null) {
//				System.out.println("chiamo model si servizi");
				modelBP.siServiziInternet(iban);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request,response);
			return;
		}
		
		double costoNuovo=100;
		try {
			BancopostaBean bp= modelBP.cercaByIban(iban);
			if(attivaC!=null) {
				if(bp.getServInternet()=='y')
					costoNuovo=5;
				else
					costoNuovo=7;
			}else if( attivaSI!=null) {
				if(bp.getCarta()=='y')
					costoNuovo=5;
				else
					costoNuovo=7;
			}
			modelBP.aggiornaCosto(costoNuovo, iban);
		}catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request,response);
			return;
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cliente/BancoPosta.jsp?iban="+iban);
		dispatcher.forward(request,response);
		return;
	}

}
