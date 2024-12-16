create table appUser (
                         id uuid primary key default gen_random_uuid (),
                         firstName varchar(45),
                         lastName varchar(45),
                         email varchar(45) not null unique,
                         phone varchar(45) unique,
                         password varchar(255) not null,
                         role varchar(45),
                         city varchar(45),
                         country varchar(45),
                         imageUrl varchar(255)
);

create table passwordResetCode (
                                   id uuid primary key default gen_random_uuid (),
                                   appUserId uuid,
                                   code char(4) not null,
                                   used boolean not null default false,
                                   foreign key (appUserId) references appUser(id) on delete cascade
);