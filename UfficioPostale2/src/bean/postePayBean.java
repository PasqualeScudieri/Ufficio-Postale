package bean;

import java.io.Serializable;
import java.util.Date;

public class postePayBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private int numCarta;
	private Date scadenza;
	private int codSicur;
	private String iban;
	
	public postePayBean() {
		numCarta=0;
		scadenza=new Date();
		codSicur=0;
		iban="";
	}

	public int getNumCarta() {
		return numCarta;
	}

	public void setNumCarta(int numCarta) {
		this.numCarta = numCarta;
	}

	public Date getScadenza() {
		return scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}

	public int getCodSicur() {
		return codSicur;
	}

	public void setCodSicur(int codSicur) {
		this.codSicur = codSicur;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String toString() {
		return "postePayBean [numCarta=" + numCarta + ", scadenza=" + scadenza + ", codSicur=" + codSicur + ", iban="
				+ iban + "]";
	}
	
	
	
}
