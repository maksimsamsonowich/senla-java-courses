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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nickname;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "artists_genres",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @OneToMany(mappedBy = "eventOrganizer", fetch = FetchType.LAZY)
    private Set<Event> events;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User cardOwner;


    public String toString() {
        return String.format(
                "Artist [id=%d, " +
                        "nickname=%s]",
                id,
                nickname
        );
    }

}
