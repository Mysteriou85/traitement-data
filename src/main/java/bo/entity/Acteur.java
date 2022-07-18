package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ACTEUR")
public class Acteur {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqAdditif")
    @GenericGenerator(name = "seqAdditif", strategy = "increment")
    private int id;

    @Column(name = "ID_IMDP")
    private String idImdp;

    @Column(name = "IDENTITE")
    private String identite;

    @Column(name = "NAISSANCE_DATE")
    private String naissanceDate;

    @Column(name = "NAISSANCE_LIEU")
    private String naissanceLieu;

    @Column(name = "URL")
    private String url;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="FILM_ACTEUR",
            joinColumns = @JoinColumn(name="ID_ACTEUR", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID")
    )
    private List<Film> films = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="CASTING_PRINCIPAL",
            joinColumns = @JoinColumn(name="ID_ACTEUR", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID")
    )
    private List<Film> filmsCastingPrincipal = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="ROLE_ACTEUR",
            joinColumns = @JoinColumn(name="ID_ACTEUR", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ID_ROLE", referencedColumnName = "ID")
    )
    private List<Role> roles = new ArrayList<>();

    // Constructor
    public Acteur() {
    }

    public Acteur(List<Film> films, List<Film> filmsCastingPrincipal, List<Role> roles) {
        this.films = films;
        this.filmsCastingPrincipal = filmsCastingPrincipal;
        this.roles = roles;
    }

    // Getter & Setter
    public String getIdImdp() {
        return idImdp;
    }

    public void setIdImdp(String idImdp) {
        this.idImdp = idImdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public String getNaissanceDate() {
        return naissanceDate;
    }

    public void setNaissanceDate(String naissanceDate) {
        this.naissanceDate = naissanceDate;
    }

    public String getNaissanceLieu() {
        return naissanceLieu;
    }

    public void setNaissanceLieu(String naissanceLieu) {
        this.naissanceLieu = naissanceLieu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Film> getFilmsCastingPrincipal() {
        return filmsCastingPrincipal;
    }

    public void setFilmsCastingPrincipal(List<Film> filmsCastingPrincipal) {
        this.filmsCastingPrincipal = filmsCastingPrincipal;
    }
}
