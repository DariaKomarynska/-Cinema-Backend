
--
create table users
(
    user_id NUMBER GENERATED ALWAYS AS IDENTITY,
    firstName VARCHAR2 (40) NOT NULL ,
    lastName VARCHAR2 (40) NOT NULL,
    login VARCHAR2 (40) NOT NULL unique,
    password VARCHAR2 (40) NOT NULL,
    available number default 1
);
ALTER TABLE users ADD (
  CONSTRAINT users_pk PRIMARY KEY (user_id));

create table Cinemas
(
    cinema_ID NUMBER GENERATED ALWAYS AS IDENTITY,
    manager_ID number,
    name varchar2(100) not null,
    website varchar2(60) ,
    phoneNumber varchar2(20) ,
    email varchar2(30) ,
    address varchar2(100),
    available number default 1
);
ALTER TABLE Cinemas ADD (
  CONSTRAINT Cinemas_pk PRIMARY KEY (cinema_ID));

create table rooms
(
    room_id NUMBER GENERATED ALWAYS AS IDENTITY,
    name varchar2(40) not null,
    rowsNumber number(4),
    seatsInRowNumber number(4),
    cinema_ID NUMBER NOT NULL CONSTRAINT cin_room_fk REFERENCES cinemas (cinema_ID),
    available number default 1
);
ALTER TABLE rooms ADD (
  CONSTRAINT rooms_pk PRIMARY KEY (room_id));

create table seats
(
    seat_id NUMBER GENERATED ALWAYS AS IDENTITY,
    room_ID NUMBER NOT NULL CONSTRAINT rm_seat_fk REFERENCES rooms (room_ID),
    positionX number(4) NOT NULL,
    positionY number(4) NOT NULL,
    type VARCHAR2 (40) NOT NULL,
    available number default 1
);
ALTER TABLE seats ADD (
  CONSTRAINT seats_pk PRIMARY KEY (seat_id));

create table MovieCategories
(
    MovieCategory_id NUMBER GENERATED ALWAYS AS IDENTITY,
    name varchar2(100) not null,
    description varchar2(100),
    available number default 1
);
ALTER TABLE MovieCategories ADD (
  CONSTRAINT MovieCategories_pk PRIMARY KEY (MovieCategory_id));

create table movies
(
    movie_ID  NUMBER GENERATED ALWAYS AS IDENTITY,
    length  NUMBER (4) not null,
    ageRestriction varchar2(100),
    cinema_ID number CONSTRAINT cin_mov_fk REFERENCES cinemas (cinema_ID),
    name varchar2(30) not null,
    description varchar2(100),
    movieCate_id number constraint mov_cate_fk REFERENCES MovieCategories (MovieCategory_id),
    available number default 1
);
ALTER TABLE movies ADD (
  CONSTRAINT movies_pk PRIMARY KEY (movie_ID));

create table Schedules
(
    Schedule_id NUMBER GENERATED ALWAYS AS IDENTITY,
    datetime number not null,
    movie_ID NUMBER NOT NULL CONSTRAINT mov_sched_fk REFERENCES movies (movie_ID),
    room_ID NUMBER NOT NULL CONSTRAINT room_sched_fk REFERENCES rooms (room_ID),
    openSale number not null,
    closeSale number not null,
    seatLeft number(4) not null,
    available number default 1
);
ALTER TABLE Schedules ADD (
  CONSTRAINT Schedules_pk PRIMARY KEY (Schedule_id));

create table Purchases
(
    Purchase_id NUMBER GENERATED ALWAYS AS IDENTITY,
    datetime NUMBER not null,
    amount float(2) not null,
    paymentMethod varchar2(40),
    currency VARCHAR2(10),
    schedule_id NUMBER NOT NULL CONSTRAINT sched_purch_fk REFERENCES Schedules (Schedule_id),
    available number default 1
);
ALTER TABLE Purchases ADD (
  CONSTRAINT Purchases_pk PRIMARY KEY (Purchase_id));

create table TicketTypes
(
    TicketType_id NUMBER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR2 (40) NOT NULL,
    price float(2) not null,
    cinema_ID NUMBER NOT NULL CONSTRAINT cin_tick_type_fk REFERENCES cinemas (cinema_ID),
    available number default 1
);
ALTER TABLE TicketTypes ADD (
  CONSTRAINT TicketTypes_pk PRIMARY KEY (TicketType_id));

create table Tickets
(
    Ticket_id NUMBER GENERATED ALWAYS AS IDENTITY,
    Purchase_id NUMBER NOT NULL CONSTRAINT purch_tick_fk REFERENCES Purchases (Purchase_id),
    seat_id NUMBER NOT NULL CONSTRAINT seat_tick_fk REFERENCES seats (seat_id),
    TicketType_id NUMBER NOT NULL CONSTRAINT type_tick_fk REFERENCES TicketTypes (TicketType_id),
    schedule_id NUMBER NOT NULL CONSTRAINT sched_tick_fk REFERENCES Schedules (Schedule_id),
    available number default 1
);
ALTER TABLE Tickets ADD (
  CONSTRAINT Tickets_pk PRIMARY KEY (Ticket_id));


-- Procedure for creating new seats
CREATE OR REPLACE PROCEDURE createSeats (roomId number, rowsNumber number, seatsInRowNumber number)
AS
   curRow number;
   curCol number := 0;
   typeSeat varchar2(40);
BEGIN
    typeSeat := 'comfort';
    for curRow in 1 .. rowsNumber
    loop
        for curCol in 1 .. seatsInRowNumber
        loop
            insert into seats values (default, roomId, curCol, curRow, typeSeat, default);
        end loop;
    end loop;
    typeSeat := 'luxury';
    update seats
    set type = typeSeat where room_id = roomId and positionY > 4 / 5 * rowsNumber;
END;
/


-- Create seats for new room with 1/5 'luxury' type seats
CREATE OR REPLACE TRIGGER tg_add_seats
AFTER INSERT on rooms FOR EACH ROW WHEN (new.rowsNumber is not null and new.seatsInRowNumber is not null)
BEGIN
    createSeats(:new.room_id, :new.rowsNumber, :new.seatsInRowNumber);
END;
/


-- Delete seats when room is deleted
CREATE OR REPLACE TRIGGER tg_delete_seats
AFTER UPDATE OF available on rooms FOR EACH ROW when (old.available != new.available)
BEGIN
    update seats set available = 0 where room_id = :old.room_id;
END;
/


-- Update number of seats if it was changed
CREATE OR REPLACE TRIGGER tg_update_seats
AFTER UPDATE on rooms FOR EACH ROW WHEN ((new.rowsNumber is not null and new.seatsInRowNumber is not null) and 
                                         (new.rowsNumber != old.rowsNumber or new.seatsInRowNumber != old.seatsInRowNumber))          
BEGIN
    delete from seats where room_id = :new.room_id;
    createSeats(:new.room_id, :new.rowsNumber, :new.seatsInRowNumber); 
END;
/


--Function to check if we can add a schedule without conflict. 1: if we can, 0: if we can't
create or replace function can_create_schedule(mv_id number, rm_id number, date_start number, open_sale number, close_sale number)
return number
as
    date_end    number;
    tmp_start   number;
    tmp_end     number;
begin
    select length*60*1000 + date_start into date_end
    from movies
    where mv_id = movie_id;
    for schedule in (select * from schedules ss where rm_id = ss.room_id and ss.available = 1 and (
        select mm.available from movies mm where mm.movie_id = ss.movie_id
    ) = 1)
    loop
        select schedule.datetime into tmp_start from dual;
        tmp_end := tmp_start;
        select schedule.datetime + m.length*60*1000 into tmp_end
        from movies m where m.movie_id = schedule.movie_id and m.available = 1;
        if (date_start between tmp_start and tmp_end) or (date_end between tmp_start and tmp_end) then
            return 0;
        end if;
    end loop;
    return 1;
end;
/
----Function to return status of seats in a schedule
create or replace type seat_record as object (
       seat_id number,
       positionx number,
       positiony number,
       type varchar2(40),
       is_free number
);
/
create or replace type seats_table as table of seat_record;
/
--create or drop type type seat_record
--/
create or replace function get_seats(sch_id number)
return seats_table
as
    rm_id number;
    res seats_table;
begin
    select room_id into rm_id from schedules where available = 1 and schedule_id = sch_id;
    select seat_record(s.seat_id, s.positionx, s.positiony, s.type,
    (
        select 1-count(s.seat_id) from tickets t 
        where t.available = 1 and t.schedule_id = sch_id and s.seat_id = t.seat_id
    ) )
    bulk collect into res from seats s where room_id = rm_id and available = 1;
    return res;
end get_seats;
/

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
--drop table users CASCADE CONSTRAINTS;
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