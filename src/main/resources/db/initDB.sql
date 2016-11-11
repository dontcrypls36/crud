DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR(255) NOT NULL,
  age INTEGER,
  is_admin BOOLEAN NOT NULL,
  create_date TIMESTAMP DEFAULT now()
);
