DROP DATABASE IF EXISTS agenda;
CREATE DATABASE agenda CHARACTER SET utf8mb4;
USE agenda;


CREATE TABLE contactos(
    id int(10) NOT NULL AUTO_INCREMENT,
    nombre		varchar(50),
    apellidos	varchar(50),
    email		varchar(50),
    fecha_nac   date,
    PRIMARY KEY (`id`)

);

INSERT INTO contactos (nombre, apellidos, email, fecha_nac) VALUES ('Mortadelo', 'Mortadelo', 'mortadelo@kk.com','1900-01-01');
INSERT INTO contactos (nombre, apellidos, email, fecha_nac) VALUES ('Filemon', 'Filemon', 'filemon@kk.com','1900-01-05');
INSERT INTO contactos (nombre, apellidos, email, fecha_nac) VALUES ('Juan', 'Perez Cano', 'juan@kk.com','1979-11-8');
INSERT INTO contactos (nombre, apellidos, email, fecha_nac) VALUES ('Celia', 'Perez Cano', 'celia@kk.com','1986-01-21');
