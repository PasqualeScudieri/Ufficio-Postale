package bean;

import java.io.Serializable;

public class BancopostaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private double tasso;
	/*/////////////////////////////maaaaaaaaaaa sto char////////////////////////////////*/
	private char servInternet;
	private double costo;
	/*////////////////////////////////idem//////////////////////////////*/
	private char carta;
	private String iban;
	
	public BancopostaBean() {
		tasso=0;
		servInternet='\0';
		costo=0;
		carta='\0';
		iban="";
	}

	public double getTasso() {
		return tasso;
	}

	public void setTasso(double tasso) {
		this.tasso = tasso;
	}

	public char getServInternet() {
		return servInternet;
	}

	public void setServInternet(char servInternet) {
		this.servInternet = servInternet;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public char getCarta() {
		return carta;
	}

	public void setCarta(char carta) {
		this.carta = carta;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String toString() {
		return "BancopostaBean [tasso=" + tasso + ", servInternet=" + servInternet + ", costo=" + costo + ", carta="
				+ carta + ", iban=" + iban + "]";
	}
	
	
}
