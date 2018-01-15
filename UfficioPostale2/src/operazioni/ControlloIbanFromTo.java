package operazioni;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BancopostaBean;
import model.BancoPostaModel;
import model.ContoModel;

public class ControlloIbanFromTo {
		public static String controlloIban(HttpServletRequest request, HttpServletResponse response) {
			String ibanFrom= request.getParameter("from");
			String ibanTo=request.getParameter("to");
			//System.out.println("incontrollo");
			if(ibanFrom==null || ibanFrom.equals("")) {	
				 String error="metti iban from valido";
			      request.setAttribute("error",error);
			      return "page";
			}
			
			if(ibanFrom.length()!=27) {
				 String error="Iban from dev essere lungo 27 caratteri";
			      request.setAttribute("error",error);
			      return "page";
			}
			
			if(ibanTo==null || ibanTo.equals("")) {	
				 String error="metti iban to valido";
			      request.setAttribute("error",error);
			      return "page";
			}
			
			if(ibanTo.length()!=27) {
				 String error="Iban to dev essere lungo 27 caratteri";
			      request.setAttribute("error",error);
			      return "page";
			}
			if(ibanFrom.equals(ibanTo)) {
				  String error="metti iban diversi";
			      request.setAttribute("error",error);
			      return "page";
			}
						
			String imp= request.getParameter("importo");
			double importo;
			try {
				importo=Double.parseDouble(imp);
			}catch (NumberFormatException e) {
				String error="importo non valido";
			    request.setAttribute("error",error);
			    return "page";
			}
			int saldo;
			try {
				ContoModel modelC= new ContoModel();
				saldo=modelC.cercaSaldo(ibanFrom);
			}catch(SQLException e) {
				e.printStackTrace();
			    return "error";
			}
			
			if(importo>saldo) {
				String error="importo superiore al saldo";
			    request.setAttribute("error",error);
			    return "page";
			}
			
			if(importo<=0) {
				String error="importo non valido";
			    request.setAttribute("error",error);
			    return "page";
			}
			
			try {	
				//ho deciso che nn faccio il controllo sul bancoposta to perchè è il from che mi interessa
				BancoPostaModel modelBP= new BancoPostaModel();
				BancopostaBean bancoposta= modelBP.cercaByIban(ibanFrom);
				if(bancoposta!=null && !bancoposta.getIban().equals("") && bancoposta.getServInternet()!='y') {
					String error="bancoposta  from senza servizi internet";
				    request.setAttribute("error",error);
				    return "page";
				}
			}catch(SQLException e) {
				e.printStackTrace();
			    return "error";
			}
			
			return "ok";
		}
}
