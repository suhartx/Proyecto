-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 18, 2021 at 07:08 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `osabide`
--
CREATE DATABASE IF NOT EXISTS `osabide` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `osabide`;

-- --------------------------------------------------------

--
-- Table structure for table `cita`
--

CREATE TABLE IF NOT EXISTS `cita` (
  `cod_cita` int(8) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(20) NOT NULL,
  `descripción` tinytext NOT NULL,
  `ámbito` varchar(30) NOT NULL,
  `sanitario_asociado` int(5) NOT NULL,
  `paciente_asociado` int(5) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  PRIMARY KEY (`cod_cita`),
  KEY `fk_sanitario_asociado` (`sanitario_asociado`),
  KEY `fp_paciente_asociado` (`paciente_asociado`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cita`
--

INSERT INTO `cita` (`cod_cita`, `titulo`, `descripción`, `ámbito`, `sanitario_asociado`, `paciente_asociado`, `fecha`, `hora`) VALUES
(1, 'Revision Asma', 'Revision preiodica habitual del asma', 'Comun', 3, 1, '2020-11-06', '17:00:00'),
(2, 'Gripe', 'Revision de gripe Comun', 'Comun', 3, 1, '2020-11-01', '21:00:00'),
(3, 'Muelas del Juicio', 'Extraccion de Maxilar inferior y superior', 'odontologia', 4, 1, '2020-10-06', '12:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `medicamento`
--

CREATE TABLE IF NOT EXISTS `medicamento` (
  `cod_medicamento` int(8) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(30) NOT NULL,
  `descripcion` tinytext NOT NULL,
  `ambito` varchar(30) NOT NULL,
  `fecha_lanzamiento` date NOT NULL,
  PRIMARY KEY (`cod_medicamento`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medicamento`
--

INSERT INTO `medicamento` (`cod_medicamento`, `titulo`, `descripcion`, `ambito`, `fecha_lanzamiento`) VALUES
(1, 'duoresp', 'mediacmiento para asma oral con corticoides', 'Cardio-pulmonar', '2020-05-20'),
(2, 'Terbasmin', 'Medicamento para el asma. sin corticoides', 'Cardio-pulmonar', '2020-05-04'),
(3, 'paracetamol', 'capsula de analgesico', 'general', '2010-07-06'),
(4, 'ibuprofeno', 'capsula analgesica e antiinflamatoria', 'General', '2012-10-02'),
(5, 'betadine oral', 'Betadine dirigido para uso oral', 'Odontologia', '2010-07-15');

-- --------------------------------------------------------

--
-- Table structure for table `medicamento_tratamiento`
--

CREATE TABLE IF NOT EXISTS `medicamento_tratamiento` (
  `cod_tratamiento` int(8) NOT NULL,
  `cod_medicamento` int(8) NOT NULL,
  `cantidad` int(2) NOT NULL,
  UNIQUE KEY `cod_tratamiento` (`cod_tratamiento`,`cod_medicamento`),
  KEY `fk_medicamento` (`cod_medicamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medicamento_tratamiento`
--

INSERT INTO `medicamento_tratamiento` (`cod_tratamiento`, `cod_medicamento`, `cantidad`) VALUES
(1, 1, 2),
(1, 2, 2),
(2, 3, 2),
(3, 1, 2),
(3, 3, 2),
(3, 4, 2),
(3, 5, 2),
(4, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `prueba`
--

CREATE TABLE IF NOT EXISTS `prueba` (
  `cod_prueba` int(8) NOT NULL AUTO_INCREMENT,
  `título` varchar(20) NOT NULL,
  `descripción` tinytext NOT NULL,
  `ambito` varchar(30) NOT NULL,
  `sanitario_asociado` int(5) NOT NULL,
  `paciente_asociado` int(5) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  PRIMARY KEY (`cod_prueba`),
  KEY `fk_sanitario_prueba` (`sanitario_asociado`),
  KEY `fk_paciente_prueba` (`paciente_asociado`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `prueba`
--

INSERT INTO `prueba` (`cod_prueba`, `título`, `descripción`, `ambito`, `sanitario_asociado`, `paciente_asociado`, `fecha`, `hora`) VALUES
(1, 'Espirometria', 'Prueba de respiracion para el asma', 'Cardio-pulmonar', 4, 1, '2020-09-15', '11:00:00'),
(2, 'Radiografia', 'Radiografia de brazo roto para hacer un seguimiento de su recuperacion', 'Radiologia', 3, 1, '2016-06-08', '14:00:00'),
(3, 'Ortopantomografia', 'Rediografia de los dientes para ver como estan', 'Odontologia', 4, 1, '2020-09-15', '16:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `tratamientos`
--

CREATE TABLE IF NOT EXISTS `tratamientos` (
  `cod_tratamiento` int(8) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(20) NOT NULL,
  `descripción` tinytext DEFAULT NULL,
  `ambito` varchar(20) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `medico_asociado` int(5) DEFAULT NULL,
  `paciente_asociado` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_tratamiento`),
  KEY `fk_sanitario_tratamiento` (`medico_asociado`) USING BTREE,
  KEY `fk_paciente_tratamiento` (`paciente_asociado`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tratamientos`
--

INSERT INTO `tratamientos` (`cod_tratamiento`, `titulo`, `descripción`, `ambito`, `fecha`, `hora`, `medico_asociado`, `paciente_asociado`) VALUES
(1, 'tratamiento Asma', 'uso diario de terbasmin y duoresp 3 veces al dia', 'Cardio-pulmonar', '2020-11-12', '00:00:00', 3, 1),
(2, 'Tratamiento fiebre', 'uso de medicamentos ibuprofeno y paracetamol', 'general', '2020-08-12', '00:00:00', 3, 1),
(3, 'Muelas del juicio', 'Tratamiento de muelas del juicio con betadine oral analgesicos y antiinflamatorios', 'Odontologia', '2020-09-17', '00:00:00', 4, 1),
(4, 'cxzczxcz', 'xczxczxczxc', 'zxccxczx', '2021-01-06', '12:00:00', 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `cod_usuario` int(5) NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombre` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL,
  `apellidos` varchar(40) COLLATE utf8mb4_spanish_ci NOT NULL,
  `sexo` enum('m','f','ns') COLLATE utf8mb4_spanish_ci NOT NULL,
  `tipo` enum('paciente','medico','enfermero') COLLATE utf8mb4_spanish_ci NOT NULL,
  `altura` int(3) NOT NULL COMMENT 'En centimetros',
  `peso` float(5,2) NOT NULL COMMENT 'en kilos',
  `alergias` tinytext COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `colesterol` int(3) DEFAULT NULL COMMENT 'miligramos por decilitro (mg / dL)',
  `tension` int(3) DEFAULT NULL,
  `enfermedades` mediumtext COLLATE utf8mb4_spanish_ci DEFAULT NULL COMMENT 'enfermedades con breve explicación de ellas',
  `tipo_sangre` varchar(4) COLLATE utf8mb4_spanish_ci NOT NULL COMMENT 'tipo de sangre del paciente',
  `medico_cabecera` int(5) DEFAULT NULL,
  `contrasenya` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`cod_usuario`),
  UNIQUE KEY `dni` (`dni`),
  KEY `fk_medico` (`medico_cabecera`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`cod_usuario`, `dni`, `nombre`, `apellidos`, `sexo`, `tipo`, `altura`, `peso`, `alergias`, `colesterol`, `tension`, `enfermedades`, `tipo_sangre`, `medico_cabecera`, `contrasenya`) VALUES
(1, '79068571E', 'Suhar', 'Txabarri Aurrekoetxea', 'm', 'paciente', 185, 78.00, 'acaros', NULL, NULL, 'asma', '0+', 3, 'suhar1523'),
(2, '78954624A', 'Maitane', 'San Vicente', 'f', 'enfermero', 165, 60.00, 'Polen -Lactosa', NULL, NULL, NULL, '0+', 3, 'Passw0rd'),
(3, '56496587W', 'Irati', 'Cabrera', 'f', 'medico', 168, 65.00, 'Frutos Secos', NULL, NULL, NULL, 'AB+', 4, 'Passw0rd'),
(4, '45213625S', 'Maddi', 'Sanchez Arrate', 'f', 'medico', 160, 55.00, 'Lactosa', NULL, NULL, NULL, 'A-', 3, 'Passw0rd');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cita`
--
ALTER TABLE `cita`
  ADD CONSTRAINT `fk_sanitario_asociado` FOREIGN KEY (`sanitario_asociado`) REFERENCES `usuarios` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fp_paciente_asociado` FOREIGN KEY (`paciente_asociado`) REFERENCES `usuarios` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `medicamento_tratamiento`
--
ALTER TABLE `medicamento_tratamiento`
  ADD CONSTRAINT `fk_medicamento` FOREIGN KEY (`cod_medicamento`) REFERENCES `medicamento` (`cod_medicamento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tratamiento` FOREIGN KEY (`cod_tratamiento`) REFERENCES `tratamientos` (`cod_tratamiento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `prueba`
--
ALTER TABLE `prueba`
  ADD CONSTRAINT `fk_paciente_prueba` FOREIGN KEY (`paciente_asociado`) REFERENCES `usuarios` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sanitario_prueba` FOREIGN KEY (`sanitario_asociado`) REFERENCES `usuarios` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tratamientos`
--
ALTER TABLE `tratamientos`
  ADD CONSTRAINT `fk_paciente_cita` FOREIGN KEY (`paciente_asociado`) REFERENCES `usuarios` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sanitario_cita` FOREIGN KEY (`medico_asociado`) REFERENCES `usuarios` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_medico` FOREIGN KEY (`medico_cabecera`) REFERENCES `usuarios` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
