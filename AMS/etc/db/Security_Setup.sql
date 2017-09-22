

insert into security.role (name, readonly, access_all, description) VALUES ('Admin', true,true,'Administrator');  
insert into security.role (name, readonly, access_all, description) VALUES ('Manager', true,true,'Manager'); 
insert into security.role (name, readonly, access_all, description) VALUES ('PSU', true,false,'Partner Super User'); 
insert into security.role (name, readonly, access_all, description) VALUES ('Merchant', true,false,'Merchant Manager'); 
insert into security.role (name, readonly, access_all, description) VALUES ('Distributor', true,false,'Distributor'); 
insert into security.role (name, readonly, access_all, description) VALUES ('User', true,false,'User'); 

   
insert into usuario.usuario (creation_date, name, lastname, username, password, role) VALUES (now(), 'Super', 'User', 'admin', 'admin',1);

insert into security.pageaccess (name,idpage) VALUES ('Dashboard', 'dashboard');
insert into security.pageaccess (name,idpage) VALUES ('Transactions', 'transactions');
insert into security.pageaccess (name,idpage) VALUES ('Agrupations', 'agrupations');
insert into security.pageaccess (name,idpage) VALUES ('Merchants', 'merchants'); 
insert into security.pageaccess (name,idpage) VALUES ('Terminals', 'terminals');
insert into security.pageaccess (name,idpage) VALUES ('Clerks', 'clerks');
insert into security.pageaccess (name,idpage) VALUES ('Users', 'users');
insert into security.pageaccess (name,idpage) VALUES ('Credit Cards', 'cards');
insert into security.pageaccess (name,idpage) VALUES ('Distributors', 'distributors');
insert into security.pageaccess (name,idpage) VALUES ('Partners', 'partners');
insert into security.pageaccess (name,idpage) VALUES ('Card Programs', 'programs');
insert into security.pageaccess (name,idpage) VALUES ('Fee Allocation Config', 'feeAllocationConfigs');
insert into security.pageaccess (name,idpage) VALUES ('Fee Allocator', 'feeAllocators');
insert into security.pageaccess (name,idpage) VALUES ('Reports', 'reports');
insert into security.pageaccess (name,idpage) VALUES ('Profile', 'profile');
insert into security.pageaccess (name,idpage) VALUES ('Security', 'security'); 
insert into security.pageaccess (name,idpage) VALUES ('Distributor', 'distributor');
insert into security.pageaccess (name,idpage) VALUES ('Merchant', 'merchant');
insert into security.pageaccess (name,idpage) VALUES ('Partner', 'partner');

insert into security.role_pageaccess (pageaccess,role) VALUES(17,1);
insert into security.role_pageaccess (pageaccess,role) VALUES(18,1);
insert into security.role_pageaccess (pageaccess,role) VALUES(19,1);
insert into security.role_pageaccess (pageaccess,role) VALUES(1,4);
insert into security.role_pageaccess (pageaccess,role) VALUES(19,4);
insert into security.role_pageaccess (pageaccess,role) VALUES(1,5);
insert into security.role_pageaccess (pageaccess,role) VALUES(18,5);
insert into security.role_pageaccess (pageaccess,role) VALUES(1,6);
insert into security.role_pageaccess (pageaccess,role) VALUES(17,6);

