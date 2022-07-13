package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "LIEU_TOURNAGE")
public class LieuTournage {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqAdditif")
    @GenericGenerator(name = "seqAdditif", strategy = "increment")
    private int id;

    @Column(name = "LIEU_TOURNAGE_VILLE")
    private String lieuTournageVille;

    @Column(name = "LIEU_TOURNAGE_ETAT")
    private String lieuTournageEtat;

    @Column(name = "LIEU_TOURNAGE_PAYS")
    private String lieuTournagePays;

    @Column
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Film> films = new HashSet<>();

    // Constructor
    public LieuTournage() {
    }

    public LieuTournage(String lieuTournageVille, String lieuTournageEtat, String lieuTournagePays, Set<Film> films) {
        this.lieuTournageVille = lieuTournageVille;
        this.lieuTournageEtat = lieuTournageEtat;
        this.lieuTournagePays = lieuTournagePays;
        this.films = films;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLieuTournageVille() {
        return lieuTournageVille;
    }

    public void setLieuTournageVille(String lieuTournageVille) {
        this.lieuTournageVille = lieuTournageVille;
    }

    public String getLieuTournageEtat() {
        return lieuTournageEtat;
    }

    public void setLieuTournageEtat(String lieuTournageEtat) {
        this.lieuTournageEtat = lieuTournageEtat;
    }

    public String getLieuTournagePays() {
        return lieuTournagePays;
    }

    public void setLieuTournagePays(String lieuTournagePays) {
        this.lieuTournagePays = lieuTournagePays;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}
