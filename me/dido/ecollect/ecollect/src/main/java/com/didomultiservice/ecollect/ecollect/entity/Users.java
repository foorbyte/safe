package com.didomultiservice.ecollect.ecollect.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "eUsers")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsers;

    private String nomPrenoms;


    private String login;

    private String password;

    private Long Etat;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "eUsers_roles",
            joinColumns = @JoinColumn(name = "IdRole"),
            inverseJoinColumns = @JoinColumn(name = "idusers"))
    private Set<Role> roles = new HashSet<>();

    // getters and setters
}
