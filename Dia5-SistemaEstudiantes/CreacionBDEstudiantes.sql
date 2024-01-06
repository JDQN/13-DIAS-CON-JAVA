-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: estudiantes_db
-- ------------------------------------------------------
-- Server version	8.0.32

--
-- Table structure for table `estudiante`
--

-- DROP TABLE IF EXISTS `estudiante`;

CREATE TABLE `estudiante` (
  `idEstudiante` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEstudiante`)
)

UPDATE estudiante SET nombre='Camila', apellido='Carnivel', telefono='123654789', email='cami@gmail.com' WHERE idEstudiante = 1;

INSERT INTO `estudiante` VALUES (1,'Juan Carlos','Perez','55','jcarlos@mail.com'),(2,'Laura','Gomez','55884477','laura@mail.com'),(3,'Karla','Jimenez','554422','karla@mail.com');

