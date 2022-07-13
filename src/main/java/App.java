import bll.TraitementDataService;
import bo.entity.*;
import dal.ConnectionDAO;
import dal.TraitementDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {
    private static ConnectionDAO connection;
    private static EntityManager em = connection.getConnectionDAO();
    private static TraitementDataService traitementDataService = new TraitementDataService(em);

    public static void main(String[] args) {

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(
                "D:\\project\\ProjetIntelliJIDEA\\traitement-data\\src\\main\\resources\\films.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray acteurList = (JSONArray) obj;
            // System.out.println(filmList);

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
