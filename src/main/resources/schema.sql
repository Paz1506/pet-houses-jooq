drop table if exists house;

create table if not exists house (
id varchar (36) primary key,
name varchar (255) not null,
capacity integer not null,
object_size varchar (5) not null default 'XS',
filled boolean not null default false
);