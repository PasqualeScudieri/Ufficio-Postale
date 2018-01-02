package bean;

import java.io.Serializable;
import java.util.Date;

public class PostaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int codice;
	private Date dataSpedizione;
	private String  tipo;
	private int dipendente;
	private String destinatario;
	private String mittente;
	private Date dataConsegna;
	private String indirizzo;
	
	public PostaBean() {
		codice=0;
		dataConsegna=new Date();
		tipo="";
		dipendente=0;
		destinatario="";
		mittente="";
		indirizzo="";
		dataConsegna=new Date();
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public Date getDataSpedizione() {
		return dataSpedizione;
	}

	public void setDataSpedizione(Date dataSpedizione) {
		this.dataSpedizione = dataSpedizione;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getDipendente() {
		return dipendente;
	}

	public void setDipendente(int dipendente) {
		this.dipendente = dipendente;
	}

	public String getMittente() {
		return mittente;
	}

	public void setMittente(String mittente) {
		this.mittente = mittente;
	}

	public Date getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}
	

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public String toString() {
		return "PostaBean [codice=" + codice + ", dataSpedizione=" + dataSpedizione + ", tipo=" + tipo + ", dipendente="
				+ dipendente + ", destinatario=" + destinatario + ", mittente=" + mittente + ", dataConsegna="
				+ dataConsegna + "]";
	}


	
	
}
