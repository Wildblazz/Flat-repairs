DROP TABLE IF EXISTS estate CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS service_category CASCADE;
DROP TABLE IF EXISTS team_employee CASCADE;
DROP TABLE IF EXISTS team CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS material_category CASCADE;
DROP TABLE IF EXISTS trade_mark CASCADE;
DROP TABLE IF EXISTS materials CASCADE;
DROP TABLE IF EXISTS service CASCADE;
DROP TYPE IF EXISTS price CASCADE;
DROP TYPE IF EXISTS unit_of_measure CASCADE;

CREATE TYPE unit_of_measure AS ENUM ('hour', 'meter');

create type price_level as enum ('cheap', 'medium', 'premium');

CREATE TYPE price AS
(
    number  integer,
    measure unit_of_measure
);

CREATE TABLE IF NOT EXISTS users
(
    user_id      serial PRIMARY KEY,
    username     varchar(64)  NOT NULL UNIQUE,
    passwordHash varchar(512) NOT NULL,
    name         varchar(64),
    surname      varchar(64),
    created_on   TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS estate
(
    estate_id serial PRIMARY KEY UNIQUE,
    area      integer,
    rooms     integer,
    user_id   integer,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS service_category
(
    category_id serial PRIMARY KEY UNIQUE,
    name        integer
);

CREATE TABLE IF NOT EXISTS service
(
    service_id  serial PRIMARY KEY UNIQUE,
    name        varchar(64),
    description varchar(64),
    category    integer,
    price       price,
    FOREIGN KEY (category) REFERENCES service_category (category_id)
);

CREATE TABLE IF NOT EXISTS team
(
    team_id   serial PRIMARY KEY UNIQUE,
    team_name varchar(32)
);

CREATE TABLE IF NOT EXISTS employee
(
    employee_id serial PRIMARY KEY UNIQUE,
    name        varchar(64),
    surname     varchar(64),
    description varchar(64),
    category    integer,
    price       price,
    skill       int,
    FOREIGN KEY (category) REFERENCES service_category (category_id)
);

CREATE TABLE IF NOT EXISTS team_employee
(
    team_id     integer,
    employee_id integer,
    FOREIGN KEY (team_id) REFERENCES team (team_id),
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

CREATE TABLE IF NOT EXISTS material_category
(
    category_id serial PRIMARY KEY UNIQUE,
    name        varchar(64)
);

CREATE TABLE IF NOT EXISTS trade_mark
(
    trade_mark_id serial PRIMARY KEY UNIQUE,
    name          varchar(64)
);

CREATE TABLE IF NOT EXISTS materials
(
    material_id          serial PRIMARY KEY UNIQUE,
    price                price,
    trade_mark_id        integer,
    material_category_id integer,
    price_level          price_level,
    FOREIGN KEY (trade_mark_id) REFERENCES trade_mark (trade_mark_id),
    FOREIGN KEY (material_category_id) REFERENCES material_category (category_id)
);

CREATE TABLE IF NOT EXISTS order
(
    order_id             serial PRIMARY KEY UNIQUE,
    price                price,
    trade_mark_id        integer,
    material_category_id integer,
    FOREIGN KEY (trade_mark_id) REFERENCES trade_mark (trade_mark_id),
    FOREIGN KEY (material_category_id) REFERENCES material_category (category_id)
);