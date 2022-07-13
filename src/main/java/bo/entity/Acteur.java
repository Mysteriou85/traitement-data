package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ACTEUR")
public class Acteur extends Individu {

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
