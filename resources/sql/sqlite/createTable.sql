CREATE TABLE IF NOT EXISTS players (
    id INTEGER PRIMARY KEY,
    playerName VARCHAR(32),
    dragonDefeated TINYINT,
    specialTags TEXT
);

CREATE TABLE IF NOT EXISTS settings (
    id INTEGER PRIMARY KEY,
    key VARCHAR(64),
    value VARCHAR(64)
);