package bean;

import java.io.Serializable;

public class PacchiBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private double peso;
	private double volume;
	private int codice;

	
	public PacchiBean() {
		peso=0;
		volume=0;
		codice=0;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	public double getVolume() {
		return volume;
	}


	public void setVolume(double volume) {
		this.volume = volume;
	}


	public int getCodice() {
		return codice;
	}


	public void setCodice(int codice) {
		this.codice = codice;
	}


	public String toString() {
		return "PacchiBean [peso=" + peso + ", volume=" + volume + ", codice=" + codice + "]";
	}

	



}
