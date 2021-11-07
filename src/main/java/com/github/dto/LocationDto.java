package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@NoArgsConstructor
public class LocationDto {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "institutionName")
    private String institutionName;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "capacity")
    private int capacity;

    LocationDto(int id, String institutionName, String address, int capacity) {
        this.id = id;
        this.institutionName = institutionName;
        this.address = address;
        this.capacity = capacity;
    }
}
