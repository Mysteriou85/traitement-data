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

    public void createFilm (Film film) {
        em.getTransaction().begin();
        em.persist(film);
        em.getTransaction().commit();
    }

    public Film getFilm(Film film) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE " +
                "f.nom =: film", Film.class);
        query.setParameter("film", film.getNom());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public Film getFilmByString(String film) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE " +
                "f.nom =: film", Film.class);
        query.setParameter("film", film);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public List<Film> getFilmByDate(String date1, String date2) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE " +
                "f.anneeSortie >= :date1 AND f.anneeSortie <= :date2", Film.class);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList().size() > 0 ? query.getResultList() : null;
    }

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
     * Récupère une liste de film
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
