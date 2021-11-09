package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationDto {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "institutionName")
    private String institutionName;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "capacity")
    private int capacity;

}
