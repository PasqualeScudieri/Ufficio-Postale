package bean;

import java.io.Serializable;

public class NotificheBean  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String cf;
	private int matricola;
	private String tipo;
	private int codice;
	private String iban;
	private int codPosta;
	
	public NotificheBean() {
		cf="";
		matricola=0;
		tipo="";
		codice=0;
		iban="";
		codPosta=0;
	}


	public int getCodPosta() {
		return codPosta;
	}

	public void setCodPosta(int codPosta) {
		this.codPosta = codPosta;
	}



	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		return "NotificheBean [cf=" + cf + ", matricola=" + matricola + ", tipo=" + tipo + ", codice=" + codice
				+ ", iban=" + iban + ", codPosta=" + codPosta + "]";
	}

	
}
