CREATE TABLE users (
    id integer unique,
    login varchar(10) NOT NULL unique,
    password varchar(18) NOT NULL,
    email varchar NOT NULL,
    phone_number varchar(15) NOT NULL,
    firstname varchar NOT NULL,
    surname varchar NOT NULL,
    PRIMARY KEY (id)
);