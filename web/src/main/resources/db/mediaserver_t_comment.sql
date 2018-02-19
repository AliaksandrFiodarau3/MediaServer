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
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment` (
  `comment_id`   INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id`      INT(10) UNSIGNED NOT NULL,
  `song_id`      INT(10) UNSIGNED NOT NULL,
  `comment_text` VARCHAR(5000)             DEFAULT NULL,
  `comment_time` TIME                      DEFAULT NULL,
  `comment_date` DATE                      DEFAULT NULL,
  PRIMARY KEY (`comment_id`, `user_id`, `song_id`),
  UNIQUE KEY `idx_cid` (`comment_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment`
  DISABLE KEYS */;
INSERT INTO `t_comment`
VALUES (1, 39, 12, 'Ниче так!', '22:05:15', '2017-08-19'), (2, 39, 15, 'Это Хит!', '22:06:20', '2017-08-19'),
  (3, 39, 15, 'Спасибо! Купил))', '22:10:30', '2017-09-19'), (4, 39, 12, 'Куплю!', '21:40:07', '2017-10-22'),
  (5, 39, 56, 'Классика)', '17:20:52', '2017-10-23'), (6, 39, 13, '+100500))', '17:21:47', '2017-10-23'),
  (7, 39, 56, 'Даже Больше))', '13:48:12', '2017-10-24'), (8, 39, 56, 'Даже Больше))', '13:48:18', '2017-10-24'),
  (9, 151, 13, 'qdwfwe', '16:35:08', '2017-10-25'), (10, 151, 13, 'qweqweqwwq', '16:35:33', '2017-10-25'),
  (11, 151, 13, 'dhgjyjrjy', '16:36:50', '2017-10-25');
/*!40000 ALTER TABLE `t_comment`
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
