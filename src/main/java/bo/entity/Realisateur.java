package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "REALISATEUR")
public class Realisateur {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqAdditif")
    @GenericGenerator(name = "seqAdditif", strategy = "increment")
    private int id;

    @Column(name = "IDENTITE")
    private String identite;

    @Column(name = "URL")
    private String url;

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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
