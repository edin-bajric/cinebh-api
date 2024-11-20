alter table projection drop projectionTime;
alter table projection add startDate DATE;
alter table projection add endDate DATE;

create table projectionTime (
    id UUID primary key default gen_random_uuid (),
    projectionId UUID not null,
    time time not null,
    foreign key (projectionId) references projection(id) on delete cascade
);