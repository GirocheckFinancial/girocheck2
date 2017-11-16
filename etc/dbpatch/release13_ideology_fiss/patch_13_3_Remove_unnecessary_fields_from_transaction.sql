INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(13, 'patch_13_2', now(), 'Create_ideology_result_info_table');

CREATE TABLE `ideology_result_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT, 
  `info_type` int(10),
  `info_key` varchar(255) DEFAULT NULL, 
  `message` varchar(255) DEFAULT NULL, 
  `ideology_result` int(10) NOT NULL, 
  PRIMARY KEY (`id`),
  KEY `info_ideology_result_idx` (`ideology_result`) USING BTREE, 
  CONSTRAINT `ideology_result_fk3` FOREIGN KEY (`ideology_result`) REFERENCES `ideology_result` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;