CREATE SCHEMA social_media;

CREATE TABLE IF NOT EXISTS social_media.users (
    id uuid DEFAULT gen_random_uuid(),
    name varchar(256),
    surname varchar(256),
    birth_date timestamp,
    gender varchar(16),
    interests varchar(4000),
    city varchar(128),
    password bytea,
    salt bytea,
    PRIMARY KEY (id)
);