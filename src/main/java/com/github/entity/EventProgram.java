package com.github.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Getter
@Setter
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
    private int id;

    private Time continuance;

    private double price;

    @OneToOne(mappedBy = "eventProgram", fetch = FetchType.LAZY)
    private Event event;

    public String toString() {
        return String.format(
                "EventProgram [id=%d " +
                        "continuance=%s, " +
                        "price=%d]",
                id,
                continuance,
                price
        );
    }

}
