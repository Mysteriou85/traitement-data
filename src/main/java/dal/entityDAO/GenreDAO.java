package dal.entityDAO;

import bo.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class GenreDAO {
    private final EntityManager em;

    public GenreDAO(EntityManager em) {
        this.em = em;
    }

    public void createGenre (Genre genre) {
        em.getTransaction().begin();
        em.persist(genre);
        em.getTransaction().commit();
    }

    public Genre getGenre(Genre genre) {
        // note le typeGenre correspond à TYPE_GENRE
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.typeGenre = :genre", Genre.class);
        query.setParameter("genre", genre.getTypeGenre());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
