package com.github.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "role_name")
    private String role;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles",
            fetch = FetchType.LAZY)
    private Set<Credential> credentials;

}
