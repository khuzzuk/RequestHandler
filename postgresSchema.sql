create sequence M_ID;
create table Messages (ID int not null default nextval('M_ID'), message varchar(255) not null, primary key (ID));