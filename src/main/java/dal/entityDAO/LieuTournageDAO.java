package dal.entityDAO;

import bo.entity.LieuTournage;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class LieuTournageDAO {
    private final EntityManager em;

    public LieuTournageDAO(EntityManager em) {
        this.em = em;
    }

    public void createLieuTournage (LieuTournage lieuTournage) {
        em.getTransaction().begin();
        em.persist(lieuTournage);
        em.getTransaction().commit();
    }

    public LieuTournage getLieuTournage(LieuTournage lieuTournage) {
        TypedQuery<LieuTournage> query = em.createQuery("SELECT l FROM LieuTournage l WHERE " +
                "l.lieuTournagePays = :lieuTournagePays AND " +
                "l.lieuTournageEtat = :lieuTournageEtat AND " +
                "l.lieuTournageVille = :lieuTournageVille", LieuTournage.class);
        query.setParameter("lieuTournagePays", lieuTournage.getLieuTournagePays());
        query.setParameter("lieuTournageEtat", lieuTournage.getLieuTournageEtat());
        query.setParameter("lieuTournageVille", lieuTournage.getLieuTournageVille());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
