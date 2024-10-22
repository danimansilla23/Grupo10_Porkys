-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-10-2024 a las 14:51:58
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `porky`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carritos`
--

CREATE TABLE `carritos` (
  `idCarrito` int(11) NOT NULL,
  `Estado` varchar(45) NOT NULL,
  `Fecha_creacion` datetime NOT NULL,
  `Clientes_idCliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carritos`
--

INSERT INTO `carritos` (`idCarrito`, `Estado`, `Fecha_creacion`, `Clientes_idCliente`) VALUES
(1, '1', '2024-10-20 10:30:00', 1),
(2, '0', '2024-10-15 15:00:00', 2),
(3, '1', '2024-10-21 09:00:00', 3),
(8, '1', '2024-10-22 09:11:08', 2),
(9, '1', '2024-10-22 09:46:30', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carritos_x_productos`
--

CREATE TABLE `carritos_x_productos` (
  `Carritos_idCarrito` int(11) NOT NULL,
  `Productos_idProductos` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `tamaño` varchar(45) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `observacion` mediumtext DEFAULT NULL,
  `idPrdutosCarrito` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carritos_x_productos`
--

INSERT INTO `carritos_x_productos` (`Carritos_idCarrito`, `Productos_idProductos`, `cantidad`, `tamaño`, `precio`, `observacion`, `idPrdutosCarrito`) VALUES
(1, 1, 3, NULL, 3000.00, NULL, 1),
(1, 2, 3, NULL, 3000.00, NULL, 2),
(1, 3, 1, 'Grande', 30.00, 'Extra vainilla', 3),
(1, 4, 3, NULL, 3000.00, NULL, 4),
(1, 5, 1, 'Mediano', 35.00, 'Con mermelada de frutos rojos', 5),
(2, 2, 1, 'Pequeño', 15.00, 'Con extra frutillas', 6),
(3, 4, 3, 'Grande', 45.00, 'Sin gluten', 7),
(8, 2, 1, NULL, 1000.00, NULL, 8),
(1, 1, 3, NULL, 3000.00, NULL, 9),
(9, 4, 3, NULL, 300.00, NULL, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `idCliente` int(11) NOT NULL,
  `Nombre_Cliente` varchar(45) NOT NULL,
  `Telefono_Cliente` varchar(15) DEFAULT NULL,
  `Direccion_cliente` varchar(45) DEFAULT NULL,
  `Contrasenia_cliente` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`idCliente`, `Nombre_Cliente`, `Telefono_Cliente`, `Direccion_cliente`, `Contrasenia_cliente`) VALUES
(1, 'Juan Pérez', '123456789', 'Calle Falsa 123', 'password123'),
(2, 'María Gómez', '987654321', 'Avenida Siempre Viva 456', 'password456'),
(3, 'Carlos Ramírez', '456789123', 'Boulevard Central 789', 'password789'),
(4, 'Ana Fernández', '789123456', 'Calle 10 1010', 'password1010');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materiasprimas`
--

CREATE TABLE `materiasprimas` (
  `id_MateriaPrima` int(11) NOT NULL,
  `Nombre_MP` varchar(100) NOT NULL,
  `Fecha_Vto_Prox` date NOT NULL,
  `stock` int(11) NOT NULL,
  `unidades` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `materiasprimas`
--

INSERT INTO `materiasprimas` (`id_MateriaPrima`, `Nombre_MP`, `Fecha_Vto_Prox`, `stock`, `unidades`) VALUES
(3, 'Huevos', '2024-10-20', 20, 'unidades'),
(4, 'Leche', '2024-10-12', 80, 'litros'),
(5, 'Manteca', '2024-11-05', 65, 'kg'),
(6, 'Dulce de leche', '2024-09-30', 40, 'kg'),
(7, 'Chocolate amargo', '2025-02-28', 32, 'kg'),
(8, 'Frutillas', '2024-10-10', 20, 'kg'),
(18, 'Coco Rallado', '2024-02-03', 20, 'Gramos'),
(26, 'Oreos', '2024-11-06', 24, 'Unidad'),
(27, 'Azucar', '2024-11-08', 4, 'Kg'),
(28, 'Azucar Blck', '2024-10-12', 200, 'gramos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `idProductos` int(11) NOT NULL,
  `Nombre_Producto` varchar(45) NOT NULL,
  `Precio_vta` varchar(45) DEFAULT NULL,
  `tamaño` varchar(45) DEFAULT NULL,
  `descripcion_producto` varchar(45) DEFAULT NULL,
  `ProductosBase_idProductosBase` int(11) DEFAULT NULL,
  `imagen_url` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idProductos`, `Nombre_Producto`, `Precio_vta`, `tamaño`, `descripcion_producto`, `ProductosBase_idProductosBase`, `imagen_url`) VALUES
(1, 'Torta de Chocolate', '500', '20 cm', 'Torta con bizcocho y cobertura de chocolate', 1, 'torta_chocolate.jpg'),
(2, 'Tarta de Frutillas', '600', '18 cm', 'Tarta con bizcocho y crema pastelera', 2, 'tarta_frutillas.jpg'),
(3, 'Pastel de Vainilla', '450', '25 cm', 'Pastel de vainilla con cobertura de chocolate', 3, 'pastel_vainilla.jpg'),
(4, 'Torta de Arandanos', '1500', '20', 'Deliciosa torta de frutilla', NULL, '/torta_frutilla.jpg'),
(5, 'Chesscake', '3000', '18', 'Sin tac', NULL, '/torta_frutilla.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productosbase`
--

CREATE TABLE `productosbase` (
  `idProductosBase` int(11) NOT NULL,
  `Nombre_base` varchar(45) NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productosbase`
--

INSERT INTO `productosbase` (`idProductosBase`, `Nombre_base`, `Descripcion`) VALUES
(1, 'Bizcocho', 'Bizcocho base para tortas y pasteles'),
(2, 'Galleta Chesscake', 'Base para Chesscakes'),
(3, 'Cobertura de Chocolate', 'Cobertura de chocolate para decoraciones y co');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carritos`
--
ALTER TABLE `carritos`
  ADD PRIMARY KEY (`idCarrito`),
  ADD KEY `Clientes_idCliente` (`Clientes_idCliente`);

--
-- Indices de la tabla `carritos_x_productos`
--
ALTER TABLE `carritos_x_productos`
  ADD PRIMARY KEY (`idPrdutosCarrito`),
  ADD KEY `fk_carritos_idCarrito` (`Carritos_idCarrito`),
  ADD KEY `fk_productos_idProductos` (`Productos_idProductos`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indices de la tabla `materiasprimas`
--
ALTER TABLE `materiasprimas`
  ADD PRIMARY KEY (`id_MateriaPrima`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idProductos`),
  ADD KEY `ProductosBase_idProductosBase` (`ProductosBase_idProductosBase`);

--
-- Indices de la tabla `productosbase`
--
ALTER TABLE `productosbase`
  ADD PRIMARY KEY (`idProductosBase`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carritos`
--
ALTER TABLE `carritos`
  MODIFY `idCarrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `carritos_x_productos`
--
ALTER TABLE `carritos_x_productos`
  MODIFY `idPrdutosCarrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `materiasprimas`
--
ALTER TABLE `materiasprimas`
  MODIFY `id_MateriaPrima` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `idProductos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `productosbase`
--
ALTER TABLE `productosbase`
  MODIFY `idProductosBase` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carritos`
--
ALTER TABLE `carritos`
  ADD CONSTRAINT `carritos_ibfk_1` FOREIGN KEY (`Clientes_idCliente`) REFERENCES `clientes` (`idCliente`);

--
-- Filtros para la tabla `carritos_x_productos`
--
ALTER TABLE `carritos_x_productos`
  ADD CONSTRAINT `fk_carritos_idCarrito` FOREIGN KEY (`Carritos_idCarrito`) REFERENCES `carritos` (`idCarrito`),
  ADD CONSTRAINT `fk_productos_idProductos` FOREIGN KEY (`Productos_idProductos`) REFERENCES `productos` (`idProductos`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`ProductosBase_idProductosBase`) REFERENCES `productosbase` (`idProductosBase`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
