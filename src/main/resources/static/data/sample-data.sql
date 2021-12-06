USE stima;

INSERT INTO colors (color_id, code)
VALUES (1, '#000');

INSERT INTO projects (name, start_date, end_date, color_id, parent_project_id)
VALUES ('Demo1', '2021-01-01', '2021-01-02', 1, null),
       ('Demo2', '2021-01-01', '2021-01-02', 1, null),
       ('Demo3', '2021-01-01', '2021-01-02', 1, null),
       ('Demo4', '2021-01-01', '2021-01-02', 1, null);
