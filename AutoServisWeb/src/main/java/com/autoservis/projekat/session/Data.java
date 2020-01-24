package com.autoservis.projekat.session;

public class Data {
	
	private String ime;
	private String prezime;
	
	private Integer zavrsene = 0;
	private Integer u_procesu = 0;
	private Integer ceka = 0;
	
	public Data(String ime, String prezime, Integer zavrsene, Integer u_procesu, Integer ceka) {
		this.ime = ime;
		this.prezime = prezime;
		this.zavrsene = zavrsene;
		this.u_procesu = u_procesu;
		this.ceka = ceka;
	}
	
	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public Integer getZavrsene() {
		return zavrsene;
	}

	public Integer getU_procesu() {
		return u_procesu;
	}

	public Integer getCeka() {
		return ceka;
	}
	
}
