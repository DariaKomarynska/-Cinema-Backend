--
create table users
(
  user_id NUMBER (4) not null CONSTRAINT user_pk PRIMARY KEY,
  firstName VARCHAR2 (40) NOT NULL ,
  lastName VARCHAR2 (40) NOT NULL, 
  login VARCHAR2 (40) NOT NULL,
  password VARCHAR2 (40) NOT NULL
 );

create table Cinemas
(
    cinema_ID number (4) not null constraint cinema_pk  primary key,
    manager_ID number,
    name varchar2(30) not null
);

create table movies
(
    movie_ID  NUMBER (4) not null constraint movie_pk PRIMARY key,
    length  NUMBER (4) not null,
    ageRestriction number,
    cinema_ID number,
    name varchar2(30) not null,
    description varchar2(100)
);

create table rooms
(
    room_id number(4) not null constraint room_pk primary key,
    name varchar2(100) not null,
    rowsNumber number(4),
    seatsInRowNumber number(4),
    cinemaId number(4) not null
);

create table seats
(
  seat_id number(4) not null CONSTRAINT seat_pk PRIMARY KEY,
  room_id number(4) NOT NULL,
  position VARCHAR2 (40) NOT NULL, 
  name VARCHAR2 (40) NOT NULL,
  type VARCHAR2 (40) NOT NULL
 );

create table MovieCategories
(
    MovieCategory_id number(4) not null constraint MovieCategory_pk primary key,
    name varchar2(100) not null,
    description varchar2(100),
    cinemaId number not null
);

create table Schedules
(
    Schedule_id number(4) not null constraint Schedule_pk primary key,
    datetime DATE not null,
    movie_Id number(4) not null,
    room_id number(4) not null,
    openSale varchar2(60) not null,
    closeSale varchar2(60) not null,
    seatLeft number(4) not null
);

create table Purchases
(
    Purchase_id number(4) not null constraint Purchase_pk primary key,
    datetime varchar2(100) not null,
    amount float(2) not null,
    paymentMethod varchar2(40),
    currency VARCHAR2(10),
    Schedule_id number(4) not null
);

create table Tickets
(
    Ticket_id number(4) not null constraint Ticket_pk primary key,
    Purchase_id number(4) not null,
    seat_id number(4) not null,
    TicketType_id number(4) not null
);

create table TicketTypes
(
  TicketType_id NUMBER (4) not null CONSTRAINT TicketType_pk PRIMARY KEY,
  name VARCHAR2 (40) NOT NULL ,
  price float(2) not null,
  cinema_ID number(4)
 );

--
describe users;
describe cinemas;
describe movies;
describe rooms;
describe seats;
describe MovieCategories;
describe Schedules;
describe Tickets;
describe Purchases;
describe TicketTypes;
--
drop table cinemas;
drop table movies;
drop table rooms;
drop table users;
drop table seats;
drop table MovieCategories;
drop table Schedules;
drop table Tickets;
drop table Purchases;
drop table TicketTypes;
-- 
select * from users;
select * from cinemas;
select * from movies;
select * from rooms;
select * from seats;
select * from MovieCategories;
select * from Schedules;
select * from Tickets;
select * from Purchases;
select * from TicketTypes;
--