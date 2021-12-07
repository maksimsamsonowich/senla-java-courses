package com.github.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
@Accessors(chain = true)
@NamedEntityGraph(
        name = "event-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("tickets"),
                @NamedAttributeNode("eventProgram"),
                @NamedAttributeNode("location"),
                @NamedAttributeNode(value = "eventOrganizer", subgraph = "artist-sub-graph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "artist-sub-graph",
                        attributeNodes = {
                                @NamedAttributeNode("cardOwner"),
                                @NamedAttributeNode("genres")
                        }
                )
        }
)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "age_limit", nullable = false)
    private short ageLimit;

    @Column(name = "occupied_places", nullable = false)
    private short occupiedPlace;

    @Column(name = "date", nullable = false)
    private Date date;

    @OneToMany(mappedBy = "eventHolding", fetch = FetchType.LAZY)
    private Set<Ticket> tickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artists_id")
    private Artist eventOrganizer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "events_program_id")
    private EventProgram eventProgram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locations_id")
    private Location location;

    @Override
    public boolean equals(Object object) {
        Event event = (Event) object;
        return id == event.getId();
    }

    public String toString() {
        return String.format(
                "Event [id=%d, " +
                        "title=%s, " +
                        "description=%s, " +
                        "ageLimit=%d, " +
                        "occupiedPlace=%d, " +
                        "date=%s]",
                id,
                title,
                description,
                ageLimit,
                occupiedPlace,
                date
        );
    }

}
