
-------------------------
insert into users values (default, 'Betty', 'BROWN', 'bow12', 'bo526wil', default);
insert into users values (default, 'Luther', 'WOOD', 'bow13', 'bo536wil', default);
insert into users values (default, 'Nick', 'THOMSON', 'bow14', 'bo546wil', default);
insert into users values (default, 'Charles', 'ROBERTSON', 'bow15', 'bo556wil', default);
insert into users values (default, 'Ara', 'CAMPBELL', 'bow16', 'bo566wil', default);
insert into users values (default, 'Bob', 'STEWART', 'bow17', 'bo576wil', default);
insert into users values (default, 'Jeff', 'ANDERSON', 'bow18', 'bo586wil', default);
insert into users values (default, 'James', 'WILSON', 'bow19', 'bo596wil', default);
insert into users values (default, 'Sonny', 'BROWN', 'bow21', 'bo16wil', default);
insert into users values (default, 'Luther', 'THOMSON', 'bow31', 'bo26wil', default);
insert into users values (default, 'Charles', 'WOOD', 'bow41', 'bo36wil', default);
insert into users values (default, 'Bob', 'BROWN', 'bow51', 'bo46wil', default);
insert into users values (default, 'Betty', 'ROBERTSON', 'bow4551', 'bo96wil', default);
insert into users values (default, 'Otis', 'WILSON', 'bow71', 'bo86wil', default);
insert into users values (default, 'John', 'Wick', 'admin', '21232f297a57a5a743894a0e4a801fc3', default);
commit;

-------------------------

insert into cinemas values (default, 0001, 'Warszawa Centralna', 'https://bobmovies.us/', '7306563244', 'minh4425@mail.com', 'Józefa Bema 83/73, 01-233 Warszawa', default);
insert into cinemas values (default, 0002, 'Atlantic Kino', '	https://fmovies.pe', '7563566315', 'nguyen4589@mail.com', 'Czerwona Droga 1/6, 87-100 Toruń', default);
insert into cinemas values (default, 0003, 'Multikino', 'https://bobcinema.us/', '73065663294','cong456@mail.com', 'aleja Ignacego Daszyńskiego 33, 31-035 Kraków', default);
insert into cinemas values (default, 0004, 'NewCinema', 'https://movieddl.to/', '73064547248', 'cinema4583@mail.com', 'Zamkowa 1, 20-117 Lublin', default);
commit;
-------------------------

insert into rooms values (default, 'Room 1', 5, 6, 1, default);
insert into rooms values (default, 'Room 2', 4, 6, 1, default);
insert into rooms values (default, 'Room 1', 4, 6, 2, default);
insert into rooms values (default, 'Room 2', 6, 5, 2, default);
insert into rooms values (default, 'Room 1', 3, 4, 3, default);
insert into rooms values (default, 'Room 2', 7, 5, 3, default);
commit;
-------------------------

--insert into seats values (default, 1, 2, 4, 'comfort', default);
--insert into seats values (default, 1, 2, 5, 'luxury', default);

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

insert into Schedules values (default, 1638057506000,  1, 1, 1628935106000, 1629107906000, 2, default);
insert into Schedules values (default, 1637971106000,  1, 2, 1621159106000, 1621589106000, 2, default);
insert into Schedules values (default, 1635292706000,  1, 2, 1621763906000, 1621764506000, 2, default);
insert into Schedules values (default, 1635260306000,  2, 3, 1619168400000, 1619258400000, 2, default);
insert into Schedules values (default, 1635245906000,  2, 3, 1618390800000, 1618342800000, 2, default);
insert into Schedules values (default, 1635159506000,  3, 4, 1618230800000, 1618375800000, 2, default);
insert into Schedules values (default, 1641646800000,  1, 1, 1641027600000, 1641646800000, 50, default);
insert into Schedules values (default, 1641650400000,  3, 2, 1641027600000, 1641650400000, 50, default);
insert into Schedules values (default, 1641654000000,  5, 3, 1641027600000, 1641654000000, 50, default);
insert into Schedules values (default, 1641657600000,  7, 4, 1641027600000, 1641657600000, 50, default);
insert into Schedules values (default, 1641661200000,  9, 5, 1641027600000, 1641661200000, 50, default);
insert into Schedules values (default, 1641664800000,  11, 6, 1641027600000, 1641664800000, 50, default);
commit;
-------------------------

insert into Purchases values (default, 1635159506000, 80.00, 'Credit card', 'usd', 1, default);
insert into Purchases values (default, 1632567506000, 40.00, 'Cash', 'uah', 1, default);
insert into Purchases values (default, 1631617106000, 80.00, 'Credit card', 'usd', 2, default);
insert into Purchases values (default, 1631620706000, 40.00,'Credit card', 'pln', 2, default);
insert into Purchases values (default, 1631617106000, 40.00, 'Cash', 'uah', 2, default);
insert into Purchases values (default, 1631613506000, 80.00, 'Credit card', 'usd', 3, default);
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
insert into Tickets values (default, 3, 4, 6, 4, default);
commit;
-------------------------

