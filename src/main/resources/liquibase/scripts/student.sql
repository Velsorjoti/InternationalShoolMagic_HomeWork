-- liquibase formatted sql

-- changeset v:1
CREATE TABLE student (
    idS BIGSERIAL PRIMARY KEY ,
    name VARCHAR(255),
    age INTEGER,
    faculty_id BIGINT REFERENCES faculty(idF)
)
-- changeset v:2
ALTER TABLE student ADD CONSTRAINT age_constraint CHECK (age > 16);