package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventProgramDto {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "continuance")
    private Time continuance;

    @JsonProperty(value = "date")
    private Date date;

    @JsonProperty(value = "price")
    private double price;

}
