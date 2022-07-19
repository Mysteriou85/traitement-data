package dal.entityDAO;

import bo.entity.Acteur;
import bo.entity.Film;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ActeurDAO {
    private final EntityManager em;

    public ActeurDAO(EntityManager em) {
        this.em = em;
    }

    public void createActeur (Acteur acteur) {
        em.getTransaction().begin();
        em.persist(acteur);
        em.getTransaction().commit();
    }

    public Acteur getActeur(Acteur acteur) {
        TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.identite = :acteur", Acteur.class);
        query.setParameter("acteur", acteur.getIdentite());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public Acteur getActeurByString(String acteur) {
        TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.identite = :nomActeur", Acteur.class);
        query.setParameter("nomActeur", acteur);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

}
