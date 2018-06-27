﻿insert into USERS (id, first_name, last_name, email, birth_date, password, registration_date, is_admin) 
 values
	('1', 'Iogann', 'Kepler', 'kepler111@gmail.com', '1970-01-01', '123', '2017-05-15', 'false'),
	('2', 'Ilon', 'Mask', 'imask@gmail.com', '1970-01-01', '123', '2017-06-13', 'true'),
	('3', 'Karl', 'Sagan', 'ksagan@gmail.com', '1934-01-01', '123', '2017-06-11', 'false'),
	('4', 'Stephen', 'Hocking', 'shocking@gmail.com', '1950-05-05', '123', '2017-09-12', 'false'),
   	('5', 'Sergey', 'Korolev', 'skorolev@mail.ru', '1907-01-01', '123', '2018-01-01', 'true'),
   	('6', 'Gallileo', 'Galilei', 'gg77@gmail.com', '1988-01-01', '123', '2018-01-01', 'false'),
   	('7', 'Pinky', 'Brain', 'brain@gmail.com', '1967-01-01', '123', '2018-01-31', 'false'),
   	('8', 'Patrik', 'Star', 'stars@gmail.com', '1977-01-01', '123', '2018-01-31', 'false'),
   	('9', 'Forest', 'Hamp', 'fhamp@gmail.com', '1970-01-01', '123', '2018-01-31', 'false'),
   	('10', 'Frodo', 'Torbins', 'ftorbins@gmail.com', '1989-05-15', '123', '2018-01-31', 'false'),
   	('11', 'Dovakin', 'Dragonborn', 'bdovakin@gmail.com', '1977-01-11', '123', '2018-01-31', 'false'),
   	('12', 'Sponge', 'Bob', 'spgb@gmail.com', '1990-01-01', '123', '2018-01-31', 'false')
;

insert into ROUTES (id, creation_date, start_point, destination_point, route_type, user_id, cost, duration, distance)
 values
	('1', '2018-01-31', 'Москва', 'Воронеж', '1', '1', 200.5, 115.0, 1100.0),
	('2', '2018-01-31', 'Москва', 'Воронеж', '2', '4', 300.0, 1150.0, 50.0),
	('3', '2018-01-31', 'Москва', 'Воронеж', '3', '1', 200.0, 1200.0, 255.0),
	('4', '2018-02-01', 'Воронеж', 'Тбилиси', '1', '7', 500.0, 122.0, 348.0),
	('5', '2018-02-02', 'Воронеж', 'Тбилиси', '2', '7', 450.0, 1110.0, 722.0),
	('6', '2018-02-03', 'Воронеж', 'Тбилиси', '3', '7', 480.0, 1220.0, 575.0),
	('7', '2018-02-03', 'Воронеж', 'Санкт-Петербург', '1', '9', 1440.0, 1220.0, 180.0),
	('11', '2018-02-05', 'Воронеж', 'Санкт-Петербург', '3', '7', 580.0, 1000.0, 702.0),
	('12', '2018-02-06', 'Воронеж', 'Санкт-Петербург', '2', '2', 430.0, 1060.0, 742.0),
	('13', '2018-02-07', 'Воронеж', 'Тбилиси', '1', '4', 774.0, 1033.0, 766.0),
	('14', '2018-02-08', 'Воронеж', 'Тбилиси', '2', '12', 753.0, 1330.0, 187.0),
	('15', '2018-02-08', 'Воронеж', 'Ростов-на-Дону', '1', '4', 444.0, 1130.0, 225.0),
	('16', '2018-02-10', 'Воронеж', 'Ростов-на-Дону', '2', '7', 422.0, 11110.0, 456.0),
	('17', '2018-02-10', 'Воронеж', 'Санкт-Петербург', '3', '4', 380.0, 5240.0, 1200.0),
	('21', '2018-02-14', 'Воронеж', 'Магадан', '3', '1', 422.0, 11110.0, 447.0),
	('22', '2018-02-15', 'Москва', 'Магадан', '2', '6', 422.0, 5760.0, 367.0),
	('23', '2018-02-16', 'Москва', 'Магадан', '3', '1', 422.0, 3333.0, 837.0),
	('24', '2018-02-17', 'Москва', 'Магадан', '1', '6', 422.0, 3330.0, 982.0)
;

insert into EDGES (id, creation_date, start_point, destination_point, distance, duration, start_date, end_date, cost, currency, transport_type, edge_type, edge_order, route_id)
 values
	('1', '2018-01-31', 'Москва', 'Воронеж', '600', '2.15', '2018-03-01', '2018-03-01', '51', 'USD', 'plane', '3', 1, 1),
	('2', '2018-01-31', 'Москва', 'Воронеж', '600', '6.20', '2018-03-01', '2018-03-02', '15', 'USD', 'train', '2', 1, 2),
	('3', '2018-01-31', 'Москва', 'Воронеж', '600', '8.20', '2018-03-01', '2018-03-02', '12', 'USD', 'bus', '1', 1, 3),
	('4', '2018-02-01', 'Воронеж', 'Тбилиси', '1180', '6.10', '2018-03-01', '2018-03-01', '163', 'USD', 'plane', '3', 2, 1),
	('5', '2018-02-01', 'Ростов-на-Дону', 'Тбилиси', '738', '1.30', '2018-03-01', '2018-03-01', '51', 'USD', 'plane', '2', 3, 2),
	('6', '2018-02-01', 'Воронеж', 'Ростов-на-Дону', '500', '5', '2018-03-01', '2018-03-02', '15', 'USD', 'train', '2', 2, 2),
	('7', '2018-02-01', 'Воронеж', 'Ростов-на-Дону', '500', '6', '2018-03-01', '2018-03-02', '13', 'USD', 'bus', '1', 2, 3),
	('8', '2018-02-03','Воронеж', 'Санкт-Петербург', '1080', '1.55', '2018-03-01', '2018-03-02', '77', 'USD', 'plane', '3', 1, 4),
	('9', '2018-02-03','Воронеж', 'Тамбов', '185', '2.5', '2018-03-01', '2018-03-01', '8', 'USD', 'bus', '2', 1, 5),
	('10', '2018-02-03','Тамбов', 'Санкт-Петербург', '1060', '2', '2018-03-01', '2018-03-01', '70', 'USD', 'plane', '2', 2, 7),
	('11', '2018-02-03','Воронеж', 'Санкт-Петербург', '1080', '9', '2018-03-01', '2018-03-02', '74', 'USD', 'train', '1', 1, 5),
	('12', '2018-02-03','Воронеж', 'Магадан', '6100', '14.45','2018-03-01', '2018-03-03', '388', 'USD', 'plane', '3', 2, 6),
	('13', '2018-02-03','Воронеж', 'Белгород', '200', '3.2','2018-03-01', '2018-03-01', '10', 'USD', 'bus', '2', 1, 6),
	('14', '2018-02-03','Белгород', 'Магадан', '5800', '13','2018-03-01', '2018-03-03', '333', 'USD', 'plane', '2', 3, 12),	
	('15', '2018-02-03','Москва', 'Магадан', '5950', '7','2018-03-01', '2018-03-03', '239', 'USD', 'plane', '2', 1, 13),
	('16', '2018-02-18','Москва', 'Санкт-Петербург', '639', '1.20','2018-03-01', '2018-03-01', '31', 'USD', 'plane', '3', 2, 13),
	('17', '2018-02-18','Москва', 'Санкт-Петербург', '639', '6.2','2018-03-01', '2018-03-02', '15', 'USD', 'train', '2', 1, 15),
	('18', '2018-02-18','Москва', 'Санкт-Петербург', '639', '6.5','2018-03-01', '2018-03-02', '13', 'USD', 'bus', '1', 1, 11)
;