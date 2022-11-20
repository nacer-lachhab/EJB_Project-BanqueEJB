package metier.entities;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Compte
 *
 */
@XmlRootElement //binder les attributs, serialiser sous format xml a la demande client
@Entity
public class Compte implements Serializable {

	   
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double solde;
	private Date dateCreation;
	private static final long serialVersionUID = 1L;

	public Compte() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public double getSolde() {
		return this.solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}   
	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	@Override
	public String toString() {
		return "Compte [id=" + id + ", solde=" + solde + ", dateCreation=" + dateCreation + "]";
	}
}
