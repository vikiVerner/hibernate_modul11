INSERT INTO client (name)
VALUES ('John Doe'),
       ('Mary Smith'),
       ('Peter Johnson'),
       ('Emily Williams'),
       ('Michael Brown'),
       ('Laura Davis'),
       ('Robert Wilson'),
       ('Jessica Martinez'),
       ('Daniel Anderson'),
       ('Sarah Taylor');

INSERT INTO planet (id, name)
VALUES ('MERC1', 'Mercury'),
       ('VEN0', 'Venus'),
       ('E4R7', 'Earth'),
       ('M4RS', 'Mars'),
       ('S47', 'Saturn');

INSERT INTO ticket (client_id, from_planet_id, to_planet_id, created_at)
VALUES (1, 'MERC1', 'VEN0', '2023-08-05 12:34:56'),
       (2, 'VEN0', 'E4R7', '2023-08-06 10:11:12'),
       (3, 'E4R7', 'M4RS', '2023-08-07 15:16:17'),
       (4, 'M4RS', 'S47', '2023-08-08 08:09:10'),
       (5, 'S47', 'MERC1', '2023-08-09 13:14:15'),
       (6, 'M4RS', 'VEN0', '2023-08-10 17:18:19'),
       (7, 'E4R7', 'S47', '2023-08-11 20:21:22'),
       (8, 'VEN0', 'MERC1', '2023-08-12 23:24:25'),
       (9, 'MERC1', 'E4R7', '2023-08-13 02:03:04'),
       (10, 'S47', 'M4RS', '2023-08-14 05:06:07');







