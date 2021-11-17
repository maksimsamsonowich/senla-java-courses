package com.github.entity;

import lombok.*;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventProgram {

    private int id;

    private Time continuance;

    private Date date;

    private double price;

}
