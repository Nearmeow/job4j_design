-- 1. Создать таблицы и заполнить их начальными данными
create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('IT'), ('HR'), ('FINANCE');
insert into employees(name, department_id) values ('Oleg', 1);
insert into employees(name, department_id) values ('Tina', 3);
insert into employees(name, department_id) values ('Petr', 1);

-- 2. Выполнить запросы с left, rigth, full, cross соединениями
select * from employees e left join departments d on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;
select * from employees e full join departments d on e.department_id = d.id;
select * from employees e cross join departments d;

-- 3. Используя left join найти департаменты, у которых нет работников
select d.name from departments d
left join employees e
on e.department_id = d.id
where e.department_id is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат
select * from departments d left join employees e on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens(
    id serial primary key,
    name varchar(255),
	gender char
);
insert into teens(name, gender) values ('Tina', 'F'), ('Julia', 'F'), ('Marina', 'F');
insert into teens(name, gender) values ('Oleg', 'M'), ('Tom', 'M');

select distinct t1.name as GIRL, t2.name as BOY
from teens t1
cross join teens t2
where not t1.gender = t2.gender
and t1.gender = 'F';