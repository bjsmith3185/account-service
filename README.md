# account-service


todo.

in front-Service
    add emthod to return all account types by customer id

in account-service
    add method to return all account types by customer id
    add account type to Account dto
    


sql for project testing

DROP DATABASE IF EXISTS practice_account_test;
create schema if not exists practice_account_test;
use practice_account_test;

create table if not exists account (
	account_id int(11) not null auto_increment primary key,
    balance varchar(255) not null
);


create table if not exists customer (
	customer_id int(11) not null auto_increment primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null
);


sql for production 

DROP DATABASE IF EXISTS practice_account;
create schema if not exists practice_account;
use practice_account;

create table if not exists account (
	account_id int(11) not null auto_increment primary key,
    balance varchar(255) not null
);


create table if not exists customer (
	customer_id int(11) not null auto_increment primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null
);

























