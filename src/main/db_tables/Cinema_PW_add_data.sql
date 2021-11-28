delete from Purchases;
delete from Schedules;
delete from Tickets;

-------------------------
insert into users values (0001, 'Bob', 'WILSON', 'bow11', 'bo56wil'); 
insert into users values (0002, 'Betty', 'BROWN', 'bow12', 'bo526wil'); 
insert into users values (0003, 'Luther', 'WOOD', 'bow13', 'bo536wil'); 
insert into users values (0004, 'Nick', 'THOMSON', 'bow14', 'bo546wil'); 
insert into users values (0005, 'Charles', 'ROBERTSON', 'bow15', 'bo556wil'); 
insert into users values (0006, 'Ara', 'CAMPBELL', 'bow16', 'bo566wil'); 
insert into users values (0007, 'Bob', 'STEWART', 'bow17', 'bo576wil'); 
insert into users values (0008, 'Jeff', 'ANDERSON', 'bow18', 'bo586wil'); 
insert into users values (0009, 'James', 'WILSON', 'bow19', 'bo596wil'); 
insert into users values (0010, 'Sonny', 'BROWN', 'bow21', 'bo16wil'); 
insert into users values (0011, 'Luther', 'THOMSON', 'bow31', 'bo26wil'); 
insert into users values (0012, 'Charles', 'WOOD', 'bow41', 'bo36wil'); 
insert into users values (0013, 'Bob', 'BROWN', 'bow51', 'bo46wil'); 
insert into users values (0014, 'Betty', 'ROBERTSON', 'bow51', 'bo96wil'); 
insert into users values (0015, 'Otis', 'WILSON', 'bow71', 'bo86wil'); 
insert into users values (0016, 'John', 'Wick', 'admin', '21232f297a57a5a743894a0e4a801fc3'); 
commit;
-------------------------

-------------------------
--work
insert into cinemas values (0001, 0001, 'Warszawa Centralna');
insert into cinemas values (0002, 0002, 'Atlantic Kino');
insert into cinemas values (0003, 0003, 'Multikino');
commit;
-------------------------
--work
insert into rooms values (1, 'Room 1', 12, 20, 1);
insert into rooms values (2, 'Room 2', 24, 40, 1);
insert into rooms values (3, 'Room 3', 20, 30, 1);
insert into rooms values (4, 'Room 1', 12, 20, 2);
insert into rooms values (5, 'Room 2', 24, 40, 2);
insert into rooms values (6, 'Room 1', 20, 30, 3);
insert into rooms values (7, 'Room 2', 24, 30, 3);
commit;
-------------------------
--work
insert into seats values (1, 1, 2, 2, 'comfort');
insert into seats values (2, 1, 2, 3, 'comfort');
insert into seats values (3, 1, 2, 4, 'comfort');
insert into seats values (4, 1, 2, 5, 'luxury');

insert into seats values (5, 2, 1, 2, 'comfort');
insert into seats values (6, 2, 1, 3, 'comfort');
insert into seats values (7, 2, 2, 1, 'comfort');
insert into seats values (8, 2, 3, 6, 'comfort');
insert into seats values (9, 2, 3, 7, 'comfort');
insert into seats values (10, 2, 3, 8, 'comfort');
insert into seats values (11, 2, 3, 9, 'luxury');
insert into seats values (12, 2, 4, 1, 'luxury');

insert into seats values (13, 3, 2, 5, 'comfort');
insert into seats values (14, 3, 2, 6, 'comfort');
insert into seats values (15, 3, 2, 7, 'luxury');
insert into seats values (16, 3, 2, 8, 'luxury');
insert into seats values (17, 3, 2, 9, 'luxury');
insert into seats values (18, 3, 2, 10, 'luxury');

insert into seats values (19, 4, 1, 2, 'comfort');
insert into seats values (20, 4, 2, 3, 'comfort');
--insert into seats values (21, 4, 'west', 'seat', 'comfort');
--insert into seats values (22, 4, 'west', 'seat', 'comfort');
--insert into seats values (23, 4, 'east', 'seat', 'comfort');
--insert into seats values (24, 4, 'east', 'seat', 'luxury');
--
--insert into seats values (25, 5, 'north', 'seat', 'comfort');
--insert into seats values (26, 5, 'west', 'seat', 'comfort');
--insert into seats values (27, 5, 'west', 'seat', 'comfort');
--insert into seats values (28, 5, 'west', 'seat', 'luxury');
--insert into seats values (29, 5, 'east', 'seat', 'luxury');
--
--insert into seats values (30, 6, 'north', 'seat', 'comfort');
--insert into seats values (31, 6, 'west', 'seat', 'comfort');
--insert into seats values (32, 6, 'west', 'seat', 'comfort');
--insert into seats values (33, 6, 2, 'seat', 'comfort');
--insert into seats values (34, 6, 'east', 'seat', 'luxury');
--insert into seats values (35, 6, 'east', 'seat', 'luxury');
--
--insert into seats values (36, 7, 'north', 'seat', 'comfort');
--insert into seats values (37, 7, 'west', 'seat', 'comfort');
--insert into seats values (38, 7, 2, 'seat', 'comfort');
--insert into seats values (39, 7, 'east', 'seat', 'luxury');

commit;
-------------------------
--work
insert into MovieCategories values (1, 'Horror', 'funny', 1);
insert into MovieCategories values (2, 'Romance', 'funny', 1);
insert into MovieCategories values (3, 'Thriller', 'funny', 1);
insert into MovieCategories values (4, 'drama', 'funny', 2);
insert into MovieCategories values (5, 'film', 'funny', 2);
insert into MovieCategories values (6, 'Action', 'funny', 3);
insert into MovieCategories values (7, 'romantic', 'funny', 3);
commit;
-------------------------

insert into movies values (0001, 90, 14, 1, '50 shades of grey', 'romantic');
insert into movies values (0002, 120, 18, 1, '51 shades of grey', 'drama');
insert into movies values (0003, 140, 18, 1, '52 shades of grey', 'film');
insert into movies values (0004, 120, 14, 2, '53 shades of grey', 'Horror');
insert into movies values (0005, 135, 16, 1, 'Spider-Man 1', 'Action');
insert into movies values (0006, 140, 18, 2, 'Spider-Man 2', 'Romance');
insert into movies values (0007, 90, 18, 1, 'Spider-Man 3', 'Thriller');
insert into movies values (0008, 90, 16, 2, 'The Matrix 1', 'romantic');
insert into movies values (0009, 140, 6, 1, 'The Matrix 2', 'Horror');
insert into movies values (0010, 120, 14, 3, 'The Matrix 3', 'Romance');
insert into movies values (0011, 135, 18, 3, 'The Matrix 4', 'Thriller');
insert into movies values (0012, 140, 14, 3, 'The Matrix 5', 'drama');
insert into movies values (0013, 135, 6, 2, 'Evil 1', 'film');
insert into movies values (0014, 140, 18, 1, 'Evil 2', 'Action');
insert into movies values (0015, 90, 6, 1, 'Evil 3', 'romantic');
commit;
-------------------------

insert into Schedules values (1, 1638057506,  1, 1, 1628935106, 1629107906, 2);
insert into Schedules values (2, 1637971106,  1, 2, 1621159106, 1621589106, 2);
insert into Schedules values (3, 1635292706,  1, 2, 1621763906, 1621764506, 2);
insert into Schedules values (4, 1635260306,  2, 3, 1619168400, 1619258400, 2);
insert into Schedules values (5, 1635245906,  2, 3, 1618390800, 1618342800, 2);
insert into Schedules values (6, 1635159506,  3, 4, 1618230800, 1618375800, 2);
commit;
-------------------------

insert into Purchases values (1, 1635159506, 80.00, 'Credit card', 'usd', 1);
insert into Purchases values (2, 1632567506, 40.00, 'Cash', 'uah', 1);
insert into Purchases values (3, 1631617106, 80.00, 'Credit card', 'usd', 2);
insert into Purchases values (4, 1631620706, 40.00,'Credit card', 'pln', 2);
insert into Purchases values (5, 1631617106, 40.00, 'Cash', 'uah', 2);
insert into Purchases values (6, 1631613506, 80.00, 'Credit card', 'usd', 3);
commit;
-------------------------

insert into TicketTypes values (1, 'adult', 80.00, 1);
insert into TicketTypes values (2, 'child', 40.00, 1);
insert into TicketTypes values (3, 'adult', 60.00, 2);
insert into TicketTypes values (4, 'child', 30.00, 2);
insert into TicketTypes values (5, 'adult', 70.00, 3);
insert into TicketTypes values (6, 'child', 20.00, 3);
commit;
-------------------------

insert into Tickets values (1, 1, 2, 1, 1);
insert into Tickets values (2, 1, 3, 1, 2);
insert into Tickets values (3, 2, 6, 4, 2);
insert into Tickets values (4, 2, 7, 3, 3);
insert into Tickets values (5, 2, 8, 4, 3);
insert into Tickets values (6, 3, 14, 6, 4);
commit;
-------------------------
