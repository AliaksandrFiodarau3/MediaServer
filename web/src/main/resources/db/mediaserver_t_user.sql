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
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `user_id`        INT(10) UNSIGNED               NOT NULL AUTO_INCREMENT,
  `user_login`     VARCHAR(50) CHARACTER SET big5 NOT NULL,
  `user_password`  INT(20)                        NOT NULL,
  `user_name`      VARCHAR(50)                             DEFAULT NULL,
  `user_surname`   VARCHAR(50)                             DEFAULT NULL,
  `user_email`     VARCHAR(50) CHARACTER SET big5 NOT NULL,
  `user_photo`     VARCHAR(1000) CHARACTER SET big5        DEFAULT NULL,
  `user_adminRoot` TINYINT(1)                     NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idx_ulogin` (`user_login`),
  UNIQUE KEY `idx_uemail` (`user_email`),
  UNIQUE KEY `id_UNIQUE` (`user_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 152
  DEFAULT CHARSET = utf8
  COMMENT ='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user`
  DISABLE KEYS */;
INSERT INTO `t_user` VALUES (39, 'User', -1910782086, 'Bilbo', 'Bagins', 'Bagins@hobit.shir',
                             'http://photo.cultjer.com/img/cache/width/1000/ug_photo/2014_10/80444620141013001436.jpg',
                             0), (44, 'Hobbitt', -1910782086, 'Folko', 'Begins', 'Folko@hobit.sh',
                                  'https://im0-tub-by.yandex.net/i?id=f98738b7d19a437a24f8a4dc33d0ecaa-sr&n=13', 1),
  (68, 'Admin', -1910782086, 'Gendalf', 'Grey', 'Gendalf@hobit.sh',
   'https://im0-tub-by.yandex.net/i?id=085b057098737490776d012b50f098b6-l&n=13', 1),
  (125, 'Legolas', -1910782086, 'Green', 'Legolas', 'Tranduil@elf.li',
   'https://upload.wikimedia.org/wikipedia/ru/c/c0/Legolas.jpg', 0),
  (151, 'qwerty', 1820099449, 'qwerty', 'qwqrwfe', 'qwerty@qw.qw',
   'https://pp.userapi.com/c4536/u105256651/a_6914fd2b.jpg', 0);
/*!40000 ALTER TABLE `t_user`
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
