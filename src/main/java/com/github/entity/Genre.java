package com.github.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    private int id;

    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Artist> artists;

    public String toString() {
        return String.format(
                "Genre [id=%d, " +
                        "name=%s]",
                id,
                name
        );
    }

}
