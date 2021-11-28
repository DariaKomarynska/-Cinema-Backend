--delete from users;
--delete from cinemas;
--delete from movies;
--delete from rooms;
--delete from seats;
--delete from MovieCategories;
--delete from Schedules;
--delete from Tickets;
--delete from Purchases;
--delete from TicketTypes;

-------------------------
--work
insert into users values (default, 'Bob', 'WILSON', 'bow11', 'bo56wil');
insert into users values (default, 'Betty', 'BROWN', 'bow12', 'bo526wil');
insert into users values (default, 'Luther', 'WOOD', 'bow13', 'bo536wil');
insert into users values (default, 'Nick', 'THOMSON', 'bow14', 'bo546wil');
insert into users values (default, 'Charles', 'ROBERTSON', 'bow15', 'bo556wil');
insert into users values (default, 'Ara', 'CAMPBELL', 'bow16', 'bo566wil');
insert into users values (default, 'Bob', 'STEWART', 'bow17', 'bo576wil');
insert into users values (default, 'Jeff', 'ANDERSON', 'bow18', 'bo586wil');
insert into users values (default, 'James', 'WILSON', 'bow19', 'bo596wil');
insert into users values (default, 'Sonny', 'BROWN', 'bow21', 'bo16wil');
insert into users values (default, 'Luther', 'THOMSON', 'bow31', 'bo26wil');
insert into users values (default, 'Charles', 'WOOD', 'bow41', 'bo36wil');
insert into users values (default, 'Bob', 'BROWN', 'bow51', 'bo46wil');
insert into users values (default, 'Betty', 'ROBERTSON', 'bow4551', 'bo96wil');
insert into users values (default, 'Otis', 'WILSON', 'bow71', 'bo86wil');
insert into users values (default, 'John', 'Wick', 'admin', '21232f297a57a5a743894a0e4a801fc3');
commit;

-------------------------
--work
insert into cinemas values (default, 0001, 'Warszawa Centralna');
insert into cinemas values (default, 0002, 'Atlantic Kino');
insert into cinemas values (default, 0003, 'Multikino');
commit;
-------------------------
--work
insert into rooms values (default, 'Room 1', 12, 20, 1);
insert into rooms values (default, 'Room 2', 24, 40, 1);
insert into rooms values (default, 'Room 3', 20, 30, 1);
insert into rooms values (default, 'Room 1', 12, 20, 2);
insert into rooms values (default, 'Room 2', 24, 40, 2);
insert into rooms values (default, 'Room 1', 20, 30, 3);
insert into rooms values (default, 'Room 2', 24, 30, 3);
commit;
-------------------------
--work
insert into seats values (default, 1, 2, 2, 'comfort');
insert into seats values (default, 1, 2, 3, 'comfort');
insert into seats values (default, 1, 2, 4, 'comfort');
insert into seats values (default, 1, 2, 5, 'luxury');

insert into seats values (default, 2, 1, 2, 'comfort');
insert into seats values (default, 2, 1, 3, 'comfort');
insert into seats values (default, 2, 2, 1, 'comfort');
insert into seats values (default, 2, 3, 6, 'comfort');
insert into seats values (default, 2, 3, 7, 'comfort');
insert into seats values (default, 2, 3, 8, 'comfort');
insert into seats values (default, 2, 3, 9, 'luxury');
insert into seats values (default, 2, 4, 1, 'luxury');

insert into seats values (default, 3, 2, 5, 'comfort');
insert into seats values (default, 3, 2, 6, 'comfort');
insert into seats values (default, 3, 2, 7, 'luxury');
insert into seats values (default, 3, 2, 8, 'luxury');
insert into seats values (default, 3, 2, 9, 'luxury');
insert into seats values (default, 3, 2, 10, 'luxury');

insert into seats values (default, 4, 1, 2, 'comfort');
insert into seats values (default, 4, 2, 3, 'comfort');

commit;
-------------------------
--work
insert into MovieCategories values (default, 'Horror', 'funny', 1);
insert into MovieCategories values (default, 'Romance', 'funny', 1);
insert into MovieCategories values (default, 'Thriller', 'funny', 1);
insert into MovieCategories values (default, 'drama', 'funny', 2);
insert into MovieCategories values (default, 'film', 'funny', 2);
insert into MovieCategories values (default, 'Action', 'funny', 3);
insert into MovieCategories values (default, 'romantic', 'funny', 3);
commit;
-------------------------

insert into movies values (default, 90, 14, 1, '50 shades of grey', 'romantic');
insert into movies values (default, 120, 18, 1, '51 shades of grey', 'drama');
insert into movies values (default, 140, 18, 1, '52 shades of grey', 'film');
insert into movies values (default, 120, 14, 2, '53 shades of grey', 'Horror');
insert into movies values (default, 135, 16, 1, 'Spider-Man 1', 'Action');
insert into movies values (default, 140, 18, 2, 'Spider-Man 2', 'Romance');
insert into movies values (default, 90, 18, 1, 'Spider-Man 3', 'Thriller');
insert into movies values (default, 90, 16, 2, 'The Matrix 1', 'romantic');
insert into movies values (default, 140, 6, 1, 'The Matrix 2', 'Horror');
insert into movies values (default, 120, 14, 3, 'The Matrix 3', 'Romance');
insert into movies values (default, 135, 18, 3, 'The Matrix 4', 'Thriller');
insert into movies values (default, 140, 14, 3, 'The Matrix 5', 'drama');
insert into movies values (default, 135, 6, 2, 'Evil 1', 'film');
insert into movies values (default, 140, 18, 1, 'Evil 2', 'Action');
insert into movies values (default, 90, 6, 1, 'Evil 3', 'romantic');
commit;
-------------------------

insert into Schedules values (default, 1638057506,  1, 1, 1628935106, 1629107906, 2);
insert into Schedules values (default, 1637971106,  1, 2, 1621159106, 1621589106, 2);
insert into Schedules values (default, 1635292706,  1, 2, 1621763906, 1621764506, 2);
insert into Schedules values (default, 1635260306,  2, 3, 1619168400, 1619258400, 2);
insert into Schedules values (default, 1635245906,  2, 3, 1618390800, 1618342800, 2);
insert into Schedules values (default, 1635159506,  3, 4, 1618230800, 1618375800, 2);
commit;
-------------------------

insert into Purchases values (default, 1635159506, 80.00, 'Credit card', 'usd', 1);
insert into Purchases values (default, 1632567506, 40.00, 'Cash', 'uah', 1);
insert into Purchases values (default, 1631617106, 80.00, 'Credit card', 'usd', 2);
insert into Purchases values (default, 1631620706, 40.00,'Credit card', 'pln', 2);
insert into Purchases values (default, 1631617106, 40.00, 'Cash', 'uah', 2);
insert into Purchases values (default, 1631613506, 80.00, 'Credit card', 'usd', 3);
commit;
-------------------------

insert into TicketTypes values (default, 'adult', 80.00, 1);
insert into TicketTypes values (default, 'child', 40.00, 1);
insert into TicketTypes values (default, 'adult', 60.00, 2);
insert into TicketTypes values (default, 'child', 30.00, 2);
insert into TicketTypes values (default, 'adult', 70.00, 3);
insert into TicketTypes values (default, 'child', 20.00, 3);
commit;
-------------------------

insert into Tickets values (default, 1, 2, 1, 1);
insert into Tickets values (default, 1, 3, 1, 2);
insert into Tickets values (default, 2, 6, 4, 2);
insert into Tickets values (default, 2, 7, 3, 3);
insert into Tickets values (default, 2, 8, 4, 3);
insert into Tickets values (default, 3, 14, 6, 4);
commit;
-------------------------

