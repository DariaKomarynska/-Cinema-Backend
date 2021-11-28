--
create table users
(
    user_id NUMBER (4) not null CONSTRAINT user_pk PRIMARY KEY,
    firstName VARCHAR2 (40) NOT NULL ,
    lastName VARCHAR2 (40) NOT NULL,
    login VARCHAR2 (40) NOT NULL unique,
    password VARCHAR2 (40) NOT NULL
);

create table Cinemas
(
    cinema_ID number not null constraint cinema_pk  primary key,
    manager_ID number,
    name varchar2(30) not null
);

create table movies
(
    movie_ID  NUMBER not null constraint movie_pk PRIMARY key,
    length  NUMBER (4) not null,
    ageRestriction number,
    cinema_ID number CONSTRAINT cin_mov_fk REFERENCES cinemas (cinema_ID),
    name varchar2(30) not null,
    description varchar2(100)
);

create table rooms
(
    room_id number not null constraint room_pk primary key,
    name varchar2(40) not null,
    rowsNumber number(4),
    seatsInRowNumber number(4),
    cinema_ID NUMBER NOT NULL CONSTRAINT cin_room_fk REFERENCES cinemas (cinema_ID)
);

create table seats
(
    seat_id number not null CONSTRAINT seat_pk PRIMARY KEY,
    room_ID NUMBER NOT NULL CONSTRAINT rm_seat_fk REFERENCES rooms (room_ID),
    positionX number(4) NOT NULL,
    positionY number(4) NOT NULL,
    type VARCHAR2 (40) NOT NULL
);

create table MovieCategories
(
    MovieCategory_id number not null constraint MovieCategory_pk primary key,
    name varchar2(100) not null,
    description varchar2(100),
    cinema_ID NUMBER NOT NULL CONSTRAINT cin_mov_cat_fk REFERENCES cinemas (cinema_ID)
);

create table Schedules
(
    Schedule_id number not null constraint Schedule_pk primary key,
    datetime number not null,
    movie_ID NUMBER NOT NULL CONSTRAINT mov_sched_fk REFERENCES movies (movie_ID),
    room_ID NUMBER NOT NULL CONSTRAINT room_sched_fk REFERENCES rooms (room_ID),
    openSale number not null,
    closeSale number not null,
    seatLeft number(4) not null
);

create table Purchases
(
    Purchase_id number not null constraint Purchase_pk primary key,
    datetime NUMBER not null,
    amount float(2) not null,
    paymentMethod varchar2(40),
    currency VARCHAR2(10),
    schedule_id NUMBER NOT NULL CONSTRAINT sched_purch_fk REFERENCES Schedules (Schedule_id)
);

create table TicketTypes
(
    TicketType_id NUMBER not null CONSTRAINT TicketType_pk PRIMARY KEY,
    name VARCHAR2 (40) NOT NULL,
    price float(2) not null,
    cinema_ID NUMBER NOT NULL CONSTRAINT cin_tick_type_fk REFERENCES cinemas (cinema_ID)
);

create table Tickets
(
    Ticket_id number not null constraint Ticket_pk primary key,
    Purchase_id NUMBER NOT NULL CONSTRAINT purch_tick_fk REFERENCES Purchases (Purchase_id),
    seat_id NUMBER NOT NULL CONSTRAINT seat_tick_fk REFERENCES seats (seat_id),
    TicketType_id NUMBER NOT NULL CONSTRAINT type_tick_fk REFERENCES TicketTypes (TicketType_id),
    schedule_id NUMBER NOT NULL CONSTRAINT sched_tick_fk REFERENCES Schedules (Schedule_id)
);

--
--describe users;
--describe cinemas;
--describe movies;
--describe rooms;
--describe seats;
--describe MovieCategories;
--describe Schedules;
--describe Tickets;
--describe Purchases;
--describe TicketTypes;
----
--drop table cinemas CASCADE CONSTRAINTS;
--drop table movies CASCADE CONSTRAINTS;
--drop table rooms CASCADE CONSTRAINTS;
--drop table users;
--drop table seats CASCADE CONSTRAINTS;
--drop table MovieCategories;
--drop table Schedules CASCADE CONSTRAINTS;
--drop table Tickets;
--drop table Purchases CASCADE CONSTRAINTS;
--drop table TicketTypes;
--
--select * from users;
--select * from cinemas;
--select * from movies;
--select * from rooms;
--select * from seats;
--select * from MovieCategories;
--select * from Schedules;
--select * from Tickets;
--select * from Purchases;
--select * from TicketTypes;
--