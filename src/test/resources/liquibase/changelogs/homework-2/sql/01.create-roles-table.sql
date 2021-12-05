CREATE TABLE roles (
    id serial unique,
    role_name varchar(20) NOT NULL unique,
    PRIMARY KEY (id)
);