package com.didomultiservice.ecollect.ecollect;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('region_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "libelle", length = Integer.MAX_VALUE)
    private String libelle;

    @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
    private String code;

}