package dal.entityDAO;

import bo.entity.Pays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PaysDAO {
    private final EntityManager em;

    public PaysDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * Méthode qui permet d'enregistré un pays dans la base de donnée
     * @param pays
     */
    public void createPays (Pays pays) {
        em.getTransaction().begin();
        em.persist(pays);
        em.getTransaction().commit();
    }

    /**
     * Méthode qui permet de chercher un pays dans la base de donné avec un Pays
     * @param pays
     * @return
     */
    public Pays getPays(Pays pays) {
        TypedQuery<Pays> query = em.createQuery("SELECT p FROM Pays p WHERE p.nomPays = :pays", Pays.class);
        query.setParameter("pays", pays.getNomPays());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
