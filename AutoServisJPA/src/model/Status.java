package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@Table(name="status")
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStatus;

	private String opis;

	//bi-directional many-to-one association to Popravka
	@OneToMany(mappedBy="status")
	private List<Popravka> popravkas;

	public Status() {
	}

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Popravka> getPopravkas() {
		return this.popravkas;
	}

	public void setPopravkas(List<Popravka> popravkas) {
		this.popravkas = popravkas;
	}

	public Popravka addPopravka(Popravka popravka) {
		getPopravkas().add(popravka);
		popravka.setStatus(this);

		return popravka;
	}

	public Popravka removePopravka(Popravka popravka) {
		getPopravkas().remove(popravka);
		popravka.setStatus(null);

		return popravka;
	}

}