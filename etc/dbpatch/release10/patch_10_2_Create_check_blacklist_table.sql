INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(10, 'patch_10_2', now(), 'Create_check_blacklist_rule_table');

CREATE TABLE `check_blacklist_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT, 
  `aba` varchar(255) DEFAULT NULL,
  `dda` varchar(255) DEFAULT NULL,
  `maker` varchar(255) DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL, 
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
