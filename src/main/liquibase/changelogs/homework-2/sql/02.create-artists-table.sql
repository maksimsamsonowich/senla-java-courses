CREATE TABLE artists (
    users_id integer NOT NULL,
    nickname varchar(40) NOT NULL,
    id integer NOT NULL,
    PRIMARY KEY (id),
    foreign key (users_id) references users(id)
);