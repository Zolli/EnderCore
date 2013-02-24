CREATE TABLE IF NOT EXISTS players (
    id INT auto_increment,
    playerName VARCHAR(32),
    dragonDefeated TINYINT,
    specialTags TEXT
);

CREATE TABLE IF NOT EXISTS settings (
    id INT auto_increment,
    key VARCHAR(64),
    value VARCHAR(64)
);