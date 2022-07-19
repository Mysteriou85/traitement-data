package bll;

import bo.entity.Acteur;
import bo.entity.Film;
import dal.entityDAO.ActeurDAO;
import dal.entityDAO.FilmDAO;

import javax.persistence.EntityManager;
import java.util.List;

public class ScannerService {
    private static ActeurDAO acteurDAO;
    private static FilmDAO filmDAO;

    public ScannerService(EntityManager em) {
        acteurDAO = new ActeurDAO(em);
        filmDAO = new FilmDAO(em);
    }

    public static List<Film> selectFilmParActeur(String nomActeur) {
        System.out.println("====>" + nomActeur);
        Acteur acteur = acteurDAO.getActeurByString(nomActeur);
        if (acteur != null) {
            return acteur.getFilms();
        }
        return null;
    }

    public static List<Acteur> selectActeurParFilm(String nomFilm) {
        Film film = filmDAO.getFilmByString(nomFilm);
        if (film != null) {
            return film.getActeurs();
        }
        return null;
    }

    public static List<Film> selectFilmEntreDate(String date1, String date2) {
        List<Film> filmList = filmDAO.getFilmByDate(date1, date2);
        if (filmList != null) {
            return filmList;
        }
        return null;
    }

    public static List<Film> selectCommunEntreActeur(String acteur1, String acteur2) {
        List<Film> filmList = filmDAO.getFilmByActeurCommun(acteur1, acteur2);
        if(filmList != null) {
            return filmList;
        }
        return null;
    }

    public static List<Acteur> selectActeurCommunEntre1Film(String film1, String film2) {
        List<Acteur> acteurList = acteurDAO.getActeurByFilmCommun(film1, film2);
        if(acteurList != null) {
            return acteurList;
        }
        return null;
    }

    public static List<Film> selectFilmAvecActeurEtDate(String acteur, String date1, String date2) {
        List<Film> filmList = filmDAO.getFilmByActorAndDate(acteur, date1, date2);
        if(filmList != null) {
            return filmList;
        }
        return null;
    }
}
