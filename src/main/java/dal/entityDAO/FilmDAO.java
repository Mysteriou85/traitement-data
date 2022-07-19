package dal.entityDAO;

import bo.entity.Acteur;
import bo.entity.Film;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FilmDAO {
    private final EntityManager em;
    public FilmDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * Méthode qui permet d'enregistré un film dans la base de donnée
     * @param film
     */
    public void createFilm (Film film) {
        em.getTransaction().begin();
        em.persist(film);
        em.getTransaction().commit();
    }

    /**
     * Méthode qui permet de chercher un film dans la base de donné avec un Film
     * @param film
     * @return
     */
    public Film getFilm(Film film) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE " +
                "f.nom =: film", Film.class);
        query.setParameter("film", film.getNom());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    /**
     * Méthode qui permet de chercher un film dans la base de donné avec un String
     * @param film
     * @return
     */
    public Film getFilmByString(String film) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE " +
                "f.nom =: film", Film.class);
        query.setParameter("film", film);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    /**
     * Méthode qui permet de chercher une liste de film dans la base de donné avec deux String
     * @param date1
     * @param date2
     * @return
     */
    public List<Film> getFilmByDate(String date1, String date2) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE " +
                "f.anneeSortie >= :date1 AND f.anneeSortie <= :date2", Film.class);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList().size() > 0 ? query.getResultList() : null;
    }

    /**
     * Méthode qui permet de chercher une liste de film dans la base de donné avec deux String
     * @param acteur1
     * @param acteur2
     * @return
     */
    public List<Film> getFilmByActeurCommun(String acteur1, String acteur2) {
        TypedQuery<Film> query = em.createQuery("SELECT DISTINCT f FROM Film f JOIN f.acteurs a WHERE " +
                "a.identite = :acteur1 AND " +
                "f.id IN (SELECT f.id FROM " +
                "Film f JOIN f.acteurs a WHERE " +
                "a.identite = :acteur2)", Film.class);
        query.setParameter("acteur1", acteur1);
        query.setParameter("acteur2", acteur2);

        return query.getResultList().size() > 0 ? query.getResultList() : null;
    }

    /**
     * Méthode qui permet de chercher une liste de film dans la base de donné avec trois String
     * @param acteur
     * @param date1
     * @param date2
     * @return
     */
    public List<Film> getFilmByActorAndDate(String acteur, String date1, String date2) {
        TypedQuery<Film> query = em.createQuery("SELECT DISTINCT f FROM Film f JOIN f.acteurs a WHERE " +
                "a.identite = :acteur AND " +
                "f.id IN (SELECT f.id FROM " +
                "Film f JOIN f.acteurs a WHERE " +
                "f.anneeSortie >= :date1 AND f.anneeSortie <= :date2)", Film.class);
        query.setParameter("acteur", acteur);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);

        return query.getResultList().size() > 0 ? query.getResultList() : null;
    }
}
