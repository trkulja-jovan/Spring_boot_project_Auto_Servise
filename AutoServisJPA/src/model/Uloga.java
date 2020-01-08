package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.transaction.Transactional;

import java.util.List;


/**
 * The persistent class for the uloga database table.
 * 
 */
@Entity
@Table(name="uloga")
@NamedQuery(name="Uloga.findAll", query="SELECT u FROM Uloga u")
public class Uloga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUloga;

	@Column(name="naziv_uloge")
	private String nazivUloge;

	//bi-directional many-to-one association to Radnik
	@OneToMany(mappedBy="uloga")
	private List<Radnik> radniks;

	public Uloga() {
	}

	public int getIdUloga() {
		return this.idUloga;
	}

	public void setIdUloga(int idUloga) {
		this.idUloga = idUloga;
	}

	public String getNazivUloge() {
		return this.nazivUloge;
	}

	public void setNazivUloge(String nazivUloge) {
		this.nazivUloge = nazivUloge;
	}

	@Transactional
	public List<Radnik> getRadniks() {
		return this.radniks;
	}

	public void setRadniks(List<Radnik> radniks) {
		this.radniks = radniks;
	}

	public Radnik addRadnik(Radnik radnik) {
		getRadniks().add(radnik);
		radnik.setUloga(this);

		return radnik;
	}

	public Radnik removeRadnik(Radnik radnik) {
		getRadniks().remove(radnik);
		radnik.setUloga(null);

		return radnik;
	}

}