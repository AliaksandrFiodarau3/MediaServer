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
-- Table structure for table `t_album`
--

DROP TABLE IF EXISTS `t_album`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_album` (
  `album_id`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `artist_id`         INT(10) UNSIGNED NOT NULL,
  `album_title`       VARCHAR(50)      NOT NULL,
  `album_year`        INT(11)                   DEFAULT NULL,
  `album_description` VARCHAR(1000)             DEFAULT NULL,
  `album_image`       VARCHAR(1000)             DEFAULT NULL,
  PRIMARY KEY (`album_id`, `artist_id`),
  UNIQUE KEY `album_id_UNIQUE` (`album_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 29
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_album`
--

LOCK TABLES `t_album` WRITE;
/*!40000 ALTER TABLE `t_album`
  DISABLE KEYS */;
INSERT INTO `t_album` VALUES (13, 2, 'Nevermind', 1991,
                              'Nevermind — второй и самый коммерчески успешный альбом американской гранж-группы Nirvana[2]. Был выпущен 24 сентября 1991 года на лейбле DGC Records, продюсером записи выступил Бутч Виг. Создавая этот альбом, Курт Кобейн стремился вывести музыку за пределы ограничений гранж-сцены Сиэтла, находясь под влиянием таких групп как Pixies и используя их динамику.',
                              'https://upload.wikimedia.org/wikipedia/commons/thumb/1/19/Nirvana_around_1992.jpg/250px-Nirvana_around_1992.jpg'),
  (14, 2, 'In Utero', 1993, 'In Utero (с лат. — «В утробе»[1]) — третий и последний студийный альбом американской гранж-группы Nirvana, выпущенный 13 сентября 1993 года на лейбле DGC Records.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkJkydu97dyjdo0POWQlrsXNju_Uheyh6O2PD4B5ZaejgmHm57ig'),
  (15, 1, 'Load', 1996, 'Load (рус. «Заряд») — шестой студийный альбом метал-группы Metallica, вышедший 4 июня 1996 года на лейбле Elektra Records. За первую неделю было продано 680 000 копий (первое место среди всех альбомов группы по объёму продаж за неделю). Load дебютировал и находился в течение четырёх недель подряд на первой строчке Billboard 200. Тираж альбома составил более пяти миллионов копий по всему миру. Диск получил платиновый статус пять раз.', 'https://up-1.cdn-fullscreendirect.com/production/photos/7549/large/20150806_195123_7549_752578.jpeg'),
  (16, 1, 'ReLoad', 1997, 'ReLoad (рус. «Перезарядка») — седьмой студийный альбом американской хэви-метал группы Metallica, вышедший в 1997 году.', 'https://up-1.cdn-fullscreendirect.com/production/photos/7549/medium/20150806_195123_7549_752579.jpeg'),
  (17, 3, 'Damaged', 1981, 'Damaged — дебютный альбом американской хардкор-панк-группы Black Flag, вышедший 5 декабря 1981 года на собственном лейбле группы SST Records. В 2003 году журнал Rolling Stone поставил его на 340 место списка «500 величайших альбомов всех времён»[1].', 'http://68.media.tumblr.com/tumblr_m0k14whUSw1r5gmoeo1_r1_1280.jpg'),
  (18, 3, 'My War', 1984, 'My War — второй студийный альбом американской хардкор-панк группы Black Flag, издан в 1984 году.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiYIdYWCL6UjeNQXvWMAI6UDuxjKlN_f18JvwXhHmkaEnWG5k6'),
  (21, 1, 'Ride the Lightning', 1984, 'Ride the Lightning (Ñ Ð°Ð½Ð³Ð».âââÂ«ÐÑÐµÐ´Ð»Ð°Ð¹ Ð¼Ð¾Ð»Ð½Ð¸ÑÂ») â Ð²ÑÐ¾ÑÐ¾Ð¹ ÑÑÑÐ´Ð¸Ð¹Ð½ÑÐ¹ Ð°Ð»ÑÐ±Ð¾Ð¼ ÑÑÑÑ-Ð¼ÐµÑÐ°Ð» Ð³ÑÑÐ¿Ð¿Ñ Metallica, Ð²ÑÑÐµÐ´ÑÐ¸Ð¹ 27 Ð¸ÑÐ»Ñ 1984 Ð³Ð¾Ð´Ð° Ð½Ð° Ð»ÐµÐ¹Ð±Ð»Ðµ Megaforce Records Ð¸ Ð¿ÐµÑÐµÐ¸Ð·Ð´Ð°Ð½Ð½ÑÐ¹ Ð»ÐµÐ¹Ð±Ð»Ð¾Ð¼ Elektra Records 16 Ð½Ð¾ÑÐ±ÑÑ 1984 Ð³Ð¾Ð´Ð°. ÐÐ° Ð¾Ð±Ð»Ð¾Ð¶ÐºÐµ Ð¸Ð·Ð¾Ð±ÑÐ°Ð¶ÑÐ½ ÑÐ»ÐµÐºÑÑÐ¸ÑÐµÑÐºÐ¸Ð¹ ÑÑÑÐ», Ð¾ÐºÑÑÐ¶ÑÐ½Ð½ÑÐ¹ ÑÐ°Ð·ÑÑÐ´Ð°Ð¼Ð¸ Ð¼Ð¾Ð»Ð½Ð¸Ð¹, Ð¸ÑÑÐ¾Ð´ÑÑÐ¸Ð¼Ð¸ Ð¸Ð· Ð»Ð¾Ð³Ð¾ÑÐ¸Ð¿Ð° Ð³ÑÑÐ¿Ð¿Ñ. ÐÐ°Ð·Ð²Ð°Ð½Ð¸Ðµ Ð±ÑÐ»Ð¾ Ð·Ð°Ð¸Ð¼ÑÑÐ²Ð¾Ð²Ð°Ð½Ð½Ð¾ Ð¸Ð· ÑÐ¾Ð¼Ð°Ð½Ð° Ð¡ÑÐ¸Ð²ÐµÐ½Ð° ÐÐ¸Ð½Ð³Ð° Â«ÐÑÐ¾ÑÐ¸Ð²Ð¾ÑÑÐ¾ÑÐ½Ð¸ÐµÂ»; Ð²ÑÑÐ°Ð¶ÐµÐ½Ð¸Ðµ Â«Ð¾ÑÐµÐ´Ð»Ð°ÑÑ Ð¼Ð¾Ð»Ð½Ð¸ÑÂ» Ð¾Ð·Ð½Ð°ÑÐ°ÐµÑ Â«Ð±ÑÑÑ ÐºÐ°Ð·Ð½ÑÐ½Ð½ÑÐ¼ Ð½Ð° ÑÐ»ÐµÐºÑÑÐ¸ÑÐµÑÐºÐ¾Ð¼ ÑÑÑÐ»ÐµÂ».', 'https://upload.wikimedia.org/wikipedia/ru/thumb/a/a6/%D0%9E%D0%B1%D0%BB%D0%BE%D0%B6%D0%BA%D0%B0_%D0%B2%D1%82%D0%BE%D1%80%D0%BE%D0%B3%D0%BE_%D1%81%D1%82%D1%83%D0%B4%D0%B8%D0%B9%D0%BD%D0%BE%D0%B3%D0%BE_%D0%B0%D0%BB%D1%8C%D0%B1%D0%BE%D0%BC%D0%B0_Metallica.jpg/330px-%D0%9E%D0%B1%D0%BB%D0%BE%D0%B6%D0%BA%D0%B0_%D0%B2%D1%82%D0%BE%D1%80%D0%BE%D0%B3%D0%BE_%D1%81%D1%82%D1%83%D0%B4%D0%B8%D0%B9%D0%BD%D0%BE%D0%B3%D0%BE_%D0%B0%D0%BB%D1%8C%D0%B1%D0%BE%D0%BC%D0%B0_Metallica.jpg'),
  (25, 15, 'Ясные Дни', 1234, 'Ясные дни', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/19/Nirvana_around_1992.jpg/250px-Nirvana_around_1992.jpg'),
  (26, 20, 'Сделано в СССР', 2005, 'Сделано в СССР', 'http://civility.ru/wp-content/uploads/2014/04/Oleg-Gazmanov-4.jpg'),
  (27, 21, 'All the hits', 1990, 'All the hits', 'https://im0-tub-by.yandex.net/i?id=ffb7f3c4f090873a3bc89ba35bdd54f3-l&n=13'),
  (28, 21, 'qwert', 1998, 'asdf', 'https://pp.userapi.com/c4536/u105256651/a_6914fd2b.jpg');
/*!40000 ALTER TABLE `t_album`
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
