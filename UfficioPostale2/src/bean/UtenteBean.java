package bean;

import java.io.Serializable;

public class UtenteBean implements Serializable{

	private static final long serialVersionUID = 1336867260088959286L;
	
	private String cliente;
	private String user;
	private String password;
	private String tipo;
	
	public UtenteBean() {
		cliente="";
		user="";
		password="";
		tipo="";
	}

	public UtenteBean(String cliente, String user, String password, String tipo) {
		this.cliente = cliente;
		this.user = user;
		this.password = password;
		this.tipo=tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "UtenteBean [cliente=" + cliente + ", user=" + user + ", password=" + password + ", tipo=" + tipo + "]";
	}
	

}
