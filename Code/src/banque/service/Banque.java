package banque.service;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import banque.exceptions.CompteIntrouvableException;
import banque.exceptions.SoldeInsuffisantException;
import banque.model.Compte;

public class Banque{
	
	private Map<Integer, Compte> comptes;
	private Set<Integer> numerosComptes;
	
	public Banque(){
		this.comptes = new HashMap<>();
		this.numerosComptes = new HashSet<>();
	}
	
	public void ajouterCompte(Compte compte) throws Exception {
        if (numerosComptes.contains(compte.getNumero())) {
            throw new Exception("Numéro de compte déjà utilisé !");
        }
        comptes.put(compte.getNumero(), compte);
        numerosComptes.add(compte.getNumero());
    }
	
    public Compte rechercherCompte(int numero) throws CompteIntrouvableException {
        Compte compte = comptes.get(numero);
        if (compte == null) {
            throw new CompteIntrouvableException("Compte n°" + numero + " introuvable !");
        }
        return compte;
    }
	

	public void virement(int numDebiteur, int numCrediteur, double montant) throws Exception {
        if (numDebiteur == numCrediteur) {
            throw new Exception("Impossible de faire un virement vers le même compte !");
        }
        Compte debiteur = rechercherCompte(numDebiteur);
        Compte crediteur = rechercherCompte(numCrediteur);

        debiteur.debiter(montant);
        crediteur.crediter(montant);

        // Ajouter opérations virement dans historique
        debiteur.ajouterOperation(new banque.model.Operation(banque.model.TypeOperation.VIREMENT, montant,
                "Virement vers compte " + numCrediteur));
        crediteur.ajouterOperation(new banque.model.Operation(banque.model.TypeOperation.VIREMENT, montant,
                "Virement reçu du compte " + numDebiteur));
    }

	public void afficherComptes() {
		if(comptes.size() == 0) {
			System.out.println("Aucun compte.");
		}
		else{
			System.out.println("Liste des comptes :");
			for (Map.Entry<Integer, Compte> entry : comptes.entrySet()) {
				Integer numeroCompte = entry.getKey();
				Compte compte = entry.getValue();
				System.out.println("Numéro de compte : " + numeroCompte + " - " + compte);
			}
		}
	}

}