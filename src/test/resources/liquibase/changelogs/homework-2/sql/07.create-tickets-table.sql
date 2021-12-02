CREATE TABLE tickets (
    id SERIAL not null,
    user_id integer DEFAULT NULL,
    event_id integer DEFAULT NULL,
    order_date date NOT NULL,
    foreign key (user_id) references users(id),
    foreign key (event_id) references events(id)
);