INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(9, 'patch_9_2', now(), 'Create_balance_inquiry_log_table');

CREATE TABLE `balance_inquiry_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `result_code` int(10) DEFAULT NULL,
  `result_message` varchar(255) DEFAULT NULL,
  `last4cc` varchar(4) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `creditcard` int(10) DEFAULT NULL,   
  `errorCode` varchar(255) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
