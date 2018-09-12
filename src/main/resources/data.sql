insert into room (id, roomnumber ) values (1, 100), (2, 101), (3, 102), (4, 202), (5, 220), (6, 221);

insert into role (id, role) values (1, 'USER'), (2, 'PRESENTER'), (3, 'ADMIN');

INSERT INTO user (id, username, password, email, role_id) VALUES
(1, 'Lukas Bright', '$2y$12$SPuMXZCafG.b74R8x7zUeeuLsQbrGGxUIX1RE1QJ0H25ROR7gUkbK', 'simple@email.ru', 1),
(2, 'Zachary Armstrong', '$2y$12$SPuMXZCafG.b74R8x7zUeeuLsQbrGGxUIX1RE1QJ0H25ROR7gUkbK', 'simple1@email.ru', 2),
(3, 'Liam Mason', '$2y$12$SPuMXZCafG.b74R8x7zUeeuLsQbrGGxUIX1RE1QJ0H25ROR7gUkbK', 'simple2@email.ru', 3),
(4, 'Tom Wilkinson', '$2y$12$SPuMXZCafG.b74R8x7zUeeuLsQbrGGxUIX1RE1QJ0H25ROR7gUkbK', 'simple3@email.ru', 1),
(5, 'Kian Lawson', '$2y$12$SPuMXZCafG.b74R8x7zUeeuLsQbrGGxUIX1RE1QJ0H25ROR7gUkbK', 'simple4@email.ru', 1);

INSERT INTO presentation (id, topic, perftime, room_id) values
(1, 'Spring boot Ripper2', '2018-02-03 10:35:00', 2),
(2, 'Spring boot Ripper', '2018-02-02 20:35:00', 2),
(3, 'Spring ripper', '2018-02-02 20:35:00', 3);

INSERT INTO user_presentation( user_id, presentation_id) values (1, 1), (2, 1), (2, 2);

