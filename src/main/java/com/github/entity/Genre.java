package com.github.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Genre {

    @Id
    private int id;

    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Artist> artists;
}
