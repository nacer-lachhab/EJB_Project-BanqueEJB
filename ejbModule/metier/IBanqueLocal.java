package metier;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Compte;

@Local
public interface IBanqueLocal {

	public Compte addCompte(Compte compte);
	public Compte getCompte(Long idCompte);
	public List<Compte> allComptes();
	public void verser(Long idCompte,double montant);
	public void retrait(Long idCompte,double montant);
	public void virement(Long idCompteSrc,Long idCompteDest,double montant);
}
