-- Get values of all tickets have been sold
select sum(amount), currency from tickets t join purchases p using (purchase_id) 
where t.available  = 1 and p.available = 1
group by currency;

-- Get all free seats from schedule have id = 13
select * from get_seats(13) where is_free = 1;

-- Get check if schedule id = 7 in room id = 7 with datetime = 1 January 2022 23:07:06.042 can add or not -> 1 (yes)
select can_create_schedule(7, 7, 1641078426042, 1640078426042, 1641078426042) as can_create from dual;

-- Get check if schedule id = 7 in room id = 7 with datetime = 14 January 2022 16:40:26.042 can add or not -> 0 (no)
select can_create_schedule(7, 7, 1642178426042, 1642078426042, 1642178426042) as can_create from dual;

-- Test trigger auto add seats when we add a room
insert into rooms values (default, 'Vip_test', 5, 6, 1, default);
select * from seats where room_id = (select max(room_id) from rooms);
