package com.github.entity;

import lombok.*;


import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    private int id;

    private String login;

    private String password;

    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    private String firstName;

    private String surname;

    @OneToMany(mappedBy = "cardOwner")
    private Set<Artist> artistCard;

    @OneToMany(mappedBy = "owner")
    private Set<Ticket> tickets;

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return id == user.getId();
    }
}
