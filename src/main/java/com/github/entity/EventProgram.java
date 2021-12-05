package com.github.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(
        name = "events-program-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "event", subgraph = "event-sub-graph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "event-sub-graph",
                        attributeNodes = {
                                @NamedAttributeNode("tickets"),
                                @NamedAttributeNode("eventProgram"),
                                @NamedAttributeNode("location")
                        }
                )
        }
)
@Table(name = "events_program")
public class EventProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Time continuance;

    private double price;

    @ToString.Exclude
    @OneToOne(mappedBy = "eventProgram", fetch = FetchType.LAZY)
    private Event event;

}
