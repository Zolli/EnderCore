CREATE TABLE IF NOT EXISTS players (
    id INTEGER PRIMARY KEY,
    playerName VARCHAR(32),
    dragonDefeated TINYINT,
    specialTags TEXT
);