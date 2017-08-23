INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(7, 'patch_11_2', now(), 'patch_11_2_Add_merchant_location_fields');
  

ALTER TABLE `girocheck`.`merchant` ADD COLUMN `longitude` VARCHAR(100);
ALTER TABLE `girocheck`.`merchant` ADD COLUMN `latitude` VARCHAR(100);


 