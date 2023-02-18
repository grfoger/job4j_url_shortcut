CREATE TABLE users (
    id serial primary key,
    url text NOT NULL UNIQUE,
    login text NOT NULL UNIQUE,
    password text NOT NULL
);