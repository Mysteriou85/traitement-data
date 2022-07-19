package bll;

import bo.entity.Acteur;
import bo.entity.Film;
import dal.entityDAO.ActeurDAO;
import dal.entityDAO.FilmDAO;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Classe qui s'occupe des services lié au scanner (intéraction utilisateur -> base de donnée)
 */
public class ScannerService {
    private static ActeurDAO acteurDAO;
    private static FilmDAO filmDAO;

    /**
     * Constructeur de la classe ScannerService
     * @param em
     */
    public ScannerService(EntityManager em) {
        acteurDAO = new ActeurDAO(em);
        filmDAO = new FilmDAO(em);
    }

    /**
     * Méthode qui retourne une liste de film avec un String
     * @param nomActeur
     * @return
     */
    public static List<Film> selectFilmParActeur(String nomActeur) {
        Acteur acteur = acteurDAO.getActeurByString(nomActeur);
        if (acteur != null) {
            return acteur.getFilms();
        }
        return null;
    }

    /**
     * Méthode qui retourne une liste d'acteur avec un String
     * @param nomFilm
     * @return
     */
    public static List<Acteur> selectActeurParFilm(String nomFilm) {
        Film film = filmDAO.getFilmByString(nomFilm);
        if (film != null) {
            return film.getActeurs();
        }
        return null;
    }

    /**
     * Méthode qui retourne une liste de film avec deux String
     * @param date1
     * @param date2
     * @return
     */
    public static List<Film> selectFilmEntreDate(String date1, String date2) {
        List<Film> filmList = filmDAO.getFilmByDate(date1, date2);
        if (filmList != null) {
            return filmList;
        }
        return null;
    }

    /**
     * Méthode qui retourne une liste de film avec deux String
     * @param acteur1
     * @param acteur2
     * @return
     */
    public static List<Film> selectCommunEntreActeur(String acteur1, String acteur2) {
        List<Film> filmList = filmDAO.getFilmByActeurCommun(acteur1, acteur2);
        if(filmList != null) {
            return filmList;
        }
        return null;
    }

    /**
     * Méthode qui retourne une liste d'acteur avec deux String
     * @param film1
     * @param film2
     * @return
     */
    public static List<Acteur> selectActeurCommunEntre1Film(String film1, String film2) {
        List<Acteur> acteurList = acteurDAO.getActeurByFilmCommun(film1, film2);
        if(acteurList != null) {
            return acteurList;
        }
        return null;
    }

    /**
     * Méthode qui retourne une liste de film avec trois String
     * @param acteur
     * @param date1
     * @param date2
     * @return
     */
    public static List<Film> selectFilmAvecActeurEtDate(String acteur, String date1, String date2) {
        List<Film> filmList = filmDAO.getFilmByActorAndDate(acteur, date1, date2);
        if(filmList != null) {
            return filmList;
        }
        return null;
    }
}
