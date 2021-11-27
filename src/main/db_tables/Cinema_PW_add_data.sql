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

insert into cinemas values (0001, 0001, 'Warszawa Centralna');
insert into cinemas values (0002, 0002, 'Atlantic Kino');
insert into cinemas values (0003, 0003, 'Multikino');
commit;
-------------------------

insert into rooms values (1, 'Room 1', 12, 20, 1);
insert into rooms values (2, 'Room 2', 24, 40, 1);
insert into rooms values (3, 'Room 3', 20, 30, 1);
insert into rooms values (4, 'Room 1', 12, 20, 2);
insert into rooms values (5, 'Room 2', 24, 40, 2);
insert into rooms values (6, 'Room 1', 20, 30, 3);
insert into rooms values (7, 'Room 2', 24, 30, 3);
commit;
-------------------------

insert into seats values (1, 1, 'north', 'seat', 'comfort');
insert into seats values (2, 1, 'north', 'seat', 'comfort');
insert into seats values (3, 1, 'south', 'seat', 'comfort');
insert into seats values (4, 1, 'west', 'seat', 'luxury');

insert into seats values (1, 2, 'north', 'seat', 'comfort');
insert into seats values (2, 2, 'north', 'seat', 'comfort');
insert into seats values (3, 2, 'north', 'seat', 'comfort');
insert into seats values (4, 2, 'south', 'seat', 'comfort');
insert into seats values (5, 2, 'south', 'seat', 'comfort');
insert into seats values (6, 2, 'west', 'seat', 'comfort');
insert into seats values (7, 2, 'east', 'seat', 'luxury');
insert into seats values (8, 2, 'east', 'seat', 'luxury');

insert into seats values (1, 3, 'south', 'seat', 'comfort');
insert into seats values (2, 3, 'south', 'seat', 'comfort');
insert into seats values (3, 3, 'south', 'seat', 'luxury');
insert into seats values (4, 3, 'west', 'seat', 'luxury');
insert into seats values (5, 3, 'east', 'seat', 'luxury');
insert into seats values (6, 3, 'east', 'seat', 'luxury');

insert into seats values (1, 4, 'north', 'seat', 'comfort');
insert into seats values (2, 4, 'south', 'seat', 'comfort');
insert into seats values (3, 4, 'west', 'seat', 'comfort');
insert into seats values (4, 4, 'west', 'seat', 'comfort');
insert into seats values (5, 4, 'east', 'seat', 'comfort');
insert into seats values (6, 4, 'east', 'seat', 'luxury');

insert into seats values (1, 5, 'north', 'seat', 'comfort');
insert into seats values (2, 5, 'west', 'seat', 'comfort');
insert into seats values (3, 5, 'west', 'seat', 'comfort');
insert into seats values (4, 5, 'west', 'seat', 'luxury');
insert into seats values (5, 5, 'east', 'seat', 'luxury');

insert into seats values (1, 6, 'north', 'seat', 'comfort');
insert into seats values (2, 6, 'west', 'seat', 'comfort');
insert into seats values (3, 6, 'west', 'seat', 'comfort');
insert into seats values (4, 6, 'south', 'seat', 'comfort');
insert into seats values (5, 6, 'east', 'seat', 'luxury');
insert into seats values (6, 6, 'east', 'seat', 'luxury');

insert into seats values (1, 7, 'north', 'seat', 'comfort');
insert into seats values (2, 7, 'west', 'seat', 'comfort');
insert into seats values (3, 7, 'south', 'seat', 'comfort');
insert into seats values (4, 7, 'east', 'seat', 'luxury');

commit;
-------------------------

insert into MovieCategories values (1, 'Horror', 'funny', 1);
insert into MovieCategories values (2, 'Romance', 'funny', 1);
insert into MovieCategories values (3, 'Thriller', 'funny', 1);
insert into MovieCategories values (4, 'drama', 'funny', 2);
insert into MovieCategories values (5, 'film', 'funny', 2);
insert into MovieCategories values (6, 'Action', 'funny', 3);
insert into MovieCategories values (7, 'romantic', 'funny', 3);
commit;
-------------------------

insert into Schedules values (1, '2021-01-21 20:00', 1, 1, 'now', 'tomorrow', 2);
insert into Schedules values (2, '2021-02-21 20:30', 1, 1, 'now', 'tomorrow', 2);
insert into Schedules values (3, '2021-03-21 21:00', 1, 1, 'now', 'tomorrow', 2);
insert into Schedules values (4, '2021-03-21 21:00', 2, 1, 'now', 'tomorrow', 2);
insert into Schedules values (5, '2021-03-21 21:00', 2, 1, 'now', 'tomorrow', 2);
insert into Schedules values (6, '2021-03-21 20:00', 3, 1, 'now', 'tomorrow', 2);
insert into Schedules values (7, '2021-03-21 20:00', 3, 1, 'now', 'tomorrow', 2);
insert into Schedules values (8, '2021-04-21 20:00', 4, 1, 'now', 'tomorrow', 2);
insert into Schedules values (9, '2021-04-21 20:00', 5, 1, 'now', 'tomorrow', 2);
insert into Schedules values (10, '2021-04-21 21:00', 6, 1, 'now', 'tomorrow', 2);
insert into Schedules values (11, '2021-04-21 20:00', 6, 1, 'now', 'tomorrow', 2);
insert into Schedules values (12, '2021-04-21 21:00', 6, 1, 'now', 'tomorrow', 2);
insert into Schedules values (13, '2021-04-21 21:00', 7, 1, 'now', 'tomorrow', 2);
insert into Schedules values (14, '2021-04-21 21:00', 8, 1, 'now', 'tomorrow', 2);
insert into Schedules values (15, '2021-04-21 21:00', 9, 1, 'now', 'tomorrow', 2);
insert into Schedules values (16, '2021-04-21 20:00', 10, 1, 'now', 'tomorrow', 2);
insert into Schedules values (17, '2021-04-21 20:00', 11, 1, 'now', 'tomorrow', 2);
insert into Schedules values (18, '2021-04-21 21:00', 12, 1, 'now', 'tomorrow', 2);
insert into Schedules values (19, '2021-04-21 20:00', 13, 1, 'now', 'tomorrow', 2);
insert into Schedules values (20, '2021-04-21 20:00', 14, 1, 'now', 'tomorrow', 2);
insert into Schedules values (21, '2021-04-21 21:00', 15, 1, 'now', 'tomorrow', 2);
insert into Schedules values (22, '2021-04-21 20:00', 1, 1, 'now', 'tomorrow', 2);
commit;