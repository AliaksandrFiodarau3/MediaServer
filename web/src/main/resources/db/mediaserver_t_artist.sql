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
-- Table structure for table `t_artist`
--

DROP TABLE IF EXISTS `t_artist`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_artist` (
  `artist_id`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `genre_id`           INT(10) UNSIGNED NOT NULL,
  `artist_title`       VARCHAR(50)      NOT NULL,
  `artist_description` VARCHAR(10000)            DEFAULT NULL,
  `artist_image`       VARCHAR(1000)             DEFAULT NULL,
  PRIMARY KEY (`artist_id`, `genre_id`),
  UNIQUE KEY `idx_aid` (`artist_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 22
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_artist`
--

LOCK TABLES `t_artist` WRITE;
/*!40000 ALTER TABLE `t_artist`
  DISABLE KEYS */;
INSERT INTO `t_artist` VALUES (1, 1, 'Metallica',
                               'Metallica (читается как Метáллика) — американская метал-группа, образованная в 1981 году. Metallica оказала большое влияние на развитие метала и входит (наряду с такими группами как Slayer, Megadeth и Anthrax) в «большую четвёрку трэш-метала». Альбомы Metallica были проданы в общей сложности в количестве более 110 миллионов экземпляров по всему миру, что делает её одним из самых коммерчески успешных металлических коллективов. В 2011 году один из крупнейших журналов о метал-музыке Kerrang! в июньском номере признал Metallica лучшей метал-группой последних 30 лет.',
                               'https://avatars.yandex.net/get-music-content/49707/c44f1a1e.p.3121/s200x200'),
  (2, 2, 'Nirvana',
   'Nirvana — американская рок-группа, созданная вокалистом и гитаристом Куртом Кобейном и басистом Кристом Новоселичем в Абердине, штат Вашингтон, в 1987 году. В составе коллектива сменились несколько барабанщиков; дольше всех с группой играл ударник Дэйв Грол, присоединившийся к Кобейну и Новоселичу в 1990 году. В 1989 году Nirvana стала частью сиэтлской музыкальной сцены, выпустив на инди-лейбле Sub Pop дебютный альбом Bleach. После подписания контракта с крупным лейблом DGC Records. Nirvana приобрела неожиданный успех с песней «Smells Like Teen Spirit» из своего второго альбома Nevermind, выпущенного в 1991 году. Впоследствии Nirvana вошла в музыкальный мейнстрим, популяризовав поджанр альтернативного рока, названный гранжем. Курт Кобейн оказался в глазах СМИ не просто музыкантом, а «голосом поколения», а Nirvana стала флагманом «поколения Х». В 1993 году вышел третий и последний студийный альбом группы, In Utero, композиции которого в музыкальном плане сильно отличались от предыдущих работ коллектива. \\nНедолгая, но яркая история группы прервалась в связи со смертью Курта Кобейна 5 апреля 1994 года, но в последующие годы известность команды лишь росла. В 2002 году незавершённая демозапись песни «You Know You’re Right», над которой группа работала незадолго до смерти Кобейна, заняла первые строчки мировых хит-парадов. Со времени выхода дебютного альбома записи Nirvana были проданы в количестве 75 миллионов экземпляров по всему миру, и в 25 миллионов копий в США.',
   'https://avatars.yandex.net/get-music-content/32236/045190cb.p.9262/s400x400'), (3, 3, 'Black Flag',
                                                                                    'Black Flag ([blæk flæg], рус. чёрный флаг или чёрное знамя) — хардкор-панк-группа из Калифорнии, США, существовавшая в период с 1976 по 1986 годы. Изначально имела название «Panic». Состав коллектива менялся на протяжении всего творческого пути, единственным постоянным его участником оставался гитарист Грег Гинн. Оказали огромное влияние на современную панк-культуру, являясь основателями нового музыкального направления — хардкор-панка. В противовес коммерческой музыке, которая получала поддержку крупных звукозаписывающих компаний, освещение во всевозможных журналах и музыкальных радиостанциях, Black Flag принципиально отказывались от всего этого, таким образом заложив основу новой панковской субкультуры DIY (Do It Yourself), а также основали свой собственный лейбл SST Records после отказа от выпуска их пластинок. Музыканты не зависели ни от одной коммерческой организации. Также группа оказала влияние на более поздние коллективы играющие метал и гранж, вдохновив своим творчеством такие известные коллективы как Nirvana, Melvins, Soundgarden, Slayer, Eyehategod и др.',
                                                                                    ' http://www.spirit-of-rock.com/les%20goupes/B/Black%20Flag/pics/logo.jpg'),
  (20, 14, 'Газманов', 'Это он', 'https://im0-tub-by.yandex.net/i?id=8c65968053172f75f19534aee8e59114-l&n=13'),
  (21, 15, 'Bob Marley', 'Bob Marley', 'https://im0-tub-by.yandex.net/i?id=c15162653aa05a42b76e52fbbace3132&n=13');
/*!40000 ALTER TABLE `t_artist`
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
