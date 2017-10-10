-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: testing_system
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (112,'Україна');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_options`
--

DROP TABLE IF EXISTS `question_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_options` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `is_right` tinyint(1) NOT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_options_1_idx` (`question_id`),
  CONSTRAINT `fk_question_options_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_options`
--

LOCK TABLES `question_options` WRITE;
/*!40000 ALTER TABLE `question_options` DISABLE KEYS */;
INSERT INTO `question_options` VALUES (1,'FirstOption',1,1),(2,'Second',0,1),(3,'FO',1,2),(4,'FO',1,3),(5,'FO',1,4),(6,'FO',0,4),(7,'asdf',0,7),(8,'sadf',0,7),(9,'asdfasf',1,8),(10,'asdf',0,8),(11,'asdf',0,8),(12,'asdf',0,8),(13,'safsd',1,9),(14,'fdfdf',0,9),(15,'fdfdfd',1,9),(16,'asdf',1,10),(17,'asdf',0,10),(18,'sadf',1,10),(19,'asdfs',1,11),(20,'asdf',1,11),(21,'fdfd',0,11),(22,'fdfd',0,11),(23,'Київ',1,12),(24,'Харків',0,12),(25,'Мінськ',0,12),(26,'Хрущов',0,13),(27,'Кравчук',1,13),(28,'Ющенко',1,13),(29,'Сталін',0,13),(30,'Шепетівка',1,14),(31,'Чернівці',1,14),(32,'Гомель',0,14),(33,'Брест',0,14),(34,'28',0,15),(35,'24',1,15),(36,'25',0,15),(37,'27',0,15),(38,'23',0,15),(39,'фівафіва',1,16),(40,'фівафіва',0,16),(41,'фівафіва',0,16),(42,'asdfasdf',1,17);
/*!40000 ALTER TABLE `question_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text,
  `mark` decimal(4,2) NOT NULL,
  `order_number` int(11) NOT NULL,
  `test_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_questions_1_idx` (`test_id`),
  CONSTRAINT `fk_questions_1` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'FirstQuestion',1.00,1,1),(2,'SecondQuestion',1.00,2,1),(3,'ThirdQuestion',1.00,3,1),(4,'FirstQuestion',1.00,1,2),(5,'SecondQuestion',1.00,2,2),(7,'asfdsd',1.00,1,5),(8,'asdf',1.00,1,6),(9,'asdf',2.00,2,6),(10,'asdfsadfddf',1.00,1,8),(11,'fadsfas',2.00,2,8),(12,'Столиця України ?',1.00,1,9),(13,'Хто з нищеперелічених осіб були президентами україни ?',2.00,2,9),(14,'Які міста розташовані на території України ?',2.00,3,9),(15,'Скільки адміністративних областей налічується в Україні ?',1.00,4,9),(16,'ТАТАТАТАТАК',2.00,1,10),(17,'asdfasdf',1.00,1,11);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_records`
--

DROP TABLE IF EXISTS `test_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `mark` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_test_records_tests_idx` (`test_id`),
  KEY `fk_test_records_users_idx` (`student_id`),
  CONSTRAINT `fk_test_records_tests` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_test_records_users` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_records`
--

LOCK TABLES `test_records` WRITE;
/*!40000 ALTER TABLE `test_records` DISABLE KEYS */;
INSERT INTO `test_records` VALUES (10,9,2,'2017-10-03 21:42:46','4/6'),(11,9,2,'2017-10-03 22:35:13','6/6'),(12,5,2,'2017-10-03 22:35:49','0/1'),(13,5,2,'2017-10-03 22:35:55','0/1'),(14,5,2,'2017-10-03 22:35:59','0/1'),(15,9,2,'2017-10-04 12:50:56','3/6'),(16,10,2,'2017-10-07 23:33:15','0/2'),(18,10,2,'2017-10-08 01:08:07','2/2'),(19,9,2,'2017-10-08 01:16:18','3/6'),(20,11,2,'2017-10-08 01:17:29','1/1'),(21,9,4,'2017-10-08 17:16:44','6/6'),(22,9,2,'2017-10-10 20:58:49','2/6'),(23,9,2,'2017-10-10 21:37:09','6/6'),(24,9,2,'2017-10-10 21:42:15','4/6'),(25,9,2,'2017-10-10 21:44:04','6/6'),(29,9,2,'2017-10-10 21:44:40','6/6'),(30,9,2,'2017-10-10 21:46:43','6/6');
/*!40000 ALTER TABLE `test_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_records_question_options`
--

DROP TABLE IF EXISTS `test_records_question_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_records_question_options` (
  `test_record_id` int(11) NOT NULL,
  `question_option_id` int(11) NOT NULL,
  PRIMARY KEY (`test_record_id`,`question_option_id`),
  KEY `fk_test_records_question_options_question_options_idx` (`question_option_id`),
  KEY `fk_test_records_question_options_test_records_idx` (`test_record_id`),
  CONSTRAINT `fk_test_records_question_options_question_options` FOREIGN KEY (`question_option_id`) REFERENCES `question_options` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_test_records_question_options_test_records` FOREIGN KEY (`test_record_id`) REFERENCES `test_records` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_records_question_options`
--

LOCK TABLES `test_records_question_options` WRITE;
/*!40000 ALTER TABLE `test_records_question_options` DISABLE KEYS */;
INSERT INTO `test_records_question_options` VALUES (12,7),(14,7),(13,8),(14,8),(10,23),(11,23),(15,23),(19,23),(21,23),(22,23),(23,23),(24,23),(25,23),(29,23),(30,23),(19,24),(19,26),(22,26),(10,27),(11,27),(15,27),(21,27),(22,27),(23,27),(24,27),(25,27),(29,27),(30,27),(10,28),(11,28),(15,28),(21,28),(23,28),(24,28),(25,28),(29,28),(30,28),(10,30),(11,30),(15,30),(19,30),(21,30),(22,30),(23,30),(24,30),(25,30),(29,30),(30,30),(11,31),(19,31),(21,31),(23,31),(25,31),(29,31),(30,31),(10,32),(15,32),(10,33),(15,33),(10,35),(11,35),(19,35),(21,35),(22,35),(23,35),(24,35),(25,35),(29,35),(30,35),(15,36),(16,39),(18,39),(16,41),(20,42);
/*!40000 ALTER TABLE `test_records_question_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tests`
--

DROP TABLE IF EXISTS `tests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `order_number` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tests_category_idx` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tests`
--

LOCK TABLES `tests` WRITE;
/*!40000 ALTER TABLE `tests` DISABLE KEYS */;
INSERT INTO `tests` VALUES (1,'FirstTest',1,NULL),(2,'SeconTest',2,NULL),(5,'asdf',1,111),(6,'asdfs',1,111),(8,'asdfad',1,111),(9,'Географія України',1,112),(10,'Цікаві відомості про Україну',1,112),(11,'asdf',1,112);
/*!40000 ALTER TABLE `tests` ENABLE KEYS */;
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
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `user_role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Yuri','Kaplun','yuri','1','380673255791','yorek.kap@gmail.com','TUTOR'),(2,'Ivan','Ivanov','student','student','380673255791','asd@gmail.com','STUDENT'),(3,'Tutor','Tutor','t','t','380673255791','yorek.kap@gmail.com','TUTOR'),(4,'Roger','Federer','rf','rf','380673255791','roge@gmail.com','STUDENT'),(5,'a','a','a','a','380673255793','a@a','TUTOR');
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

-- Dump completed on 2017-10-11  2:17:30
