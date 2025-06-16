package banque.model;
import banque.exceptions.SoldeInsuffisantException;

public class CompteCourant extends Compte{

	private double decouvertAutorise;
	
	public CompteCourant(int numero, String nomTitulaire, String prenomTitulaire, double solde, double decouvertAutorise){
		super(numero, nomTitulaire, prenomTitulaire, solde);
		this.decouvertAutorise = decouvertAutorise;
	}
	
	@Override
    public void debiter(double montant) throws SoldeInsuffisantException {
        if (this.solde + decouvertAutorise < montant) {
            throw new SoldeInsuffisantException("Solde insuffisant, découvert autorisé dépassé !");
        }
        this.solde -= montant;
        historiqueOperations.add(new Operation(TypeOperation.DEBIT, montant, "Débit compte courant"));
    }

    @Override
    public String toString() {
        return super.toString() + " (Compte Courant, Découvert autorisé : " + decouvertAutorise + ")";
    }
}