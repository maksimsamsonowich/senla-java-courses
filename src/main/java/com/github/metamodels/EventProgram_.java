package com.github.metamodels;

import com.github.entity.Event;
import com.github.entity.EventProgram;
import lombok.NoArgsConstructor;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@StaticMetamodel(EventProgram.class)
public abstract class EventProgram_ {

    public static volatile SingularAttribute<EventProgram, Date> date;

    public static volatile SingularAttribute<EventProgram, Time> continuance;

    public static volatile SingularAttribute<EventProgram, Double> price;

    public static volatile SingularAttribute<EventProgram, Integer> id;

    public static volatile SingularAttribute<EventProgram, Event> event;

    public static final String DATE = "date";

    public static final String CONTINUANCE = "continuance";

    public static final String PRICE = "price";

    public static final String ID = "id";

    public static final String EVENT = "event";

}