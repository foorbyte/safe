package com.didomultiservice.ecollect.ecollect;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EUsersRoleId implements java.io.Serializable {
    private static final long serialVersionUID = 2571768815511438695L;
    @Column(name = "id_role", nullable = false)
    private Long idRole;

    @Column(name = "idusers", nullable = false)
    private Integer idusers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EUsersRoleId entity = (EUsersRoleId) o;
        return Objects.equals(this.idusers, entity.idusers) &&
                Objects.equals(this.idRole, entity.idRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idusers, idRole);
    }

}