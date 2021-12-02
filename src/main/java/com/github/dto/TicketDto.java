package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "orderDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @JsonProperty(value = "event")
    private EventDto eventHolding;

    @JsonProperty(value = "owner")
    private UserTicketsDto owner;

}
