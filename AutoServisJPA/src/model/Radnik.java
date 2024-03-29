package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.transaction.Transactional;


/**
 * The persistent class for the radnik database table.
 * 
 */
@Entity
@Table(name="radnik")
@NamedQuery(name="Radnik.findAll", query="SELECT r FROM Radnik r")
public class Radnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRadnik;

	private String ime;

	@Column(name="kor_ime")
	private String korIme;

	private String kvalifikacije;

	private String password;

	private String prezime;

	//bi-directional many-to-many association to Popravka
	@ManyToMany(mappedBy="radniks")
	private List<Popravka> popravkas;

	//bi-directional many-to-one association to Uloga
	@ManyToOne
	@JoinColumn(name="Uloga_idUloga")
	private Uloga uloga;

	public Radnik() {
	}

	public int getIdRadnik() {
		return this.idRadnik;
	}

	public void setIdRadnik(int idRadnik) {
		this.idRadnik = idRadnik;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getKorIme() {
		return this.korIme;
	}

	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}

	public String getKvalifikacije() {
		return this.kvalifikacije;
	}

	public void setKvalifikacije(String kvalifikacije) {
		this.kvalifikacije = kvalifikacije;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	@Transactional
	public List<Popravka> getPopravkas() {
		return this.popravkas;
	}

	public void setPopravkas(List<Popravka> popravkas) {
		this.popravkas = popravkas;
	}

	public Uloga getUloga() {
		return this.uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

}