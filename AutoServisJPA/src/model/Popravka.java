package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;


/**
 * The persistent class for the popravka database table.
 * 
 */
@Entity
@Table(name="popravka")
@NamedQuery(name="Popravka.findAll", query="SELECT p FROM Popravka p")
public class Popravka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPopravka;

	@Temporal(TemporalType.DATE)
	@Column(name="datum_prijema")
	private Date datumPrijema;

	@Temporal(TemporalType.DATE)
	@Column(name="datum_zavrsetka")
	private Date datumZavrsetka;

	@Column(name="opis_popravke")
	private String opisPopravke;
	
	private Double cena;

	//bi-directional many-to-many association to Radnik
	@ManyToMany
	@JoinTable(
		name="popravka_has_radnik"
		, joinColumns={
			@JoinColumn(name="Popravka_idPopravka")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Radnik_idRadnik")
			}
		)
	private List<Radnik> radniks;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="Status_idStatus")
	private Status status;

	//bi-directional many-to-many association to Usluga
	@ManyToMany
	@JoinTable(
		name="popravka_has_usluga"
		, joinColumns={
			@JoinColumn(name="Popravka_idPopravka")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Usluga_idUsluga")
			}
		)
	private List<Usluga> uslugas;

	//bi-directional many-to-many association to Vozilo
	@ManyToMany
	@JoinTable(
		name="vozilo_has_popravka"
		, joinColumns={
			@JoinColumn(name="Popravka_idPopravka")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Vozilo_idVozilo")
			}
		)
	private List<Vozilo> vozilos;

	public Popravka() {
	}

	public int getIdPopravka() {
		return this.idPopravka;
	}

	public void setIdPopravka(int idPopravka) {
		this.idPopravka = idPopravka;
	}

	public Date getDatumPrijema() {
		return this.datumPrijema;
	}

	public void setDatumPrijema(Date datumPrijema) {
		this.datumPrijema = datumPrijema;
	}

	public Date getDatumZavrsetka() {
		return this.datumZavrsetka;
	}

	public void setDatumZavrsetka(Date datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	public String getOpisPopravke() {
		return this.opisPopravke;
	}

	public void setOpisPopravke(String opisPopravke) {
		this.opisPopravke = opisPopravke;
	}
	
	@Transactional
	public List<Radnik> getRadniks() {
		return this.radniks;
	}

	public void setRadniks(List<Radnik> radniks) {
		this.radniks = radniks;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Transactional
	public List<Usluga> getUslugas() {
		return this.uslugas;
	}

	public void setUslugas(List<Usluga> uslugas) {
		this.uslugas = uslugas;
	}
	
	@Transactional
	public List<Vozilo> getVozilos() {
		return this.vozilos;
	}

	public void setVozilos(List<Vozilo> vozilos) {
		this.vozilos = vozilos;
	}
	
	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	public Double getCena() {
		return cena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cena == null) ? 0 : cena.hashCode());
		result = prime * result + ((datumPrijema == null) ? 0 : datumPrijema.hashCode());
		result = prime * result + ((datumZavrsetka == null) ? 0 : datumZavrsetka.hashCode());
		result = prime * result + idPopravka;
		result = prime * result + ((opisPopravke == null) ? 0 : opisPopravke.hashCode());
		result = prime * result + ((radniks == null) ? 0 : radniks.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((uslugas == null) ? 0 : uslugas.hashCode());
		result = prime * result + ((vozilos == null) ? 0 : vozilos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Popravka other = (Popravka) obj;
		if (cena == null) {
			if (other.cena != null)
				return false;
		} else if (!cena.equals(other.cena))
			return false;
		if (datumPrijema == null) {
			if (other.datumPrijema != null)
				return false;
		} else if (!datumPrijema.equals(other.datumPrijema))
			return false;
		if (datumZavrsetka == null) {
			if (other.datumZavrsetka != null)
				return false;
		} else if (!datumZavrsetka.equals(other.datumZavrsetka))
			return false;
		if (idPopravka != other.idPopravka)
			return false;
		if (opisPopravke == null) {
			if (other.opisPopravke != null)
				return false;
		} else if (!opisPopravke.equals(other.opisPopravke))
			return false;
		if (radniks == null) {
			if (other.radniks != null)
				return false;
		} else if (!radniks.equals(other.radniks))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (uslugas == null) {
			if (other.uslugas != null)
				return false;
		} else if (!uslugas.equals(other.uslugas))
			return false;
		if (vozilos == null) {
			if (other.vozilos != null)
				return false;
		} else if (!vozilos.equals(other.vozilos))
			return false;
		return true;
	}
	
	

}