-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: phone_station
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (11,'Hello World','Hello everybody, we\'d like to introduce you our brand new phone station. It\'s great opportunity to make long partnership. We hope you\'d like services we provide.','2017-01-31 17:00:13'),(12,'Nice to hear you','How about internet 24/7 or great amount of talking minutes out of the local network for democratic prices? Choose tariff that suits you best. Come on. Do it. Now !','2017-01-31 17:02:57'),(13,'Celebrities choose us !','Have you know that Henry Rollins and Nick Cave are great fans of our phone station? It\'s true, they both was standing at the very beginning of out company.','2017-01-31 17:04:41'),(14,'Super internet','Our new tariff \"Super internet\" is here to blow you mind. Check this out today!','2017-01-31 17:05:53'),(15,'Слава Україні!','Ми раді повідомити вам, що ви завжди зможете скористатися з української локалізації нашого сайтую.','2017-02-01 01:39:15'),(16,'Ho ho ho','There are three ho, take it or leave it.','2017-02-01 03:53:03');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `price` double NOT NULL,
  `date` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_payments_users_idx` (`user_id`),
  CONSTRAINT `fk_payments_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (10,'Balance refill','Balance refill from card: 1234-4321-1234-4321',500,'2017-01-14 20:35:07',27),(11,'Inkognito service','Payment for service provided',-130,'2017-01-14 20:36:23',27),(28,'Balance refill','Balance refill from card: 1111-1111-1111-1111',500,'2017-01-27 22:58:03',1),(29,'Inkognito service','Payment for service provided',-130,'2017-01-27 23:02:04',1),(30,'Jokes and stories service','Payment for service provided',-20,'2017-01-27 23:02:17',1),(31,'Endless social networks service','Payment for service provided',-70,'2017-01-27 23:06:59',1),(32,'Exclusive number service','Payment for service provided',-200,'2017-01-27 23:15:06',1),(33,'Balance refill','Balance refill from card: 1234-1234-1234-1234',100,'2017-01-27 23:15:38',1),(34,'Balance refill','Balance refill from card: 3242-4323-4324-2342',500,'2017-02-01 01:35:23',1),(35,'Awesome rington service','Payment for service provided',-50,'2017-02-01 01:36:03',1);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (4,'Additional number','Get an additional number',100),(5,'Exclusive number','Get an exclussive number',200),(6,'Awesome rington','Get random awesome ringtone for your phone',50),(7,'Inkognito','Users that receives your calls will not see your phone number',130),(8,'Jokes and stories','Get new story or joke everyday !',20),(10,'Endless social networks','Now you can get in touch with you friends in social network whenever you want. Awesome',70);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tariffs`
--

DROP TABLE IF EXISTS `tariffs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tariffs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `cost_per_month` double NOT NULL,
  `minutes_of_calls_in_network` int(11) NOT NULL,
  `minutes_of_calls_out_of_network` int(11) NOT NULL,
  `internet_mb` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariffs`
--

LOCK TABLES `tariffs` WRITE;
/*!40000 ALTER TABLE `tariffs` DISABLE KEYS */;
INSERT INTO `tariffs` VALUES (2,'Tariff for poor people','Bad tariff for poor people',50,500,100,1000),(3,'Just OK','Average tariff for your average needs',50,-1,500,7000),(4,'Classy ','For class peoples',200,-1,-1,-1),(5,'Super internet','Be online 24 hours 7 days a week !',70,100,100,10000);
/*!40000 ALTER TABLE `tariffs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `balance` double NOT NULL,
  `tariff_id` int(11) DEFAULT NULL,
  `user_role` varchar(30) NOT NULL,
  `username` varchar(45) NOT NULL,
  `additional_phone` varchar(45) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `additional-phone_UNIQUE` (`additional_phone`),
  KEY `fk_subscriber_tariff_idx` (`tariff_id`),
  CONSTRAINT `fk_users_tariffs` FOREIGN KEY (`tariff_id`) REFERENCES `tariffs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Yuriy','Kaplun','380673255733','dalglish',630,2,'USER','yuka',NULL,'2017-01-11'),(25,'Yuriy','Kaplun','380999666333','admin',0,NULL,'ADMIN','Admin',NULL,NULL),(27,'Петро','П\'яточкін','380345678933','123',370,4,'USER','Петрик',NULL,'2017-01-14'),(29,'Jimmy ','Page','380673233333','dalglish',0,NULL,'USER','GUITAR_HERO',NULL,'2017-01-28'),(30,'John','Frusciante','380673255666','a',0,NULL,'USER','fru',NULL,'2017-01-28');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_services`
--

DROP TABLE IF EXISTS `users_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_pair` (`user_id`,`service_id`),
  KEY `fk_users_idx` (`user_id`),
  KEY `fk_services_idx` (`service_id`),
  CONSTRAINT `fk_services` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_services`
--

LOCK TABLES `users_services` WRITE;
/*!40000 ALTER TABLE `users_services` DISABLE KEYS */;
INSERT INTO `users_services` VALUES (25,1,6),(22,1,7),(23,1,8),(24,1,10),(16,27,7);
/*!40000 ALTER TABLE `users_services` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-01 12:35:23
