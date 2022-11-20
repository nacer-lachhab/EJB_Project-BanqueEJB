package metier;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Compte;

@Remote
public interface IBanqueRemote {

	public Compte addCompte(Compte compte);
	public Compte getCompte(Long idCompte);
	public Compte editCompte(Long idCompte,Compte newvalue);
	public void deleteCompte(Long idCompte);
	public List<Compte> allComptes();
	public void verser(Long idCompte,double montant);
	public void retrait(Long idCompte,double montant);
	public void virement(Long idCompteSrc,Long idCompteDest,double montant);
}
