package com.github.metamodels;

import com.github.entity.*;
import lombok.NoArgsConstructor;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Date;

@NoArgsConstructor
@StaticMetamodel(Event.class)
public abstract class Event_ {

    public static volatile SingularAttribute<Event, Date> date;

    public static volatile SingularAttribute<Event, Short> ageLimit;

    public static volatile SetAttribute<Event, Ticket> tickets;

    public static volatile SingularAttribute<Event, Artist> eventOrganizer;

    public static volatile SingularAttribute<Event, EventProgram> eventProgram;

    public static volatile SingularAttribute<Event, String> description;

    public static volatile SingularAttribute<Event, Location> location;

    public static volatile SingularAttribute<Event, Integer> id;

    public static volatile SingularAttribute<Event, String> title;

    public static volatile SingularAttribute<Event, Short> occupiedPlace;

    public static final String DATE = "date";

    public static final String AGE_LIMIT = "ageLimit";

    public static final String TICKETS = "tickets";

    public static final String EVENT_ORGANIZER = "eventOrganizer";

    public static final String EVENT_PROGRAM = "eventProgram";

    public static final String DESCRIPTION = "description";

    public static final String LOCATION = "location";

    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String OCCUPIED_PLACE = "occupiedPlace";

}
