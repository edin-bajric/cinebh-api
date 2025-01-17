drop table seatProjectionSeats;

drop table seatProjection;

create table ticket (
                        id uuid primary key default gen_random_uuid (),
                        projectionId uuid not null,
                        appUserId uuid not null,
                        createdAt timestamp default current_timestamp,
                        foreign key (projectionId) references projection(id) on delete cascade,
                        foreign key (appUserId) references appUser(id)
);

create table ticket_seat (
                             ticketId uuid not null,
                             seatId uuid not null,
                             primary key (ticketId, seatId),
                             foreign key (ticketId) references ticket(id) on delete cascade,
                             foreign key (seatId) references seat(id) on delete cascade
);