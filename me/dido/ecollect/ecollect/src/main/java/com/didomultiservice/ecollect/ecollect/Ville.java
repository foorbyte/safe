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
@Table(name = "ville")
public class Ville {
    @Id
    @ColumnDefault("nextval('ville_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
    private String code;

    @Column(name = "libelle", length = Integer.MAX_VALUE)
    private String libelle;

}