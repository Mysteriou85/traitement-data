package dal.entityDAO;

import bo.entity.Acteur;
import bo.entity.Film;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ActeurDAO {
    private final EntityManager em;

    public ActeurDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * Méthode qui permet d'enregistré un acteur dans la base de donnée
     * @param acteur
     */
    public void createActeur (Acteur acteur) {
        em.getTransaction().begin();
        em.persist(acteur);
        em.getTransaction().commit();
    }

    /**
     * Méthode qui permet de chercher un acteur dans la base de donné avec un Acteur
     * @param acteur
     * @return
     */
    public Acteur getActeur(Acteur acteur) {
        TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.identite = :acteur", Acteur.class);
        query.setParameter("acteur", acteur.getIdentite());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    /**
     * Méthode qui permet de chercher un acteur dans la base de donné avec un String
     * @param acteur
     * @return
     */
    public Acteur getActeurByString(String acteur) {
        TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.identite = :nomActeur", Acteur.class);
        query.setParameter("nomActeur", acteur);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    /**
     * Méthode qui permet de chercher des acteurs à partir de deux films
     * @param film1
     * @param film2
     * @return
     */
    public List<Acteur> getActeurByFilmCommun(String film1, String film2) {
        TypedQuery<Acteur> query = em.createQuery("SELECT DISTINCT a FROM Acteur a JOIN a.films f WHERE " +
                "f.nom = :film1 AND " +
                "a.id IN (SELECT a.id FROM " +
                "Acteur a JOIN a.films f WHERE " +
                "f.nom = :film2)", Acteur.class);
        query.setParameter("film1", film1);
        query.setParameter("film2", film2);

        return query.getResultList().size() > 0 ? query.getResultList() : null;
    }

}
