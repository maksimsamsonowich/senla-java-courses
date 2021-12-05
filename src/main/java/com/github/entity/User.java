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
@Table(name = "users")
@NamedEntityGraph(
        name = "user-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("tickets"),
                @NamedAttributeNode("artistCard")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    private String firstName;

    private String surname;

    @ToString.Exclude
    @OneToMany(mappedBy = "cardOwner")
    private Set<Artist> artistCard;

    @ToString.Exclude
    @OneToMany(mappedBy = "owner")
    private Set<Ticket> tickets;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creds_id")
    private Credential credential;

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return id == user.getId();
    }

}
