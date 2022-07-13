package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "INDIVIDU")
public class Individu {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqAdditif")
    @GenericGenerator(name = "seqAdditif", strategy = "increment")
    private int id;

    @Column(name = "IDENTITE")
    private String identite;

    @Column(name = "NAISSANCE_DATE")
    private String naissanceDate;

    @Column(name = "NAISSANCE_LIEU")
    private String naissanceLieu;

    @Column(name = "URL")
    private String url;

    // Constructor
    public Individu(String identite, String naissanceDate, String naissanceLieu, String url) {
        this.identite = identite;
        this.naissanceDate = naissanceDate;
        this.naissanceLieu = naissanceLieu;
        this.url = url;
    }

    public Individu() {
    }


    // Getter & Setter

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
}
