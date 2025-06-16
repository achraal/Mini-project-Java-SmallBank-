package banque.model;
import java.util.ArrayList;
import java.util.List;
import banque.interfaces.OperationsBancaires;
import banque.exceptions.SoldeInsuffisantException;

public abstract class Compte implements OperationsBancaires{
	private int numero;
	private String nomTitulaire;
	private String prenomTitulaire;
	protected double solde;
	protected List<Operation> historiqueOperations;
	
	public Compte(int numero, String nomTitulaire, String prenomTitulaire, double solde){
		this.numero = numero;
		this.nomTitulaire = nomTitulaire;
		this.prenomTitulaire = prenomTitulaire;
		this.solde = solde;
		this.historiqueOperations = new ArrayList<>();
	}
	
	public int getNumero(){
		return this.numero;
	}
	
	public String getNomTitulaire(){
		return this.nomTitulaire;
	}
	
	public String getPrenomTitulaire(){
		return this.prenomTitulaire;
	}
	
	public double getSolde(){
		return this.solde;
	}
	
	@Override
	public String toString(){
		return "Compte n°" + this.numero + " - Titulaire : " + this.nomTitulaire + " " + this.prenomTitulaire + " - Solde : " + this.solde + " dhs.";
	}
	
	@Override
	public void crediter(double montant){
		this.solde += montant;
		historiqueOperations.add(new Operation(TypeOperation.CREDIT, montant, "Crédit"));
	}
	
	@Override
	public void debiter(double montant) throws SoldeInsuffisantException {
		if (this.solde < montant){
			throw new SoldeInsuffisantException("Votre solde est insuffisant !!");
		}
		this.solde -= montant;
		historiqueOperations.add(new Operation(TypeOperation.DEBIT, montant, "Débit"));
	}
	
	public void afficherHistorique(){
		if(historiqueOperations.size() == 0){
			System.out.println("L'historique est vide.");
		}
		else{
			System.out.println("Historique des opérations pour le compte n°" + numero);
			for (Operation historique : historiqueOperations){
				System.out.println(historique);
			}
		}
	}
	
	public void ajouterOperation(Operation operation) {
		this.historiqueOperations.add(operation);
	}

}