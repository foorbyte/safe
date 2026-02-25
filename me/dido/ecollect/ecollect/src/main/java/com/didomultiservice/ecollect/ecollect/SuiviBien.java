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
@Table(name = "suivi_biens")
public class SuiviBien {
    @Id
    @ColumnDefault("nextval('suivi_bien_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('suivi_bien_type_suivi_seq')")
    @JoinColumn(name = "type_suivi_biens_id", nullable = false)
    private com.didomultiservice.ecollect.ecollect.TypeSuiviBien typeSuiviBiens;

    @Column(name = "libelle", nullable = false, length = Integer.MAX_VALUE)
    private String libelle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('suivi_biens_bien_id_seq')")
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

}