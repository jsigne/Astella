package com.astella.api.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String phonenumber;

    @OneToMany(mappedBy = "client")
    private Set<Appointment> appointments = new HashSet<>();

}