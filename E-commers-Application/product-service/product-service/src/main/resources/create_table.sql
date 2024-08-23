drop table category;
drop table product;
alter table product drop foreign key foreign_key_constraint;

create table if not exists category (
        id integer not null auto_increment,
        description varchar(255),
        name varchar(255),
        primary key (id)
    );

create table if not exists product (
        available_quantity float(53) not null,
        category_id integer,
        id integer not null auto_increment,
        price decimal(38,2),
        description varchar(255),
        name varchar(255),
        primary key (id)
    );


alter table product
       add constraint foreign_key_constraint
       FOREIGN KEY (category_id)
       references category (id);
