
insert into app_user (user_id, user_name, encryted_password, enabled)
values (6, 'anonim', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true);


insert into  role (role_id, role_name)
values (6, 'ROLE_ANONIM');

insert into  user_role (id, app_user_user_id, role_role_id)
values (7,6,6);

