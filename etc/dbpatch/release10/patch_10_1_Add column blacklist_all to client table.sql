INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(6, 'patch_10_1', now(), "Add column blacklist_all to client table");

alter table client add column blacklist_all bit(1) DEFAULT false;