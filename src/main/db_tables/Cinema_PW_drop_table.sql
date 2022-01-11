drop table cinemas CASCADE CONSTRAINTS;
drop table movies CASCADE CONSTRAINTS;
drop table rooms CASCADE CONSTRAINTS;
drop table users CASCADE CONSTRAINTS;
drop table seats CASCADE CONSTRAINTS;
drop table MovieCategories;
drop table Schedules CASCADE CONSTRAINTS;
drop table Tickets;
drop table Purchases CASCADE CONSTRAINTS;
drop table TicketTypes;

--update seats set available = 0 where seat_id = 3;
--
--insert into purchases values (default, 4564654123, 500, default, default, 3, default);
--
--select * from purchases where available = 1 
--order by purchase_id desc fetch next 1 rows only;
--
--insert into tickets values (default, 21, 3, 5, 3, default);
