create table address(
    id serial primary key,
    city varchar(255)
);

create table users(
    id serial primary key,
    name varchar(255),
    address_id int references address(id)
);

insert into address(city) values ('Moscow');
insert into address(city) values ('Perm');
insert into address(city) values ('Sochi');

insert into users(name, address_id) values ('Oleg', 1);
insert into users(name, address_id) values ('Olga', 2);
insert into users(name, address_id) values ('Peter', 1);
insert into users(name, address_id) values ('Julia', 3);
insert into users(name, address_id) values ('Maria', 2);
insert into users(name, address_id) values ('Vlad', 1);

select * from users
join address
on users.address_id = address.id;

select * from users as u
join address as a
on u.address_id = a.id;

select u.name as Имя, a.city as Город
from users as u
join address as a
on u.address_id = a.id;

select u.name as Имя, a.city as Город
from users as u
join address as a
on u.address_id = a.id
where a.city like '%o%';