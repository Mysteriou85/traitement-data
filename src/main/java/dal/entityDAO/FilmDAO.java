package dal.entityDAO;

import bo.entity.Film;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class FilmDAO {
    private final EntityManager em;

    public FilmDAO(EntityManager em) {
        this.em = em;
    }


    public Film getFilm(Film film) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE " +
                "f.nom =: film", Film.class);
        query.setParameter("film", film.getNom());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public void createFilm (Film film) {
        em.getTransaction().begin();
        em.persist(film);
        em.getTransaction().commit();
    }
}
