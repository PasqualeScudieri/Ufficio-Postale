package operazioni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class TestGiroconto {

	GirocontoServlet servlet= new GirocontoServlet();
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


	//TC 74
	@Test
	public void valoreIbanFromNonValido() throws Exception {
		request.setParameter("from", "a");

		try {
			servlet.doPost(request, response);
		}catch (NullPointerException e) {

		}

		Object obj= request.getAttribute("error");
		System.out.println(obj);
		assertEquals("Iban from dev essere lungo 27 caratteri", obj.toString());
	}
	
	
	//TC 74
	@Test
	public void valoreIbanToNonValido() throws Exception {
		request.setParameter("from", "IT76P0760103400100000002480");
		request.setParameter("to", "a");

		try {
			servlet.doPost(request, response);
		}catch (NullPointerException e) {

		}

		Object obj= request.getAttribute("error");
		System.out.println(obj);
		assertEquals("Iban to dev essere lungo 27 caratteri", obj.toString());
	}
	
	
}
