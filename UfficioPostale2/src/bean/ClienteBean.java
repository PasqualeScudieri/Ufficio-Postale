package bean;

import java.io.Serializable;
import java.util.Date;

public class ClienteBean implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private String cf;
	private String luogoNascita;
	private String indirizzo;
	private Date dataNascita;
	
	public ClienteBean() {
		nome="";
		cognome="";
		cf="";
		luogoNascita="";
		indirizzo="";
		dataNascita= new Date();
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getCf() {
		return cf;
	}
	
	public void setCf(String cf) {
		this.cf = cf;
	}
	
	public String getLuogoNascita() {
		return luogoNascita;
	}
	
	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public Date getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String toString() {
		return "ClienteBean [nome=" + nome + ", cognome=" + cognome + ", cf=" + cf + ", luogoNascita=" + luogoNascita
				+ ", indirizzo=" + indirizzo + ", dataNascita=" + dataNascita + "]";
	}
	
	public ClienteBean clone() {
			try {
		      return (ClienteBean) super.clone();
		    }
		    catch(CloneNotSupportedException e) {
		      return null;
		    }
	}
		
}
