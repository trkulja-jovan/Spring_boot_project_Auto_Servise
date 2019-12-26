package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vozilo database table.
 * 
 */
@Entity
@Table(name="vozilo")
@NamedQuery(name="Vozilo.findAll", query="SELECT v FROM Vozilo v")
public class Vozilo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVozilo;

	private String marka;

	@Column(name="reg_tablice")
	private String regTablice;

	//bi-directional many-to-one association to Vlasnik
	@ManyToOne
	@JoinColumn(name="Vlasnik_idVlasnik")
	private Vlasnik vlasnik;

	//bi-directional many-to-many association to Popravka
	@ManyToMany(mappedBy="vozilos")
	private List<Popravka> popravkas;

	public Vozilo() {
	}

	public int getIdVozilo() {
		return this.idVozilo;
	}

	public void setIdVozilo(int idVozilo) {
		this.idVozilo = idVozilo;
	}

	public String getMarka() {
		return this.marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getRegTablice() {
		return this.regTablice;
	}

	public void setRegTablice(String regTablice) {
		this.regTablice = regTablice;
	}

	public Vlasnik getVlasnik() {
		return this.vlasnik;
	}

	public void setVlasnik(Vlasnik vlasnik) {
		this.vlasnik = vlasnik;
	}

	public List<Popravka> getPopravkas() {
		return this.popravkas;
	}

	public void setPopravkas(List<Popravka> popravkas) {
		this.popravkas = popravkas;
	}

}