package bo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.persistence.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestLecture {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("traitement-data");
        EntityManager em = entityManagerFactory.createEntityManager();

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(
                "D:\\project\\ProjetIntelliJIDEA\\traitement-data\\src\\main\\resources\\films.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray filmList = (JSONArray) obj;
            System.out.println(filmList);

            filmList.forEach( emp -> parseActeurObject( (JSONObject) emp ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseActeurObject(JSONObject acteur)
    {
        JSONObject acteurObject = (JSONObject) acteur.get("roles");

//        String identite = (String) acteurObject.get("identite");
//        System.out.println(identite);
//
//        String naissance = (String) acteurObject.get("naissance");
//        System.out.println(naissance);
//
//        String url = (String) acteurObject.get("url");
//        System.out.println(url);
    }
}
