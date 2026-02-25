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
@Table(name = "medias_biens")
public class MediasBien {
    @Id
    @ColumnDefault("nextval('medias_biens_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('medias_biens_bien_id_seq')")
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

    @Column(name = "url", nullable = false, length = Integer.MAX_VALUE)
    private String url;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "libelle", length = Integer.MAX_VALUE)
    private String libelle;

}