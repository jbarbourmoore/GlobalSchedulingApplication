CREATE DATABASE  IF NOT EXISTS `globalschedulingapplication` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `globalschedulingapplication`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: globalschedulingapplication
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `Appointment_ID` int NOT NULL,
  `Title` text NOT NULL,
  `Description` text NOT NULL,
  `Location` text NOT NULL,
  `Type` text NOT NULL,
  `Start` datetime NOT NULL,
  `End` datetime NOT NULL,
  `Create_Date` datetime NOT NULL,
  `Created_By` text NOT NULL,
  `Last_Update` datetime NOT NULL,
  `Last_Updated_By` text NOT NULL,
  `Author_ID` int NOT NULL,
  `Editor_ID` int NOT NULL,
  `Employee_ID` int NOT NULL,
  PRIMARY KEY (`Appointment_ID`),
  UNIQUE KEY `Appointment_ID_UNIQUE` (`Appointment_ID`),
  KEY `fk_author_id_idx` (`Author_ID`),
  KEY `fk_editor_id_idx` (`Editor_ID`),
  KEY `fk_employee_id_idx` (`Employee_ID`),
  CONSTRAINT `fk_author_id` FOREIGN KEY (`Author_ID`) REFERENCES `authors` (`ID`),
  CONSTRAINT `fk_editor_id` FOREIGN KEY (`Editor_ID`) REFERENCES `editors` (`ID`),
  CONSTRAINT `fk_employee_id` FOREIGN KEY (`Employee_ID`) REFERENCES `employees` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (1,'Nebula of Bonva','The Time Is Running Out! Think About These 8 Ways To Change Your Book','Conference Room A','Planning Session','2023-02-26 17:00:00','2023-02-26 18:00:00','2023-02-02 00:00:00','admin','2023-02-08 23:36:48','admin',1,6,2),(2,'Light Jetpack','The 8 Best Things About Book','Conference Room A','De-Briefing','2023-03-03 19:19:00','2023-03-03 20:20:00','2023-02-02 00:00:00','admin','2023-02-02 00:00:00','admin',6,5,3),(3,'Hubble of Honor','The 8 Biggest Book Mistakes You Can Easily Avoid','Conference Room B','Planning Session','2023-03-21 12:00:00','2023-03-21 13:00:00','2023-02-02 00:00:00','admin','2023-02-09 14:23:58','admin',2,3,3),(4,'Autonomous Moon','The 8 Most Successful Book Companies In Region','Conference Room D','Planning Session','2023-02-23 22:00:00','2023-02-24 01:00:00','2023-02-02 03:41:00','admin','2023-02-08 16:35:49','admin',2,3,5),(5,'The Woman who went to Tepnt','Think Your Book Is Safe? 8 Ways You Can Lose It Today','Conference Room A','Planning Session','2023-03-30 18:50:00','2023-03-30 19:50:00','2023-03-02 01:46:00','admin','2023-02-08 16:49:18','admin',5,6,3),(6,'Moon of Vortex','Thinking About Book? 8 Reasons Why It\'s Time To Stop!','Conference Room C','De-Briefing','2023-02-16 21:00:00','2023-02-16 23:00:00','2023-03-02 16:03:00','admin','2023-02-09 14:24:19','admin',5,1,4),(7,'4931: Hubble Glory','8 Places To Get Deals On Book','Conference Room B','Planning Session','2025-01-23 00:25:00','2025-01-23 02:20:00','2023-02-02 00:00:00','admin','2025-01-23 00:13:14','admin',1,3,3),(8,'Exploring Janchia','8 Reasons People Laugh About Your Book','Conference Room A','Celebration','2023-03-21 13:45:00','2023-03-21 14:45:00','2023-02-02 12:23:00','admin','2023-02-09 14:25:41','admin',4,1,1),(9,'5014: Mars Knights','8 Amazing Book Hacks','Conference Room D','Planning Session','2023-04-19 14:00:00','2023-04-19 15:00:00','2023-02-02 16:46:00','admin','2023-02-09 14:23:31','admin',5,2,5),(10,'Sinking Moon','8 Awesome Tips About Book From Unlikely Sources','Conference Room B','De-Briefing','2023-02-15 21:49:00','2023-02-15 22:49:00','2023-02-02 15:12:00','admin','2023-02-09 14:26:19','admin',4,3,1),(11,'Waking up on Xenangolia','8 Creative Ways You Can Improve Your Book','Conference Room A','Planning Session','2023-02-12 18:30:00','2023-02-12 19:00:00','2023-02-02 00:00:00','admin','2023-02-09 14:26:39','admin',3,5,5),(12,'A pocket guide to Tamon','8 Easy Steps To More Book Sales','Conference Room C','Celebration','2023-02-04 21:00:00','2023-02-04 22:00:00','2023-02-02 17:02:00','admin','2023-02-08 01:32:42','admin',2,4,3);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `ID` int NOT NULL,
  `Name` text NOT NULL,
  `Address` text NOT NULL,
  `Postal_Code` text NOT NULL,
  `Phone` text NOT NULL,
  `Email` text NOT NULL,
  `Create_Date` datetime NOT NULL,
  `Created_By` text NOT NULL,
  `Last_Update` datetime NOT NULL,
  `Last_Updated_By` text NOT NULL,
  `Region_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `Region_ID_idx` (`Region_ID`),
  CONSTRAINT `fk_region_id_author` FOREIGN KEY (`ID`) REFERENCES `regions` (`Region_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Anne McCaffrey','1919 Boardwalk','01291','869-908-1874','anne@dragonflight.com','2021-09-10 13:14:00','script','2023-02-09 00:49:07','admin',103),(2,'Mercedes Lackey','47 Horse Manor ','AF19B','11-445-910-2135','misty@queensarrows.co','2021-09-10 13:14:00','script','2023-02-08 23:56:28','admin',21),(3,'Larry Dixon','48 Horse Manor ','AF19B','11-445-910-2136','GryphonKing@queensarrows.co','2021-09-10 13:14:00','script','2023-02-08 23:56:09','admin',21),(4,'Tamora Pierce','1234 Haven Ave','12343','456-234-1232','Tamora@Lioness.net','2023-01-31 16:29:00','admin','2023-02-08 20:36:11','admin',71),(5,'Rick Riordan','123 smith street','124421','243-645-1243','Rick@MountOlympus.com','2023-02-01 01:34:00','admin','2023-02-01 01:34:00','admin',104),(6,'Kwame Mbalia','826 Tristan Lane','81263D','231-235-8674','Kwame@keepspunching.org','2023-02-01 01:34:00','admin','2023-02-09 00:48:40','admin',1);
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `Country_ID` int NOT NULL,
  `Country` text NOT NULL,
  `Create_Date` datetime NOT NULL,
  `Created_By` text NOT NULL,
  `Last_Update` datetime NOT NULL,
  `Last_Updated_By` text NOT NULL,
  PRIMARY KEY (`Country_ID`),
  UNIQUE KEY `Country_ID_UNIQUE` (`Country_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'U.S','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script'),(2,'UK','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script'),(3,'Canada','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script'),(4,'France','2023-02-07 16:27:00','admin','2023-02-07 16:27:00','admin');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editors`
--

DROP TABLE IF EXISTS `editors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editors` (
  `ID` int NOT NULL,
  `Name` text NOT NULL,
  `Address` text NOT NULL,
  `Postal_Code` text NOT NULL,
  `Phone` text NOT NULL,
  `Email` text NOT NULL,
  `Create_Date` datetime NOT NULL,
  `Created_By` text NOT NULL,
  `Last_Update` datetime NOT NULL,
  `Last_Updated_By` text NOT NULL,
  `Region_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `fk_region_id_editor_idx` (`Region_ID`),
  CONSTRAINT `fk_region_id_editor` FOREIGN KEY (`Region_ID`) REFERENCES `regions` (`Region_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editors`
--

LOCK TABLES `editors` WRITE;
/*!40000 ALTER TABLE `editors` DISABLE KEYS */;
INSERT INTO `editors` VALUES (1,'Taha Ayers','3725 Timber Ridge Road','95814','916-768-0717','Taha@gmail.com','2021-09-10 13:14:00','script','2023-01-31 16:32:00','admin',43),(2,'Reggie Myers','3440 Griffin Street','85008','602-275-9620','rmyers@yahoo.com','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',42),(3,'Sam Mcmillan','4242 Jerry Dove Drive','AF19B','11-445-910-2136','sMcMillan@rocketmail.com','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',69),(4,'Omari Yoder','1234 Haven Ave','12343','456-234-1232','Omari@aol.com','2023-01-31 16:29:00','admin','2023-02-09 00:33:21','admin',119),(5,'Katy Hurst','123 smith street','81263D','243-645-1243','KatyHurst@gmail.com','2023-02-08 22:53:31','admin','2023-02-08 22:53:31','admin',12),(6,'Howard Montoya','826 Tristan Lane','81263D','231-235-8674','HowardM@gmail.com','2023-02-01 01:34:00','admin','2023-02-09 00:36:06','admin',102),(7,'Tom Smith','8364 Fig Tree Lane','GL563','11-234-534-2345','Sam@smith.net','2023-02-08 22:53:31','admin','2023-02-08 22:53:31','admin',103);
/*!40000 ALTER TABLE `editors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `ID` int NOT NULL,
  `Name` text NOT NULL,
  `Address` text NOT NULL,
  `Postal_Code` text NOT NULL,
  `Phone` text NOT NULL,
  `Email` text NOT NULL,
  `Create_Date` datetime NOT NULL,
  `Created_By` text NOT NULL,
  `Last_Update` datetime NOT NULL,
  `Last_Updated_By` text NOT NULL,
  `Region_ID` int NOT NULL,
  `User_Name` text NOT NULL,
  `Salted_Hash` text NOT NULL,
  `Salt` text NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  CONSTRAINT `fk_region_id_employee` FOREIGN KEY (`ID`) REFERENCES `regions` (`Region_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Administrator','4874 Copperhead Road','6902','860-591-7525','admin@alanath.org','2021-09-10 13:14:00','script','2023-01-31 16:32:00','admin',12,'admin','2361b048cd549716313e6e9ff8d67ac00a72961c147489b125c97ef73bb03808','7e32c033'),(2,'Gabriela Jefferson','4413 Clousson Road','51034','712-880-1155','GabrielaJ23@alanath.org','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',103,'GabrielaJ23','80859e092f3c49a313fa181e1bf0e98fda85e86986785ea05c370aee834b5a8b','2530c12'),(3,'Nancy Winters','4947 Courtright Street','58577','701-460-9506','NancyW97@alanath.ord','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',52,'NancyW97','d75827baad5d73baf9697d37c1316e019c852d676bb29cbce45cc3897c1ee825','73c6c3b2'),(4,'Douglas Villegas','3934 Romrog Way','68801','308-201-5702','DouglasV12@alanath.org','2023-01-31 16:29:00','admin','2023-02-01 08:36:00','admin',12,'DouglasV12','1c2a7fa57ed4619bacaa1116f408d6f7f9f4a3cd30539cd86b9199f509cc71c0','48533e64'),(5,'Shivam Thomas','4910 Cedar Lane','2143','617-629-5901','ShivamT76@alanath.org','2023-02-01 01:34:00','admin','2023-02-01 01:34:00','admin',23,'ShivamT76','fa12d88316bfceff2bcc382503d089a00bcecee84661d599ffc8d7d68a095ef1','64a294a6');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regions`
--

DROP TABLE IF EXISTS `regions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regions` (
  `Region_ID` int NOT NULL,
  `Region_Name` text NOT NULL,
  `Create_Date` datetime NOT NULL,
  `Created_By` text NOT NULL,
  `Last_Update` datetime NOT NULL,
  `Last_Updated_By` text NOT NULL,
  `Country_ID` int NOT NULL,
  PRIMARY KEY (`Region_ID`),
  UNIQUE KEY `Region_ID_UNIQUE` (`Region_ID`),
  KEY `fk_country_id_idx` (`Country_ID`),
  CONSTRAINT `fk_country_id` FOREIGN KEY (`Country_ID`) REFERENCES `countries` (`Country_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regions`
--

LOCK TABLES `regions` WRITE;
/*!40000 ALTER TABLE `regions` DISABLE KEYS */;
INSERT INTO `regions` VALUES (1,'Alabama','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(2,'Arizona','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(3,'Arkansas','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(4,'California','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(5,'Colorado','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(6,'Connecticut','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(7,'Delaware','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(8,'District of Columbia','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(9,'Florida','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(10,'Georgia','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(11,'Idaho','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(12,'Illinois','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(13,'Indiana','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(14,'Iowa','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(15,'Kansas','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(16,'Kentucky','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(17,'Louisiana','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(18,'Maine','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(19,'Maryland','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(20,'Massachusetts','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(21,'Michigan','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(22,'Minnesota','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(23,'Mississippi','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(24,'Missouri','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(25,'Montana','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(26,'Nebraska','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(27,'Nevada','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(28,'New Hampshire','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(29,'New Jersey','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(30,'New Mexico','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(31,'New York','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(32,'North Carolina','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(33,'North Dakota','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(34,'Ohio','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(35,'Oklahoma','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(36,'Oregon','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(37,'Pennsylvania','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(38,'Rhode Island','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(39,'South Carolina','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(40,'South Dakota','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(41,'Tennessee','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(42,'Texas','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(43,'Utah','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(44,'Vermont','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(45,'Virginia','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(46,'Washington','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(47,'West Virginia','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(48,'Wisconsin','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(49,'Wyoming','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(52,'Hawaii','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(54,'Alaska','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',1),(60,'Northwest Territories','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(61,'Alberta','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(62,'British Columbia','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(63,'Manitoba','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(64,'New Brunswick','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(65,'Nova Scotia','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(66,'Prince Edward Island','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(67,'Ontario','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(68,'QuÃ©bec','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(69,'Saskatchewan','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(70,'Nunavut','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(71,'Yukon','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(72,'Newfoundland and Labrador','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',3),(101,'England','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',2),(102,'Wales','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',2),(103,'Scotland','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',2),(104,'Northern Ireland','2021-09-10 13:14:00','script','2021-09-10 17:14:00','script',2),(105,'Auvergne-Rhone-Alpes','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(106,'Bourgogne-Franche-Comte','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(107,'Bretagne','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(108,'Centre-Val de Loire','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(109,'Corse','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(110,'Grand Est','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(111,'Hauts-de-France','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(112,'Ile-de-France','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(113,'Normandie','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(114,'Nouvelle-Aquitaine','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(115,'Occitanie','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(116,'Pays de La Loire','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(117,'Provence-Alpes-Cote d\'Azur','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(118,'Guadeloupe','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(119,'Guyane','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(120,'La Reunion','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(121,'Martinique','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4),(122,'Mayotte','2023-02-07 16:24:00','admin','2023-02-07 16:24:00','admin',4);
/*!40000 ALTER TABLE `regions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-08 19:38:52
