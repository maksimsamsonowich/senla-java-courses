package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
public class EventProgramDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "continuance")
    private Time continuance;

    @JsonProperty(value = "date")
    private Date date;

    @JsonProperty(value = "price")
    private double price;

}
