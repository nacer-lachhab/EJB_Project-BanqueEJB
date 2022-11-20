package metier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import metier.entities.Compte;

@Stateless(name = "Bank")//name ="Bank": utilisé quand le moteur d'ejb publie la ref dans JNDI
public class BanqueEJBImp implements IBanqueLocal,IBanqueRemote{

	//injection via persistence-unit dans persistence.xml [assosié a un DataSource cad DB]
	//unitName="banqueEJB": optionnel comme il n'y qu'une seule.
	@PersistenceContext(unitName="banqueEJB") 
	private EntityManager em;
	
	@Override
	public Compte addCompte(Compte compte) {
		em.persist(compte);
		return compte;
	}

	@Override
	public Compte getCompte(Long idCompte) {
		Compte compteById = em.find(Compte.class,idCompte);
		if(compteById == null) throw new RuntimeException("no count with id: "+idCompte);
		return compteById;
	}

	@Override
	public List<Compte> allComptes() {
		//select tous les objets c de la class Compte.
		TypedQuery<Compte> req = em.createQuery("select c from Compte c",Compte.class);
		return req.getResultList();
	}

	@Override
	public void verser(Long idCompte, double montant) {
		Compte compteById = getCompte(idCompte);
		compteById.setSolde(compteById.getSolde()+montant);
	}

	@Override
	public void retrait(Long idCompte, double montant) {
		Compte compteById = getCompte(idCompte);
		if(compteById.getSolde()<montant) throw new RuntimeException("solde insuffisant");
		compteById.setSolde(compteById.getSolde()-montant);
		
	}

	@Override
	public void virement(Long idCompteSrc, Long idCompteDest, double montant) {
		retrait(idCompteSrc, montant);
		verser(idCompteDest, montant);
	}

	@Override
	public Compte editCompte(Long idCompte, Compte newvalueCp) {
		Compte compteDb = getCompte(idCompte);//tester son existence
		compteDb.setDateCreation(compteDb.getDateCreation());
		compteDb.setSolde(newvalueCp.getSolde());
		return compteDb;
	}

	@Override
	public void deleteCompte(Long idCompte) {
		em.remove(getCompte(idCompte));
		System.out.println("compte avec id: "+idCompte+"supprimé");
	}	
}
