create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price)
values ('PC', 20000), ('Phone', 10000), ('Mouse', 500), ('Keyboard', 1000), ('Player', 700);
insert into people(name) values ('Oleg'), ('Vlad'), ('Julia'), ('Tina');
insert into devices_people(device_id, people_id) values (1, 1), (3, 1), (4, 1);
insert into devices_people(device_id, people_id) values (2, 2), (3, 1), (5, 1);
insert into devices_people(device_id, people_id) values (1, 3), (2, 3), (4, 3);
insert into devices_people(device_id, people_id) values (2, 4), (3, 4), (5, 4);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as p_d
join people as p
on p.id = p_d.people_id
join devices as d
on d.id = p_d.device_id
group by p.name;

select p.name, avg(d.price)
from devices_people as p_d
join people as p
on p.id = p_d.people_id
join devices as d
on d.id = p_d.device_id
group by p.name
having avg(d.price) > 5000;