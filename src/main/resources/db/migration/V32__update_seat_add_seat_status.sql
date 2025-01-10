create table seatStatus (
                            id uuid primary key default gen_random_uuid (),
                            status varchar(45) not null
);

insert into seatStatus (status)
values
    ('available'),
    ('reserved'),
    ('selected');

alter table seat add column statusId uuid;

alter table seat
    add constraint fk_seat_status foreign key (statusId) references seatStatus (id);

update seat
set statusId = (select id from seatStatus where status = 'available');
