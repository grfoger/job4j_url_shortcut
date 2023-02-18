CREATE TABLE links (
  id serial primary key,
  url text NOT NULL UNIQUE,
  code text NOT NULL UNIQUE,
  user_id int NOT NULL references users(id),
  count int DEFAULT 0
);