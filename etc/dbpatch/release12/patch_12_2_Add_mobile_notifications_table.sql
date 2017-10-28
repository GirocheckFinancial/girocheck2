INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(12, 'patch_12_1', now(), "Add_mobile_notifications_table");
 
 
CREATE TABLE `mobile_notification` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `text` varchar(1024) DEFAULT NULL, 
  `mobile_client` int(10) NOT NULL, 
  `creation_date` timestamp NULL DEFAULT NULL,
  `seen_by_user` bit(1) DEFAULT false,   
  PRIMARY KEY (`id`),
  KEY `esadsafs_idx` (`mobile_client`) USING BTREE,
  CONSTRAINT `mobile_notifications_fk` FOREIGN KEY (`mobile_client`) REFERENCES `mobile_client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;