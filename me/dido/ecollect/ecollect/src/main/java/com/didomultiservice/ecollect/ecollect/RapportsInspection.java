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
@Table(name = "rapports_inspection")
public class RapportsInspection {
    @Id
    @ColumnDefault("nextval('rapports_inspection_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date_inspection")
    private OffsetDateTime dateInspection;

    @Column(name = "rapport", length = Integer.MAX_VALUE)
    private String rapport;

    @Column(name = "url_pdf", length = Integer.MAX_VALUE)
    private String urlPdf;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('rapports_inspection_bien_id_seq')")
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

}