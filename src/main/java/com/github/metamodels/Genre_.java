package com.github.metamodels;

import com.github.entity.Artist;
import com.github.entity.Genre;
import lombok.NoArgsConstructor;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@NoArgsConstructor
@StaticMetamodel(Genre.class)
public abstract class Genre_ {

    public static volatile SetAttribute<Genre, Artist> artists;

    public static volatile SingularAttribute<Genre, String> name;

    public static volatile SingularAttribute<Genre, Integer> id;

    public static final String ARTISTS = "artists";

    public static final String NAME = "name";

    public static final String ID = "id";

}
