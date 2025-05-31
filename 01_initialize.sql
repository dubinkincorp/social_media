CREATE SCHEMA social_media;

CREATE TABLE IF NOT EXISTS social_media.users (
    id bigint generated always as identity primary key,
    login varchar(128),
    name varchar(256),
    surname varchar(256),
    birth_date timestamp,
    gender varchar(16),
    interests varchar(4000),
    city varchar(128)
);