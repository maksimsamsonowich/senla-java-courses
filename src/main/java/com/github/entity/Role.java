package com.github.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String role;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles",
            fetch = FetchType.LAZY)
    private Set<User> users;

}
