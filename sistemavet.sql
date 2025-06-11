-- MySQL dump 10.13  Distrib 9.0.1, for macos14.7 (arm64)
--
-- Host: localhost    Database: SistemaVeterinaria
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Agenda`
--

DROP TABLE IF EXISTS `Agenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Agenda` (
  `idAgenda` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idAgenda`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Agenda`
--

LOCK TABLES `Agenda` WRITE;
/*!40000 ALTER TABLE `Agenda` DISABLE KEYS */;
INSERT INTO `Agenda` VALUES (1),(2);
/*!40000 ALTER TABLE `Agenda` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `iniciarAgenda` AFTER INSERT ON `agenda` FOR EACH ROW BEGIN
CALL insertar_fechas_enero(NEW.idAgenda);
CALL insertar_FechaHora_Agenda(NEW.idAgenda);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Cita`
--

DROP TABLE IF EXISTS `Cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cita` (
  `idCita` int NOT NULL AUTO_INCREMENT,
  `tratamiento` text,
  `motivoDeConsulta` text,
  `estadoDeCita` varchar(50) DEFAULT NULL,
  `idMascota` int NOT NULL,
  `idDueno` int NOT NULL,
  `idAgenda` int NOT NULL,
  `idFechaHora` int NOT NULL,
  PRIMARY KEY (`idCita`),
  KEY `idMascota` (`idMascota`),
  KEY `Cedula` (`idDueno`),
  KEY `fk_idAgenda` (`idAgenda`),
  KEY `idFechaHora` (`idFechaHora`),
  CONSTRAINT `cita_ibfk_2` FOREIGN KEY (`idMascota`) REFERENCES `mascota` (`idMascota`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cita_ibfk_3` FOREIGN KEY (`idDueno`) REFERENCES `veterinario` (`Cedula`) ON UPDATE CASCADE,
  CONSTRAINT `cita_ibfk_4` FOREIGN KEY (`idFechaHora`) REFERENCES `FechaHora` (`idFechaHora`),
  CONSTRAINT `fk_idAgenda` FOREIGN KEY (`idAgenda`) REFERENCES `Veterinario` (`agendaID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cita`
--

LOCK TABLES `Cita` WRITE;
/*!40000 ALTER TABLE `Cita` DISABLE KEYS */;
INSERT INTO `Cita` VALUES (1,NULL,'Gastritis','Pendiente',2,12345,1,6);
/*!40000 ALTER TABLE `Cita` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `actualizar_disponibilidad` AFTER INSERT ON `cita` FOR EACH ROW BEGIN
UPDATE FechaHora
SET disponible = 0
WHERE idFechaHora = NEW.idFechaHora
AND idAgenda = NEW.idAgenda;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `cita_enfermedad`
--

DROP TABLE IF EXISTS `cita_enfermedad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cita_enfermedad` (
  `idCita` int NOT NULL,
  `idEnfermedad` int NOT NULL,
  PRIMARY KEY (`idCita`,`idEnfermedad`),
  KEY `idEnfermedad` (`idEnfermedad`),
  CONSTRAINT `cita_enfermedad_ibfk_1` FOREIGN KEY (`idCita`) REFERENCES `cita` (`idCita`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cita_enfermedad_ibfk_2` FOREIGN KEY (`idEnfermedad`) REFERENCES `enfermedad` (`idEnfermedad`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita_enfermedad`
--

LOCK TABLES `cita_enfermedad` WRITE;
/*!40000 ALTER TABLE `cita_enfermedad` DISABLE KEYS */;
/*!40000 ALTER TABLE `cita_enfermedad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `idDuenio` int NOT NULL,
  `idProducto` int NOT NULL,
  PRIMARY KEY (`idDuenio`,`idProducto`),
  KEY `idProducto` (`idProducto`),
  CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`idDuenio`) REFERENCES `dueno` (`idDuenio`) ON UPDATE CASCADE,
  CONSTRAINT `compra_ibfk_2` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion_dueno`
--

DROP TABLE IF EXISTS `direccion_dueno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion_dueno` (
  `numeroDeCasa` int NOT NULL,
  `calle` varchar(255) DEFAULT NULL,
  `colonia` varchar(255) DEFAULT NULL,
  `idDuenio` int DEFAULT NULL,
  PRIMARY KEY (`numeroDeCasa`),
  KEY `idDuenio` (`idDuenio`),
  CONSTRAINT `direccion_dueno_ibfk_1` FOREIGN KEY (`idDuenio`) REFERENCES `dueno` (`idDuenio`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion_dueno`
--

LOCK TABLES `direccion_dueno` WRITE;
/*!40000 ALTER TABLE `direccion_dueno` DISABLE KEYS */;
INSERT INTO `direccion_dueno` VALUES (7,'Manuel Doblado','José Cardel',2);
/*!40000 ALTER TABLE `direccion_dueno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion_secretaria`
--

DROP TABLE IF EXISTS `direccion_secretaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion_secretaria` (
  `numeroDeCasa` int NOT NULL,
  `calle` varchar(255) DEFAULT NULL,
  `colonia` varchar(255) DEFAULT NULL,
  `numeroDeINE` int DEFAULT NULL,
  PRIMARY KEY (`numeroDeCasa`),
  KEY `numeroDeINE` (`numeroDeINE`),
  CONSTRAINT `direccion_secretaria_ibfk_1` FOREIGN KEY (`numeroDeINE`) REFERENCES `secretaria` (`numeroDeINE`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion_secretaria`
--

LOCK TABLES `direccion_secretaria` WRITE;
/*!40000 ALTER TABLE `direccion_secretaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion_secretaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion_veterinario`
--

DROP TABLE IF EXISTS `direccion_veterinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion_veterinario` (
  `idCasa` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(255) DEFAULT NULL,
  `colonia` varchar(255) DEFAULT NULL,
  `numeroDeCasa` int DEFAULT NULL,
  `cedula` int DEFAULT NULL,
  PRIMARY KEY (`idCasa`),
  KEY `cedula` (`cedula`),
  CONSTRAINT `direccion_veterinario_ibfk_1` FOREIGN KEY (`cedula`) REFERENCES `veterinario` (`Cedula`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion_veterinario`
--

LOCK TABLES `direccion_veterinario` WRITE;
/*!40000 ALTER TABLE `direccion_veterinario` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion_veterinario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Dueno`
--

DROP TABLE IF EXISTS `Dueno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Dueno` (
  `idDuenio` int NOT NULL AUTO_INCREMENT,
  `telefono` varchar(15) DEFAULT NULL,
  `nombreCompleto` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idDuenio`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dueno`
--

LOCK TABLES `Dueno` WRITE;
/*!40000 ALTER TABLE `Dueno` DISABLE KEYS */;
INSERT INTO `Dueno` VALUES (2,'2289790583','Teresita Francisca','tere@gmail.com');
/*!40000 ALTER TABLE `Dueno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enfermedad`
--

DROP TABLE IF EXISTS `enfermedad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enfermedad` (
  `idEnfermedad` int NOT NULL AUTO_INCREMENT,
  `nombreDeEnfermedad` varchar(255) DEFAULT NULL,
  `clasificacionDeEspecie` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idEnfermedad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enfermedad`
--

LOCK TABLES `enfermedad` WRITE;
/*!40000 ALTER TABLE `enfermedad` DISABLE KEYS */;
/*!40000 ALTER TABLE `enfermedad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fecha`
--

DROP TABLE IF EXISTS `Fecha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Fecha` (
  `idFecha` int NOT NULL AUTO_INCREMENT,
  `Fecha` date NOT NULL,
  `agendaId` int NOT NULL,
  PRIMARY KEY (`idFecha`),
  KEY `agendaId` (`agendaId`),
  CONSTRAINT `fecha_ibfk_1` FOREIGN KEY (`agendaId`) REFERENCES `Agenda` (`idAgenda`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fecha`
--

LOCK TABLES `Fecha` WRITE;
/*!40000 ALTER TABLE `Fecha` DISABLE KEYS */;
INSERT INTO `Fecha` VALUES (1,'2025-06-10',1),(2,'2025-06-11',1),(3,'2025-06-12',1);
/*!40000 ALTER TABLE `Fecha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FechaCita_cita`
--

DROP TABLE IF EXISTS `FechaCita_cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FechaCita_cita` (
  `idCita` int NOT NULL,
  `dia` int DEFAULT NULL,
  `mes` int DEFAULT NULL,
  `anio` int DEFAULT NULL,
  `hora` time DEFAULT NULL,
  PRIMARY KEY (`idCita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FechaCita_cita`
--

LOCK TABLES `FechaCita_cita` WRITE;
/*!40000 ALTER TABLE `FechaCita_cita` DISABLE KEYS */;
/*!40000 ALTER TABLE `FechaCita_cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FechaHora`
--

DROP TABLE IF EXISTS `FechaHora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FechaHora` (
  `idFecha` int NOT NULL,
  `idHora` int NOT NULL,
  `idAgenda` int NOT NULL,
  `disponible` tinyint DEFAULT '1',
  `idFechaHora` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idFechaHora`),
  KEY `idFecha` (`idFecha`),
  KEY `idHora` (`idHora`),
  KEY `idAgenda` (`idAgenda`),
  CONSTRAINT `fechahora_ibfk_1` FOREIGN KEY (`idFecha`) REFERENCES `Fecha` (`idFecha`) ON UPDATE CASCADE,
  CONSTRAINT `fechahora_ibfk_2` FOREIGN KEY (`idHora`) REFERENCES `Hora` (`idHora`) ON UPDATE CASCADE,
  CONSTRAINT `fechahora_ibfk_3` FOREIGN KEY (`idAgenda`) REFERENCES `Agenda` (`idAgenda`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FechaHora`
--

LOCK TABLES `FechaHora` WRITE;
/*!40000 ALTER TABLE `FechaHora` DISABLE KEYS */;
INSERT INTO `FechaHora` VALUES (1,1,1,0,1),(1,2,1,0,2),(1,3,1,1,3),(2,1,1,1,4),(2,2,1,1,5),(2,3,1,0,6),(1,1,2,0,7),(1,2,2,1,8),(1,3,2,1,9),(2,1,2,1,10),(2,2,2,1,11),(2,3,2,1,12);
/*!40000 ALTER TABLE `FechaHora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hora`
--

DROP TABLE IF EXISTS `Hora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Hora` (
  `idHora` int NOT NULL AUTO_INCREMENT,
  `hora` varchar(5) NOT NULL,
  PRIMARY KEY (`idHora`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hora`
--

LOCK TABLES `Hora` WRITE;
/*!40000 ALTER TABLE `Hora` DISABLE KEYS */;
INSERT INTO `Hora` VALUES (1,'08:00'),(2,'08:30'),(3,'09:00'),(4,'09:30'),(5,'10:00'),(6,'10:30'),(7,'11:00'),(8,'11:30'),(9,'12:00'),(10,'12:30'),(11,'13:00'),(12,'13:30'),(13,'14:00'),(14,'14:30'),(15,'15:00'),(16,'15:30'),(17,'16:00'),(18,'16:30'),(19,'17:00'),(20,'17:30'),(21,'18:00'),(22,'18:30'),(23,'19:00'),(24,'19:30'),(25,'20:00'),(26,'21:30'),(27,'22:00'),(28,'22:30'),(29,'23:00'),(30,'23:30');
/*!40000 ALTER TABLE `Hora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mascota`
--

DROP TABLE IF EXISTS `mascota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mascota` (
  `idMascota` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `fechaDeNacimiento` date DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `peso` decimal(10,2) DEFAULT NULL,
  `idDuenio` int DEFAULT NULL,
  PRIMARY KEY (`idMascota`),
  KEY `idDuenio` (`idDuenio`),
  CONSTRAINT `mascota_ibfk_1` FOREIGN KEY (`idDuenio`) REFERENCES `dueno` (`idDuenio`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mascota`
--

LOCK TABLES `mascota` WRITE;
/*!40000 ALTER TABLE `mascota` DISABLE KEYS */;
INSERT INTO `mascota` VALUES (2,'Nala','2018-09-08','Dorada',5.00,2);
/*!40000 ALTER TABLE `mascota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `existencia` int DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  `especie` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Secretaria`
--

DROP TABLE IF EXISTS `Secretaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Secretaria` (
  `numeroDeINE` int NOT NULL AUTO_INCREMENT,
  `nombreCompleto` varchar(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `nombreDeUsuario` varchar(50) DEFAULT NULL,
  `contrasenia` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`numeroDeINE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Secretaria`
--

LOCK TABLES `Secretaria` WRITE;
/*!40000 ALTER TABLE `Secretaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `Secretaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Veterinario`
--

DROP TABLE IF EXISTS `Veterinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Veterinario` (
  `Cedula` int NOT NULL,
  `nombreCompleto` varchar(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `nombreDeUsuario` varchar(50) DEFAULT NULL,
  `contrasenia` varchar(50) DEFAULT NULL,
  `agendaID` int DEFAULT NULL,
  PRIMARY KEY (`Cedula`),
  UNIQUE KEY `unique_agenda` (`agendaID`),
  CONSTRAINT `fk_veterinario_agenda` FOREIGN KEY (`agendaID`) REFERENCES `Agenda` (`idAgenda`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Veterinario`
--

LOCK TABLES `Veterinario` WRITE;
/*!40000 ALTER TABLE `Veterinario` DISABLE KEYS */;
INSERT INTO `Veterinario` VALUES (12345,'Mariano Pescado','2287761900','marpes','marpes12345',1);
/*!40000 ALTER TABLE `Veterinario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-11  5:01:43
