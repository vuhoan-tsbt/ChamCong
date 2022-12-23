-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: d13cnpm7chamcong
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Phòng Kỹ Thuật'),(2,'ACCOUNTANT'),(3,'PERSONNEL'),(4,'EXECUTIVE BOARD');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `content` varchar(2550) DEFAULT NULL,
  `type_of_news` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2022-11-19 23:03:41.441642',7,_binary '\0','2022-11-19 23:03:41.441642',7,'toàn bộ nhân viên sẽ được nghỉ 2 ngày trong dịp tết dương lịch','Thông báo về lịch nghỉ tết dương lịch');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `dtype` varchar(31) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `target_id` varchar(255) NOT NULL,
  `type` int NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
INSERT INTO `otp` VALUES ('NumberOTP',1,'2022-11-19 21:04:29.305963','726616','3',0,'2022-11-19 21:04:30.074209');
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy`
--

DROP TABLE IF EXISTS `policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `policy` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content_policy` varchar(255) DEFAULT NULL,
  `name_policy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy`
--

LOCK TABLES `policy` WRITE;
/*!40000 ALTER TABLE `policy` DISABLE KEYS */;
INSERT INTO `policy` VALUES (1,'Các nhân viên mỗi tháng sẽ được nghỉ 1 ngày có phép','Chính sách nghỉ phép với nhân viên');
/*!40000 ALTER TABLE `policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `allowance` bigint NOT NULL,
  `position` varchar(255) DEFAULT NULL,
  `salary` bigint NOT NULL,
  `wage` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,1500000,'GENERAL MANAGER',60000000,1),(2,1200000,'MANAGE',30000000,2),(3,1000000,'OFFICIAL STAFF',15000000,3),(4,1000000,'ACCOUNTANT',18000000,4),(5,1000000,'HR',13000000,5),(6,0,'CEO',100000000,6);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Quản trị viên','ADMIN'),(11,'Người dùng','USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `months` datetime(6) DEFAULT NULL,
  `salary_for_one_hour_work` bigint DEFAULT NULL,
  `total_salary` bigint DEFAULT NULL,
  `total_working_hours` bigint DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe8hdt7omll7234r03bwmv8q07` (`user_id`),
  CONSTRAINT `FKe8hdt7omll7234r03bwmv8q07` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES (1,'2022-11-19 22:58:34.476843',170454,28977182,170,7);
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timekeeping`
--

DROP TABLE IF EXISTS `timekeeping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timekeeping` (
  `id` int NOT NULL AUTO_INCREMENT,
  `months` varchar(255) DEFAULT NULL,
  `radix_code` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7x7td6visqj2ovxtyoj0c8smj` (`user_id`),
  CONSTRAINT `FK7x7td6visqj2ovxtyoj0c8smj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timekeeping`
--

LOCK TABLES `timekeeping` WRITE;
/*!40000 ALTER TABLE `timekeeping` DISABLE KEYS */;
INSERT INTO `timekeeping` VALUES (1,'11','dy47fe3yoe',6);
/*!40000 ALTER TABLE `timekeeping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timekeeping_details`
--

DROP TABLE IF EXISTS `timekeeping_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timekeeping_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `entry_time` datetime(6) DEFAULT NULL,
  `timeout` datetime(6) DEFAULT NULL,
  `timekeeping_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsbkag50wl8gryvkn1v27sbir6` (`timekeeping_id`),
  CONSTRAINT `FKsbkag50wl8gryvkn1v27sbir6` FOREIGN KEY (`timekeeping_id`) REFERENCES `timekeeping` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timekeeping_details`
--

LOCK TABLES `timekeeping_details` WRITE;
/*!40000 ALTER TABLE `timekeeping_details` DISABLE KEYS */;
INSERT INTO `timekeeping_details` VALUES (1,'2022-11-19 22:53:24.838859','2022-11-19 22:54:25.519682',1);
/*!40000 ALTER TABLE `timekeeping_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `dtype` varchar(31) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `date_of_birth` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  `staff_code` varchar(255) DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  `position_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `manager_id` int DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `name_avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKh0iurmwjgjf5metvxmuitl8fu` (`email`,`staff_code`),
  KEY `FKgkh2fko1e4ydv1y6vtrwdc6my` (`department_id`),
  KEY `FK4ej0twvfqwwu5xdcns6u2qne3` (`position_id`),
  KEY `FKn4pb12f3y8ktduy8fnc2xlln1` (`role_id`),
  KEY `FKl9blkgio1nb00hot7kaxoy7q9` (`manager_id`),
  CONSTRAINT `FK4ej0twvfqwwu5xdcns6u2qne3` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  CONSTRAINT `FKgkh2fko1e4ydv1y6vtrwdc6my` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKl9blkgio1nb00hot7kaxoy7q9` FOREIGN KEY (`manager_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKn4pb12f3y8ktduy8fnc2xlln1` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Manager',1,'2022-10-25 08:44:42.626239',0,_binary '\0','2022-11-19 22:31:38.818424',1,1,'Hưng Yên',NULL,'2000-08-21','vuhoan485@gmail.com','Nguyễn Văn Nam','$2a$10$MG2D0KVVKsD8ftMHtiJR.uHmzz1PE5Onqth5ruAn4LC6gYnbO/t1G',NULL,'MNGR000001',4,6,1,NULL,'0987512212',NULL),('Manager',3,'2022-10-25 20:24:54.329218',0,_binary '\0','2022-10-25 20:47:11.829133',3,1,'Nam Định',NULL,'2000-06-22','hoancnttepu@gmail.com','Vũ Ngọc Hoan','$2a$10$.nVOl15rE6vjIwGjt672/eKltyaidvywSTPGXP1m0EV3Sja1cJ31K','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJob2FuY250dGVwdUBnbWFpbC5jb20iLCJpYXQiOjE2Njg4NjYzNjksImV4cCI6MTY2OTE2NjM2OX0.MOrrTTmOqPW16Ei-HPS5VyomHdNn_iabSJ4ic7JkNR9w0eyIeO6N1JTvsMsSMDcDm3inhEqhnLneEscamQVXxA','MNGR000003',4,6,1,NULL,'0988971325',NULL),('Employee',6,'2022-11-19 22:25:34.118130',1,_binary '\0','2022-12-05 23:13:06.380183',1,0,NULL,NULL,NULL,'a@aa.aa','a','$2a$10$d85TzcZ/doFP7OvXtCNFF.lPHbpivy6LQhD4TJVYtrjXgxBUyUKQ6',NULL,'STAFF000003',2,2,2,NULL,NULL,NULL),('Employee',7,'2022-11-19 22:55:52.976067',1,_binary '\0','2022-11-19 22:55:52.976067',1,1,NULL,NULL,NULL,'Nam@gmail.com',NULL,'$2a$10$urWQ7Knm7cK65T3Iqs1lK.7klnqPkclS7LhK7dbdOHkfFgMFzq86O',NULL,'STAFF000004',2,2,3,NULL,NULL,NULL),('Employee',14,'2022-12-12 10:14:57.826560',1,_binary '\0','2022-12-12 10:14:57.826560',1,1,NULL,NULL,NULL,'lehangulis1412@gmail.com',NULL,'$2a$10$Gw1rpQxmGbol/W6j8cueReSwrwROSI57hz539yfLvUsKamgXUmefy',NULL,'STAFF000005',2,5,3,NULL,NULL,NULL),('Employee',15,'2022-12-12 10:26:35.877407',1,_binary '\0','2022-12-12 10:26:35.877407',1,1,NULL,NULL,NULL,'minh123@gmail.com',NULL,'$2a$10$rpleWJS8qXN.lw3GkL.x2.UYbV./vHpfXofyCZPW/zwkIqqdl4y0q',NULL,'STAFF000006',1,2,2,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login_history`
--

DROP TABLE IF EXISTS `user_login_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_login_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `refresh_token` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdojpfv6cu53lvf9trehbj8t12` (`user_id`),
  CONSTRAINT `FKdojpfv6cu53lvf9trehbj8t12` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login_history`
--

LOCK TABLES `user_login_history` WRITE;
/*!40000 ALTER TABLE `user_login_history` DISABLE KEYS */;
INSERT INTO `user_login_history` VALUES (2,'2022-10-29 08:26:20.096316',0,_binary '\0','2022-10-29 08:26:20.096316',0,'hsbykojjpa4hk6yojsjnndcl6evkis5k49w82h3rosdddizvf9090zcqu1stwvpr','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjY3MDA2NzgwLCJleHAiOjE2NjczMDY3ODB9.JTZlv39QsN3QfI-SpLRxLFpsUJ9BtSCunsvpq7TxVQQvU11KU1CoAs0MkinKeBaJ41Jp67IablhmouKPE7zq6Q',1),(3,'2022-11-01 21:49:13.159076',0,_binary '\0','2022-11-01 21:49:13.159076',0,'47dsqfdunm2swklys8yfy4ujtavfe3c8eknt6yyv343f3gc6gwxrl2yqr2ogujln','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjY3MzE0MTUzLCJleHAiOjE2Njc2MTQxNTN9.aBF0qBwomRG6eVYXLO-1C5rdUxFlyykHzGdqH31j1FGC1fK45CNOOeHZkuo7jJHMp_aTDvASYIaxcohrAqgNGQ',1),(4,'2022-11-19 20:57:45.897994',0,_binary '\0','2022-11-19 20:57:45.897994',0,'cslxqy6zc399vgqoxnojjfhfqx9otu7gf3l4b7i9ujub6qier2spb7viobikpdqd','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjY4ODY2MjY1LCJleHAiOjE2NjkxNjYyNjV9.b4vnQGoYjNBW1xtmb7xrqz9kMHkz3TOXGdo_pAlIB6VN1zEGndJXTfm3sWDNU7069qRAieYrKn37L44-JLWnxg',1),(5,'2022-11-19 22:25:55.301675',0,_binary '\0','2022-11-19 22:25:55.301675',0,'81l7190lljiod0brtb2nnxkhilhid3faxyumlkisnv0zoqom2lqfa22d9vytst85','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZUBnbWFpbC5jb20iLCJpYXQiOjE2Njg4NzE1NTUsImV4cCI6MTY2OTE3MTU1NX0.nJRug1bOoa86CYKj2o-UTbv78qoi5FwgVgsHVjzIgafr2MNfqQ_O3CwyqQjsV6eDBBjbWBd2-G8ox9k3kBxK8A',6),(6,'2022-11-19 22:56:03.602431',0,_binary '\0','2022-11-19 22:56:03.602431',0,'9hl6xays3qkql6dtgxifnisuae5f2n158d02uu0cvd9dryfmslgmsfsz7juld98u','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOYW1AZ21haWwuY29tIiwiaWF0IjoxNjY4ODczMzYzLCJleHAiOjE2NjkxNzMzNjN9.5MtoSQdw6ZbpF72fxY-5KtWBFepXdUr8BL3ksA2bwxTcM0LxffOlbB7OHBNktQsEFO89Uqcw_O_Q-PKnsFlQPA',7),(7,'2022-12-05 21:28:23.477255',0,_binary '\0','2022-12-05 21:28:23.477255',0,'o1hnvdrc085ochsz6q65upcy0ypevejfig5zmn7i2ke1h6mo6s8993if9f77vgdw','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjcwMjUwNTAzLCJleHAiOjE2NzA1NTA1MDN9.HnmDfxaDyC-C_pQTNysVZ6jWACAMAzvU1YfixcL0mVO-LR-UQdy6Qz5TFDBzCgM3jx4evCbowz1TqSMIZDK1iQ',1),(8,'2022-12-05 21:30:39.196405',0,_binary '\0','2022-12-05 21:30:39.196405',0,'1liw0cu9qmg0p1390cslsuirayxzb8n9sje0ot0tkody89hujqfbkpf4xsju210f','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjcwMjUwNjM5LCJleHAiOjE2NzA1NTA2Mzl9.K2zfmgdHZdad7tprZDT-RXZlzMmV3bwxdepzGZkGXNJRTpfGy3kqxuJfSNSwibpUDzpTzrFBqhUtW4o778YQcQ',1),(9,'2022-12-05 21:39:03.232311',0,_binary '\0','2022-12-05 21:39:03.232311',0,'6olf5nfbp4nd1z9eo4qhcd8hsmkvrrp5be3rubipfhqmwh3wdilmrdggwtv0y1yl','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOYW1AZ21haWwuY29tIiwiaWF0IjoxNjcwMjUxMTQzLCJleHAiOjE2NzA1NTExNDN9.0u_AqtvaUXzlAhfSkpkKor1zNiyXH0dFzLCzNHAZzq4ZySOVEk1c3ES6VhAvEUphwfzufesbFmjJOTJw_wfH7Q',7),(10,'2022-12-05 22:18:36.637320',0,_binary '\0','2022-12-05 22:18:36.637320',0,'8hr2dqlt9qxh3ekdr6l4a0j7c2skn63l44i8xsvum9cbm4b5au604w8lx2afimio','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjcwMjUzNTE2LCJleHAiOjE2NzA1NTM1MTZ9.rSvT6CAgshnQasebHv06QV4XNJbLghtAga7ZDaQfi_x8hNtX4dii_GBwfIV3HmvaC4WX8LqGdxvJXpgI2flXlQ',1),(11,'2022-12-05 22:51:43.002322',0,_binary '\0','2022-12-05 22:51:43.002322',0,'r5u6c5lrjio46hhzvm0ouoc7zvdnwqcl6rvluloul99m0tf0tiynppb0ngifecp1','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjcwMjU1NTAyLCJleHAiOjE2NzA1NTU1MDJ9.jWiInLZoFVy49y9chSS7Ngo-wBDbl8KXpdSJkkFmkNeRkGPqarIA0LVK6SsTWEyRLs_gOhMcwUQ3TMr6rtDFYA',1),(12,'2022-12-12 10:13:55.156034',0,_binary '\0','2022-12-12 10:13:55.156034',0,'m0b2fojhbrlz2p3z7izj7dd1zic5heqnkqnfpayqypq0vilghk0cldxfjjf745i5','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjcwODE0ODM1LCJleHAiOjE2NzExMTQ4MzV9.AlwhIMtEqNt0t4aWLJbZ4hZ-6zaD_J8lr3CBMfCmjcTAayCtNrYo0ebtLOTepZdOf5dafbwse77Up-A4Ycq61w',1),(13,'2022-12-21 20:14:49.795452',0,_binary '\0','2022-12-21 20:14:49.795452',0,'i6ilr2otjswwi21bxx1ck3wqje59883vkws6xbm8yt6qdcwb1dkj0gijtclix8on','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjcxNjI4NDg5LCJleHAiOjE2NzE5Mjg0ODl9.08uJnV876t5cvrD_yd8Hg8D4X284fSf-andLJUpQ9243SMkMPP-NZp8JjZL2KVdzO_BQQK4_NlLvM-p12gN2Qg',1);
/*!40000 ALTER TABLE `user_login_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'ADMIN'),(2,'STAFF'),(3,'EDITER');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-23 19:04:31
