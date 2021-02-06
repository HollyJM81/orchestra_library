CREATE TABLE composers (
    id serial PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    first_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL
);