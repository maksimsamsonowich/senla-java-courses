package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.entity.Event;
import com.github.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "orderDate")
    private Date orderDate;

    @JsonProperty(value = "event")
    private Event eventHolding;

    @JsonProperty(value = "owner")
    private User owner;

}
