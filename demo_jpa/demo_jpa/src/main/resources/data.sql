
create table tbl_product(
    id int identity primary key,
     name varchar (200),
    description varchar(500),
    stock decimal,
    price decimal,
    status varchar (1),
    create_at date,
    category_id int
);

create table tbl_categories(
id int identity primary key,
name varchar (200)

);

alter table tbl_product add constraint fk_cat_pro
    foreign key (category_id)
        references tbl_categories(id);


insert into tbl_categories(name) values('shoes');
//select * from TBL_CATEGORIES;
select * from TBL_PRODUCT;
insert into tbl_product(name, description, stock, price, status, create_at,category_id)
values('addidas','walk',10,100,'A',now(),1);
/*
insert into tbl_categories(id, name) values('books');
insert into tbl_categories(id, name) values('electronics');

insert into tbl_products(id, name, description, stock, price, status, create_at,category_id)values( 1,'addidas','walk',10,100,'A',now(),1);
*/