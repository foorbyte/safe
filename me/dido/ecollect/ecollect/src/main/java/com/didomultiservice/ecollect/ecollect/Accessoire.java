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
@Table(name = "accessoires")
public class Accessoire {
    @Id
    @ColumnDefault("nextval('accessoires_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
    private String code;

    @Column(name = "nom_accessoire", nullable = false, length = Integer.MAX_VALUE)
    private String nomAccessoire;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('accessoires_detail_biens_id_seq')")
    @JoinColumn(name = "detail_biens_id", nullable = false)
    private com.didomultiservice.ecollect.ecollect.Appartement detailBiens;

    @Column(name = "nombre_accessoire")
    private Integer nombreAccessoire;

}