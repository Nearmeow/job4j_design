create table role(
    id serial primary key,
    name varchar(255)
);

create table users(
    id serial primary key,
    name varchar(255),
    address varchar(255),
    role_id int references role(id)
);