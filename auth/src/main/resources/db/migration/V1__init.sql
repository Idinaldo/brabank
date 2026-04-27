CREATE TABLE identities(
    id uuid primary key,
    email varchar(255) not null unique,
    password_hash char(60) not null,
    status varchar(30) not null default 'PENDING_VERIFICATION',
    role varchar(30) not null default 'CLIENT',
    created_at timestamp with time zone not null default current_timestamp,
    updated_at timestamp with time zone not null default current_timestamp
);