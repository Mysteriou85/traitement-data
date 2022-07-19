package bll;

import bo.entity.*;
import dal.entityDAO.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui s'occupe des services pour le traitement des données du fichier JSON en base de donnée
 */
public class TraitementDataService {

    private static ActeurDAO acteurDAO;
    private static FilmDAO filmDAO;
    private static GenreDAO genreDAO;
    private static LieuTournageDAO lieuTournageDAO;
    private static PaysDAO paysDAO;
    private static RealisateurDAO realisateurDAO;
    private static RoleDAO roleDAO;

    /**
     * Constructeur de la classe TraitementDataService
     * @param em
     */
    public TraitementDataService(EntityManager em) {
        acteurDAO = new ActeurDAO(em);
        filmDAO = new FilmDAO(em);
        genreDAO = new GenreDAO(em);
        lieuTournageDAO = new LieuTournageDAO(em);
        paysDAO = new PaysDAO(em);
        realisateurDAO = new RealisateurDAO(em);
        roleDAO = new RoleDAO(em);
    }

    /**
     * Méthode qui permet d'appeler la création d'un acteur sur la base de donnée
     * @param o
     */
    public void saveActeur(Object o) {

        Acteur acteur = parseActeur((JSONObject) o);
        acteurDAO.createActeur(acteur);
    }

    /**
     * Méthode qui permet de parse Acteur
     * @param a
     * @return
     */
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

    /**
     * Méthode qui permet de parse Role
     * @param r
     * @return
     */
    private static Role parseRole(JSONObject r) {
        Role role = new Role();
        role.setPersonnage(r.get("characterName").toString());

        // Observe les données dans "film" et les ajoutes à une liste
        List<Film> filmList = new ArrayList<>();
        filmList.add(parseFilm((JSONObject) r.get("film")));

        role.setFilms(filmList);
        //return role;
        return getRole(role);
    }

    /**
     * Méthode qui permet d'appeler la création d'un role sur la base de donnée
     * @param roleVerif
     * @return
     */
    public static Role getRole(Role roleVerif) {
        if(roleDAO.getRole(roleVerif) == null) {
            roleDAO.createRole(roleVerif);
            return roleVerif;
        } else {
            return roleDAO.getRole(roleVerif);
        }
    }

    /**
     * Méthode qui permet de parse Film
     * @param f
     * @return
     */
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

        // La liste de genre est set dans film
        film.setGenres(genreList);

        // Observe les données dans "pays" et les set dans le film
        if(f.get("pays") != null ) {
            film.setPays(parsePays((JSONObject) f.get("pays")));
        }

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
        film.setActeurs(acteurList);

        List<Acteur> castingPrincipalList = new ArrayList<>();
        JSONArray castingPrincipalJSON = (JSONArray) f.get("castingPrincipal");
        for (Object o : castingPrincipalJSON) {
            castingPrincipalList.add(parseCastingPrincipal((JSONObject) o));
        }
        film.setCastingPrincipal(castingPrincipalList);

        return getFilm(film);
    }

    /**
     * Méthode qui permet d'appeler la création d'un film sur la base de donnée
     * @param filmVerif
     * @return
     */
    public static Film getFilm(Film filmVerif) {
        if(filmDAO.getFilm(filmVerif) == null) {
            filmDAO.createFilm(filmVerif);
            return filmVerif;
        } else {
            return filmDAO.getFilm(filmVerif);
        }
    }

    /**
     * Méthode qui permet de parse Genre
     * @param g
     * @return
     */
    private static Genre parseGenre(String g) {
        Genre genre = new Genre();

        genre.setTypeGenre(g);

        return getGenre(genre);
    }

    /**
     * Méthode qui permet d'appeler la création d'un genre sur la base de donnée
     * @param genreVerif
     * @return
     */
    public static Genre getGenre(Genre genreVerif) {
        if(genreDAO.getGenre(genreVerif) == null) {
            genreDAO.createGenre(genreVerif);
            return genreVerif;
        } else {
            return genreDAO.getGenre(genreVerif);
        }
    }

    /**
     * Méthode qui permet de parse Pays
     * @param p
     * @return
     */
    private static Pays parsePays(JSONObject p) {
        Pays pays = new Pays();

        pays.setNomPays(p.get("nom").toString());
        pays.setUrl(p.get("url").toString());

        return getPays(pays);
    }

    /**
     * Méthode qui permet d'appeler la création d'un Pays sur la base de donnée
     * @param paysVerif
     * @return
     */
    public static Pays getPays (Pays paysVerif) {
        if(paysDAO.getPays(paysVerif) == null) {
            paysDAO.createPays(paysVerif);
            return paysVerif;
        } else {
            return paysDAO.getPays(paysVerif);
        }
    }

    /**
     * Méthode qui permet de parse un LieuTournage
     * @param l
     * @return
     */
    private static LieuTournage parseLieuTournage(JSONObject l) {
        LieuTournage lieuTournage = new LieuTournage();

        lieuTournage.setLieuTournageVille(l.get("ville").toString());
        lieuTournage.setLieuTournageEtat(l.get("etatDept").toString());
        lieuTournage.setLieuTournagePays(l.get("pays").toString());

        return getLieuTournage(lieuTournage);
    }

    /**
     * Méthode qui permet d'appeler la création d'un LieuTournage sur la base de donnée
     * @param lieuTournageVerif
     * @return
     */
    public static LieuTournage getLieuTournage (LieuTournage lieuTournageVerif) {
        if(lieuTournageDAO.getLieuTournage(lieuTournageVerif) == null) {
            lieuTournageDAO.createLieuTournage(lieuTournageVerif);
            return lieuTournageVerif;
        } else {
            return lieuTournageDAO.getLieuTournage(lieuTournageVerif);
        }
    }

    /**
     * Méthode qui permet de parse un Réalisateur
     * @param r
     * @return
     */
    private static Realisateur parseRealisateur(JSONObject r) {
        Realisateur realisateur = new Realisateur();

        realisateur.setIdentite(r.get("identite").toString());
        realisateur.setUrl(r.get("url").toString());

        return getRealisateur(realisateur);
    }

    /**
     * Méthode qui permet d'appeler la création d'un réalisateur sur la base de donnée
     * @param realisateurVerif
     * @return
     */
    public static Realisateur getRealisateur (Realisateur realisateurVerif) {
        if(realisateurDAO.getRealisateur(realisateurVerif) == null) {
            realisateurDAO.createRealisateur(realisateurVerif);
            return realisateurVerif;
        } else {
            return realisateurDAO.getRealisateur(realisateurVerif);
        }
    }

    /**
     * Méthode qui permet de parse une liste d'acteur
     * @param a
     * @return
     */
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

        return getActeur(acteur);
    }

    /**
     * Méthode qui permet de parse une liste d'action présent dans le casting principale
     * @param a
     * @return
     */
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

        return getActeur(acteur);
    }

    /**
     * Méthode qui permet d'appeler la création d'un acteur sur la base de donnée
     * @param acteurVerif
     * @return
     */
    public static Acteur getActeur (Acteur acteurVerif) {
        if(acteurDAO.getActeur(acteurVerif) == null) {
            acteurDAO.createActeur(acteurVerif);
            return acteurVerif;
        } else {
            return acteurDAO.getActeur(acteurVerif);
        }
    }

}
