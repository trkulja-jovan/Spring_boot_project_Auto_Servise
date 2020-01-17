package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.transaction.Transactional;

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
	
	@Transactional
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idStatus;
		result = prime * result + ((opis == null) ? 0 : opis.hashCode());
		result = prime * result + ((popravkas == null) ? 0 : popravkas.hashCode());
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
		Status other = (Status) obj;
		if (idStatus != other.idStatus)
			return false;
		if (opis == null) {
			if (other.opis != null)
				return false;
		} else if (!opis.equals(other.opis))
			return false;
		if (popravkas == null) {
			if (other.popravkas != null)
				return false;
		} else if (!popravkas.equals(other.popravkas))
			return false;
		return true;
	}
	
	

}