package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "REALISATEUR")
public class Realisateur extends Individu {

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="REALISATEUR_FILM",
            joinColumns = @JoinColumn(name="ID_REALISATEUR", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID")
    )
    private List<Film> films = new ArrayList<>();

    // Constructor
    public Realisateur() {
    }

    public Realisateur(List<Film> films) {
        this.films = films;
    }

    // Getter & Setter
    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
