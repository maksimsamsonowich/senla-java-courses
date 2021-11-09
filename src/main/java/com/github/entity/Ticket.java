package com.github.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ticket {

    private int id;
    private Date orderDate;

    private Event event;
    private User owner;
}
