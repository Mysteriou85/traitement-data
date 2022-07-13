package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "FILM")
public class Film {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqAdditif")
    @GenericGenerator(name = "seqAdditif", strategy = "increment")
    private int id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "PLOT")
    private String plot;

    @Column(name = "LANGUE")
    private String langue;

    @Column(name = "ANNEE_SORTIE")
    private Date anneeSortie;

    @Column(name = "URL")
    private String url;

    // *********************
    // * Jointure de table *
    // *********************
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="FILM_ACTEUR",
        joinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name="ID_ACTEUR", referencedColumnName = "ID")
    )
    private List<Acteur> acteurs;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="CASTING_PRINCIPAL",
            joinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ID_ACTEUR", referencedColumnName = "ID")
    )
    private List<Acteur> castingPrincipal;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="FILM_ROLE",
            joinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ID_ROLE", referencedColumnName = "ID")
    )
    private List<Role> roles;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="REALISATEUR_FILM",
            joinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ID_REALISATEUR", referencedColumnName = "ID")
    )
    private List<Realisateur> realisateurs;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="FILM_GENRE",
            joinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ID_GENRE", referencedColumnName = "ID")
    )
    private List<Genre> genres;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_LIEU_TOURNAGE")
    private LieuTournage lieuTournage;

    // Constructor
    public Film() {
    }

    public Film(String nom,
                String plot,
                String langue,
                Date anneeSortie,
                String url,
                List<Acteur> acteurs,
                List<Acteur> castingPrincipal,
                List<Role> roles,
                List<Realisateur> realisateurs,
                List<Genre> genres,
                LieuTournage lieuTournage) {
        this.nom = nom;
        this.plot = plot;
        this.langue = langue;
        this.anneeSortie = anneeSortie;
        this.url = url;
        this.acteurs = acteurs;
        this.castingPrincipal = castingPrincipal;
        this.roles = roles;
        this.realisateurs = realisateurs;
        this.genres = genres;
        this.lieuTournage = lieuTournage;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public Date getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(Date anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(List<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    public List<Acteur> getCastingPrincipal() {
        return castingPrincipal;
    }

    public void setCastingPrincipal(List<Acteur> castingPrincipal) {
        this.castingPrincipal = castingPrincipal;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Realisateur> getRealisateurs() {
        return realisateurs;
    }

    public void setRealisateurs(List<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public LieuTournage getLieuTournage() {
        return lieuTournage;
    }

    public void setLieuTournage(LieuTournage lieuTournage) {
        this.lieuTournage = lieuTournage;
    }
}
