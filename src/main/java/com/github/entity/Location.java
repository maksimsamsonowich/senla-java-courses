package com.github.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
public class Location {

    @Id
    private int id;

    private String title;

    private String address;

    private int capacity;

    @OneToMany(mappedBy = "location")
    private Set<Event> events;

    @Override
    public boolean equals(Object object) {
        Location location = (Location) object;
        return id == location.getId();
    }
}
