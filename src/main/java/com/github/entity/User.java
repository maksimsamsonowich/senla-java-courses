package com.github.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Accessors(chain = true)
@NamedEntityGraph(
        name = "user-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("artistCard"),
                @NamedAttributeNode("tickets")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String toString() {
        return String.format(
                "User [id=%d," +
                        "login=%s," +
                        "password=%s," +
                        "email=%s," +
                        "phoneNumber=%s," +
                        "firstName=%s," +
                        "surname=%s]",
                id,
                login,
                password,
                email,
                phoneNumber,
                firstName,
                surname
        );
    }

}
