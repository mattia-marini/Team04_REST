CREATE TABLE Team
(
    name     VARCHAR(64) NOT NULL,
    strength int         NOT NULL,
    CONSTRAINT pk_team PRIMARY KEY (name)
);

CREATE TABLE Championship
(
    id          INT NOT NULL,
    description VARCHAR(64),
    CONSTRAINT pk_championship PRIMARY KEY (id)
);

CREATE TABLE Match
(
    home_team       VARCHAR(64) NOT NULL,
    away_team       VARCHAR(64) NOT NULL,
    championship_id INT         NOT NULL,
    date_time       DATETIME    NOT NULL,

    CONSTRAINT pk_match PRIMARY KEY (home_team, away_team, championship_id, date_time),

    CONSTRAINT fk_match_home_team FOREIGN KEY (home_team)
        REFERENCES Team (name)
        ON DELETE CASCADE,

    CONSTRAINT fk_match_away_team FOREIGN KEY (away_team)
        REFERENCES Team (name)
        ON DELETE CASCADE,

    CONSTRAINT fk_match_championship FOREIGN KEY (championship_id)
        REFERENCES Championship (id)
        ON DELETE CASCADE,

    CONSTRAINT ck_match_teams CHECK (home_team <> away_team)
);
