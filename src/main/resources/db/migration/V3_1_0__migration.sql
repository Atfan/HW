
insert into app_user (user_id, user_name, encryted_password, enabled)
values (1, 'admin', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true),
       (2, 'user', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true),
       (3, 'student', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true),
       (4, 'teacher', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true);


insert into  role (role_id, role_name)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_STUDENT'),
       (4, 'ROLE_TEACHER');

insert into  user_role (id, app_user_user_id, role_role_id)
values (1,1,1),
       (2,1,2),
       (3,2,2),
       (4,3,3),
       (5,4,4);

