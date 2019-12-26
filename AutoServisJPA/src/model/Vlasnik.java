package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vlasnik database table.
 * 
 */
@Entity
@Table(name="vlasnik")
@NamedQuery(name="Vlasnik.findAll", query="SELECT v FROM Vlasnik v")
public class Vlasnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVlasnik;

	private String ime;

	private String mesto;

	private String prezime;

	//bi-directional many-to-one association to Vozilo
	@OneToMany(mappedBy="vlasnik")
	private List<Vozilo> vozilos;

	public Vlasnik() {
	}

	public int getIdVlasnik() {
		return this.idVlasnik;
	}

	public void setIdVlasnik(int idVlasnik) {
		this.idVlasnik = idVlasnik;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getMesto() {
		return this.mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Vozilo> getVozilos() {
		return this.vozilos;
	}

	public void setVozilos(List<Vozilo> vozilos) {
		this.vozilos = vozilos;
	}

	public Vozilo addVozilo(Vozilo vozilo) {
		getVozilos().add(vozilo);
		vozilo.setVlasnik(this);

		return vozilo;
	}

	public Vozilo removeVozilo(Vozilo vozilo) {
		getVozilos().remove(vozilo);
		vozilo.setVlasnik(null);

		return vozilo;
	}

}