package bean;

import java.io.Serializable;
import java.util.Date;

public class DipendentiBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private String indirizzo;
	private String luogonascita;
	private Date dataNascita;
	private String cf;
	private int matricola;
	private String tipo;
	
	public DipendentiBean() {
		nome="";
		cognome="";
		indirizzo="";
		luogonascita="";
		cf="";
		matricola=0;
		tipo="";
		dataNascita=new Date();
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getCf() {
		return cf;
	}




	public void setCf(String cf) {
		this.cf = cf;
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


	public int getMatricola() {
		return matricola;
	}


	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public String getLuogonascita() {
		return luogonascita;
	}


	public void setLuogonascita(String luogonascita) {
		this.luogonascita = luogonascita;
	}


	public Date getDataNascita() {
		return dataNascita;
	}


	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String toString() {
		return "DipendentiBean [nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo + ", luogonascita="
				+ luogonascita + ", dataNascita=" + dataNascita + ", cf=" + cf + ", matricola=" + matricola + ", tipo="
				+ tipo + "]";
	}

	
}
