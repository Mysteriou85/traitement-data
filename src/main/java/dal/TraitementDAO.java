package dal;

import bo.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class TraitementDAO {

    private final EntityManager em;

    public TraitementDAO() {
        this.em = ConnectionDAO.getConnectionDAO();
    }

    public void createActeur (Acteur acteur) {
        em.getTransaction().begin();
        em.persist(acteur);
        em.getTransaction().commit();
    }

    public void createFilm (Film film) {
        em.getTransaction().begin();
        em.persist(film);
        em.getTransaction().commit();
    }

    public void createGenre (Genre genre) {
        em.getTransaction().begin();
        em.persist(genre);
        em.getTransaction().commit();
    }

    public void createIndividu (Individu individu) {
        em.getTransaction().begin();
        em.persist(individu);
        em.getTransaction().commit();
    }

    public void createLieuTournage (LieuTournage lieuTournage) {
        em.getTransaction().begin();
        em.persist(lieuTournage);
        em.getTransaction().commit();
    }

    public void createPays (Pays pays) {
        em.getTransaction().begin();
        em.persist(pays);
        em.getTransaction().commit();
    }

    public void createRealisateur (Realisateur realisateur) {
        em.getTransaction().begin();
        em.persist(realisateur);
        em.getTransaction().commit();
    }

    public void createRole (Role role) {
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();
    }

    // A MODIFIER
//    public Acteur getActeur(String libelle) {
//        TypedQuery<Acteur> query = em.createQuery("SELECT e FROM ACTEUR e WHERE e.libelle = :libelle", Acteur.class);
//        query.setParameter("libelle", libelle);
//        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
//    }
//
//    public Film getFilm(String libelle) {
//        TypedQuery<Film> query = em.createQuery("SELECT e FROM FILM e WHERE e.libelle = :libelle", Film.class);
//        query.setParameter("libelle", libelle);
//        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
//    }
//
//    public Genre getGenre(String libelle) {
//        TypedQuery<Genre> query = em.createQuery("SELECT e FROM GENRE e WHERE e.libelle = :libelle", Genre.class);
//        query.setParameter("libelle", libelle);
//        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
//    }
//
//    public Individu getIndividu(String libelle) {
//        TypedQuery<Individu> query = em.createQuery("SELECT e FROM INDIVIDU e WHERE e.libelle = :libelle", Individu.class);
//        query.setParameter("libelle", libelle);
//        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
//    }
//
//    public LieuTournage getLieuTournage(String libelle) {
//        TypedQuery<LieuTournage> query = em.createQuery("SELECT e FROM LIEU_TOURNAGE e WHERE e.libelle = :libelle", LieuTournage.class);
//        query.setParameter("libelle", libelle);
//        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
//    }
//
//    public Pays getPays(String libelle) {
//        TypedQuery<Pays> query = em.createQuery("SELECT e FROM PAYS e WHERE e.libelle = :libelle", Pays.class);
//        query.setParameter("libelle", libelle);
//        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
//    }
//
//    public Realisateur getRealisateur(String libelle) {
//        TypedQuery<Realisateur> query = em.createQuery("SELECT e FROM REALISATEUR e WHERE e.libelle = :libelle", Realisateur.class);
//        query.setParameter("libelle", libelle);
//        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
//    }
//
//    public Role getRole(String libelle) {
//        TypedQuery<Role> query = em.createQuery("SELECT e FROM ROLE e WHERE e.libelle = :libelle", Role.class);
//        query.setParameter("libelle", libelle);
//        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
//    }


}
