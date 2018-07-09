CREATE DATABASE  IF NOT EXISTS `dbavaliacao` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbavaliacao`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: dbavaliacao
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CPF` varchar(11) NOT NULL,
  `NOME` varchar(100) NOT NULL,
  `DATANASCIMENTO` datetime NOT NULL,
  `CELULAR` varchar(11) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_CPF` (`CPF`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IDESCOLA` int(11) NOT NULL,
  `DESCRICAO` varchar(45) NOT NULL,
  `CARGAHORARIA` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ESCOLA_CURSO_idx` (`IDESCOLA`),
  CONSTRAINT `FK_ESCOLA_CURSO` FOREIGN KEY (`IDESCOLA`) REFERENCES `escola` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `escola`
--

DROP TABLE IF EXISTS `escola`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `escola` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CNPJ` varchar(14) NOT NULL,
  `RAZAOSOCIAL` varchar(45) NOT NULL,
  `ENDERECO` varchar(45) NOT NULL,
  `DATAFUNDACAO` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_CNPJ` (`CNPJ`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nota`
--

DROP TABLE IF EXISTS `nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nota` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOTA` float DEFAULT NULL,
  `IDCURSO` int(11) NOT NULL,
  `IDALUNO` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_IDCURSO_IDALUNO` (`IDCURSO`,`IDALUNO`),
  KEY `FK_ALUNO_NOTA_idx` (`IDALUNO`),
  KEY `FK_CURSO_NOTA_idx` (`IDCURSO`),
  CONSTRAINT `FK_ALUNO_NOTA` FOREIGN KEY (`IDALUNO`) REFERENCES `aluno` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_CURSO_NOTA` FOREIGN KEY (`IDCURSO`) REFERENCES `curso` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-01 14:22:00
