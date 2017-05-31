INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(9, 'patch_9_4', now(), "Add_column_merchant_to_transaction");

ALTER TABLE `transaction` ADD COLUMN `merchant` int(10) NOT NULL;

update transaction t inner join terminal term on t.terminal = term.id set t.merchant = term.merchant;