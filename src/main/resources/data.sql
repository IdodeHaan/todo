insert into user_details (id, name)
values (1, 'Jaap');

insert into user_details (id, name)
values (2, 'ido');

insert into todo(title, description, target_date, completed, user_id)
values ('boodschappen', 'kaas en ham', current_date, 'false', 2);

insert into todo(title, description, target_date, completed, user_id)
values ('Bieb', 'boek terugbrengen', current_date, 'false', 2);

insert into todo(title, description, target_date, completed, user_id)
values ('Sleutel', 'kopie laten maken', current_date, 'false', 2);

insert into todo(title, description, target_date, completed, user_id)
values ('fysio', '', current_date, 'false', 2);