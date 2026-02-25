package com.didomultiservice.ecollect.ecollect;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "type_intervention")
public class TypeIntervention {
    @Id
    @ColumnDefault("nextval('type_intervention_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "libelle", nullable = false, length = Integer.MAX_VALUE)
    private String libelle;

    @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
    private String code;

}