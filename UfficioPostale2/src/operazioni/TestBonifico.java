package operazioni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gestioneUtenti.RegistrazioneAdminServlet;

public class TestBonifico {

	BonificoServlet servlet= new BonificoServlet();
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


	//TC 73
	@Test
	public void valoreIbanFromNonValido() throws Exception {
		request.setParameter("from", "a");

		try {
			servlet.doPost(request, response);
		}catch (NullPointerException e) {

		}

		Object obj= request.getAttribute("error");
		//System.out.println(obj);
		assertEquals("Iban from dev essere lungo 27 caratteri", obj.toString());
	}
}
