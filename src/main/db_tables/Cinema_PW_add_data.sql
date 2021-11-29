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

insert into cinemas values (default, 0001, 'Warszawa Centralna', 'https://bobmovies.us/', '7306563244', 'minh4425@mail.com', '01-381, ul. Powstańców Śląskich 70', default);
insert into cinemas values (default, 0002, 'Atlantic Kino', '	https://fmovies.pe', '7563566315', 'nguyen4589@mail.com', '01-381, ul. Modlińska', default);
insert into cinemas values (default, 0003, 'Multikino', 'https://bobcinema.us/', '73065663294','cong456@mail.com', '01-381, ul. Stefana Żeromskiego 29', default);
insert into cinemas values (default, 0004, 'NewCinema', 'https://movieddl.to/', '73064547248', 'cinema4583@mail.com', '01-381, ul. Rakowiecka 25/27', default);
commit;
-------------------------

insert into rooms values (default, 'Room 1', 12, 20, 1, default);
insert into rooms values (default, 'Room 2', 24, 40, 1, default);
insert into rooms values (default, 'Room 3', 20, 30, 1, default);
insert into rooms values (default, 'Room 1', 12, 20, 2, default);
insert into rooms values (default, 'Room 2', 24, 40, 2, default);
insert into rooms values (default, 'Room 1', 20, 30, 3, default);
insert into rooms values (default, 'Room 2', 24, 30, 3, default);
commit;
-------------------------

insert into seats values (default, 1, 2, 2, 'comfort', default);
insert into seats values (default, 1, 2, 3, 'comfort', default);
insert into seats values (default, 1, 2, 4, 'comfort', default);
insert into seats values (default, 1, 2, 5, 'luxury', default);


insert into seats values (default, 2, 1, 2, 'comfort', default);
insert into seats values (default, 2, 1, 3, 'comfort', default);
insert into seats values (default, 2, 2, 1, 'comfort', default);
insert into seats values (default, 2, 3, 6, 'comfort', default);
insert into seats values (default, 2, 3, 7, 'comfort', default);
insert into seats values (default, 2, 3, 8, 'comfort', default);
insert into seats values (default, 2, 3, 9, 'luxury', default);
insert into seats values (default, 2, 4, 1, 'luxury', default);

insert into seats values (default, 3, 2, 5, 'comfort', default);
insert into seats values (default, 3, 2, 6, 'comfort', default);
insert into seats values (default, 3, 2, 7, 'luxury', default);
insert into seats values (default, 3, 2, 8, 'luxury', default);
insert into seats values (default, 3, 2, 9, 'luxury', default);
insert into seats values (default, 3, 2, 10, 'luxury', default);

insert into seats values (default, 4, 1, 2, 'comfort', default);
insert into seats values (default, 4, 2, 3, 'comfort', default);

commit;
-------------------------
--work
insert into MovieCategories values (default, 'Horror', 'funny', default);
insert into MovieCategories values (default, 'Romance', 'funny', default);
insert into MovieCategories values (default, 'Thriller', 'funny', default);
insert into MovieCategories values (default, 'drama', 'funny', default);
insert into MovieCategories values (default, 'film', 'funny', default);
insert into MovieCategories values (default, 'Action', 'funny', default);
insert into MovieCategories values (default, 'romantic', 'funny', default);
commit;
-------------------------

insert into movies values (default, 90, '14+', 1, '50 shades of grey', 'romantic', 1, default);
insert into movies values (default, 120, '18+', 1, '51 shades of grey', 'drama', 3, default);
insert into movies values (default, 140, '18+', 1, '52 shades of grey', 'film', 2, default);
insert into movies values (default, 120, '14+', 2, '53 shades of grey', 'Horror', 4, default);
insert into movies values (default, 135, '16+', 1, 'Spider-Man 1', 'Action', 6, default);
insert into movies values (default, 140, '18+', 2, 'Spider-Man 2', 'Romance', 5, default);
insert into movies values (default, 90, '18+', 1, 'Spider-Man 3', 'Thriller', 7, default);
insert into movies values (default, 90, '16+', 2, 'The Matrix 1', 'romantic', 5, default);
insert into movies values (default, 140, '6+', 1, 'The Matrix 2', 'Horror', 7, default);
insert into movies values (default, 120, '14+', 3, 'The Matrix 3', 'Romance', 3, default);
insert into movies values (default, 135, '18+', 3, 'The Matrix 4', 'Thriller', 3, default);
insert into movies values (default, 140, '14+', 3, 'The Matrix 5', 'drama', 1, default);
insert into movies values (default, 135, '6+', 2, 'Evil 1', 'film', 2, default);
insert into movies values (default, 140, '18+', 1, 'Evil 2', 'Action', 5, default);
insert into movies values (default, 90, '6+', 1, 'Evil 3', 'romantic', 6, default);
commit;
-------------------------

insert into Schedules values (default, 1638057506,  1, 1, 1628935106, 1629107906, 2, default);
insert into Schedules values (default, 1637971106,  1, 2, 1621159106, 1621589106, 2, default);
insert into Schedules values (default, 1635292706,  1, 2, 1621763906, 1621764506, 2, default);
insert into Schedules values (default, 1635260306,  2, 3, 1619168400, 1619258400, 2, default);
insert into Schedules values (default, 1635245906,  2, 3, 1618390800, 1618342800, 2, default);
insert into Schedules values (default, 1635159506,  3, 4, 1618230800, 1618375800, 2, default);
commit;
-------------------------

insert into Purchases values (default, 1635159506, 80.00, 'Credit card', 'usd', 1, default);
insert into Purchases values (default, 1632567506, 40.00, 'Cash', 'uah', 1, default);
insert into Purchases values (default, 1631617106, 80.00, 'Credit card', 'usd', 2, default);
insert into Purchases values (default, 1631620706, 40.00,'Credit card', 'pln', 2, default);
insert into Purchases values (default, 1631617106, 40.00, 'Cash', 'uah', 2, default);
insert into Purchases values (default, 1631613506, 80.00, 'Credit card', 'usd', 3, default);
commit;
-------------------------

insert into TicketTypes values (default, 'adult', 80.00, 1, default);
insert into TicketTypes values (default, 'child', 40.00, 1, default);
insert into TicketTypes values (default, 'adult', 60.00, 2, default);
insert into TicketTypes values (default, 'child', 30.00, 2, default);
insert into TicketTypes values (default, 'adult', 70.00, 3, default);
insert into TicketTypes values (default, 'child', 20.00, 3, default);
commit;
-------------------------

insert into Tickets values (default, 1, 2, 1, 1, default);
insert into Tickets values (default, 1, 3, 1, 2, default);
insert into Tickets values (default, 2, 6, 4, 2, default);
insert into Tickets values (default, 2, 7, 3, 3, default);
insert into Tickets values (default, 2, 8, 4, 3, default);
insert into Tickets values (default, 3, 14, 6, 4, default);
commit;
-------------------------

