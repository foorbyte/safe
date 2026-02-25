package com.didomultiservice.ecollect.ecollect;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "interventions")
public class Intervention {
    @Id
    @ColumnDefault("nextval('intervention_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "libelle", length = Integer.MAX_VALUE)
    private String libelle;

    @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
    private String code;

    @Column(name = "date_declaration")
    private OffsetDateTime dateDeclaration;

    @Column(name = "date_intervention")
    private OffsetDateTime dateIntervention;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('intervention_type_intervention_id_seq')")
    @JoinColumn(name = "type_intervention_id", nullable = false)
    private com.didomultiservice.ecollect.ecollect.TypeIntervention typeIntervention;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "date_cloture")
    private OffsetDateTime dateCloture;

    @Column(name = "est_repetitive")
    private Boolean estRepetitive;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('intervention_statut_id_seq')")
    @JoinColumn(name = "statut_intervention_id", nullable = false)
    private com.didomultiservice.ecollect.ecollect.StatutIntervention statutIntervention;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('interventions_bien_id_seq')")
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

}