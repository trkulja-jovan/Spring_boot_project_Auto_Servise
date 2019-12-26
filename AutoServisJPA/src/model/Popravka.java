package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="Status_idStatus")
	private Status status;

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

	//bi-directional many-to-many association to Sifarnik
	@ManyToMany
	@JoinTable(
		name="sifarnik_has_popravka"
		, joinColumns={
			@JoinColumn(name="Popravka_idPopravka")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Sifarnik_idKvar")
			}
		)
	private List<Sifarnik> sifarniks;

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

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Radnik> getRadniks() {
		return this.radniks;
	}

	public void setRadniks(List<Radnik> radniks) {
		this.radniks = radniks;
	}

	public List<Vozilo> getVozilos() {
		return this.vozilos;
	}

	public void setVozilos(List<Vozilo> vozilos) {
		this.vozilos = vozilos;
	}

	public List<Sifarnik> getSifarniks() {
		return this.sifarniks;
	}

	public void setSifarniks(List<Sifarnik> sifarniks) {
		this.sifarniks = sifarniks;
	}

	public List<Usluga> getUslugas() {
		return this.uslugas;
	}

	public void setUslugas(List<Usluga> uslugas) {
		this.uslugas = uslugas;
	}

}