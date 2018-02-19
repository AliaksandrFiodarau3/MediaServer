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
-- Table structure for table `t_song`
--

DROP TABLE IF EXISTS `t_song`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_song` (
  `song_id`       INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `album_id`      INT(10) UNSIGNED NOT NULL,
  `song_title`    VARCHAR(50)      NOT NULL,
  `song_duration` TIME(3)                   DEFAULT NULL,
  `song_price`    DOUBLE           NOT NULL,
  PRIMARY KEY (`song_id`, `album_id`),
  UNIQUE KEY `idx_sid` (`song_id`),
  KEY `fk_song_album_idx` (`album_id`),
  CONSTRAINT `fk_song_album` FOREIGN KEY (`album_id`) REFERENCES `t_album` (`album_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 61
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_song`
--

LOCK TABLES `t_song` WRITE;
/*!40000 ALTER TABLE `t_song`
  DISABLE KEYS */;
INSERT INTO `t_song` VALUES (12, 18, 'Can\'t Decide', '00:05:00.000', 55), (13, 13, 'Breed', '00:03:04.000', 1),
  (14, 14, 'Dump', '00:02:29.000', 1), (15, 15, 'Cure', '00:04:54.000', 2), (16, 16, 'Fuel', '00:04:29.000', 2),
  (56, 26, 'Сделано в СССР', '00:03:46.000', 1), (59, 27, 'My War', '00:03:46.000', 1),
  (60, 28, 'papapp', '00:03:04.000', 545);
/*!40000 ALTER TABLE `t_song`
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
