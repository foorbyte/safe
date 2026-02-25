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
@Table(name = "propretaires")
public class Propretaire {
    @Id
    @ColumnDefault("nextval('propretaires_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
    private String code;

    @Column(name = "nom_prenom", nullable = false, length = Integer.MAX_VALUE)
    private String nomPrenom;

    @Column(name = "adresse", length = Integer.MAX_VALUE)
    private String adresse;

    @Column(name = "telephone", nullable = false, length = Integer.MAX_VALUE)
    private String telephone;

}