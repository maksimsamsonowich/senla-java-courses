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
public class ArtistDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "nickname")
    private String nickname;

    @JsonProperty(value = "genres")
    private Set<GenreDto> genres;

    @JsonProperty(value = "cardOwner")
    private UserDto cardOwner;

}
