create table dogs(
	id serial primary key,
	name varchar (255),
	breed text,
	age integer
);
insert into dogs(name, breed, age) values ('Tina', 'Dachshund', 9);
update dogs set age = 10;
delete from dogs;