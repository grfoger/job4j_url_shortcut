CREATE TABLE users (
    id serial primary key NOT NULL UNIQUE,
    url text NOT NULL UNIQUE,
    login text UNIQUE,
    password text
);