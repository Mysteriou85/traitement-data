import bll.TraitementDataService;
import dal.ConnectionDAO;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe qui permet de convertire le fichier JSON en base de données
 */
public class JsonToBaseDonnee {
    private static ConnectionDAO connection;
    private static EntityManager em = connection.getConnectionDAO();
    private static TraitementDataService traitementDataService = new TraitementDataService(em);

    public static void main(String[] args) {

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(
                "D:\\project\\ProjetIntelliJIDEA\\traitement-data\\src\\main\\resources\\films.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray acteurList = (JSONArray) obj;

            for (Object o : acteurList) {
                traitementDataService.saveActeur(o);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
