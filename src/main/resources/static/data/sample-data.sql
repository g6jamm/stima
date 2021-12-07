USE stima;

INSERT INTO permissions (permission_id, name) VALUES (1,'ADMIN'),(2,'ADMIN');
INSERT INTO colors (color_id, code) VALUES (1,'pink');
INSERT INTO roles (role_id, name) VALUES (1,'Project Manager'),(2,'Employee');
INSERT INTO resource_type (price_per_hour, name) VALUES (1250,'Senior developer'),(800,'Junior developer'),(1000,'Project Manager');

INSERT INTO projects (name, start_date, end_date, color_id, parent_project_id)
VALUES ('Demo1', '2021-01-01', '2021-01-02', 1, null),
       ('Demo2', '2021-01-01', '2021-01-02', 1, null),
       ('Demo3', '2021-01-01', '2021-01-02', 1, null),
       ('Demo4', '2021-01-01', '2021-01-02', 1, null),
       ('Project Pink','2021-12-01','2021-12-01',1,1);

INSERT INTO users (first_name, last_name, email, password, resource_type_id, permission_id) VALUES ('John','Doe','demo@demo.com',_binary 'Demo',1,1);
INSERT INTO tasks (name, hours, resource_type_id, project_id, start_date, end_date) VALUES ('Demo task',200,1,1,'2021-12-02','2021-12-03');
