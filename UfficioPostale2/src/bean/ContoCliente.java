package bean;

public class ContoCliente {
	private ClienteBean cliente;
	private String iban;
	
	public ContoCliente() {
		cliente=new ClienteBean();
		iban="";
	}
	
	public ClienteBean getCliente() {
		return cliente.clone();
	}
	
	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente.clone();
	}
	
	public String getIban() {
		return iban;
	}
	
	public void setIban(String iban) {
		this.iban = iban;
	}

	public String toString() {
		return "ContoCliente [cliente=" + cliente.toString() + ", iban=" + iban + "]";
	}
	
	
}
