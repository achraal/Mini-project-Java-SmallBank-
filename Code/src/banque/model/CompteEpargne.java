package banque.model;

import banque.exceptions.SoldeInsuffisantException;

public class CompteEpargne extends Compte {

	private double tauxInteret;
	
	public CompteEpargne(int numero, String nomTitulaire, String prenomTitulaire, double solde, double tauxInteret){
		super(numero, nomTitulaire, prenomTitulaire, solde);
		this.tauxInteret = tauxInteret;
	}
	
	@Override 
	public void debiter(double montant) throws SoldeInsuffisantException {
		if (this.solde < montant){
			throw new SoldeInsuffisantException("Votre solde est insuffisant !!");
		}
		this.solde -= montant;
		historiqueOperations.add(new Operation(TypeOperation.DEBIT, montant, "Débit compte épargne"));
	}
	
    public void appliquerInteret() {
        double interet = solde * tauxInteret / 100;
        solde += interet;
        historiqueOperations.add(new Operation(TypeOperation.CREDIT, interet, "Intérêt appliqué"));
    }

    @Override
    public String toString() {
        return super.toString() + " (Compte Épargne, Taux d'intérêt : " + tauxInteret + "%)";
    }
}