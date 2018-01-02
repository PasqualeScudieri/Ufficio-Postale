package bean;

import java.io.Serializable;

public class TelefonoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String numero;
	private String clienteCF;
	
	public TelefonoBean(){
		numero="";
		clienteCF="";
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getClienteCF() {
		return clienteCF;
	}

	public void setClienteCF(String clienteCF) {
		this.clienteCF = clienteCF;
	}

	
	public String toString() {
		return "TelefonoBean [numero=" + numero + ", clienteCF=" + clienteCF + "]";
	}
	
	
	
	
}
