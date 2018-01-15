package ajaxServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BancopostaBean;
import bean.postePayBean;
import model.BancoPostaModel;
import model.PostePayModel;

/**
 * Questa servlet serve a portare a termine la chiamata ajax che permette nella pagina di Giroconto di non avere l'iban selezionato
 * nell'altra select 
 */
@WebServlet("/cliente/IbanServlet")
public class IbanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IbanServlet() {
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
	
	/**Metodo utilizzato per la chiamata ajax
	 * @param request  nella request devono essere setati i paramentri "iban" e "cf"
	 * @param response la response che viene restituita
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		
		StringBuffer packed = new StringBuffer();
		packed.append("<info>");
				
		String param = (String)request.getParameter("iban");

		String cf = (String)request.getParameter("cf");
		BancoPostaModel modelBP= new BancoPostaModel();
		ArrayList<BancopostaBean> arrayBP= new ArrayList<>();
		try {
			arrayBP=modelBP.cercaPerCf(cf);
		}catch(SQLException e) {
			
		}
		
		PostePayModel modelPP= new PostePayModel();
		ArrayList<postePayBean> arrayPP=new ArrayList<>();
		try {
			arrayPP=modelPP.cercaPerCf(cf);
		}catch(SQLException e){
			
		}
		
		if(param != null) {
			int size=0;
			for(BancopostaBean bpb:arrayBP) {
				if(!bpb.getIban().equals(param) && bpb.getServInternet()=='y') {
					packed.append("<iban>");
					packed.append(bpb.getIban());
					packed.append("</iban>");
					size++;
				}
			}
			for(postePayBean ppb:arrayPP) {
				if(!ppb.getIban().equals(param)) {
					packed.append("<iban>");
					packed.append(ppb.getIban());
					packed.append("</iban>");
					size++;
				}
			}
			packed.append("<size>");
			packed.append(size);
			packed.append("</size>");

		}
	
		packed.append("</info>");
		response.getWriter().write(packed.toString());
	}

}
