-- TiendaJDBC — esquema y datos de ejemplo
--
-- Uso:
--   mysql -u root -p < schema.sql
--
-- Crea la base `tienda` con las tablas que espera la aplicación y la carga con
-- datos de ejemplo, para que todas las consultas del menú devuelvan resultados.

DROP DATABASE IF EXISTS tienda;
CREATE DATABASE tienda CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE tienda;

CREATE TABLE fabricante (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE producto (
    codigo            INT AUTO_INCREMENT PRIMARY KEY,
    nombre            VARCHAR(100)  NOT NULL,
    precio            DOUBLE        NOT NULL,
    codigo_fabricante INT           NOT NULL,
    CONSTRAINT fk_producto_fabricante
        FOREIGN KEY (codigo_fabricante) REFERENCES fabricante (codigo)
);

INSERT INTO fabricante (codigo, nombre) VALUES
    (1, 'Asus'),
    (2, 'Lenovo'),
    (3, 'Hewlett-Packard'),
    (4, 'Samsung'),
    (5, 'Seagate');

INSERT INTO producto (codigo, nombre, precio, codigo_fabricante) VALUES
    (1,  'Disco duro SATA3 1TB',        86.99,  5),
    (2,  'Memoria RAM DDR4 8GB',       120.00,  1),
    (3,  'Disco SSD 1TB',              150.99,  4),
    (4,  'GeForce GTX 1050Ti',         185.00,  1),
    (5,  'GeForce GTX 1080 Xtreme',    755.00,  2),
    (6,  'Monitor 24 LED Full HD',     202.00,  1),
    (7,  'Monitor 27 LED Full HD',     245.99,  1),
    (8,  'Portátil Yoga 520',          559.00,  2),
    (9,  'Portátil Ideapad 320',       444.00,  2),
    (10, 'Impresora HP Deskjet 3720',   59.99,  3),
    (11, 'Impresora HP Laserjet Pro',  180.00,  3);
