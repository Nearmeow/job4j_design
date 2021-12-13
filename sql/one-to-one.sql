create table address(
    id serial primary key,
    city varchar(255),
    street varchar(255),
    number int
);

create table employee(
    id serial primary key,
    name varchar(255),
    address_id int references address(id) unique
);