package com.github.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private Set<Event> events;

    public String toString() {
        return String.format(
                "Location [id=%d, " +
                        "title=%s, " +
                        "address=%s, " +
                        "capacity=%d]",
                id,
                title,
                address,
                capacity
        );
    }

}
