INSERT INTO student (id, jmbag, first_name, last_name, date_of_birth, number_of_ects ) VALUES (1, '1234567890', 'Ivo', 'Ivić', '1998-11-10', 120);
INSERT INTO student (id, jmbag, first_name, last_name, date_of_birth, number_of_ects ) VALUES (2, '1234567891', 'Lucija', 'Lucić', '2001-02-04', 98);
INSERT INTO student (id, jmbag, first_name, last_name, date_of_birth, number_of_ects ) VALUES (3, '1234567892', 'Pero', 'Peric', '2000-08-16', 110);
INSERT INTO student (id, jmbag, first_name, last_name, date_of_birth, number_of_ects ) VALUES (4, '1234567893', 'Cuba', 'Cubilic', '1999-01-11', 60);

INSERT INTO course(id, name, number_of_ects) VALUES (1, 'Programiranje u Javi', 7);
INSERT INTO course(id, name, number_of_ects) VALUES (2, 'Osnove Elektrotehnike', 5);

INSERT INTO student_course(course_id, student_id) VALUES (1, 1);
INSERT INTO student_course(course_id, student_id) VALUES (2, 2);

INSERT INTO student_course(course_id, student_id) VALUES (1, 3);
INSERT INTO student_course(course_id, student_id) VALUES (2, 4);

INSERT INTO user(id, username, password, firstname, lastname) VALUES (1, 'user', '$2a$10$5M2AVCSWtA4Vl95kKyqi1uK4gUJjuP/2gEa7vEyM/3vean6/TIRdG', 'Pero', 'Peric');
/* pw: user*/
INSERT INTO user(id, username, password, firstname, lastname) VALUES (2, 'admin', '$2a$10$1Ug9GbozBekSz5R2WR1xMeA4Hujz9UhkUcvNrZnFyE5UUTvmmXSQC', 'Ivana', 'Ivic');
/* pw: admin*/

INSERT INTO authority(id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority(id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_authority(user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority(user_id, authority_id) VALUES (2, 2);
