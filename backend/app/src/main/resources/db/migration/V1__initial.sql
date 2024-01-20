create table users
(
    id         int GENERATED ALWAYS AS IDENTITY primary key,
    first_name varchar,
    last_name  varchar,
    email      varchar,
    is_admin   boolean
);

create table workshop
(
    id          int GENERATED ALWAYS AS IDENTITY primary key,
    title       varchar                  not null,
    description varchar,
    start_time  timestamp with time zone not null,
    end_time    timestamp with time zone not null,
    capacity    int                      not null
);

create table workshop_registration
(
    id          int GENERATED ALWAYS AS IDENTITY primary key,
    user_id     int,
    workshop_id int,
    state       int not null default 0,
    created_at timestamp with time zone not null default current_timestamp,
    updated_at timestamp with time zone not null default current_timestamp,
    constraint fk_workshop foreign key (workshop_id) references workshop (id),
    constraint fk_user foreign key (user_id) references users (id)
);