package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@NoArgsConstructor
public class EventDto {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "ageLimit")
    private int ageLimit;

    @JsonProperty(value = "occupiedPlace")
    private int occupiedPlace;

    EventDto(int id, String title, String description, int ageLimit, int occupiedPlace) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ageLimit = ageLimit;
        this.occupiedPlace = occupiedPlace;
    }
}
