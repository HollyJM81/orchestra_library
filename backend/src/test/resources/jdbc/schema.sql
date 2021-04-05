CREATE TABLE IF NOT EXISTS COMPOSER
(
    ID            SERIAL PRIMARY KEY,
    FIRST_NAME    varchar(255),
    MIDDLE_NAME   varchar(255),
    LAST_NAME     varchar(255),
    DATE_OF_BIRTH date
);