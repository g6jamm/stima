INSERT INTO permissions (permission_id, name) VALUES (1,'ADMIN'),(2,'ADMIN');
INSERT INTO colors (color_id, code) VALUES (1,'pink');
INSERT INTO roles (role_id, name) VALUES (1,'Project Manager'),(2,'Employee');
INSERT INTO resource_type (price_per_hour, name) VALUES (800,'Junior developer'),(2,800,'Junior developer'),(3,800,'Junior developer');

INSERT INTO projects (project_id, name, start_date, end_date, color_id, parent_project_id) VALUES (1,'Project Pink','2021-12-01','2021-12-01',1,1);
INSERT INTO users (user_id, first_name, last_name, email, password, resource_type_id, permission_id) VALUES (2,'John','Doe','demo@demo.com',_binary 'Demo',1,1);
INSERT INTO tasks (task_id, name, hours, resource_type_id, project_id, start_date, end_date) VALUES (1,'Demo task',200,1,1,'2021-12-02','2021-12-03');
