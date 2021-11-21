package com.github.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EventProgram {

    @Id
    private int id;

    private Time continuance;

    private Date date;

    private double price;

    @OneToOne(mappedBy = "eventProgram", fetch = FetchType.LAZY)
    private Event event;

}
