package posta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import com.sun.org.apache.bcel.internal.generic.NEW;

import bean.ClienteBean;

public class TestSpedisciServlet {

	SpedisciServlet servlet= new SpedisciServlet();
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

	//TC 80s
	@Test
	public void valoreTipoNonValido() throws Exception {
		request.setParameter("tipo", "posta");
		request.setParameter("dest", "Giuseppe Bianchi");
		request.setParameter("indirizzo", "Via Roma 22");
		MockHttpSession session= new MockHttpSession();
		ClienteBean clienteBean= new ClienteBean();
		clienteBean.setCf("1234567890123456");
		session.setAttribute("cliente", clienteBean);
		request.setSession(session);
		try {
			servlet.doPost(request, response);
		}catch (NullPointerException e) {

		}

		Object obj= request.getAttribute("error");
		System.out.println(obj);
		assertEquals("inserisci un tipo valido", obj.toString());
	}
	
	
}
