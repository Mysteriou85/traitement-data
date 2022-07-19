import bll.ScannerService;
import bll.TraitementDataService;
import bo.entity.Acteur;
import bo.entity.Film;
import dal.ConnectionDAO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class Interaction {

    private static ConnectionDAO connection;
    private static EntityManager em = connection.getConnectionDAO();
    private static ScannerService scannerService = new ScannerService(em);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                Choisissez une option :
                1 - Afficher la filmographie d'un acteur/actrice
                2 - Afficher le casting d'un film
                3 - Afficher les films sortis entre 2 années
                4 - Afficher les films communs à 2 acteurs/actrices
                5 - Afficher les acteurs/actrices commun à 2 films
                6 - Afficher les films sortis entre 2 années et qui possède un acteur/actrice spécifique
                9 - Sortie de l'application""");
        int selectionMenu = scanner.nextInt();

        // Ajout de la ligne suivante sinon les "nextLine()" ne fonctionne pas
        scanner.nextLine();
        switch (selectionMenu) {
            case (1) -> {
                System.out.println("* - Veuillez indiquer un acteur/actrice");
                String selectS1Acteur = scanner.nextLine();
                List<Film> filmographieActeur = scannerService.selectFilmParActeur(selectS1Acteur);

                System.out.println("Liste des films dans lequel" + selectS1Acteur + "a joué :");
                for (Film film : filmographieActeur) {
                    System.out.println("-> " + film.getNom());
                }
            }
            case (2) -> {
                System.out.println("* - Veuillez indiquer un film");
                String selectS2Film = scanner.nextLine();
                List<Acteur> casteFilm = scannerService.selectActeurParFilm(selectS2Film);

                System.out.println("Liste de la caste d'acteur de" + selectS2Film + ":");
                for (Acteur acteur : casteFilm) {
                    System.out.println("-> " + acteur.getIdentite());
                }
            }
            case (3) -> {
                System.out.println("* - Veuillez indiquer la 1ère année");
                String selectS3Annee1 = scanner.nextLine();
                System.out.println("* - Veuillez indiquer la 2ème année");
                String selectS3Annee2 = scanner.nextLine();
                List<Film> filmEntreDeuxAnnee = scannerService.selectFilmEntreDate(selectS3Annee1, selectS3Annee2);

                System.out.println("Voici la liste des films sortie entre " + selectS3Annee1 + " et " + selectS3Annee2 + " :");
                for (Film film : filmEntreDeuxAnnee) {
                    System.out.println("-> " + film.getNom());
                }

            }
            case (4) -> {
                System.out.println("* - Veuillez indiquer le 1er acteur/actrice");
                String selectS4Acteur1 = scanner.nextLine();
                System.out.println("* - Veuillez indiquer le 2ème acteur/actrice");
                String selectS4Acteur2 = scanner.nextLine();
            }
            case (5) -> {
                System.out.println("* - Veuillez indiquer le 1er film");
                String selectS5Film1 = scanner.nextLine();
                System.out.println("* - Veuillez indiquer le 2ème film");
                String selectS5Film2 = scanner.nextLine();
            }
            case (6) -> {
                System.out.println("* - Veuillez indiquer la 1ère année");
                String selectS6Annee1 = scanner.nextLine();
                System.out.println("* - Veuillez indiquer la 2ème année");
                String selectS6Annee2 = scanner.nextLine();
                System.out.println("* - Veuillez indiquer un acteur/actrice");
                String selectS6Acteur = scanner.nextLine();
            }
            case (9) -> {
                System.out.println("* - Fin de l'application");
                scanner.close();
            }
        }

    }
}
