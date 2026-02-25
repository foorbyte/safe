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
@Table(name = "historique_entretien")
public class HistoriqueEntretien {
    @Id
    @ColumnDefault("nextval('historique_entretien_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('historique_entretien_intervention_id_seq')")
    @JoinColumn(name = "intervention_id", nullable = false)
    private com.didomultiservice.ecollect.ecollect.Intervention intervention;

}