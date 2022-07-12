package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
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
    private Date naissanceDate;

    @Column(name = "NAISSANCE_LIEU")
    private Date naissanceLieu;

    @Column(name = "URL")
    private String url;

}
