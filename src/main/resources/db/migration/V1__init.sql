create table snapshots
(
    id            bigserial not null,
    content       varchar(65535),
    last_modified timestamp,
    title         varchar(255),
    user_id       bigserial not null,
    primary key (id)
);

create table users
(
    id       bigserial not null,
    email    varchar(255),
    password varchar(255),
    primary key (id)
);

alter table users
    add constraint UC_User unique (email);
alter table snapshots
    add constraint FK_User_Snapshot foreign key (user_id) references users


