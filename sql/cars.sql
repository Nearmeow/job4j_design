create table body(
    id serial primary key,
    name varchar(255)
);

create table engine(
    id serial primary key,
    name varchar(255)
);

create table transmission(
    id serial primary key,
    name varchar(255)
);

create table car(
    id serial primary key,
    name varchar(255),
    body_id int references body(id) not null,
	engine_id int references engine(id) not null,
	transmission_id int references transmission(id) not null
);

insert into body(name) values ('body1'), ('body2'), ('body3');
insert into engine(name) values ('engine1'), ('engine2'), ('engine3');
insert into transmission(name) values ('transmission1'), ('transmission2'), ('transmission3');
insert into car(name, body_id, engine_id, transmission_id) values ('car1', 1, 2, 3);
insert into car(name, body_id, engine_id, transmission_id) values ('car2', 3, 1, 3);
insert into car(name, body_id, engine_id, transmission_id) values ('car3', 1, 1, 3);

select car.name as car, b.name as body, e.name as engine, t.name as transmission
from car
join body as b
on car.body_id = b.id
join engine as e
on car.engine_id = e.id
join transmission as t 
on car.transmission_id = t.id;

select b.name as body
from body as b
left join car
on b.id = car.body_id
where car.body_id is null;

select e.name as engine
from engine as e
left join car
on e.id = car.engine_id
where car.engine_id is null;

select t.name as transmission
from transmission as t
left join car
on t.id = car.transmission_id
where car.transmission_id is null;