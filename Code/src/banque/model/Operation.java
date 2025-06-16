package banque.model;
import java.time.LocalDateTime;
import banque.model.TypeOperation;
import java.time.format.DateTimeFormatter;


public class Operation{
	private TypeOperation type;
	private double montant;
	private LocalDateTime date;
	private String details;

	public Operation(TypeOperation type, double montant, String details){
		this.type = type;
		this.montant = montant;
		this.date = LocalDateTime.now();
		this.details = details;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String dateFormatee = this.date.format(formatter);
		return "[" + dateFormatee + "] " + this.type + " : " + this.montant + " dhs (" + this.details + ")";
	}
}