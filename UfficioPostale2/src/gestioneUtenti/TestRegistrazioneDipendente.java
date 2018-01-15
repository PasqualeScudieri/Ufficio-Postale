package gestioneUtenti;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import conti.NuovoBancoPostaServlet;


public class TestRegistrazioneDipendente {

	RegistrazioneAdminServlet servlet= new RegistrazioneAdminServlet();
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	PrintWriter responseWriter;
	
	@Before
	public void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		responseWriter = mock(PrintWriter.class);
	
	}

	@After
	public void tearDown() {
		request = null;
		response = null;
		responseWriter = null;
	}

	//TC 29
	@Test
	public void valoreTipoNonValido() throws Exception {
		request.setParameter("nome", "Giuseppe");
		request.setParameter("cognome", "Vitiello");
		request.setParameter("cf", "1234567890123456");
		request.setParameter("indirizzo", "via Dante 1");
		request.setParameter("luogoNascita", "Pompei");
		request.setParameter("dataNascita", "1970-11-22");
		request.setParameter("matricola", "123456");
		request.setParameter("tipo", "maestra");
		

		try {
			servlet.doPost(request, response);
		}catch (NullPointerException e) {
			
		}
		
		Object obj= request.getAttribute("error");
		//System.out.println(obj);
		assertEquals("Inserisci email <br>inserire un tipo valido <br/>", obj.toString());
	}
}
