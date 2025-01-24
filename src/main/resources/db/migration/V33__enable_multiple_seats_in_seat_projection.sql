drop table seatProjection;

create table seatProjection (
                                id uuid default gen_random_uuid() primary key,
                                projectionId uuid not null references projection(id)
);

create table seatProjectionSeats (
                                     seatProjectionId uuid not null references seatProjection(id) on delete cascade,
                                     seatId uuid not null references seat(id) on delete cascade,
                                     primary key (seatProjectionId, seatId)
);