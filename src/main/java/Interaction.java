import bll.ScannerService;
import bll.TraitementDataService;
import dal.ConnectionDAO;

import javax.persistence.EntityManager;
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

        switch (selectionMenu) {
            case (1) -> {
                System.out.println("* - Veuillez indiquer un acteur/actrice");
                String selectS1Acteur = scanner.nextLine();
            }
            case (2) -> {
                System.out.println("* - Veuillez indiquer un film");
                String selectS2Film = scanner.nextLine();
            }
            case (3) -> {
                System.out.println("* - Veuillez indiquer la 1ère année");
                int selectS3Annee1 = scanner.nextInt();
                System.out.println("* - Veuillez indiquer la 2ème année");
                int selectS3Annee2 = scanner.nextInt();
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
                int selectS6Annee1 = scanner.nextInt();
                System.out.println("* - Veuillez indiquer la 2ème année");
                int selectS6Annee2 = scanner.nextInt();
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
