package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqAdditif")
    @GenericGenerator(name = "seqAdditif", strategy = "increment")
    private int id;

    @Column(name = "PERSONNAGE")
    private String personnage;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="FILM_ROLE",
            joinColumns = @JoinColumn(name="ID_ROLE", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID")
    )
    private List<Film> films;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="ROLE_ACTEUR",
            joinColumns = @JoinColumn(name="ID_ROLE", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ID_ACTEUR", referencedColumnName = "ID")
    )
    private List<Acteur> acteurs;

    // Constructor
    public Role() {
    }

    public Role(String personnage, List<Film> films, List<Acteur> acteurs) {
        this.personnage = personnage;
        this.films = films;
        this.acteurs = acteurs;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonnage() {
        return personnage;
    }

    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public List<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(List<Acteur> acteurs) {
        this.acteurs = acteurs;
    }
}
