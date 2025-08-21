-- Insert Teams
INSERT INTO Team (name, strength)
VALUES ('Manchester United', 85),
       ('Barcelona', 88),
       ('Real Madrid', 90),
       ('Bayern Munich', 87),
       ('Liverpool', 86),
       ('Juventus', 84),
       ('PSG', 86),
       ('Chelsea', 83);

-- Insert Championships
INSERT INTO Championship (id, description)
VALUES (1, 'Champions League'),
       (2, 'Europa League'),
       (3, 'Super Cup');

-- Matches
-- Champions League Matches
INSERT INTO Match (home_team, away_team, championship_id, date_time)
VALUES ('Barcelona', 'Bayern Munich', 1, '2025-09-15 20:00:00'),
       ('Real Madrid', 'Manchester United', 1, '2025-09-15 21:00:00'),
       ('Liverpool', 'Juventus', 1, '2025-09-16 19:45:00'),
       ('PSG', 'Chelsea', 1, '2025-09-16 21:00:00'),
       ('Bayern Munich', 'Barcelona', 1, '2025-10-20 20:15:00'),
       ('Manchester United', 'Real Madrid', 1, '2025-10-20 21:00:00'),
       ('Juventus', 'Liverpool', 1, '2025-10-21 19:30:00'),
       ('Chelsea', 'PSG', 1, '2025-10-21 21:15:00');

-- Europa League Matches
INSERT INTO Match (home_team, away_team, championship_id, date_time)
VALUES ('Chelsea', 'Bayern Munich', 2, '2025-09-18 18:45:00'),
       ('Juventus', 'Barcelona', 2, '2025-09-18 20:00:00'),
       ('Manchester United', 'PSG', 2, '2025-09-19 19:00:00'),
       ('Liverpool', 'Real Madrid', 2, '2025-09-19 20:45:00'),
       ('Bayern Munich', 'Chelsea', 2, '2025-10-23 18:30:00'),
       ('Barcelona', 'Juventus', 2, '2025-10-23 20:00:00'),
       ('PSG', 'Manchester United', 2, '2025-10-24 19:15:00'),
       ('Real Madrid', 'Liverpool', 2, '2025-10-24 21:00:00');

-- Super Cup Matches
INSERT INTO Match (home_team, away_team, championship_id, date_time)
VALUES ('Real Madrid', 'Barcelona', 3, '2025-08-14 20:30:00'),
       ('Bayern Munich', 'Liverpool', 3, '2025-08-14 21:15:00'),
       ('Manchester United', 'Chelsea', 3, '2025-08-15 19:45:00'),
       ('Juventus', 'PSG', 3, '2025-08-15 21:00:00'),
       ('Barcelona', 'Bayern Munich', 3, '2025-11-10 20:00:00'),
       ('Liverpool', 'Manchester United', 3, '2025-11-10 21:00:00'),
       ('Chelsea', 'Juventus', 3, '2025-11-11 19:30:00'),
       ('PSG', 'Real Madrid', 3, '2025-11-11 21:00:00');
