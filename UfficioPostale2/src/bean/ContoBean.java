package bean;

import java.io.Serializable;

public class ContoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String iban;
	private double saldo;
	
	public ContoBean() {
		iban="";
		saldo=0;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String toString() {
		return "ContoBean [iban=" + iban + ", saldo=" + saldo + "]";
	}
	
	
}
