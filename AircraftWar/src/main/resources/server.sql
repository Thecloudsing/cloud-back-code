create database aircraft_war;
use aircraft_war;
create table user_authentication (
    id int primary key auto_increment ,
    uid int unsigned unique not null ,
    account char(32) unique not null ,
    password char(32) not null
) character set utf8 engine=innodb;

create table user_interface (
    id int unsigned primary key auto_increment ,
    uuid varchar(255) unique not null ,
    username char(16),
    integral int not null default 0
) character set utf8 engine=innodb;

create table friend_interface (
    id int unsigned primary key auto_increment ,
    uid int unsigned unique not null ,
    fid int unsigned unique not null
) character set utf8 engine=innodb;

drop table user_authentication;
drop table user_interface;
drop table friend_interface;


select friend_interface.id, username, integral from friend_interface, user_interface where user_interface.id = 1 and fid = user_interface.id
