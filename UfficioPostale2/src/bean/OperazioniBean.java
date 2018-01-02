package bean;

import java.io.Serializable;
import java.util.Date;

public class OperazioniBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int codice;
	private double importo;
	private Date dataOper;
	private String tipo;
	private char segno;

	public OperazioniBean() {
		codice= 0;
		importo= 0;
		dataOper= new Date();
		tipo="";
		segno='\0';
	}
	
	
	public int getCodice() {
		return codice;
	}
	
	
	public void setCodice(int codice) {
		this.codice = codice;
	}
	
	
	public double getImporto() {
		return importo;
	}
	
	
	public void setImporto(double importo) {
		this.importo = importo;
	}
	
	
	public Date getDataOper() {
		return dataOper;
	}
	
	
	public void setDataOper(Date dataOper) {
		this.dataOper = dataOper;
	}
	
	
	public String getTipo() {
		return tipo;
	}
	
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public char getSegno() {
		return segno;
	}

	public void setSegno(char segno) {
		this.segno = segno;
	}


	public String toString() {
		return "OperazioniBean [codice=" + codice + ", importo=" + importo + ", dataOper=" + dataOper + ", tipo=" + tipo
				+ ", segno=" + segno + "]";
	}
	
}