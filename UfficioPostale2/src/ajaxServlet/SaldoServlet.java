package ajaxServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ContoModel;

/**
 * Servlet implementation class SaldoServlet
 */
@WebServlet("/cliente/SaldoServlet")
public class SaldoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaldoServlet() {
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

		response.setContentType("text/xml");
		
		StringBuffer packed = new StringBuffer();
		packed.append("<info>");
				
		String param = (String)request.getParameter("iban");
		int saldo=0;
		
		ContoModel modelC= new ContoModel();
		try {
			saldo=modelC.cercaSaldo(param);
		}catch (SQLException e) {
			saldo=0;
		}
		if(param != null) {
			packed.append("<saldo>");
			packed.append(saldo);
			packed.append("</saldo>");
		}
	
		packed.append("</info>");
		response.getWriter().write(packed.toString());
	}

}
