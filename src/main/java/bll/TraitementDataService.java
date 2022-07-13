package bll;

import bo.entity.*;
import dal.TraitementDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class TraitementDataService {

    private static TraitementDAO dao;

    public TraitementDataService(EntityManager em) {
        this.dao = new TraitementDAO(em);
    }

    public void saveActeur(Object o) {

        Acteur acteur = parseActeur((JSONObject) o);
        dao.createActeur(acteur);
    }

    private static Acteur parseActeur(JSONObject a) {
        Acteur acteur = new Acteur();
        acteur.setUrl(a.get("url").toString());
        acteur.setIdentite(a.get("identite").toString());
        acteur.setIdImdp(a.get("id").toString());

        JSONObject naissanceJSON = (JSONObject) a.get("naissance");
        acteur.setNaissanceDate(naissanceJSON.get("dateNaissance").toString());
        acteur.setNaissanceLieu(naissanceJSON.get("lieuNaissance").toString());

        List<Role> roleList = new ArrayList<>();
        // Observe les données dans "roles"
        JSONArray rolesJSON = (JSONArray) a.get("roles");
        for (Object o : rolesJSON) {
            roleList.add(parseRole((JSONObject) o));
        }
        for (Role role : roleList) {
            role.getActeurs().add(acteur);
        }

        acteur.setRoles(roleList);

        return acteur;
    }

    private static Role parseRole(JSONObject r) {
        Role role = new Role();
        role.setPersonnage(r.get("characterName").toString());

        // Observe les données dans "film" et les ajoutes à une liste
        List<Film> filmList = new ArrayList<>();
        filmList.add(parseFilm((JSONObject) r.get("film")));

        role.setFilms(filmList);
        return role;
    }

    private static Film parseFilm(JSONObject f) {
        Film film = new Film();

        film.setNom(f.get("nom").toString());
        if(f.get("plot") != null) {
            film.setPlot(f.get("plot").toString());
        }
        if(f.get("langue") != null) {
            film.setLangue(f.get("langue").toString());
        }
        if(f.get("anneeSortie") != null) {
            film.setAnneeSortie(f.get("anneeSortie").toString());
        }
        film.setUrl(f.get("url").toString());

        // Observe les données dans "genre" et les ajoutes à une liste
        List<Genre> genreList = new ArrayList<>();
        JSONArray genresJSON = (JSONArray) f.get("genres");
        for (Object o : genresJSON) {
            // Le tableau "genre" contient que des strings
            genreList.add(parseGenre((String) o));
        }
//        System.out.println(genreList);
        // La liste de genre est set dans film
        film.setGenres(genreList);

        // Observe les données dans "pays" et les set dans le film
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

        List<Acteur> acteurList = new ArrayList<>();
        JSONArray acteurJSON = (JSONArray) f.get("acteurs");
        for (Object o : acteurJSON) {
            acteurList.add(parseActeurListe((JSONObject) o));
        }

        List<Acteur> castingPrincipalList = new ArrayList<>();
        JSONArray castingPrincipalJSON = (JSONArray) f.get("castingPrincipal");
        for (Object o : castingPrincipalJSON) {
            castingPrincipalList.add(parseCastingPrincipal((JSONObject) o));
        }

        return film;
    }

    private static Genre parseGenre(String g) {
        Genre genre = new Genre();

        genre.setTypeGenre(g);

        return getGenre(genre);
    }

    public static Genre getGenre(Genre genreVerif) {
        if(dao.getGenre(genreVerif) == null) {
            dao.createGenre(genreVerif);
            return genreVerif;
        } else {
            return dao.getGenre(genreVerif);
        }
    }

    private static Pays parsePays(JSONObject p) {
        Pays pays = new Pays();

        if(p != null) {
            pays.setNomPays(p.get("nom").toString());
            pays.setUrl(p.get("url").toString());
        }

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

    private static Acteur parseActeurListe(JSONObject a) {
        Acteur acteur = new Acteur();

        acteur.setIdentite(a.get("identite").toString());
        acteur.setUrl(a.get("url").toString());
        acteur.setIdImdp(a.get("id").toString());

        JSONObject naissanceJSON = (JSONObject) a.get("naissance");
        if(naissanceJSON != null) {
            acteur.setNaissanceDate(naissanceJSON.get("dateNaissance").toString());
        }
        if(naissanceJSON != null) {
            acteur.setNaissanceLieu(naissanceJSON.get("lieuNaissance").toString());
        }

        return acteur;
    }

    private static Acteur parseCastingPrincipal(JSONObject a) {
        Acteur acteur = new Acteur();

        acteur.setIdentite(a.get("identite").toString());
        acteur.setUrl(a.get("url").toString());
        acteur.setIdImdp(a.get("id").toString());

        JSONObject naissanceJSON = (JSONObject) a.get("naissance");

        if(naissanceJSON != null) {
            acteur.setNaissanceDate(naissanceJSON.get("dateNaissance").toString());
        }
        if(naissanceJSON != null) {
            acteur.setNaissanceLieu(naissanceJSON.get("lieuNaissance").toString());
        }

        return acteur;
    }

}
