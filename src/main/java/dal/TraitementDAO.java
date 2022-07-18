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
    public Film getFilm(Film film) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.url = :film", Film.class);
        query.setParameter("film", film.getUrl());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public Genre getGenre(Genre genre) {
        // note le typeGenre correspond à TYPE_GENRE
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.typeGenre = :genre", Genre.class);
        query.setParameter("genre", genre.getTypeGenre());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

//    public Individu getIndividu(String libelle) {
//        TypedQuery<Individu> query = em.createQuery("SELECT e FROM INDIVIDU e WHERE e.libelle = :libelle", Individu.class);
//        query.setParameter("libelle", libelle);
//        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
//    }

    // NOTE : A revoir, cela ne fonctionne pas et cela ne prend pas en compte les films qui n'ont pas de ville
    public LieuTournage getLieuTournage(LieuTournage lieuTournage) {
        TypedQuery<LieuTournage> query = em.createQuery("SELECT l FROM LieuTournage l WHERE l.lieuTournageVille = :lieuTournage", LieuTournage.class);
        query.setParameter("lieuTournage", lieuTournage.getLieuTournageVille());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public Pays getPays(Pays pays) {
        TypedQuery<Pays> query = em.createQuery("SELECT p FROM Pays p WHERE p.nomPays = :pays", Pays.class);
        query.setParameter("pays", pays.getNomPays());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

//    public Realisateur getRealisateur(String libelle) {
//        TypedQuery<Realisateur> query = em.createQuery("SELECT e FROM REALISATEUR e WHERE e.libelle = :libelle", Realisateur.class);
//        query.setParameter("libelle", libelle);
//        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
//    }

    public Role getRole(Role role) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.personnage = :role", Role.class);
        query.setParameter("role", role.getPersonnage());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }


}
