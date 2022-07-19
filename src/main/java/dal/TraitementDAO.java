package dal;

import bo.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class TraitementDAO {

    private final EntityManager em;

    public TraitementDAO(EntityManager em) {
        this.em = em;
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
    public Film getFilm(Film film) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.nom = :film AND f.url = :url", Film.class);
        query.setParameter("film", film.getNom());
        query.setParameter("url", film.getUrl());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public Genre getGenre(Genre genre) {
        // note le typeGenre correspond à TYPE_GENRE
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.typeGenre = :genre", Genre.class);
        query.setParameter("genre", genre.getTypeGenre());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
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

    public Pays getPays(Pays pays) {
        TypedQuery<Pays> query = em.createQuery("SELECT p FROM Pays p WHERE p.nomPays = :pays", Pays.class);
        query.setParameter("pays", pays.getNomPays());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public Realisateur getRealisateur(Realisateur realisateur) {
        TypedQuery<Realisateur> query = em.createQuery("SELECT r FROM Realisateur r WHERE r.identite = :realisateur", Realisateur.class);
        query.setParameter("realisateur", realisateur.getIdentite());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public Acteur getActeur(Acteur acteur) {
        TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.identite = :acteur", Acteur.class);
        query.setParameter("acteur", acteur.getIdentite());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public Role getRole(Role role) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.personnage = :role", Role.class);
        query.setParameter("role", role.getPersonnage());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }


}
