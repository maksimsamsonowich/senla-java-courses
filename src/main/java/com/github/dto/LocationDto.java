package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationDto {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "capacity")
    private int capacity;

}
