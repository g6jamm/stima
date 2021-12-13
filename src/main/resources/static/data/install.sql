CREATE SCHEMA IF NOT EXISTS stima;
USE stima;

DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS project_users;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS colors;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS resource_types;
DROP TABLE IF EXISTS permissions;
DROP TABLE IF EXISTS roles;

CREATE TABLE users
(
    user_id          int(10)        NOT NULL AUTO_INCREMENT,
    first_name       varchar(255)   NOT NULL,
    last_name        varchar(255)   NOT NULL,
    email            varchar(255)   NOT NULL UNIQUE,
    password         varbinary(255) NOT NULL,
    resource_type_id int(10)        NOT NULL,
    permission_id    int(10)        NOT NULL,
    PRIMARY KEY (user_id)
);
CREATE TABLE projects
(
    project_id        int(10)      NOT NULL AUTO_INCREMENT,
    name              varchar(255) NOT NULL,
    start_date        date         NOT NULL,
    end_date          date         NOT NULL,
    parent_project_id int(10),
    colorscode        varchar(7)   NOT NULL,
    PRIMARY KEY (project_id)
);
CREATE TABLE tasks
(
    task_id          int(10)      NOT NULL AUTO_INCREMENT,
    name             varchar(255) NOT NULL,
    hours            double       NOT NULL,
    resource_type_id int(10)      NOT NULL,
    project_id       int(10)      NOT NULL,
    start_date       date         NOT NULL,
    end_date         date         NOT NULL,
    PRIMARY KEY (task_id)
);
CREATE TABLE resource_types
(
    resource_type_id int(10)     NOT NULL AUTO_INCREMENT,
    price_per_hour   int(10)     NOT NULL,
    name             varchar(50) NOT NULL,
    PRIMARY KEY (resource_type_id)
);
CREATE TABLE permissions
(
    permission_id int(10)      NOT NULL AUTO_INCREMENT,
    name          varchar(255) NOT NULL,
    PRIMARY KEY (permission_id)
);
CREATE TABLE colors
(
    code varchar(7)  NOT NULL,
    name varchar(50) NOT NULL UNIQUE,
    PRIMARY KEY (code),
    INDEX (code)
);
CREATE TABLE project_users
(
    project_user_id int(10) NOT NULL AUTO_INCREMENT,
    project_id      int(10) NOT NULL,
    user_id         int(10) NOT NULL,
    role_id         int(10) NOT NULL,
    PRIMARY KEY (project_user_id)
);
CREATE TABLE roles
(
    role_id int(10)      NOT NULL AUTO_INCREMENT,
    name    varchar(255) NOT NULL,
    PRIMARY KEY (role_id)
);

ALTER TABLE users
    ADD CONSTRAINT FKusers426032 FOREIGN KEY (permission_id) REFERENCES permissions (permission_id);
ALTER TABLE project_users
    ADD CONSTRAINT FKproject_us392526 FOREIGN KEY (project_id) REFERENCES projects (project_id);
ALTER TABLE project_users
    ADD CONSTRAINT FKproject_us409367 FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE project_users
    ADD CONSTRAINT FKproject_us744300 FOREIGN KEY (role_id) REFERENCES roles (role_id);
ALTER TABLE tasks
    ADD CONSTRAINT FKtasks862162 FOREIGN KEY (resource_type_id) REFERENCES resource_types (resource_type_id);
ALTER TABLE tasks
    ADD CONSTRAINT FKtasks538315 FOREIGN KEY (project_id) REFERENCES projects (project_id);
ALTER TABLE projects
    ADD CONSTRAINT FKprojects828510 FOREIGN KEY (colorscode) REFERENCES colors (code);
ALTER TABLE users
    ADD CONSTRAINT FKusers693904 FOREIGN KEY (resource_type_id) REFERENCES resource_types (resource_type_id);
