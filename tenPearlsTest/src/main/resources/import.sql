
	
INSERT INTO country (country_name) VALUES('Colombia');
INSERT INTO state (state_name,country_id) VALUES('Antioquia',1);
INSERT INTO state (state_name,country_id) VALUES('Valle',1);
INSERT INTO city (city_name,state_id) VALUES('Medellin',1);
INSERT INTO city (city_name,state_id) VALUES('Envigado',1);
INSERT INTO city (city_name,state_id) VALUES('Sabaneta',1);
INSERT INTO city (city_name,state_id) VALUES('Cali',2);
INSERT INTO city (city_name,state_id) VALUES('Palmira',2);

INSERT INTO sale_representative (name_sale) VALUES('Vendedor1');
INSERT INTO sale_representative (name_sale) VALUES('Vendedor2');
INSERT INTO sale_representative (name_sale) VALUES('Vendedor3');

INSERT INTO client (address,available_credit,credit_limit, full_name,nit,phone,visits_percentage,city_id,cLient_id_sate,cLient_id_Country)VALUES('direccion1',800,1000,'nombre Cliente1','111111','telefono1',10,1,1,1);
INSERT INTO client (address,available_credit,credit_limit, full_name,nit,phone,visits_percentage,city_id,cLient_id_sate,cLient_id_Country)VALUES('direccion2',1800,2000,'nombre Cliente2','222222','telefono2',20,1,1,1);
INSERT INTO client (address,available_credit,credit_limit, full_name,nit,phone,visits_percentage,city_id,cLient_id_sate,cLient_id_Country)VALUES('direccion3',3000,3000,'nombre Cliente3','333333','telefono3',30,1,1,1);

INSERT INTO client (address,available_credit,credit_limit, full_name,nit,phone,visits_percentage,city_id,cLient_id_sate,cLient_id_Country)VALUES('direccion1',800,1000,'nombre Cliente1','444444','telefono1',10,4,2,1);
INSERT INTO client (address,available_credit,credit_limit, full_name,nit,phone,visits_percentage,city_id,cLient_id_sate,cLient_id_Country)VALUES('direccion2',1800,2000,'nombre Cliente2','555555','telefono2',20,4,2,1);
INSERT INTO client (address,available_credit,credit_limit, full_name,nit,phone,visits_percentage,city_id,cLient_id_sate,cLient_id_Country)VALUES('direccion3',3000,3000,'nombre Cliente3','666666','telefono3',30,4,2,1);

INSERT INTO visit (create_at,description,id_sale,net,visit_total,client_nit,city_id) VALUES(CURDATE(),'DESCRIPCION VISITA11',1,10,100,'111111',1);
INSERT INTO visit (create_at,description,id_sale,net,visit_total,client_nit,city_id) VALUES(CURDATE(),'DESCRIPCION VISITA12',1,10,100,'111111',1);
INSERT INTO visit (create_at,description,id_sale,net,visit_total,client_nit,city_id) VALUES(CURDATE(),'DESCRIPCION VISITA21',1,10,200,'222222',1);

INSERT INTO visit (create_at,description,id_sale,net,visit_total,client_nit,city_id) VALUES(CURDATE(),'DESCRIPCION VISITA41',1,10,100,'444444',4);
INSERT INTO visit (create_at,description,id_sale,net,visit_total,client_nit,city_id) VALUES(CURDATE(),'DESCRIPCION VISITA42',1,10,100,'444444',4);
INSERT INTO visit (create_at,description,id_sale,net,visit_total,client_nit,city_id) VALUES(CURDATE(),'DESCRIPCION VISITA51',1,10,100,'555555',4);




