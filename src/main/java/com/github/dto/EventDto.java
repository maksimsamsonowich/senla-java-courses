package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.entity.Artist;
import com.github.entity.EventProgram;
import com.github.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @JsonProperty(value = "date")
    private Date date;

    @JsonProperty(value = "eventOrganizer")
    private ArtistDto eventOrganizer;

    @JsonProperty(value = "eventProgram")
    private EventProgramDto eventProgram;

}
