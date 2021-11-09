package com.github.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

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

    @Value("param.value")
    private String someField;

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        User user = (User) object;
        return id == user.getId() && login.equals(user.getLogin()) &&
                password.equals(user.getPassword()) && email.equals(user.getEmail());
    }
}
