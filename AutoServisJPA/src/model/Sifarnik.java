package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sifarnik database table.
 * 
 */
@Entity
@Table(name="sifarnik")
@NamedQuery(name="Sifarnik.findAll", query="SELECT s FROM Sifarnik s")
public class Sifarnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKvar;

	@Column(name="opis_kvar")
	private String opisKvar;

	//bi-directional many-to-many association to Popravka
	@ManyToMany(mappedBy="sifarniks")
	private List<Popravka> popravkas;

	public Sifarnik() {
	}

	public int getIdKvar() {
		return this.idKvar;
	}

	public void setIdKvar(int idKvar) {
		this.idKvar = idKvar;
	}

	public String getOpisKvar() {
		return this.opisKvar;
	}

	public void setOpisKvar(String opisKvar) {
		this.opisKvar = opisKvar;
	}

	public List<Popravka> getPopravkas() {
		return this.popravkas;
	}

	public void setPopravkas(List<Popravka> popravkas) {
		this.popravkas = popravkas;
	}

}