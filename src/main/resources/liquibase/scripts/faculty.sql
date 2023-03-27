-- liquibase formatted sql

-- changeset V:1
CREATE TABLE faculty (
    idF BIGSERIAL PRIMARY KEY ,
    name VARCHAR(255),
    color VARCHAR(255)
)
-- changeset V:2
ALTER TABLE faculty ADD CONSTRAINT name_unique UNIQUE (name);
