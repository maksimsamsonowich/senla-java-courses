package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;


@JsonAutoDetect
@NoArgsConstructor
public class UserDto {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "login")
    private String login;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "email")
    private String email;

    UserDto(int id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
