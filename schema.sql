
-- New Schema

create database myshop default charset='utf8';
create user markz@'%' identified by 'markz123';
grant all on myshop.* to markz@'%';

use myshop;

create table member(
  id       serial,
  email    varchar(1024),
  password varchar(1024),
  name     varchar(1024)
);

insert into member(email, password, name)
values('owner@myshop.xyz', sha2('hello', 512),
	'Shop Owner');



/*
create database icoffee;
create user bill@'%' identified by 'p@ssword';
grant all on icoffee.* to bill@'%';
use icoffee;

create table coffee (
	id      serial,
	name    varchar(255),
	size    char,
	price   double
);

insert into coffee(name,size,price) values('Latte', 'T', 110);
insert into coffee(name,size,price) values('Latte', 'G', 125);
insert into coffee(name,size,price) values('Latte', 'V', 140);
insert into coffee(name,size,price) values('Mocha', 'T', 125);
insert into coffee(name,size,price) values('Mocha', 'G', 140);
insert into coffee(name,size,price) values('Mocha', 'V', 155);
*/
