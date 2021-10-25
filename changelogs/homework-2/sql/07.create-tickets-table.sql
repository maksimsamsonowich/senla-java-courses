CREATE TABLE tickets (
    user_id integer NOT NULL,
    measure_id integer NOT NULL,
    order_date date NOT NULL,
    foreign key (user_id) references users(id),
    foreign key (measure_id) references measures(id)
);