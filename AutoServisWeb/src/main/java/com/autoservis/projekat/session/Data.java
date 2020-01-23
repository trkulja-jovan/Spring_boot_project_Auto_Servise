package com.autoservis.projekat.session;

public class Data {
	
	private String ime;
	private String prezime;
	
	private Integer zavrsene = 0;
	private Integer u_procesu = 0;
	private Integer ceka = 0;
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Integer getZavrsene() {
		return zavrsene;
	}
	
	public void setZavrsene(Integer zavrsene) {
		this.zavrsene = zavrsene;
	}
	
	public Integer getU_procesu() {
		return u_procesu;
	}
	
	public void setU_procesu(Integer u_procesu) {
		this.u_procesu = u_procesu;
	}
	
	public Integer getCeka() {
		return ceka;
	}
	
	public void setCeka(Integer ceka) {
		this.ceka = ceka;
	}

}
