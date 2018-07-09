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
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,'11111111111','Aluno 1 de Sobrenome Padrão','1991-01-01 00:00:00','11911111111','aluno1@email.com'),(2,'22222222222','Aluno 2 de Sobrenome Português','1992-02-02 00:00:00','11922222222','aluno2.sobrenome@email.com.br'),(3,'33333333333','Aluno 3 de Sobrenome Diferente','1993-03-03 00:00:00','11933333333','aluno3@email.com'),(5,'44444444444','Aluno 4 de Sobrenome Padrão','1994-04-04 00:00:00','11944444444','aluno4padrao@email.com'),(6,'55555555555','Aluno 5 de Sobrenome Italiano','1995-05-05 00:00:00','11955555555','aluno5.ita@email.com.br'),(7,'66666666666','Aluno 6 de Sobrenome Belga','1996-06-06 00:00:00','11966666666','aluno6@email.com'),(8,'77777777777','Aluno 7 de Sobrenome Japonês','1997-07-07 00:00:00','11977777777','aluno7@email.com'),(9,'88888888888','Aluno 8 de Sobrenome Alemão','1998-08-08 00:00:00','11988888888','aluno8@email.com.br'),(10,'99999999999','Aluno 9 de Sobrenome Chinês','1999-09-09 00:00:00','11999999999','aluno9@email.com');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (19,45,'Curso Profissionalizante 1',10),(20,45,'Curso Profissionalizante 2',20),(21,45,'Curso Profissionalizante 3',30),(22,45,'Curso Profissionalizante 4',40);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `escola`
--

LOCK TABLES `escola` WRITE;
/*!40000 ALTER TABLE `escola` DISABLE KEYS */;
INSERT INTO `escola` VALUES (45,'00111222000199','ABC Cursos Profissionalizantes','Av. Lins de Vasconcelos, 101','2009-07-10 00:00:00');
/*!40000 ALTER TABLE `escola` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nota`
--

LOCK TABLES `nota` WRITE;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
INSERT INTO `nota` VALUES (1,5,19,1),(2,2,21,1),(3,NULL,20,2),(4,NULL,20,3),(5,8,20,5),(6,NULL,22,6),(7,7.5,22,7),(8,NULL,22,1),(9,NULL,22,2),(10,NULL,19,2),(11,NULL,19,3),(12,NULL,19,5),(13,1.5,19,9),(14,NULL,19,7),(15,NULL,19,6),(16,NULL,20,6);
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-01 14:22:34
