DROP TABLE IF EXISTS users CASCADE ;
DROP SEQUENCE IF EXISTS global_seq CASCADE ;
DROP TABLE IF EXISTS user_roles;


CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR NOT NULL,
  age INTEGER,
  create_date TIMESTAMP DEFAULT now(),
  login VARCHAR NOT NULL,
  password VARCHAR NOT NULL
);

CREATE TABLE user_roles(
  user_id INTEGER NOT NULL,
  role VARCHAR NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


