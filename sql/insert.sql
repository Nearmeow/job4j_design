insert into role (name) values ('Admin');
insert into users(name, role_id) values ('Oleg', 1);
insert into rules(data) values ('some_data');
insert into role_rules(role_id, rule_id) values (1, 1);
insert into category(name) values ('hr');
insert into state(data) values ('in progress');
insert into item(name, user_id, category_id, state_id) values ('one', 1, 1, 1);
insert into comment(data, item_id) values ('comment', 1);
insert into attachs(data, item_id) values ('attachs', 1);