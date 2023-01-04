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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Phòng Kỹ Thuật'),(2,'Phòng Kế Toán'),(3,'Phòng Nhân Sự'),(4,'Ban Điều Hành'),(5,'Team Java'),(6,'Team PhP'),(7,'Phòng Pháp Lý'),(8,'Team C#'),(9,'Team RuBy'),(10,'Team React'),(11,'Team Test'),(12,'Team Game'),(13,'Team NodeJs'),(14,'Team VueJs'),(15,'Team Mobile');
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
  `content` varchar(255) DEFAULT NULL,
  `type_of_news` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2023-01-03 20:36:07.063622',5,_binary '\0','2023-01-03 20:36:07.063622',5,'toàn bộ nhân viên trong cty sẽ được nghỉ 3 ngày từ 31/12/200 - 02/01/2023','Thông báo về nghỉ tết Dương lịch'),(2,'2023-01-03 20:36:53.386020',5,_binary '\0','2023-01-03 20:36:53.386020',5,'toàn bộ nhân viên trong cty sẽ được nghỉ 3 ngày từ 20/01/2023- 27/01/2023','Thông báo về nghỉ tết Âm lịch'),(3,'2023-01-03 20:38:05.129709',5,_binary '\0','2023-01-03 20:38:05.129709',5,'Cty sẽ tổ chức hoạt động liên hoan cho toàn thể nhân viên ngày 08/01/2023, thời gian và địa điểm sẽ được thông báo cụ thể sau','Thông báo về nghỉ bữa tiệc cuối năm');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
INSERT INTO `otp` VALUES ('NumberOTP',1,'2022-11-19 21:04:29.305963','726616','3',0,'2022-11-19 21:04:30.074209'),('NumberOTP',2,'2022-12-31 19:48:58.342177','545717','3',0,'2022-12-31 19:48:59.239776'),('NumberOTP',3,'2022-12-31 19:52:47.253642','044816','3',0,'2022-12-31 19:52:47.599708'),('NumberOTP',4,'2023-01-02 22:37:01.686293','767840','3',0,'2023-01-02 22:37:02.385620');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy`
--

LOCK TABLES `policy` WRITE;
/*!40000 ALTER TABLE `policy` DISABLE KEYS */;
INSERT INTO `policy` VALUES (1,'Các nhân viên mỗi tháng sẽ được nghỉ 1 ngày có phép','Chính sách nghỉ phép với nhân viên'),(3,' nhân viên chính thức mỗi tháng sẽ được hỗ trở 70% tiền đóng bảo hiểm','Chính sách về bảo hiểm'),(4,'Thời gian làm việc sẽ bắt đầu từ 8h30 - 17h30 hàng ngày','Quy định về thời gian làm việc');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,1500000,'Giám Đốc',60000000,1),(2,1200000,'Quản Lý',30000000,2),(3,1000000,'Nhân Viên Chính Thức',15000000,3),(4,1000000,'Kế Toán',18000000,4),(5,1000000,'HR',13000000,5),(6,0,'CEO',100000000,6),(7,2000000,'Leader',45000000,7),(8,2000000,'PM',40000000,8),(9,1000000,'TestTer',15000000,9),(10,1000000,'BA',15000000,10),(11,1000000,'Fresher',10000000,11),(12,1500000,'Junior',20000000,12),(13,1500000,'Middler',30000000,13),(14,1500000,'Senior',40000000,14);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES (1,'2023-01-03 21:06:10.064519',255681,43465777,170,7),(2,'2023-01-03 17:06:10.064519',155681,13465777,170,8),(3,'2023-01-03 14:06:10.064519',155681,13465777,157,9),(4,'2023-01-03 22:08:12.381188',255681,44999863,176,7);
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
  `entry_time` datetime(6) DEFAULT NULL,
  `months` varchar(255) DEFAULT NULL,
  `radix_code` varchar(255) DEFAULT NULL,
  `timeout` datetime(6) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7x7td6visqj2ovxtyoj0c8smj` (`user_id`),
  CONSTRAINT `FK7x7td6visqj2ovxtyoj0c8smj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timekeeping`
--

LOCK TABLES `timekeeping` WRITE;
/*!40000 ALTER TABLE `timekeeping` DISABLE KEYS */;
INSERT INTO `timekeeping` VALUES (1,'2022-12-01 08:27:20.882038','12','kjdsmuzj40','2022-12-01 17:27:20.882038',7),(2,'2022-12-02 08:29:20.882038','12','kjhsmuzj41','2022-12-02 17:37:20.882038',7),(3,'2022-12-03 08:25:20.882038','12','kjhshuzj42','2022-12-03 17:35:20.882038',7),(4,'2022-12-04 08:35:20.882038','12','kjhshhgj44','2022-12-04 17:38:20.882038',7),(5,'2022-12-01 08:27:20.882038','12','hjhashus12','2022-12-01 08:27:20.882038',8),(6,'2022-12-02 08:29:20.882038','12','hjhashus13','2022-12-02 17:37:20.882038',8),(7,'2022-12-03 08:25:20.882038','12','hjhashus14','2022-12-03 17:35:20.882038',8),(8,'2022-12-04 08:35:20.882038','12','hjhashus15','2022-12-03 17:35:20.882038',8),(9,'2022-12-03 08:22:46.476663','12','r0hiha7bmn','2022-12-03 17:52:51.237747',12),(10,'2022-12-04 08:32:46.476663','12','r0hiha7bmk','2022-12-04 17:42:51.237747',12),(11,'2022-12-05 08:32:46.476663','12','r0hiha7bhj','2022-12-05 17:42:51.237747',12),(12,'2022-12-06 08:32:46.476663','12','r0hiha7bhd','2022-12-06 17:42:51.237747',12),(13,'2023-12-05 08:13:22.070459','12','0ebqjhuhc3','2023-12-05 17:13:22.070459',7),(14,'2023-01-03 22:10:00.522160','1','n79om2tbcd','2023-01-03 22:10:27.834786',18);
/*!40000 ALTER TABLE `timekeeping` ENABLE KEYS */;
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
  `name_avatar` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  `staff_code` varchar(255) DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  `position_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `manager_id` int DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Manager',1,'2023-01-03 19:47:47.667642',0,_binary '\0','2023-01-03 19:47:47.667642',0,1,'Nam Định',NULL,'2000-06-22','vuhoan485@gmail.com','Vũ Ngọc Hoan',NULL,'$2a$10$p58Nc4EqT6tJS3vinn4Xo.JQ7YElEhe8w6rAladSXoUSJFqRUEj3G','0988971325',NULL,'MNGR000001',4,6,1,NULL),('Manager',2,'2023-01-03 19:48:00.764497',0,_binary '\0','2023-01-03 21:50:55.183737',2,1,'Hà Nội','283987099_334699778809692_1819707884444099724_n3b8c191d-a06d-49b8-a6f6-e3a1dcd63bce.jpg','2000-05-06','minhquang654321@gmail.com','Nguyễn Minh Quang',NULL,'$2a$10$gsJ5Eq8SHt9jmFhZNmDiOOr9nMqftEBYi7jrMqOOsxvWtmtmf8qwa','0349796145','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaW5ocXVhbmc2NTQzMjFAZ21haWwuY29tIiwiaWF0IjoxNjcyNzU3MzYzLCJleHAiOjE2NzMwNTczNjN9.EAtH-EGW_JumNYirAF25j1Ox5nl11aKB_5PbEMNf3bZQnaswLc99aIWK0fKCQXBSZJ9QZ_zcxKNGktZGATsqSw','MNGR000002',4,6,1,NULL),('Manager',3,'2023-01-03 20:02:44.686086',0,_binary '\0','2023-01-03 20:02:44.686086',0,1,'Bắc Giang',NULL,'2000-05-18','minhanh1852000@gmail.com','Nguyễn Thị Thảo Minh',NULL,'$2a$10$io0VAl4rr0OK6QBL0df.tOjDAWI/td7IecUYgGAotNQj/u1HW7zDS','0356425510',NULL,'MNGR000003',4,6,1,NULL),('Manager',4,'2023-01-03 20:05:04.461042',0,_binary '\0','2023-01-03 20:05:04.461042',0,1,'Hà Nội',NULL,'2000-12-20','tranhuutienminh122000@gmail.com','Trần Hữu Tiến Minh',NULL,'$2a$10$SQkHaOq2i2/uI8BiV9kBFOmJKRe.yHWi4y1cgCvjifOnMkQ.FPqLS','0898061176',NULL,'MNGR000004',4,6,1,NULL),('Employee',5,'2023-01-03 20:12:12.346165',1,_binary '\0','2023-01-03 20:12:12.346165',1,1,'Hà Nội',NULL,'2000-01-01','nv1@gmail.com','Hr',NULL,'$2a$10$6Ytdl3zCUn1v0Po3iDVEBe4HjMOfwYeW.Oq9TP1aU2V3lvXBq8gaC','0123456789',NULL,'STAFF000005',3,5,3,NULL),('Employee',6,'2023-01-03 20:23:15.483553',1,_binary '\0','2023-01-03 20:23:15.483553',1,1,'Hà Nội',NULL,'2000-02-02','nv2@gmail.com','Kế Toán',NULL,'$2a$10$dWyI3cg4KabuoeiAPdK4d.4HbLuQoeW9gSbEd7myvR9XqYuMxKc8C','0987654321',NULL,'STAFF000006',4,5,3,NULL),('Employee',7,'2023-01-03 20:25:06.141797',1,_binary '\0','2023-01-03 20:25:06.141797',1,1,NULL,NULL,NULL,'testjava1@gmail.com',NULL,NULL,'$2a$10$Bqk62k51OPK6dWvglCVizOE9JvkIpPHh5FT/.E3bncKySjw1SBb4m',NULL,NULL,'STAFF000007',5,7,2,NULL),('Employee',8,'2023-01-03 20:25:21.495758',1,_binary '\0','2023-01-03 20:25:21.495758',1,1,NULL,NULL,NULL,'testjava2@gmail.com',NULL,NULL,'$2a$10$j39Vp7R5TvAlvq9Z9G2Sw.hn0PybdAm.y.ehp9.eRE7hnwPdCw/HK',NULL,NULL,'STAFF000008',5,11,2,NULL),('Employee',9,'2023-01-03 20:25:34.575879',1,_binary '\0','2023-01-03 20:25:34.575879',1,1,NULL,NULL,NULL,'testjava3@gmail.com',NULL,NULL,'$2a$10$TZei.vwLnPd/4xG40EqkiOjqMeq/eO6Z6vbqrdM5jC45oIhWl7H.y',NULL,NULL,'STAFF000009',5,12,2,NULL),('Employee',10,'2023-01-03 20:25:43.491050',1,_binary '\0','2023-01-03 20:25:43.491050',1,1,NULL,NULL,NULL,'testjava4@gmail.com',NULL,NULL,'$2a$10$arp.5Sw4QAfNfSvfV05Fd.4a7zCrzPe5zFq9SEGHWJtxBpsttGu4G',NULL,NULL,'STAFF000010',5,13,2,NULL),('Employee',11,'2023-01-03 20:25:50.700778',1,_binary '\0','2023-01-03 20:25:50.700778',1,1,NULL,NULL,NULL,'testjava5@gmail.com',NULL,NULL,'$2a$10$YxZUuxYLYy6.enalXGOiLe6NmvsLqaNfD3MPPdiFh6JHbUlbxM0vC',NULL,NULL,'STAFF000011',5,14,2,NULL),('Employee',12,'2023-01-03 20:26:57.809639',1,_binary '\0','2023-01-03 20:26:57.809639',1,1,NULL,NULL,NULL,'testphp1@gmail.com',NULL,NULL,'$2a$10$dK.xgjEEW/SmVXN5ChGzO.5JyeFYUi0z0wIU0kyEwN/76Owyhzsg2',NULL,NULL,'STAFF000012',6,11,2,NULL),('Employee',13,'2023-01-03 20:27:15.685944',1,_binary '\0','2023-01-03 20:27:15.685944',1,1,NULL,NULL,NULL,'testphp2@gmail.com',NULL,NULL,'$2a$10$Cjxw2tzNjUyx/LhEXZTdaOrSvUfBW8AYrGRbuXGMMcx8b9ClmatC6',NULL,NULL,'STAFF000013',6,7,2,NULL),('Employee',14,'2023-01-03 20:27:21.987130',1,_binary '\0','2023-01-03 20:27:21.987130',1,1,NULL,NULL,NULL,'testphp3@gmail.com',NULL,NULL,'$2a$10$N1rnuGuxJwel8U6WDw0Q9e80a2I1ZXHyiUXn6LLAWojenMzra3reG',NULL,NULL,'STAFF000014',6,12,2,NULL),('Employee',15,'2023-01-03 20:27:28.431453',1,_binary '\0','2023-01-03 20:27:28.431453',1,1,NULL,NULL,NULL,'testphp4@gmail.com',NULL,NULL,'$2a$10$hgACj4XHNeiuzhbrnqWR6evycNhz5yn2/vM.fSMFyz/CoE4saMu8y',NULL,NULL,'STAFF000015',6,13,2,NULL),('Employee',16,'2023-01-03 20:27:36.809424',1,_binary '\0','2023-01-03 20:27:36.809424',1,1,NULL,NULL,NULL,'testphp5@gmail.com',NULL,NULL,'$2a$10$FAY3N6iKjBaDYZQEdBajkOf6eeNi6gw1KyKNUy7Qul./SBCzSM35e',NULL,NULL,'STAFF000016',6,14,2,NULL),('Employee',17,'2023-01-03 20:28:29.074738',1,_binary '\0','2023-01-03 20:28:29.074738',1,1,NULL,NULL,NULL,'phaply@gmail.com',NULL,NULL,'$2a$10$1gg1KtJuGmcmgC4XoWH6pOjd4uu9uUJKsqyhgOCKQIjHucVlcSL9S',NULL,NULL,'STAFF000017',7,2,2,NULL),('Employee',18,'2023-01-03 20:29:15.365009',1,_binary '\0','2023-01-03 22:09:53.421458',18,1,'Hà Nội','288373603_132977719400448_9084592437239001830_n7a1988c6-58c2-41cd-bace-68b4bbdf0cad.jpg','2000-01-01','PM@gmail.com','PM',NULL,'$2a$10$f32YrofX4hHpHh0vwkTNy.PO9J7AvhNBzMnAbf9o//cRaWNA0DfIS','0987654321',NULL,'STAFF000018',4,8,2,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login_history`
--

LOCK TABLES `user_login_history` WRITE;
/*!40000 ALTER TABLE `user_login_history` DISABLE KEYS */;
INSERT INTO `user_login_history` VALUES (1,'2023-01-03 20:11:48.304046',0,_binary '\0','2023-01-03 20:11:48.304046',0,'qfzr2v5i0t8gcmlbqjpv73itk6kxqvfeccxjwh11jzm08ufc9lwmkiap8px4ahiv','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjcyNzUxNTA4LCJleHAiOjE2NzMwNTE1MDh9.-QAZ0mjckUTBV3ljPuuu7VBtGuur6XIKXYCiPCy6vGtK8M4pF_j4GZtGfku_Gx5zxSxv-HvoxWplX_jA-F3Kcw',1),(2,'2023-01-03 20:35:55.370877',0,_binary '\0','2023-01-03 20:35:55.370877',0,'2kyavm06158814z7y0v612ntdr9mea4gvrt8aprdqhs0y52fxg7b1qhci3nwv0ui','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJudjFAZ21haWwuY29tIiwiaWF0IjoxNjcyNzUyOTU1LCJleHAiOjE2NzMwNTI5NTV9.s1qYSDfcrPgOKefn_99G3ee5JkPvgldwN97RzFTT_kkLDibGMROBGpRwVbyL5Lo3bFr7rHoo7el0YrQuZtoJbA',5),(3,'2023-01-03 20:43:07.375751',0,_binary '\0','2023-01-03 20:43:07.375751',0,'a91bzjyt84ewawhoj7oaabkozfr7uvf12pf1il3ceqyl07xy2fc0q0u51kkbwwnd','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0amF2YTFAZ21haWwuY29tIiwiaWF0IjoxNjcyNzUzMzg3LCJleHAiOjE2NzMwNTMzODd9.nsgu9ew0MYS55GSMot9dnLFF3nArLZLmPmiociQ-HpIXRzhcgo7HhY1NOre35Mdd5AA_T0Ld1mXSAKzcYYVJrw',7),(4,'2023-01-03 20:52:34.832780',0,_binary '\0','2023-01-03 20:52:34.832780',0,'dxc09x12foqcc5ovwvo1ioebbbohorlbquq1kjnq7uwr9f9ofyeugbwl5bw8k3lw','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0cGhwMUBnbWFpbC5jb20iLCJpYXQiOjE2NzI3NTM5NTQsImV4cCI6MTY3MzA1Mzk1NH0.9orGORr-zoT1IDhQnwqeb6pbsO_5SFr3LpKdDYbf40ewysEZ4Js_P2GZBMGSXAq-xpQYiq_WXhCddtETY0RHUg',12),(5,'2023-01-03 20:58:08.602233',0,_binary '\0','2023-01-03 20:58:08.602233',0,'mifp3eygrkll1g2z6z75kwjwqirz6tr9o9m34j9rg4qbdt45p2s5bnfcmluef5fh','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjcyNzU0Mjg4LCJleHAiOjE2NzMwNTQyODh9.2rlsUJMdOuuaDMfm0q2sSty7y6b0RgW_WV81DG6LHt-R96zeBVfPAx0spctPxDLpcozu4r65FIEPgm5CLw8wdA',1),(6,'2023-01-03 21:03:58.460145',0,_binary '\0','2023-01-03 21:03:58.460145',0,'tgm826h6nvyxjobwmukfvk4c6d92l6z0eg80yazgnzoofd78wbemwi2m1aryvpqx','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJudjFAZ21haWwuY29tIiwiaWF0IjoxNjcyNzU0NjM4LCJleHAiOjE2NzMwNTQ2Mzh9.vzL2phwcVj_uY4NRYMelV100v3R_hZnsF_O3XwM5zX9bBoXKerBUu0zepE2DaYAsxMG-IgpZaK2ali3V3-V_Yg',5),(7,'2023-01-03 21:12:55.665638',0,_binary '\0','2023-01-03 21:12:55.665638',0,'1accg1ilk63nua0rhabzzgzl609o84id2ctyf01aa1hgf1qru5rfrxvra7e07zv3','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0amF2YTFAZ21haWwuY29tIiwiaWF0IjoxNjcyNzU1MTc1LCJleHAiOjE2NzMwNTUxNzV9.VGCJ0vVQtxTE-TbJk8-d90yqFMlL-GIzX-PopgWd4P5c20J0t8Gkd-MBAVk7F5_hdHgku-QwoHI6Xa55wlB3GQ',7),(8,'2023-01-03 21:16:21.936118',0,_binary '\0','2023-01-03 21:16:21.936118',0,'utir66hgauwinwc5bd3a35o2rdyqe5a5y6h6dq3163rar1niodea73scbwz1huqg','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjcyNzU1MzgxLCJleHAiOjE2NzMwNTUzODF9.DWY3owF_huudvEKVGXG_x3dDoem8gt2z1N7Z7LqH5Ra1qi7cESSgKLaK57-uV6siLEtwAG2pzNGSBru0dati9A',1),(9,'2023-01-03 21:20:55.744633',0,_binary '\0','2023-01-03 21:20:55.744633',0,'8oyf9pf2056gf36vqr6upolgktzpolv7yokzxw889yflfjdkea3kekxy9v3eo0zi','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjcyNzU1NjU1LCJleHAiOjE2NzMwNTU2NTV9.hb1il1-lMGheSk7BWJ_BChoViws0kvODfcUhqHr3wL3wqs-KAxdHMG8fh6H3cJvBsGWXzjwhhJTiPWbQCRmnDA',1),(10,'2023-01-03 21:40:57.810342',0,_binary '\0','2023-01-03 21:40:57.810342',0,'kpdg8l6ynwvfjy005emwnhfi905xr7psu3d1szpoegpb4o596524w8sp8uo5bor2','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dWhvYW40ODVAZ21haWwuY29tIiwiaWF0IjoxNjcyNzU2ODU3LCJleHAiOjE2NzMwNTY4NTd9.4sjqbnK8WyJxq30sFSlsWiy3TrXpqP6So9QUNY5L20oBsEcP-lqYbO0oWJrLRJEnzcXK5YAsb9zJ6-4yNIZC3g',1),(11,'2023-01-03 21:44:31.831056',0,_binary '\0','2023-01-03 21:44:31.831056',0,'ubd60bogr65hpjsg24aur0gbwh777g3o39k9mp67pl4lwfh5xgccbhomeipku34p','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaW5ocXVhbmc2NTQzMjFAZ21haWwuY29tIiwiaWF0IjoxNjcyNzU3MDcxLCJleHAiOjE2NzMwNTcwNzF9.duTiJJqdEPrfwdPrx1ePTLHNMk0v2EqyKwwHiwE22G-Rnxc2R60IXUgGeTQc-zzBB68G15SfX4wdUlsF0LmQ3w',2),(12,'2023-01-03 21:50:37.422723',0,_binary '\0','2023-01-03 21:50:37.422723',0,'ze3e6w2mccj0go8a449bkj8yrgwipioh0fc1zmzi1tlsot8bgwz9bhq6qaw3shcv','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaW5ocXVhbmc2NTQzMjFAZ21haWwuY29tIiwiaWF0IjoxNjcyNzU3NDM3LCJleHAiOjE2NzMwNTc0Mzd9.9WAKTgDsO0LTQH_X4uNqEWFhdvts_nyFaOCeZeVDlZt35ui_1B8DZeze9fU-YUzEThlazkNZmgfVh31Qs-n7WA',2),(13,'2023-01-03 22:05:04.964115',0,_binary '\0','2023-01-03 22:05:04.964115',0,'b8u63wjw11dh97y2atlmxybyat22e0t95g1dx04k74z8hy5xhbt7ladf4he8njru','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaW5oYW5oMTg1MjAwMEBnbWFpbC5jb20iLCJpYXQiOjE2NzI3NTgzMDQsImV4cCI6MTY3MzA1ODMwNH0.krVLDInB_Lw4IAAFIPCwm_frDUNOaU337pH0Kh7NXIFudX0zSn1mHVFo635R2TOXVvNk0LebbTaJtnLITttlig',3),(14,'2023-01-03 22:06:45.549528',0,_binary '\0','2023-01-03 22:06:45.549528',0,'xzu3x3gj829skllf5jdyte2cv7tbinpzlw4sujwrrrsj0gj2mhxcsz1x19bn40ua','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJudjFAZ21haWwuY29tIiwiaWF0IjoxNjcyNzU4NDA1LCJleHAiOjE2NzMwNTg0MDV9.2m48jYhwrO-1CfY_938AANjniMcL4S3JmW7K8FfRhnl9LMp66z2Nk4DLSne1OvhondO7N145CoS6qfVqOuBTrA',5),(15,'2023-01-03 22:09:22.205730',0,_binary '\0','2023-01-03 22:09:22.205730',0,'t1aa1ef0c1lu8doyfyzdclwemldz38vw09i5fa77q945ca3zpr2pfr1d7stlt38r','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJQTUBnbWFpbC5jb20iLCJpYXQiOjE2NzI3NTg1NjIsImV4cCI6MTY3MzA1ODU2Mn0.fM62nW9qjkMZFgFsWQmj68ExK4W9cilgG0rgLWxwgfXz4b4UQlFQMTDkN4fpxhpbLTLVKkF4MSSXQei0NkgpIg',18),(16,'2023-01-03 22:13:24.991835',0,_binary '\0','2023-01-03 22:13:24.991835',0,'lnbhhyk699h47blxxq5n3rqbt8wpxatsl63zizfgx7rn5pojxs7w0vfd7be9e3ta','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJudjFAZ21haWwuY29tIiwiaWF0IjoxNjcyNzU4ODA0LCJleHAiOjE2NzMwNTg4MDR9.Jh1Hgfb0MGvDK60wTJIKT7_wO1qeNtzbONEQlJLYIn6RuNlTwMlK-btU50h7S9O51a_GXlO10YjFGkL8y-8YGA',5);
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

-- Dump completed on 2023-01-04  8:24:08
