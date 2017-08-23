INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(9, 'patch_11_1', now(), "Add_column_token_to_mobileClient");

ALTER TABLE `girocheck`.`mobile_client` ADD COLUMN `token` VARCHAR(100);
ALTER TABLE `girocheck`.`mobile_client` ADD COLUMN `last_login` timestamp; 