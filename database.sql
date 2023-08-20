create database control_de_stock;
use control_de_stock;

CREATE TABLE CATEGORIA (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE VARCHAR(60) NOT NULL
) Engine=InnoDB;

CREATE TABLE PRODUCTO (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE VARCHAR(60) NOT NULL,
    DESCRIPCION VARCHAR(200),
    CANTIDAD INT,
    CATEGORIA_ID INT,
    FOREIGN KEY(CATEGORIA_ID) REFERENCES CATEGORIA(ID)
) Engine=InnoDB;

INSERT INTO CATEGORIA(nombre) values ('Mueble');
INSERT INTO CATEGORIA(nombre) values ('Tecnologia');
INSERT INTO CATEGORIA(nombre) values ('Cocina');
INSERT INTO CATEGORIA(nombre) values ('Zapatillas');

INSERT INTO PRODUCTO(nombre,descripcion,cantidad,categoria_id) values ('Mesa','Mesa para 4 personas',4,1);
