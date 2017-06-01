INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(9, 'patch_9_1', now(), "Add_column_certegy_approval_number_to_transaction");

ALTER TABLE `girocheck`.`transaction` ADD COLUMN `certegy_approval_number` VARCHAR(100);