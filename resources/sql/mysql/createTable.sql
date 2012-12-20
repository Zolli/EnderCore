CREATE TABLE IF NOT EXISTS `players` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `playerName` varchar(32) NOT NULL,
  `dragonDefeated` tinyint(1) NOT NULL,
  `specialTags` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 