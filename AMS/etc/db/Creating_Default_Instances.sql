insert into boarding.fee_allocation_config (last_update,name) values (now(), 'Platform Default Fee Allocation Config');

insert into boarding.account_reference_config (id) values (1);

insert into transactional.partner (name,default_fee,creation_date,fee_allocation_config,account_reference_config_id,account_reference_id) values ('Global Recash Platform',null, now(), 1,1,null);

insert into boarding.fee (name,partner,last_update) values ('Global Recash Default Fee', 1, now());

update transactional.partner set default_fee = 1 where partner.id = 1;

insert into transactional.agrupation (name, creation_date) VALUES ('Default Agrupation', now());

insert into security.role (name, readonly, access_all, description) VALUES ('Super Admin', true,true, 'Super Administrator');

insert into usuario.usuario (name, creation_date,role) values ('Default User', now(),1);

insert into transactional.merchant (name, creation_date, agrupation) VALUES ('Default Merchant', now(), 1);

insert into transactional.card_program (name, creation_date, partner) VALUES ('Default Program', now(), 1);

insert into transactional.merchant_program (card_program, creation_date, merchant) VALUES (1, now(), 1);




insert into boarding.fee_allocator (is_platform_level,last_update,name)  values (true, now(), 'Platform Fee');
insert into boarding.fee_allocator (is_platform_level,last_update,name)  values (false, now(), 'Partner Fee');
insert into boarding.fee_allocator (is_platform_level,last_update,name)  values (false, now(), 'Merchant Fee');
insert into boarding.fee_allocator (is_platform_level,last_update,name)  values (false, now(), 'Distributor Fee');

insert into transactional.card (active,amount,card_number,cvv,expdate,merchant_program,usuario) VALUES (true, 1000, '1000000000000001', '123','2022-12-19 01:24:49.797762', 1,1 );

insert into boarding.account_reference (account_number,card_id,is_internal,last_update,masked_card_number,name,routing_number,account_reference_config) VALUES ('', 1, true, now(), '1000********0001', 'Platform Default Account', '',1);
update transactional.card_program set account_reference_id = 1 WHERE id = 1;

insert into boarding.fee_allocation (account_reference_id,fixed_rate,has_account_reference,last_update,percent,fee_allocation_config,fee_allocator) VALUES (1,0,true,now(),30,1,1);
insert into boarding.fee_allocation (fixed_rate,has_account_reference,last_update,percent,fee_allocation_config,fee_allocator) VALUES (0,false,now(),30,1,2);
insert into boarding.fee_allocation (fixed_rate,has_account_reference,last_update,percent,fee_allocation_config,fee_allocator) VALUES (0,false,now(),20,1,3);
insert into boarding.fee_allocation (fixed_rate,has_account_reference,last_update,percent,fee_allocation_config,fee_allocator) VALUES (0,false,now(),20,1,4);

insert into transactional.transaction_type (name) values ('sale');
 


