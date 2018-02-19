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
-- Table structure for table `t_order_song`
--

DROP TABLE IF EXISTS `t_order_song`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_song` (
  `order_song_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `song_id`       INT(10) UNSIGNED NOT NULL,
  `order_id`      INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`order_song_id`, `song_id`, `order_id`),
  KEY `fk_order_has_order_song_idx` (`order_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 57
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_song`
--

LOCK TABLES `t_order_song` WRITE;
/*!40000 ALTER TABLE `t_order_song`
  DISABLE KEYS */;
INSERT INTO `t_order_song`
VALUES (38, 56, 118), (40, 13, 120), (43, 58, 121), (44, 58, 122), (45, 58, 123), (47, 56, 124), (48, 14, 124),
  (49, 12, 124), (50, 14, 125), (53, 13, 127), (54, 12, 127), (55, 59, 127), (56, 13, 128);
/*!40000 ALTER TABLE `t_order_song`
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

-- Dump completed on 2018-02-04 19:22:31
