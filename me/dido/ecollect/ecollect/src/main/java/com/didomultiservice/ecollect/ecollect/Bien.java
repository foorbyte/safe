package com.didomultiservice.ecollect.ecollect;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "biens")
public class Bien {
    @Id
    @ColumnDefault("nextval('biens_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "libelle", nullable = false, length = Integer.MAX_VALUE)
    private String libelle;

    @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("nextval('biens_type_biens_seq')")
    @JoinColumn(name = "type_biens_id", nullable = false)
    private com.didomultiservice.ecollect.ecollect.TypeBien typeBiens;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("nextval('biens_proprietaire_id_seq')")
    @JoinColumn(name = "proprietaire_id", nullable = false)
    private com.didomultiservice.ecollect.ecollect.Propretaire proprietaire;

    @Column(name = "adresse", nullable = false, length = Integer.MAX_VALUE)
    private String adresse;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('biens_ville_id_seq')")
    @JoinColumn(name = "ville_id", nullable = false)
    private com.didomultiservice.ecollect.ecollect.Ville ville;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('biens_localite_id_seq')")
    @JoinColumn(name = "localite_id", nullable = false)
    private com.didomultiservice.ecollect.ecollect.Localite localite;

}