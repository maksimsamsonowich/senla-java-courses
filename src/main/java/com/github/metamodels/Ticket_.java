package com.github.metamodels;

import com.github.entity.Event;
import com.github.entity.Ticket;
import com.github.entity.User;
import lombok.NoArgsConstructor;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@NoArgsConstructor
@StaticMetamodel(Ticket.class)
public abstract class Ticket_ {

    public static volatile SingularAttribute<Ticket, User> owner;

    public static volatile SingularAttribute<Ticket, Integer> id;

    public static volatile SingularAttribute<Ticket, Event> event;

    public static volatile SingularAttribute<Ticket, Date> orderDate;

    public static final String OWNER = "owner";

    public static final String ID = "id";

    public static final String EVENT = "event";

    public static final String ORDER_DATE = "orderDate";

}
