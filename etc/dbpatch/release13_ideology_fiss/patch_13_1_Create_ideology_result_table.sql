INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(13, 'patch_13_1', now(), 'Create_ideology_result_table');

CREATE TABLE `ideology_result` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `idnumber` varchar(255) DEFAULT NULL,
  `result_key` varchar(255) DEFAULT NULL, 
  `result_message` varchar(255) DEFAULT NULL,
  `summary_result_key` varchar(255) DEFAULT NULL,
  `summary_result_message` varchar(255) DEFAULT NULL, 
  `tag_failed_key` varchar(255) DEFAULT NULL,
  `tag_failed_message` varchar(255) DEFAULT NULL, 
  `tag_error_message` varchar(255) DEFAULT NULL,
  `tag_restriction_key` varchar(255) DEFAULT NULL,
  `tag_restriction_message` varchar(255) DEFAULT NULL,
  `tag_velocity_key` varchar(255) DEFAULT NULL,
  `tag_velocity_message` varchar(255) DEFAULT NULL,
  `tag_id_live_error_key` varchar(255) DEFAULT NULL,
  `tag_id_live_error_message` varchar(255) DEFAULT NULL,
  `qualifiers_count` int(10) DEFAULT 0,   
  `velocity_results_count` int(10) DEFAULT 0, 
  `client` int(10) NOT NULL,
  `creation_date` timestamp NULL DEFAULT NULL
  `merchant` varchar(255) DEFAULT NULL,
  `disposition` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `zzsadsafs_idx` (`client`) USING BTREE, 
  CONSTRAINT `ideology_result_fk` FOREIGN KEY (`client`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 