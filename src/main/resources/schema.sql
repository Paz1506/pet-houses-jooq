drop table if exists house;
drop table if exists house_keeper;

create table if not exists house_keeper (
    id varchar (36) primary key,
    first_name varchar (255) not null,
    last_name varchar (255) not null,
    age integer not null,
    level integer not null default 1,
    status varchar not null default 'ACTIVE'
);

create table if not exists house (
    id varchar (36) primary key,
    name varchar (255) not null,
    capacity integer not null,
    object_size varchar (5) not null default 'XS',
    status varchar not null default 'ACTIVE',
    filled boolean not null default false,
    house_keeper_id varchar (36) not null references house_keeper(id)
);

