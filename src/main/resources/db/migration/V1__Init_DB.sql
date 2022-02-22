create table if not exists employee
(
    id       bigint not null
        primary key,
    login_id varchar(64),
    name     varchar(64),
    skill    varchar(255),
    surname  varchar(64)
);

alter table employee
    owner to "user";

create table if not exists job_category
(
    id   bigint       not null
        primary key,
    name varchar(255) not null
        unique
);

alter table job_category
    owner to "user";

create table if not exists employee_job_categories
(
    employee_id     bigint not null
        constraint employee_job_categories_employee_id_key
            references employee,
    job_category_id bigint not null
        constraint employee_job_categories_job_category_id_key
            references job_category,
    primary key (employee_id, job_category_id)
);

alter table employee_job_categories
    owner to "user";

create table if not exists job
(
    id                   bigint      not null
        primary key,
    description          varchar(512),
    name                 varchar(64) not null
        unique,
    cost                 integer,
    measure_unit         integer,
    quantity_in_one_unit integer,
    total_cost           integer,
    category_id          bigint
        constraint job_category_id_key
            references job_category
);

alter table job
    owner to "user";

create table if not exists material_category
(
    id   bigint      not null
        primary key,
    name varchar(32) not null
        unique
);

alter table material_category
    owner to "user";

create table if not exists materials_formula
(
    id   bigint       not null
        primary key,
    name varchar(255) not null
);

alter table materials_formula
    owner to "user";

create table if not exists job_category_required_materials_formula
(
    job_category_id      bigint not null
        constraint job_category_id_formula_key
            references job_category,
    materials_formula_id bigint not null
        constraint job_category_materials_formula_id_key
            references materials_formula
);

alter table job_category_required_materials_formula
    owner to "user";

create table if not exists materials_formula_proportions
(
    id                           bigint not null
        primary key,
    quantity_in_one_measure_unit double precision,
    ratio                        double precision,
    material_category_id         bigint
        constraint materials_formula_proportions_cat_id_key
            references material_category
);

alter table materials_formula_proportions
    owner to "user";

create table if not exists materials_formula_material_category_proportions
(
    materials_formula_id             bigint not null
        constraint props_materials_frmula_id_key
            references materials_formula,
    material_category_proportions_id bigint not null
        constraint materials_formula_prors_key
            references materials_formula_proportions,
    primary key (materials_formula_id, material_category_proportions_id)
);

alter table materials_formula_material_category_proportions
    owner to "user";

create table if not exists orders
(
    id                     bigint not null
        primary key,
    employee_quality_level varchar(255),
    estate_id              bigint,
    job_id                 bigint,
    materials_price_level  varchar(255),
    repair_time_priority   varchar(255),
    total                  double precision
);

alter table orders
    owner to "user";

create table if not exists team
(
    id   bigint      not null
        primary key,
    name varchar(32) not null
        unique
);

alter table team
    owner to "user";

create table if not exists team_employee
(
    employee_id bigint not null
        constraint team_employee_id_key
            references employee,
    team_id     bigint not null
        constraint team_employee_team_key
            references team,
    primary key (employee_id, team_id)
);

alter table team_employee
    owner to "user";

create table if not exists trade_mark
(
    id   bigint      not null
        primary key,
    name varchar(64) not null
        unique
);

alter table trade_mark
    owner to "user";

create table if not exists material
(
    id                   bigint      not null
        primary key,
    description          varchar(512),
    name                 varchar(64) not null
        unique,
    cost                 integer,
    measure_unit         integer,
    quantity_in_one_unit integer,
    total_cost           integer,
    price_level          varchar(255),
    material_category_id bigint
        constraint material_material_category_id_key
            references material_category,
    trade_mark_id        bigint
        constraint material_trade_mark_id_key
            references trade_mark
);

alter table material
    owner to "user";

create table if not exists users
(
    id            bigint        not null
        primary key,
    date_created  timestamp,
    last_modified timestamp,
    name          varchar(64),
    password_hash varchar(1024) not null,
    surname       varchar(64),
    user_name     varchar(64)   not null
        constraint user_name_key
            unique
);

alter table users
    owner to "user";

create table if not exists estate
(
    id                             bigint           not null
        primary key,
    area                           double precision not null,
    bathrooms                      integer          not null,
    bathrooms_area                 double precision not null,
    date_created                   timestamp,
    floor_area                     double precision not null,
    is_common_bathroom_with_toilet boolean          not null,
    kitchen_area                   double precision not null,
    last_modified                  timestamp,
    rooms                          integer          not null,
    toilets                        integer          not null,
    toilets_area                   double precision not null,
    user_id                        bigint
        constraint estate_user_id_key
            references users
);

alter table estate
    owner to "user";

