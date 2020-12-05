drop database if exists cardealership;

create database cardealership;

use cardealership;

drop table if exists ADMIN;

create table ADMIN(

id int auto_increment primary key,
user_name varchar(20),
user_surname varchar(20),
user_password varchar(100)

);

INSERT INTO ADMIN ( user_name, user_surname,user_password) VALUES ( 'Jon', 'Kojakovic',"5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5");
INSERT INTO ADMIN ( user_name, user_surname,user_password) VALUES ( 'Dzejla', 'Suman',"f09b0ea1d0336583a3bcf7418e1a4dac6fe8219235ca716fe2b7ed491c5d1956");
INSERT INTO ADMIN ( user_name, user_surname,user_password) VALUES ( 'Vega', 'Haklicka',"d9e4182d27600c9157e257c724efc3418b46ec7002c6bb72210739c06c23fcbc");

drop table if exists CAR;

create table CAR(

car_id int auto_increment primary key,
brand varchar(20),
model varchar(20),
car_year int,
color varchar(10),
additional varchar(20),
sold boolean

);

INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Ford', 'Fiesta',1976,'cyan', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Ford', 'Ka/Figo',1996,'red', "seat warmer", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Ford', 'Focus',1998,'grey', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Ford', 'Mondeo',1992,'dark blue', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Ford', 'Mustang',1964,'cyan', 'radio', false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Ford', 'GT',2004,'dark blue', "convertable", true);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Ford', 'Ranger Raptor',1981,'red', "trailer", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Ford', 'Focus ST',2005,'orange', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Ford', 'Fiesta ST',2004,'blue', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Ford', 'F-150 Raptor',1948, 'grey', "trailer", false);

INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Fiat', '500',2007,'blue', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Fiat', 'Punto',2003,'red', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Fiat', 'Bravo',2007,'blue', "none", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Fiat', 'Doblo',2001,'green', "none", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Fiat', 'Idea',2002,'grey', "none", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Fiat', 'Bravo',1995,'grey', "radio", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Fiat', 'Marea',1996,'black', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Fiat', 'Uno',1983,'blue', "none", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Fiat', 'Ducato',2014,'white', "none", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Fiat', 'Argeta',1982,'pink', "aux", false);

INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Renault', 'Clio IV',2014, 'white', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Renault', 'Twingo',2015,'white', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Renault', 'Talisman',2016,'black', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Renault', 'Captur',2013,'orange', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Renault', 'Espace',1995,'bourdough', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Renault', 'Megane Grandtour',2020,'beige', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Renault', 'Megane Grandcoupe',2017,'black', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Renault', 'Megane',2020,'beige', "aux", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Renault', 'Trafic',2019,'dark grey', "12 seats", false);
INSERT INTO CAR ( brand, model, car_year, color, additional, sold) VALUES ( 'Renault', 'Koleos',2008,'brown', "aux", false);

drop table if exists Inventory;

create table Inventory(

car_description varchar(20),
car_id int,
FOREIGN KEY (car_id) REFERENCES CAR(car_id) on delete cascade

);

drop table if exists BUYERINFO;

create table BUYERINFO(

buyer_id int auto_increment primary key,
buyer_name varchar(20),
buyer_surname varchar(20),
email varchar(20),
phonenumber int

);

drop table if exists RECEIPT;

create table RECEIPT(

receipt_id int auto_increment primary key,
buyer_id int,
car_id int,
date_of_purchase date,
foreign key (car_id) references CAR(car_id) on delete cascade,
foreign key (buyer_id) references BUYERINFO(buyer_id) on delete cascade

);


