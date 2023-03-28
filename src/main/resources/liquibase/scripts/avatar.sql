-- liquibase formatted sql

-- changeset V:1
CREATE TABLE avatar (
    idA BIGSERIAL PRIMARY KEY ,
    file_path VARCHAR(255),
    file_size BIGINT,
    media_type VARCHAR(255),
    student_idS BIGINT REFERENCES student(idS),
    data BYTEA
)


