INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(12, 'patch_12_3', now(), "Add_column_to_Client");
 
ALTER TABLE `girocheck`.`client` ADD COLUMN `is_mobile_client`  bit(1) DEFAULT false; 

update client c set is_mobile_client = ((select count(*) from mobile_client mc where mc.client = c.id) > 0)