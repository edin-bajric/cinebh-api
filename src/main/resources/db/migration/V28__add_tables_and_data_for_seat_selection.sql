alter table projection add hallId uuid;

create table hall (
                      id uuid primary key default gen_random_uuid (),
                      name varchar(45) not null,
                      venueId uuid,
                      foreign key (venueId) references venue(id) on delete cascade
);

insert into hall (name, venueId)
values
    ('Hall 1', (select id from venue where name = 'Cineplex'));

create table seatType (
                          id uuid primary key default gen_random_uuid (),
                          type varchar(45) not null,
                          price int not null
);

insert into seatType (type, price)
values
    ('Regular', 7),
    ('VIP', 10),
    ('Love', 24);

create table seat (
                      id uuid primary key default gen_random_uuid (),
                      name varchar(45) not null,
                      hallId uuid,
                      typeId uuid,
                      foreign key (hallId) references hall(id) on delete cascade,
                      foreign key (typeId) references seatType(id)
);

insert into seat (name, hallId, typeId)
values
    ('A1', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('A2', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('A3', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('A4', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('A5', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('A6', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('A7', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('A8', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),

    ('B1', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('B2', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('B3', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('B4', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('B5', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('B6', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('B7', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('B8', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),

    ('C1', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('C2', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('C3', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('C4', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('C5', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('C6', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('C7', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('C8', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),

    ('D1', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('D2', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('D3', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('D4', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('D5', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('D6', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('D7', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('D8', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),

    ('E1', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('E2', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('E3', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('E4', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('E5', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('E6', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('E7', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('E8', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),

    ('F1', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('F2', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('F3', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('F4', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('F5', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('F6', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('F7', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),
    ('F8', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Regular')),

    ('G1', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('G2', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('G3', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('G4', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('G5', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('G6', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('G7', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('G8', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),

    ('H1', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('H2', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('H3', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('H4', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('H5', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('H6', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('H7', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),
    ('H8', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'VIP')),

    ('I1', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Love')),
    ('I2', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Love')),
    ('I3', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Love')),
    ('I4', (select id from hall where name = 'Hall 1'), (select id from seatType where type = 'Love'));


create table seatProjection (
                                id uuid primary key default gen_random_uuid (),
                                seatId uuid,
                                projectionId uuid,
                                foreign key (seatId) references seat(id) on delete cascade,
                                foreign key (projectionId) references projection(id) on delete cascade
);