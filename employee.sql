create database empdata;

use empdata;

create table employees(
id int primary key auto_increment,
name varchar(10),
sex varchar(1),
age int
);

insert into employees values
('小明','男',18),
('小红','女',19),
('小强','男',20);

/*drop database empdata;