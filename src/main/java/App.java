import bo.entity.*;
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
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("traitement-data");
        EntityManager em = entityManagerFactory.createEntityManager();

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(
                "D:\\project\\ProjetIntelliJIDEA\\traitement-data\\src\\main\\resources\\films.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray acteurList = (JSONArray) obj;
//            System.out.println(filmList);

            for (Object o : acteurList) {
                parseActeur((JSONObject) o);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseFilmsObject(JSONObject donneeJson) {
//        Film film = new Film();
        //JSONObject acteurObject = (JSONObject) acteur.get("roles");

        String identite = donneeJson.get("identite").toString();
        System.out.println(identite);



        JSONArray roles = (JSONArray) donneeJson.get("roles");
        Iterator<Object> iteratorRoles = roles.iterator();
        while (iteratorRoles.hasNext()) {

            System.out.println(iteratorRoles.next().toString());
        }
    }

    private static Acteur parseActeur(JSONObject a) {
        Acteur acteur = new Acteur();
        acteur.setIdentite(a.get("identite").toString());

        JSONObject naissanceJSON = (JSONObject) a.get("naissance");
        acteur.setNaissanceDate(naissanceJSON.get("dateNaissance").toString());
        acteur.setNaissanceLieu(naissanceJSON.get("lieuNaissance").toString());

        JSONArray rolesJSON = (JSONArray) a.get("roles");
        for (Object o : rolesJSON) {
            parseRole((JSONObject) o);
        }

        return acteur;
    }

    private static Role parseRole(JSONObject r) {
        Role role = new Role();
        role.setPersonnage(r.get("characterName").toString());

        List<Film> filmList = new ArrayList<>();
        filmList.add(parseFilm((JSONObject) r.get("film")));


        role.setFilms(filmList);
        return role;
    }

    private static Film parseFilm(JSONObject f) {
        Film film = new Film();

        film.setNom(f.get("nom").toString());
        film.setPlot(f.get("plot").toString());
        film.setLangue(f.get("langue").toString());
        if(f.get("anneeSortie") != null) {
            film.setAnneeSortie(f.get("anneeSortie").toString());
        }
        film.setUrl(f.get("url").toString());

        List<Genre> genreList = new ArrayList<>();
        JSONArray genresJSON = (JSONArray) f.get("genres");
        for (Object o : genresJSON) {
            genreList.add(parseGenre((String) o));
        }
        film.setGenres(genreList);

        film.setPays(parsePays((JSONObject) f.get("pays")));

        if(f.get("lieuTournage") != null) {
            film.setLieuTournage(parseLieuTournage((JSONObject) f.get("lieuTournage")));
        }

        List<Realisateur> realisateurList = new ArrayList<>();
        JSONArray realisateursJSON = (JSONArray) f.get("realisateurs");
        for (Object o : realisateursJSON) {
            realisateurList.add(parseRealisateur((JSONObject) o));
        }
        film.setRealisateurs(realisateurList);

        return film;
    }

    private static Genre parseGenre(String g) {
        Genre genre = new Genre();

        genre.setTypeGenre(g);

        return genre;
    }

    private static Pays parsePays(JSONObject p) {
        Pays pays = new Pays();

        pays.setNomPays(p.get("nom").toString());
        pays.setUrl(p.get("url").toString());

        return pays;
    }

    private static LieuTournage parseLieuTournage(JSONObject l) {
        LieuTournage lieuTournage = new LieuTournage();

        lieuTournage.setLieuTournageVille(l.get("ville").toString());
        lieuTournage.setLieuTournageEtat(l.get("etatDept").toString());
        lieuTournage.setLieuTournagePays(l.get("pays").toString());

        return lieuTournage;
    }

    private static Realisateur parseRealisateur(JSONObject r) {
        Realisateur realisateur = new Realisateur();

        realisateur.setIdentite(r.get("identite").toString());
        realisateur.setUrl(r.get("url").toString());

        return realisateur;
    }
}
