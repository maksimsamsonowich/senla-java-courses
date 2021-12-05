package com.github.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artists")
@NamedEntityGraph(
        name = "artists-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("genres"),
                @NamedAttributeNode("cardOwner"),
                @NamedAttributeNode(value = "events", subgraph = "event-sub-graph")
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
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nickname;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "artists_genres", joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @ToString.Exclude
    @OneToMany(mappedBy = "eventOrganizer",
            fetch = FetchType.LAZY)
    private Set<Event> events;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User cardOwner;


}
