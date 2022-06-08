INSERT INTO ciudad (codigo, nombre, url_imagen)
VALUES
       (1,'Armenia','https://www.cronicadelquindio.com/files/noticias/120200527083602.jpg'),
       (2,'Bogotá','https://la.network/wp-content/uploads/2021/07/Emprendimientos-en-Bogota%CC%81.jpg'),
       (3,'Medellín','https://img.theculturetrip.com/x/wp-content/uploads/2020/03/shutterstock_1263058732_medellin.jpg'),
       (4,'Miami','https://pic.le-cdn.com/thumbs/520x390/04/1/properties/Property-2b15000000000775000d626935ea-125113643.jpg'),
       (5,'Pereira','https://cdn.colombia.com/sdi/2022/01/13/turismo-pereira-top-25-987237.jpg'),
       (6,'Pasto','https://upload.wikimedia.org/wikipedia/commons/8/83/San_Juan_de_Pasto_de_noche.jpg'),
       (7,'Cali','https://mediaim.expedia.com/destination/1/8ff2783243cbc874078b54438cdc8785.jpg'),
       (8,'Ibagué','https://habitatdelosandes.com/wp-content/uploads/2021/07/1539443144_482426_1539443293_noticia_normal.jpg'),
       (9,'Cartagena','https://cdn.colombia.com/images/v2/turismo/sitios-turisticos/cartagena/ciudad-cartagena-800.jpg'),
       (10,'Panamá','https://www.caf.com/media/3682081/panama-1.jpg');

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
    ("10039938", "Pastrana", "pasti@gmail.com", "Andrés", "019992"),
    ("10037788", "Petro", "petrosky@gmail.com", "Tavito", "iisio"),
    ("1020293", "Muñoz", "luisito@gmail.com", "Luis", "Kjjsh8!"),
    ("129938", "Alzate", "papa@gmail.com", "Carlos", "poq@jqu"),
    ("291928", "Bride", "brtist@gmail.com", "Gene", "berto92");

INSERT INTO codigo_descuento (id, estado, cantidad_disponible, codigo, descripcion, descuento, fecha_creacion, fecha_vencimiento )
VALUES
(1, true,10, "AVANDESC","Descuento por ser estudiante de la uq", 0.1,null,null),
(2, true,100, "ESTRELLACLIENTE","Descuento por ser vendedor estrella", 0.15,null,null),
(3, true,33, "LANZADESC","Descuento por lanzamiento de página", 0.3,null,null);



INSERT INTO metodo_pago (codigo, descripcion)
VALUES
(1, "PSE"),
(2,"Tarjeta de credito"),
(3, "Tarjeta debito");




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








INSERT INTO cama (codigo, tipo)
VALUES
(1, "Doble"),
(2, "Sencilla"),
(3, "Queen"),
(4, "triple");


