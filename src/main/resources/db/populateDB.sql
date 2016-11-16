delete from users;
delete FROM user_roles;

INSERT INTO users (id, name, age, create_date, login, password) VALUES (99997, 'Tom Cat', 25, CURRENT_TIMESTAMP, 'tomcat', 'tomcat');
INSERT INTO users (id, name, age, create_date, login ,password) VALUES (99998, 'Jerry Mouse', 25, CURRENT_TIMESTAMP, 'jerrymouse', 'jerrymouse');
INSERT INTO users (id, name, age, create_date, login, password) VALUES (99999, 'Admin the God', 30, CURRENT_TIMESTAMP, 'admin', 'admin');

INSERT INTO user_roles(user_id, role) VALUES (99997, 'USER');
INSERT INTO user_roles(user_id, role) VALUES (99998, 'USER');
INSERT INTO user_roles(user_id, role) VALUES (99999, 'ADMIN');