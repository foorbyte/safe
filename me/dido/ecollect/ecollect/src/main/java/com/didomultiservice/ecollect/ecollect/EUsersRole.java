package com.didomultiservice.ecollect.ecollect;

import com.didomultiservice.ecollect.ecollect.entity.Role;
import com.didomultiservice.ecollect.ecollect.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "e_users_roles")
public class EUsersRole {
    @EmbeddedId
    private EUsersRoleId id;

    @MapsId("idRole")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    private Users idRole;

    @MapsId("idusers")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idusers", nullable = false)
    private Role idusers;

}