INSERT INTO city(name,latitude,longitude) VALUES('Milano',45.46427,9.18951);
INSERT INTO city(name,latitude,longitude) VALUES('Roma',41.89193,12.51133);


INSERT INTO house(address,latitude,longitude,type,price,area,E_class,city_fk) VALUES ('Viale Lunigiana, 15, Milano', 45.491684, 9.204736, 'Trilocale', 100000.00, 100, 'B',1);
INSERT INTO house(address,latitude,longitude,type,price,area,E_class,city_fk) VALUES ('Via Angelo De Vincenti, 6, Milano', 45.477931, 9.141652, 'Bilocale', 120000.00, 120, 'C',1);
INSERT INTO house(address,latitude,longitude,type,price,area,E_class,city_fk) VALUES ('Via Pinamonte da Vimercate, 3, Milano', 45.479081, 9.182454, 'Trilocale', 130000.00, 90, 'A',1);
INSERT INTO house(address,latitude,longitude,type,price,area,E_class,city_fk) VALUES ('Corso di Porta Nuova, 34, Milano', 45.477285, 9.192178, 'Quadrilocale', 305000.00, 95, 'A',1);
INSERT INTO house(address,latitude,longitude,type,price,area,E_class,city_fk) VALUES ('Via Emilio Cornalia, 12, Milano', 45.483139, 9.197228, 'Loft', 180000.00, 110, 'G',1);
INSERT INTO house(address,latitude,longitude,type,price,area,E_class,city_fk) VALUES ('Via Filippo Turati, 29, Milano', 45.477429, 9.195515, 'Plurilocale', 350000.00, 105, 'A',1);
INSERT INTO house(address,latitude,longitude,price,area,E_class,type,city_fk) VALUES ("Via 1, Roma",41.89311999315887, 12.478371015625044, 300000, 80, 'F', "Bilocale",2);
INSERT INTO house(address,latitude,longitude,price,area,E_class,type,city_fk) VALUES ("Via 2, Roma", 41.89652612618998, 12.527809492187544, 180000, 90, 'G', "Bilocale",2);
INSERT INTO house(address,latitude,longitude,price,area,E_class,type,city_fk) VALUES ("Via 3, Roma",41.89095196158848, 12.489292423508232, 200000, 40, 'B', "Bilocale",2);
INSERT INTO house(address,latitude,longitude,price,area,E_class,type) VALUES ("Via 4, Tivoli",41.94964825517216, 12.710799551425566, 130000, 70, 'A', "Bilocale");
