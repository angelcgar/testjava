USE zona_fit; -- * <- cambiar por tu base de datos

CREATE TABLE `cliente` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100),
  `lastname` varchar(100),
  `membership` int
);

INSERT INTO `cliente` (`name`, `lastname`, `membership`) VALUES
('Juan', 'Pérez', 100),
('María', 'López', 200),
('Carlos', 'Gómez', 100),
('Ana', 'Martínez', 300),
('Pedro', 'Ramírez', 200),
('Lucía', 'Hernández', 100),
('Miguel', 'Torres', 300),
('Sofía', 'Vargas', 200),
('José', 'García', 100),
('Elena', 'Morales', 300),
('Andrés', 'Díaz', 200),
('Paula', 'Castro', 100),
('Felipe', 'Suárez', 300),
('Valeria', 'Mendoza', 200),
('Javier', 'Ortiz', 100);
