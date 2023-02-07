CREATE TABLE users (
    id serial primary key NOT NULL UNIQUE,
    url text NOT NULL UNIQUE,
    login text NOT NULL UNIQUE,
    password text NOT NULL
);