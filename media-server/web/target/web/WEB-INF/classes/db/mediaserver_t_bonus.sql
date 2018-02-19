CREATE DATABASE IF NOT EXISTS `mediaserver` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mediaserver`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mediaserver
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `t_bonus`
--

DROP TABLE IF EXISTS `t_bonus`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_bonus` (
  `bonus_id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `bonus_title`       VARCHAR(50) NOT NULL,
  `bonus_description` VARCHAR(1000)        DEFAULT NULL,
  `bonus_discount`    INT(10) UNSIGNED     DEFAULT NULL,
  `bonus_code`        VARCHAR(45) NOT NULL,
  PRIMARY KEY (`bonus_id`),
  UNIQUE KEY `idx_bid` (`bonus_id`),
  UNIQUE KEY `idx_btitle` (`bonus_title`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_bonus`
--

LOCK TABLES `t_bonus` WRITE;
/*!40000 ALTER TABLE `t_bonus`
  DISABLE KEYS */;
INSERT INTO `t_bonus` VALUES (1, 'Открытие магазина', 'В честь открытия магазина мы дарим вам скидку 10%.', 10, 'Open'),
  (35, 'Расспродажа', 'Распродажа 15%', 15, 'SALE'), (36, 'СИГИ', 'оа', 200, 'тратр'), (37, 'СИГИ2', 'оа', 0, 'ццц');
/*!40000 ALTER TABLE `t_bonus`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2018-02-04 19:22:32
