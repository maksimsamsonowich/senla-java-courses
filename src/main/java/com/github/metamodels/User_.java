package com.github.metamodels;

import com.github.entity.Artist;
import com.github.entity.Ticket;
import com.github.entity.User;
import lombok.NoArgsConstructor;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@NoArgsConstructor
@StaticMetamodel(User.class)
public abstract class User_ {

    public static volatile SingularAttribute<User, String> firstName;

    public static volatile SingularAttribute<User, String> password;

    public static volatile SingularAttribute<User, String> phoneNumber;

    public static volatile SetAttribute<User, Ticket> tickets;

    public static volatile SetAttribute<User, Artist> artistCard;

    public static volatile SingularAttribute<User, String> surname;

    public static volatile SingularAttribute<User, Integer> id;

    public static volatile SingularAttribute<User, String> login;

    public static volatile SingularAttribute<User, String> email;

    public static final String FIRST_NAME = "firstName";

    public static final String PASSWORD = "password";

    public static final String PHONE_NUMBER = "phoneNumber";

    public static final String TICKETS = "tickets";

    public static final String ARTIST_CARD = "artistCard";

    public static final String SURNAME = "surname";

    public static final String ID = "id";

    public static final String LOGIN = "login";

    public static final String EMAIL = "email";

}
