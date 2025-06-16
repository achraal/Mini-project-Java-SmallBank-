package banque.interfaces;

public interface OperationsBancaires{
	void crediter(double montant);
	void debiter(double montant) throws Exception;
}