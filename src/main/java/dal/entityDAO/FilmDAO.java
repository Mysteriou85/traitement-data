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
}
