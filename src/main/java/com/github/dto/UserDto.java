package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDto {

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

    @JsonProperty(value = "artistCard")
    private Set<EventArtistDto> artistCard;

    @JsonProperty(value = "tickets")
    private Set<UserTicketsDto> tickets;

}
