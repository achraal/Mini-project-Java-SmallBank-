package app;

import banque.exceptions.CompteIntrouvableException;
import banque.exceptions.SoldeInsuffisantException;
import banque.model.Compte;
import banque.model.CompteCourant;
import banque.model.CompteEpargne;
import banque.service.Banque;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationBanque {

    public static void main(String[] args) {
        Banque banque = new Banque();
        Scanner scanner = new Scanner(System.in);
        boolean quitter = false;

        while (!quitter) {
            System.out.println("\n--- MENU BANQUE ---");
            System.out.println("1. Ajouter un compte courant");
            System.out.println("2. Ajouter un compte épargne");
            System.out.println("3. Afficher tous les comptes");
            System.out.println("4. Faire un virement");
            System.out.println("5. Afficher historique d'un compte");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = -1;
            try {
                choix = scanner.nextInt();
                scanner.nextLine(); // Consommer la fin de ligne
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre valide !");
                scanner.nextLine(); // Consommer l'entrée invalide
                continue;
            }

            switch (choix) {
                case 1:
                    ajouterCompteCourant(banque, scanner);
                    break;
                case 2:
                    ajouterCompteEpargne(banque, scanner);
                    break;
                case 3:
                    banque.afficherComptes();
                    break;
                case 4:
                    faireVirement(banque, scanner);
                    break;
                case 5:
                    afficherHistoriqueCompte(banque, scanner);
                    break;
                case 0:
                    quitter = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }

        scanner.close();
    }

    private static void ajouterCompteCourant(Banque banque, Scanner scanner) {
        try {
            System.out.print("Numéro du compte : ");
            int numero = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nom du titulaire : ");
            String nom = scanner.nextLine();

            System.out.print("Prénom du titulaire : ");
            String prenom = scanner.nextLine();

            System.out.print("Solde initial : ");
            double solde = scanner.nextDouble();

            System.out.print("Découvert autorisé : ");
            double decouvert = scanner.nextDouble();
            scanner.nextLine();

            CompteCourant compte = new CompteCourant(numero, nom, prenom, solde, decouvert);
            banque.ajouterCompte(compte);
            System.out.println("Compte courant ajouté avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du compte : " + e.getMessage());
            scanner.nextLine(); // Nettoyer entrée si erreur
        }
    }

    private static void ajouterCompteEpargne(Banque banque, Scanner scanner) {
        try {
            System.out.print("Numéro du compte : ");
            int numero = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nom du titulaire : ");
            String nom = scanner.nextLine();

            System.out.print("Prénom du titulaire : ");
            String prenom = scanner.nextLine();

            System.out.print("Solde initial : ");
            double solde = scanner.nextDouble();

            System.out.print("Taux d'intérêt (%) : ");
            double taux = scanner.nextDouble();
            scanner.nextLine();

            CompteEpargne compte = new CompteEpargne(numero, nom, prenom, solde, taux);
            banque.ajouterCompte(compte);
            System.out.println("Compte épargne ajouté avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du compte : " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void faireVirement(Banque banque, Scanner scanner) {
        try {
            System.out.print("Numéro du compte débiteur : ");
            int numDebiteur = scanner.nextInt();

            System.out.print("Numéro du compte créditeur : ");
            int numCrediteur = scanner.nextInt();

            System.out.print("Montant du virement : ");
            double montant = scanner.nextDouble();
            scanner.nextLine();

            banque.virement(numDebiteur, numCrediteur, montant);
            System.out.println("Virement effectué avec succès !");
        } catch (CompteIntrouvableException | SoldeInsuffisantException e) {
            System.out.println("Erreur : " + e.getMessage());
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erreur générale : " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void afficherHistoriqueCompte(Banque banque, Scanner scanner) {
        try {
            System.out.print("Numéro du compte : ");
            int numero = scanner.nextInt();
            scanner.nextLine();

            Compte compte = banque.rechercherCompte(numero);
            compte.afficherHistorique();
        } catch (CompteIntrouvableException e) {
            System.out.println("Erreur : " + e.getMessage());
            scanner.nextLine();
        }
    }
}
