create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
    expired_date date,
    price int
);

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('МОРОЖЕННОЕ');
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 1, date ('2021-12-25'), 150);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 1, date ('2021-12-10'), 150);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 1, date ('2021-12-10'), 150);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 1, date ('2021-12-30'), 150);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 1, date ('2021-12-30'), 150);
insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, date ('2021-12-15'), 100);
insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, date ('2021-12-28'), 100);
insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, date ('2021-12-28'), 100);
insert into product(name, type_id, expired_date, price) values ('Сыр пармезан', 1, date ('2022-01-25'), 350);
insert into product(name, type_id, expired_date, price) values ('Сыр пармезан', 1, date ('2022-01-25'), 350);
insert into product(name, type_id, expired_date, price) values ('Сыр пармезан', 1, date ('2022-01-25'), 350);
insert into product(name, type_id, expired_date, price) values ('Сыр пармезан', 1, date ('2022-01-25'), 350);
insert into product(name, type_id, expired_date, price) values ('Молоко топлёное', 2, date ('2021-12-25'), 80);
insert into product(name, type_id, expired_date, price) values ('Молоко 3.2%', 2, date ('2021-12-12'), 75);
insert into product(name, type_id, expired_date, price) values ('Молоко 3.2%', 2, date ('2021-12-12'), 75);
insert into product(name, type_id, expired_date, price) values ('Молоко 3.2%', 2, date ('2021-12-12'), 75);
insert into product(name, type_id, expired_date, price) values ('Мороженное эскимо', 3, date ('2021-12-28'), 65);
insert into product(name, type_id, expired_date, price) values ('Мороженное эскимо', 3, date ('2021-12-28'), 65);
insert into product(name, type_id, expired_date, price) values ('Мороженное пломбир', 3, date ('2021-12-12'), 50);

select product.name, expired_date, price
from product
join type
on product.type_id = type.id
where type.name = 'СЫР';

select name, expired_date, price
from product
where name like '%Мороженное%';

select name, expired_date, price
from product
where expired_date < current_date;

select name, expired_date, price
from product
where price = (select max(price) from product);

select type.name, count(*)
from type
join product
on type.id = product.type_id group by type.id;

select product.name, expired_date, price
from product
join type
on product.type_id = type.id
where type.name = 'СЫР' or type.name = 'МОЛОКО';

select type.name, count(*)
from type
join product
on type.id = product.type_id
group by type.id
having count(*) < 10;

select type.name, product.name, expired_date, price
from product
join type
on product.type_id = type.id;