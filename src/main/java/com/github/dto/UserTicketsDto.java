package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
public class UserTicketsDto {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "login")
    private String login;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "phoneNumber")
    private String phoneNumber;

    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "surname")
    private String surname;

}
