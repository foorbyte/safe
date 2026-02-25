package com.didomultiservice.ecollect.ecollect.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "eRole")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;

    @Column(nullable = false)
    private String name;

    public Role() {
    }
}