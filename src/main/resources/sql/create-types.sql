CREATE TYPE unit_of_measure AS ENUM ('hour', 'meter');

CREATE TYPE price AS
(
    number integer,
    measure unit_of_measure
);