package com.github.entity;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {

    private int id;

    private String title;
    private String description;

    private short ageLimit;
    private short occupiedPlace;

    private Set<Ticket> tickets;
    private Artist eventOrganizer;

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        Event event = (Event) object;
        return id == event.getId() && title.equals(event.getTitle()) &&
                description.equals(event.getDescription()) && ageLimit == event.getAgeLimit() &&
                occupiedPlace == event.getOccupiedPlace();
    }

}
