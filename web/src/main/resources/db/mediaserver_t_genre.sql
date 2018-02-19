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
-- Table structure for table `t_genre`
--

DROP TABLE IF EXISTS `t_genre`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_genre` (
  `genre_id`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `genre_title`       VARCHAR(50)      NOT NULL,
  `genre_description` VARCHAR(1000)             DEFAULT NULL,
  `genre_image`       VARCHAR(10000)            DEFAULT NULL,
  PRIMARY KEY (`genre_id`),
  UNIQUE KEY `idx_gid` (`genre_id`),
  UNIQUE KEY `idx_gtitle` (`genre_title`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_genre`
--

LOCK TABLES `t_genre` WRITE;
/*!40000 ALTER TABLE `t_genre`
  DISABLE KEYS */;
INSERT INTO `t_genre` VALUES (2, 'Grunge',
                              'Гранж (англ. Grunge, букв. грязь), известен также как сиэтлский саунд и подстиль в \n    альтернативном роке, развившийс¤ из хардкор-панка в середине 1980-х годов в американском \n    штате Вашингтон, прежде всего в Сиэтле. По сравнению с другими направлениями \n    рок-музыки, эстетика гранжа была подчеркнуто демократичной и безыскусной, музыканты \n    появлялись на публике в обычной, часто помятой или поношенной одежде и избегали театральности.',
                              'https://pp.userapi.com/c840229/v840229468/3bdb8/1jtgv5qxVwY.jpg'), (3, 'Hardcore-punk',
                                                                                                   'Хардкор-панк (англ. Hardcore-punk) это музыкальный жанр, появившийс в США и Великобритании в \n    конце 1970-х годов в результате обособлени¤ от панк-рока. Звук, по сравнению с традиционным \n    звучанием панк-рока, стал более быстрым, тяжёлым, композиции стали более короткими.  роме того,\n    отличительная черта хардкор-панка и более размытые, синкопированные риффы по сравнению с \n    панк-роком.',
                                                                                                   'https://pp.userapi.com/c840229/v840229468/3bdbf/pu4T8AirsLg.jpg'),
  (14, 'Поп', 'Попса', 'https://im0-tub-by.yandex.net/i?id=0aa1bc1b55f6a759b4e9cd7d168a159b-l&n=13'),
  (15, 'Reggae', 'reggae', 'https://im0-tub-by.yandex.net/i?id=c75528cac394fa011bb16688a1c7283b-l&n=13');
/*!40000 ALTER TABLE `t_genre`
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
