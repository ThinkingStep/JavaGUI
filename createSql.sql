show databases;
create database project;
use project;
show tables;
create table entrylist(
	visitDate timestamp,
    agree boolean not null,
    address varchar(50) not null,
    tel varchar(30) not null,
    sign varchar(10) 
);
desc entryList;
select * from entryList;





