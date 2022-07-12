package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "LIEU_TOURNAGE_Pays")
    private String lieuTournagePays;

}
