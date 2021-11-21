package com.github.metamodels;

import com.github.entity.Artist;
import com.github.entity.Event;
import com.github.entity.Genre;
import com.github.entity.User;
import lombok.NoArgsConstructor;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@NoArgsConstructor
@StaticMetamodel(Artist.class)
public abstract class Artist_ {

    public static volatile SetAttribute<Artist, Genre> genres;

    public static volatile SingularAttribute<Artist, User> cardOwner;

    public static volatile SingularAttribute<Artist, String> nickname;

    public static volatile SingularAttribute<Artist, Integer> id;

    public static volatile SetAttribute<Artist, Event> events;

    public static final String GENRES = "genres";

    public static final String CARD_OWNER = "cardOwner";

    public static final String NICKNAME = "nickname";

    public static final String ID = "id";

    public static final String EVENTS = "events";

}
