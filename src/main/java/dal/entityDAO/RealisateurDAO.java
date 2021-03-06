package dal.entityDAO;

import bo.entity.Realisateur;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RealisateurDAO {
    private final EntityManager em;

    public RealisateurDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * Méthode qui permet d'enregistré un réalisateur dans la base de donnée
     * @param realisateur
     */
    public void createRealisateur (Realisateur realisateur) {
        em.getTransaction().begin();
        em.persist(realisateur);
        em.getTransaction().commit();
    }

    /**
     * Méthode qui permet de chercher un realisateur dans la base de donné avec un Realisateur
     * @param realisateur
     * @return
     */
    public Realisateur getRealisateur(Realisateur realisateur) {
        TypedQuery<Realisateur> query = em.createQuery("SELECT r FROM Realisateur r WHERE r.identite = :realisateur", Realisateur.class);
        query.setParameter("realisateur", realisateur.getIdentite());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
