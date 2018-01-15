package conti;

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

public class TestNuovoBancoPosta {

	NuovoBancoPostaServlet servlet= new NuovoBancoPostaServlet();
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	PrintWriter responseWriter;


	@Before
	public void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		responseWriter = mock(PrintWriter.class);
		// request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
		//response = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);
		//when(response.getWriter()).thenReturn(responseWriter);
	}

	@After
	public void tearDown() {
		request = null;
		response = null;
		responseWriter = null;
	}

	//TC_37
	@Test
	public void valoreCartaNonValido() throws Exception {
		//when(request.getParameter("carta")).thenReturn("e");
		//when(request.getParameter("servInternet")).thenReturn("y");
		//when(request.getParameter("costo")).thenReturn("2");
		//request=( ((NuovoBancoPostaServlet)servlet).test(request, response));
		//verify(responseWriter).write("inserisci un valore valido per Carta");
		request.setParameter("carta", "h");
		request.setParameter("servInternet", "");
		request.setParameter("costo", "1");

		try {
			servlet.doPost(request, response);
		}catch (NullPointerException e) {
			
		}
		
		Object obj= request.getAttribute("error");
		assertEquals("inserisci un valore valido per Carta", obj.toString());
		//System.out.println("ssssss");
		//System.out.println(obj);
	}
	
	//TC_38
	@Test
	public void valoreServInternetNonValido() throws Exception {
		request.setParameter("carta", "n");
		request.setParameter("servInternet", "h");
		request.setParameter("costo", "1");

		try {
			servlet.doPost(request, response);
		}catch (NullPointerException e) {
			
		}
		
		Object obj= request.getAttribute("error");
		assertEquals("inserisci un valore valido per Servizi Internet", obj.toString());
	}
	
}
