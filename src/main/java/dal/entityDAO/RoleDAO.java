package dal.entityDAO;

import bo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RoleDAO {
    private final EntityManager em;

    public RoleDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * Méthode qui permet d'enregistré un role dans la base de donnée
     * @param role
     */
    public void createRole (Role role) {
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();
    }

    /**
     * Méthode qui permet de chercher un role dans la base de donné avec un Role
     * @param role
     * @return
     */
    public Role getRole(Role role) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.personnage = :role", Role.class);
        query.setParameter("role", role.getPersonnage());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
