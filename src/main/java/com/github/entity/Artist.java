package com.github.entity;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Artist {

    private int id;
    private String nickname;

    private Set<Genre> genres;
    private Set<Event> events;

}
