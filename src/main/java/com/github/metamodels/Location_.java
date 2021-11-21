package com.github.metamodels;

import com.github.entity.Event;
import com.github.entity.Location;
import lombok.NoArgsConstructor;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@NoArgsConstructor
@StaticMetamodel(Location.class)
public abstract class Location_ {

    public static volatile SingularAttribute<Location, String> address;

    public static volatile SingularAttribute<Location, Integer> id;

    public static volatile SingularAttribute<Location, String> title;

    public static volatile SetAttribute<Location, Event> events;

    public static volatile SingularAttribute<Location, Integer> capacity;

    public static final String ADDRESS = "address";

    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String EVENTS = "events";

    public static final String CAPACITY = "capacity";

}
