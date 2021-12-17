USE stima;

SET FOREIGN_KEY_CHECKS = 0;
SET AUTO_INCREMENT_INCREMENT = 1;

INSERT INTO permissions (permission_id, name)
VALUES (1, 'admin'),
       (2, 'user');

INSERT INTO colors (code, name)
VALUES ('#dc5b6e', 'Light Carmine Pink'),
       ('#f19748', 'Royal Orange'),
       ('#ead04b', 'Sandstorm'),
       ('#55a973', 'Crayola\'s Forest Green'),
       ('#2d8fb6', 'Cyan Cornflower Blue'),
       ('#6a54b4', 'Royal Purple');

INSERT INTO roles (role_id, name)
VALUES (1, 'Project Manager'),
       (2, 'Employee');

INSERT INTO resource_types (price_per_hour, name)
VALUES (1250, 'Senior developer'),
       (800, 'Junior developer'),
       (1000, 'Project Manager');

INSERT INTO projects (name, start_date, end_date, colorscode, parent_project_id)
VALUES ('Thor', '2021-01-01', '2021-01-02', '#dc5b6e', null),
       ('Micro', '2021-01-01', '2021-01-02', '#f19748', null),
       ('Sharp', '2021-01-01', '2021-01-02', '#ead04b', null),
       ('Dive', '2021-01-01', '2021-01-02', '#55a973', null),
       ('Lust', '2021-12-01', '2021-12-01', '#6a54b4', 1),
       ('Tanner', '2021-12-01', '2021-12-01', '#f19748', 1),
       ('Global', '2021-12-01', '2021-12-01', '#55a973', 1),
       ('Yantra', '2021-12-01', '2021-12-01', '#2d8fb6', 1),
       ('Ace', '2021-12-01', '2021-12-01', '#ead04b', 1);

INSERT INTO users (first_name, last_name, email, password, resource_type_id, permission_id)
VALUES ('John', 'Doe', 'user@demo.com', _binary 'Demo', 1, 2),
       ('Admin', 'Admin', 'admin@demo.com', _binary 'Demo', 1, 1);

INSERT INTO tasks (name, hours, resource_type_id, project_id, start_date, end_date)
VALUES ('Sprite', 200, 1, 1, '2021-01-01', '2021-01-02'),
       ('Wired', 200, 1, 1, '2021-01-01', '2021-01-02'),
       ('Bea', 200, 1, 1, '2021-01-01', '2021-01-02'),
       ('Sunset', 200, 1, 1, '2021-01-01', '2021-01-02');

INSERT INTO project_users (project_id, user_id, role_id)
VALUES (1, 1, 1),
       (2, 1, 1),
       (3, 1, 1),
       (4, 1, 1);
