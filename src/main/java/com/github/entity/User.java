package com.github.entity;

import lombok.*;


import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private int id;
    private String login;
    private String password;
    private String email;

    private Set<Ticket> tickets;

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return id == user.getId();
    }
}
