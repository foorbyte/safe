package com.didomultiservice.ecollect.ecollect;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "type_biens")
public class TypeBien {
    @Id
    @ColumnDefault("nextval('type_biens_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code_bien", nullable = false, length = Integer.MAX_VALUE)
    private String codeBien;

    @Column(name = "libelle", nullable = false, length = Integer.MAX_VALUE)
    private String libelle;

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ColumnDefault("false")
    @Column(name = "is_locked")
    private Boolean isLocked;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

}