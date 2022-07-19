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
        List<Film> listFilm = filmDAO.getFilmByDate(date1, date2);
        if (listFilm != null) {
            return listFilm;
        }
        return null;
    }

    public static List<Film> selectCommunEntreActeur(String acteur1, String acteur2) {

    }
}
