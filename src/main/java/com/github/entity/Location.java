package com.github.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private String address;

    private int capacity;

    @ToString.Exclude
    @OneToMany(mappedBy = "location",
            fetch = FetchType.LAZY)
    private Set<Event> events;

}
