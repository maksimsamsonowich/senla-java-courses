CREATE TABLE users (
    id serial unique,
    creds_id integer,
    phone_number varchar(15) NOT NULL,
    firstname varchar NOT NULL,
    surname varchar NOT NULL,
    PRIMARY KEY (id),
    foreign key (creds_id) references creds(id)
);