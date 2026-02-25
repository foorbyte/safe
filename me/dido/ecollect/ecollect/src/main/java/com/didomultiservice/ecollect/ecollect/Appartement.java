package com.didomultiservice.ecollect.ecollect;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "appartements")
public class Appartement {
    @Id
    @ColumnDefault("nextval('detail_biens_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
    private String code;

    @Column(name = "libelle", nullable = false, length = Integer.MAX_VALUE)
    private String libelle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('detail_biens_biens_id_seq')")
    @JoinColumn(name = "biens_id", nullable = false)
    private com.didomultiservice.ecollect.ecollect.Bien biens;

    @Column(name = "numero_appartement")
    private Integer numeroAppartement;

    @Column(name = "nombre_chambre")
    private Integer nombreChambre;

    @Column(name = "montant_location")
    private BigDecimal montantLocation;

    @Column(name = "charges")
    private BigDecimal charges;

}