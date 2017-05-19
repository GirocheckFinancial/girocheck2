INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(9, 'patch_9_3', now(), 'Create_idimages_png_table');

CREATE TABLE `idimages_png` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client` int(10) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `id_front` longblob,
  `id_back` longblob,
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
