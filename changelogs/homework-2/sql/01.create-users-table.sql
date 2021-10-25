CREATE TABLE users (
    id integer unique,
    login varchar(10) NOT NULL unique,
    password varchar(18) NOT NULL,
    email varchar(18) NOT NULL,
    phone_number integer NOT NULL,
    firstname varchar(10) NOT NULL,
    surname varchar(10) NOT NULL,
    PRIMARY KEY (id)
);