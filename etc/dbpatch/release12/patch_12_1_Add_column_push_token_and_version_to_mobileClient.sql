INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(9, 'patch_12_1', now(), "Add_column_push_token_and_version_to_mobileClient");

ALTER TABLE `girocheck`.`mobile_client` ADD COLUMN `push_token` VARCHAR(255);
ALTER TABLE `girocheck`.`mobile_client` ADD COLUMN `version` int(10) default 2; 
ALTER TABLE `girocheck`.`mobile_client` ADD COLUMN `lang` VARCHAR(20);  
ALTER TABLE `girocheck`.`mobile_client` ADD COLUMN `allow_notifications` bit(1) DEFAULT b'1';

