CREATE TABLE artists_ganres (
    artists_id integer NOT NULL,
    genres_id integer NOT NULL,
    foreign key (artists_id) references artists(id),
    foreign key (genres_id) references genres(id)
);