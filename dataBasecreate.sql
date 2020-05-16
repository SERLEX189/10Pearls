
CREATE DATABASE `db_springboot_backend` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `city` (
  `id_city` bigint NOT NULL AUTO_INCREMENT,
  `city_name` varchar(255) DEFAULT NULL,
  `state_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id_city`),
  KEY `FK6p2u50v8fg2y0js6djc6xanit` (`state_id`),
  CONSTRAINT `FK6p2u50v8fg2y0js6djc6xanit` FOREIGN KEY (`state_id`) REFERENCES `state` (`id_state`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `client` (
  `nit` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `available_credit` bigint DEFAULT NULL,
  `city_id` bigint NOT NULL,
  `client_id_country` bigint NOT NULL,
  `client_id_sate` bigint NOT NULL,
  `credit_limit` bigint NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `visits_percentage` bigint NOT NULL,
  PRIMARY KEY (`nit`),
  KEY `FKkb5fr3nx3qucrbme4wewcmwbf` (`city_id`),
  CONSTRAINT `FKkb5fr3nx3qucrbme4wewcmwbf` FOREIGN KEY (`city_id`) REFERENCES `city` (`id_city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `country` (
  `id_country` bigint NOT NULL AUTO_INCREMENT,
  `country_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_country`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sale_representative` (
  `id_sale_representative` bigint NOT NULL AUTO_INCREMENT,
  `name_sale` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_sale_representative`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `state` (
  `id_state` bigint NOT NULL AUTO_INCREMENT,
  `country_id` bigint DEFAULT NULL,
  `state_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_state`),
  KEY `FKghic7mqjt6qb9vq7up7awu0er` (`country_id`),
  CONSTRAINT `FKghic7mqjt6qb9vq7up7awu0er` FOREIGN KEY (`country_id`) REFERENCES `country` (`id_country`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `visit` (
  `id_visit` bigint NOT NULL AUTO_INCREMENT,
  `city_id` bigint DEFAULT NULL,
  `client_nit` varchar(255) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id_sale` bigint NOT NULL,
  `net` bigint NOT NULL,
  `visit_total` bigint DEFAULT NULL,
  PRIMARY KEY (`id_visit`),
  KEY `FK356gl60h6ulfg0d76id3kqh2h` (`client_nit`),
  CONSTRAINT `FK356gl60h6ulfg0d76id3kqh2h` FOREIGN KEY (`client_nit`) REFERENCES `client` (`nit`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


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

