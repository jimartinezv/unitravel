INSERT INTO ciudad (codigo, nombre, url_imagen)
VALUES
       (1,'Armenia','https://www.cronicadelquindio.com/files/noticias/120200527083602.jpg'),
       (2,'Bogotá','https://www.cronicadelquindio.com/files/noticias/120200527083602.jpg'),
       (3,'Medellín','https://www.cronicadelquindio.com/files/noticias/120200527083602.jpg'),
       (4,'Miami','https://www.cronicadelquindio.com/files/noticias/120200527083602.jpg'),
       (5,'Pereira','https://www.cronicadelquindio.com/files/noticias/120200527083602.jpg'),
       (6,'Pasto','https://www.cronicadelquindio.com/files/noticias/120200527083602.jpg'),
       (7,'Cali','https://www.cronicadelquindio.com/files/noticias/120200527083602.jpg'),
       (8,'Ibagué','https://www.cronicadelquindio.com/files/noticias/120200527083602.jpg'),
       (9,'Cartagena','https://www.cronicadelquindio.com/files/noticias/120200527083602.jpg'),
       (10,'Panamá','https://www.cronicadelquindio.com/files/noticias/120200527083602.jpg');

INSERT INTO cliente (cedula, apellidos, email, nombre, password, genero, ciudad_codigo)
VALUES
("10949","Gutierrez", "guti@gmail.com", "Alvaro", "alajj", "MASCULINO", 1),
("10209","Fernandez", "fernanlucho@gmail.com", "Luis", "alajj", "MASCULINO", 2),
("1094299","Mateo", "Matii@gmail.com", "José", "8837j", "MASCULINO", 3),
("109778","Gonzalez", "mariagonza@gmail.com", "Maria", "alajj", "FEMENINO", 4),
("1", "Martinez", "jimv9200@gmail.com", "Jorge", "clave", "MASCULINO", 5);

INSERT INTO cliente_telefono (cliente_cedula, telefono, telefono_key)
VALUES
("10949","7495589","CASA"),
("10949","7495230","OFICINA"),
("10949","3217389900","CELULAR"),
("109778","737567","CASA"),
("109778","7495230","OFICINA"),
("109778","3290001199","CELULAR");

INSERT INTO administrador (cedula, apellidos, email, nombre, password)
VALUES
("10887", "Martinez", "jimv9200@gmail.com", "Jorge Iván", "019992"),
("109927", "Morales", "sebasmorales@gmail.com", "Sebastian", "iisio"),
("12887", "Martinez", "santiagomari112@gmail.com", "Santiago", "Kjjsh8!"),
("1000", "Betancour", "betan@gmail.com", "Mariano", "poq@jqu"),
("733", "Alzate", "csalz@gmail.com", "Olga", "berto92");

INSERT INTO administrador_hotel (cedula, apellidos, email, nombre, password)
VALUES
    ("10887", "Martinez", "jimv9200@gmail.com", "Jorge Iván", "019992"),
    ("109927", "Morales", "sebasmorales@gmail.com", "Sebastian", "iisio"),
    ("12887", "Martinez", "santiagomari112@gmail.com", "Santiago", "Kjjsh8!"),
    ("1000", "Betancour", "betan@gmail.com", "Mariano", "poq@jqu"),
    ("733", "Alzate", "csalz@gmail.com", "Olga", "berto92");

INSERT INTO codigo_descuento (id, estado, cantidad_disponible, codigo, descripcion, descuento, fecha_creacion, fecha_vencimiento )
VALUES
(1, true,10, "AVANDESC","Descuento por ser estudiante de la uq", 0.1,null,null),
(2, true,100, "ESTRELLACLIENTE","Descuento por ser vendedor estrella", 0.15,null,null),
(3, true,33, "LANZADESC","Descuento por lanzamiento de página", 0.3,null,null);



INSERT INTO direccion (codigo, direccion, ciudad_codigo, hotel_codigo)
VALUES
    (1,"Calle 36n 40-22", 1,null),
    (2,"Cra 22 #9-00", 2,null),
    (3,"Calle 3 8-32", 3,null),
    (4,"Calle 7 #38-13", 4,null);

INSERT INTO hotel (codigo, descripcion,direccion_codigo, nombre, estrellas)
VALUES
    (1,"El hotel mas lujoso de armenia", 1, "Hotel Armenia", 5),
    (2,"El único hotel pa descansar", 2, "Hotel El descanso", 5),
    (3,"Aquí se duerme sabroso", 3, "Hotel Duerme bueno", 3),
    (4,"Hotel pa descansar", 4, "Hotel Descansa en paz", 1);

UPDATE direccion
SET hotel_codigo=1
    WHERE codigo=1;
UPDATE direccion
SET hotel_codigo=2
WHERE codigo=2;
UPDATE direccion
SET hotel_codigo=3
WHERE codigo=3;
UPDATE direccion
SET hotel_codigo=4
WHERE codigo=4;


INSERT INTO hotel_telefonos ( hotel_codigo, telefonos, telefonos_key)
VALUES
(1,"7495589","RECEPCION"),
(1,"7495230","GERENCIA"),
(2,"3217389900","CELULAR"),
(3,"737567","RECEPCION"),
(3,"7495230","OFICINA"),
(4,"3290001199","RECEPCION");

INSERT INTO metodo_pago (codigo, descripcion)
VALUES
(1, "PSE"),
(2,"Tarjeta de credito"),
(3, "Tarjeta debito");

INSERT INTO habitacion(codigo, capacidad,nombre, precio, hotel_codigo)
VALUES
(1, 3, "209",320000, 1),
(2, 2, "Swit presidencial", 100000, 2),
(3, 4, "901", 40000, 3);


INSERT INTO vuelo(codigo, aerolinea, ciudad_destino_codigo, ciudad_origen_codigo)
VALUES
("A340","AVIANCA", 1, 2),
("L378","LATAM", 2, 3),
("S33884","SPIRIT", 4, 5),
("A4738","AVIANCA", 5,4),
("A4536","AVIANCA",2,4);

INSERT INTO reserva (codigo, cantidad_personas, estado, precio_total, cliente_cedula)
VALUES
(1,3,"COMPLETO", 1500000,"10949"),
(2,1,"COMPLETO", 1200000,"10209"),
(3,4,"COMPLETO", 2200000,"109778"),
(4,2,"COMPLETO", 899000,"10949");

INSERT INTO comentario (codigo, calificacion, comentario, fecha, cliente_cedula, hotel_codigo )
VALUES
(1,5, "El hotel es muy bonito", CURTIME(), "10949", 1),
(2,1, "Que hotel tan horrible", CURTIME(), "10949", 3),
(3,4, "Buen servicio del hotel", CURTIME(), "109778", 2),
(4,2, "No tiene agua caliente", CURTIME(), "10209", 3),
(5,3, "En el hotel espantan", CURTIME(), "1094299", 1);

INSERT INTO reserva_habitacion (codigo, precio, codigo_descuento_id, habitacion_codigo, reserva_codigo)
VALUES
    (1,960000, null, 1,1),
    (2,320000, null, 1,2),
    (3,200000, null, 2,3),
    (4,200000, null, 2,3),
    (5,80000, null, 3,4);

INSERT INTO silla(codigo, posicion, precio, vuelo_codigo)
VALUES
(1,"ventana A1",50000, "A340"),
(2,"ventana B1",55000, "A340"),
(3,"ventana C1",60000, "A340"),
(4,"PASILLO A2",50000, "A340"),
(5,"PASILLO B2",55000, "A340"),
(6,"PASILLO C2",60000, "A340"),
(7,"ventana A1",50000, "L378"),
(8,"ventana B1",55000, "L378"),
(9,"ventana C1",60000, "L378" ),
(10,"PASILLO A2",50000, "L378"),
(11,"PASILLO B2",55000, "L378"),
(12,"PASILLO C2",60000, "L378"),
(13,"ventana A1",50000, "S33884"),
(14,"ventana B1",55000, "S33884"),
(15,"ventana C1",60000, "S33884"),
(16,"PASILLO A2",50000, "S33884"),
(17,"PASILLO B2",55000, "S33884"),
(18,"PASILLO C2",60000, "S33884"),
(19,"A1", 20000,"A4738"  ),
(20,"A2", 20000,"A4738" ),
(21,"B1", 20000,"A4738" ),
(22,"B2", 20000,"A4738" ),
(23,"C1", 20000,"A4738" ),
(24,"C2", 20000,"A4738" ),
(25,"A1", 20000,"A4536" ),
(26,"A2", 20000,"A4536" ),
(27,"B1", 20000,"A4536" ),
(28,"B2", 20000,"A4536" ),
(29,"C1", 20000,"A4536" ),
(30,"C2", 20000,"A4536" );



INSERT INTO reserva_silla (codigo, precio, reserva_codigo, silla_codigo)
VALUES
(1,50000,1,1),
(2,50000,1,2),
(3,100000,2,3),
(4,100000,3,4),
(5,120000,3,5),
(6,120000,3,6);

INSERT INTO caracteristica(codigo, descripcion, tipo)
VALUES
(1,"Habitacion doble","HABITACION"),
(2,"Habitacion con television","HABITACION"),
(3,"Habitacion con caja fuerte", "HABITACION"),
(4,"Habitacion con cama doble","HABITACION"),
(5,"Hotel con piscina","HOTEL"),
(6,"Hotel con desayuno","HOTEL"),
(7,"Hotel con alimentos incluidos","HOTEL");

INSERT INTO caracteristica_habitacion(caracteristicas_habitacion_codigo, habitacion_codigo)
VALUES
    (1,1),
    (2,1),
    (1,2),
    (3,2),
    (4,3);


INSERT INTO hotel_caracteristicas( hotel_codigo, caracteristicas_codigo)
VALUES
    (1,5),
    (2,5),
    (1,6),
    (3,7),
    (3,5);





INSERT INTO cama (codigo, tipo)
VALUES
(1, "Doble"),
(2, "Sencilla"),
(3, "Queen"),
(4, "triple");