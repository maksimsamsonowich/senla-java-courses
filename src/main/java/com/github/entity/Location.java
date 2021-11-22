package com.github.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
@NamedEntityGraph(
        name = "location-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "events", subgraph = "events-sub-graph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "events-sub-graph",
                        attributeNodes = {
                                @NamedAttributeNode("eventProgram"),
                                @NamedAttributeNode("location"),
                                @NamedAttributeNode("eventOrganizer")
                        }
                )
        }
)
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
