package com.didomultiservice.ecollect.ecollect.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UserRole {
    @Id
    @ManyToOne
    @JoinColumn(name = "idUsers")
    private Users utilisateur;

    @Id
    @ManyToOne
    @JoinColumn(name = "IdRole")
    private Role role;
}
