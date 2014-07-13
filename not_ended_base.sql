CREATE DATABASE  IF NOT EXISTS `owl_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `owl_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: owl_db
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `Author`
--

DROP TABLE IF EXISTS `Author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET latin1 NOT NULL,
  `surname` varchar(50) CHARACTER SET latin1 NOT NULL,
  `fathername` varchar(50) CHARACTER SET latin1 NOT NULL,
  `about_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `about_idx` (`about_id`),
  CONSTRAINT `authAbout` FOREIGN KEY (`about_id`) REFERENCES `AuthorAbout` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Author`
--

LOCK TABLES `Author` WRITE;
/*!40000 ALTER TABLE `Author` DISABLE KEYS */;
/*!40000 ALTER TABLE `Author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AuthorAbout`
--

DROP TABLE IF EXISTS `AuthorAbout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AuthorAbout` (
  `id` int(11) NOT NULL,
  `about` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AuthorAbout`
--

LOCK TABLES `AuthorAbout` WRITE;
/*!40000 ALTER TABLE `AuthorAbout` DISABLE KEYS */;
/*!40000 ALTER TABLE `AuthorAbout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author_id` int(11) DEFAULT NULL,
  `path_id` int(11) DEFAULT NULL,
  `statistic_id` int(11) DEFAULT NULL,
  `edition_id` int(11) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `slogan` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `about_id` int(11) DEFAULT NULL,
  `name` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `path_idx` (`path_id`),
  KEY `author_idx` (`author_id`),
  KEY `statistic_idx` (`statistic_id`),
  KEY `about_idx` (`about_id`),
  KEY `edition_idx` (`edition_id`),
  CONSTRAINT `about` FOREIGN KEY (`about_id`) REFERENCES `BookAbout` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `author` FOREIGN KEY (`author_id`) REFERENCES `Author` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `edition` FOREIGN KEY (`edition_id`) REFERENCES `Edition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `path` FOREIGN KEY (`path_id`) REFERENCES `File` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `statistic` FOREIGN KEY (`statistic_id`) REFERENCES `Statistic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BookAbout`
--

DROP TABLE IF EXISTS `BookAbout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BookAbout` (
  `id` int(11) NOT NULL,
  `about` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BookAbout`
--

LOCK TABLES `BookAbout` WRITE;
/*!40000 ALTER TABLE `BookAbout` DISABLE KEYS */;
/*!40000 ALTER TABLE `BookAbout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Edition`
--

DROP TABLE IF EXISTS `Edition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Edition` (
  `id` int(11) NOT NULL,
  `where` varchar(100) DEFAULT NULL,
  `when` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Edition`
--

LOCK TABLES `Edition` WRITE;
/*!40000 ALTER TABLE `Edition` DISABLE KEYS */;
/*!40000 ALTER TABLE `Edition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `File`
--

DROP TABLE IF EXISTS `File`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `File` (
  `id` int(11) NOT NULL,
  `path` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `File`
--

LOCK TABLES `File` WRITE;
/*!40000 ALTER TABLE `File` DISABLE KEYS */;
/*!40000 ALTER TABLE `File` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Statistic`
--

DROP TABLE IF EXISTS `Statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Statistic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qod` int(11) DEFAULT NULL,
  `qov` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Statistic`
--

LOCK TABLES `Statistic` WRITE;
/*!40000 ALTER TABLE `Statistic` DISABLE KEYS */;
/*!40000 ALTER TABLE `Statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `pass` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'root','root');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-13  3:13:57
