package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

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

    // id_lieuTournage à faire

}
