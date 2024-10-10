-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: saleapp
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `personalId` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `province_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `district_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ward_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `province_id` (`province_id`),
  KEY `district_id` (`district_id`),
  KEY `ward_id` (`ward_id`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `admin_ibfk_2` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`code`),
  CONSTRAINT `admin_ibfk_3` FOREIGN KEY (`district_id`) REFERENCES `districts` (`code`),
  CONSTRAINT `admin_ibfk_4` FOREIGN KEY (`ward_id`) REFERENCES `wards` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('dattan','172 ABC','2151050138hiep@ou.edu.vn','0383876120','094202011221','79','767','27013'),('hieptestzzzz1zz','172 ABCZ','tonghiep25@gmail.com','0383876120','12345767198','79','767','27028'),('tandat','172 LVL','tonghiep666@gmail.com','0383876120','12345678909','79','768','27058');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrative_regions`
--

DROP TABLE IF EXISTS `administrative_regions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrative_regions` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name_en` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `code_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code_name_en` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrative_regions`
--

LOCK TABLES `administrative_regions` WRITE;
/*!40000 ALTER TABLE `administrative_regions` DISABLE KEYS */;
INSERT INTO `administrative_regions` VALUES (1,'Đông Bắc Bộ','Northeast','dong_bac_bo','northest'),(2,'Tây Bắc Bộ','Northwest','tay_bac_bo','northwest'),(3,'Đồng bằng sông Hồng','Red River Delta','dong_bang_song_hong','red_river_delta'),(4,'Bắc Trung Bộ','North Central Coast','bac_trung_bo','north_central_coast'),(5,'Duyên hải Nam Trung Bộ','South Central Coast','duyen_hai_nam_trung_bo','south_central_coast'),(6,'Tây Nguyên','Central Highlands','tay_nguyen','central_highlands'),(7,'Đông Nam Bộ','Southeast','dong_nam_bo','southeast'),(8,'Đồng bằng sông Cửu Long','Mekong River Delta','dong_bang_song_cuu_long','southwest');
/*!40000 ALTER TABLE `administrative_regions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrative_units`
--

DROP TABLE IF EXISTS `administrative_units`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrative_units` (
  `id` int NOT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `full_name_en` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `short_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `short_name_en` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code_name_en` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrative_units`
--

LOCK TABLES `administrative_units` WRITE;
/*!40000 ALTER TABLE `administrative_units` DISABLE KEYS */;
INSERT INTO `administrative_units` VALUES (1,'Thành phố trực thuộc trung ương','Municipality','Thành phố','City','thanh_pho_truc_thuoc_trung_uong','municipality'),(2,'Tỉnh','Province','Tỉnh','Province','tinh','province'),(3,'Thành phố thuộc thành phố trực thuộc trung ương','Municipal city','Thành phố','City','thanh_pho_thuoc_thanh_pho_truc_thuoc_trung_uong','municipal_city'),(4,'Thành phố thuộc tỉnh','Provincial city','Thành phố','City','thanh_pho_thuoc_tinh','provincial_city'),(5,'Quận','Urban district','Quận','District','quan','urban_district'),(6,'Thị xã','District-level town','Thị xã','Town','thi_xa','district_level_town'),(7,'Huyện','District','Huyện','District','huyen','district'),(8,'Phường','Ward','Phường','Ward','phuong','ward'),(9,'Thị trấn','Commune-level town','Thị trấn','Township','thi_tran','commune_level_town'),(10,'Xã','Commune','Xã','Commune','xa','commune');
/*!40000 ALTER TABLE `administrative_units` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `province_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `district_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ward_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `admin_user` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `province_id` (`province_id`),
  KEY `district_id` (`district_id`),
  KEY `ward_id` (`ward_id`),
  KEY `fk_admin` (`admin_user`),
  CONSTRAINT `branch_ibfk_1` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`code`),
  CONSTRAINT `branch_ibfk_2` FOREIGN KEY (`district_id`) REFERENCES `districts` (`code`),
  CONSTRAINT `branch_ibfk_3` FOREIGN KEY (`ward_id`) REFERENCES `wards` (`code`),
  CONSTRAINT `fk_admin` FOREIGN KEY (`admin_user`) REFERENCES `admin` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'720 ABC','01','001','00001','tandat'),(3,'100 XYZ','79','770','27148','hieptestzzzz1zz');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `logo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (3,'Xiaomi','https://res.cloudinary.com/dsbkju7j9/image/upload/v1723889973/k6uifzsc4loxdet1klqz.png'),(4,'Apple','https://res.cloudinary.com/dsbkju7j9/image/upload/v1723889942/j22ftxxzjr36zkpgwl6p.png'),(5,'Vivo','https://res.cloudinary.com/dsbkju7j9/image/upload/v1723889987/xgw3fvg23yj3awprn9tg.png'),(7,'Acer','https://res.cloudinary.com/dsbkju7j9/image/upload/v1724061461/b8fx9a0zrvvni8sufcju.jpg');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrier`
--

DROP TABLE IF EXISTS `carrier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrier`
--

LOCK TABLES `carrier` WRITE;
/*!40000 ALTER TABLE `carrier` DISABLE KEYS */;
INSERT INTO `carrier` VALUES (1,'Hỏa tốc','Giao nhanh trong 1-2 ngày'),(2,'Tiêu chuẩn','Giao hàng trong 3-5 ngày (tùy khu vực)'),(3,'GHTK','Giao hàng tiết kiệm trong 5-7 ngày (tùy khu vực)');
/*!40000 ALTER TABLE `carrier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `username` (`username`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (5,6,2,'tonghiep'),(6,3,3,'tonghiep'),(7,5,1,'tonghiep'),(9,1,2,'tonghiep12'),(10,2,1,'tonghiep12');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (2,'Smart Phone','Smart Phone ....'),(3,'Laptop','Laptop....'),(4,'Tablet','Tablet...');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `province_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `district_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ward_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `province_id` (`province_id`),
  KEY `district_id` (`district_id`),
  KEY `ward_id` (`ward_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `customer_ibfk_2` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`code`),
  CONSTRAINT `customer_ibfk_3` FOREIGN KEY (`district_id`) REFERENCES `districts` (`code`),
  CONSTRAINT `customer_ibfk_4` FOREIGN KEY (`ward_id`) REFERENCES `wards` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('hiepabc','0383876012','724/44 LÊ VĂN LƯƠNG','tonghiep28@gmail.com','79','767','27010'),('tonghiep','0383876058','724/44 LÊ VĂN LƯƠNG','tonghiep22@gmail.com','01','001','00001'),('tonghiep12','0383876000','724 Cộng Hòa','tonghiep22@gmail.com','01','001','00001');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `districts`
--

DROP TABLE IF EXISTS `districts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `districts` (
  `code` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `province_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `administrative_unit_id` int DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `idx_districts_province` (`province_code`),
  KEY `idx_districts_unit` (`administrative_unit_id`),
  CONSTRAINT `districts_administrative_unit_id_fkey` FOREIGN KEY (`administrative_unit_id`) REFERENCES `administrative_units` (`id`),
  CONSTRAINT `districts_province_code_fkey` FOREIGN KEY (`province_code`) REFERENCES `provinces` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `districts`
--

LOCK TABLES `districts` WRITE;
/*!40000 ALTER TABLE `districts` DISABLE KEYS */;
INSERT INTO `districts` VALUES ('001','Quận Ba Đình','01',5),('002','Quận Hoàn Kiếm','01',5),('003','Quận Tây Hồ','01',5),('004','Quận Long Biên','01',5),('005','Quận Cầu Giấy','01',5),('006','Quận Đống Đa','01',5),('007','Quận Hai Bà Trưng','01',5),('008','Quận Hoàng Mai','01',5),('009','Quận Thanh Xuân','01',5),('016','Huyện Sóc Sơn','01',7),('017','Huyện Đông Anh','01',7),('018','Huyện Gia Lâm','01',7),('019','Quận Nam Từ Liêm','01',5),('020','Huyện Thanh Trì','01',7),('021','Quận Bắc Từ Liêm','01',5),('024','Thành phố Hà Giang','02',4),('026','Huyện Đồng Văn','02',7),('027','Huyện Mèo Vạc','02',7),('028','Huyện Yên Minh','02',7),('029','Huyện Quản Bạ','02',7),('030','Huyện Vị Xuyên','02',7),('031','Huyện Bắc Mê','02',7),('032','Huyện Hoàng Su Phì','02',7),('033','Huyện Xín Mần','02',7),('034','Huyện Bắc Quang','02',7),('035','Huyện Quang Bình','02',7),('040','Thành phố Cao Bằng','04',4),('042','Huyện Bảo Lâm','04',7),('043','Huyện Bảo Lạc','04',7),('045','Huyện Hà Quảng','04',7),('047','Huyện Trùng Khánh','04',7),('048','Huyện Hạ Lang','04',7),('049','Huyện Quảng Hòa','04',7),('051','Huyện Hoà An','04',7),('052','Huyện Nguyên Bình','04',7),('053','Huyện Thạch An','04',7),('058','Thành phố Bắc Kạn','06',4),('060','Huyện Pác Nặm','06',7),('061','Huyện Ba Bể','06',7),('062','Huyện Ngân Sơn','06',7),('063','Huyện Bạch Thông','06',7),('064','Huyện Chợ Đồn','06',7),('065','Huyện Chợ Mới','06',7),('066','Huyện Na Rì','06',7),('070','Thành phố Tuyên Quang','08',4),('071','Huyện Lâm Bình','08',7),('072','Huyện Na Hang','08',7),('073','Huyện Chiêm Hóa','08',7),('074','Huyện Hàm Yên','08',7),('075','Huyện Yên Sơn','08',7),('150','Huyện Đà Bắc','17',7),('152','Huyện Lương Sơn','17',7),('153','Huyện Kim Bôi','17',7),('154','Huyện Cao Phong','17',7),('155','Huyện Tân Lạc','17',7),('156','Huyện Mai Châu','17',7),('157','Huyện Lạc Sơn','17',7),('158','Huyện Yên Thủy','17',7),('159','Huyện Lạc Thủy','17',7),('164','Thành phố Thái Nguyên','19',4),('165','Thành phố Sông Công','19',4),('167','Huyện Định Hóa','19',7),('168','Huyện Phú Lương','19',7),('169','Huyện Đồng Hỷ','19',7),('170','Huyện Võ Nhai','19',7),('171','Huyện Đại Từ','19',7),('172','Thành phố Phổ Yên','19',4),('173','Huyện Phú Bình','19',7),('178','Thành phố Lạng Sơn','20',4),('180','Huyện Tràng Định','20',7),('181','Huyện Bình Gia','20',7),('182','Huyện Văn Lãng','20',7),('183','Huyện Cao Lộc','20',7),('184','Huyện Văn Quan','20',7),('185','Huyện Bắc Sơn','20',7),('186','Huyện Hữu Lũng','20',7),('187','Huyện Chi Lăng','20',7),('188','Huyện Lộc Bình','20',7),('189','Huyện Đình Lập','20',7),('193','Thành phố Hạ Long','22',4),('194','Thành phố Móng Cái','22',4),('195','Thành phố Cẩm Phả','22',4),('196','Thành phố Uông Bí','22',4),('198','Huyện Bình Liêu','22',7),('199','Huyện Tiên Yên','22',7),('200','Huyện Đầm Hà','22',7),('201','Huyện Hải Hà','22',7),('202','Huyện Ba Chẽ','22',7),('203','Huyện Vân Đồn','22',7),('205','Thị xã Đông Triều','22',6),('206','Thị xã Quảng Yên','22',6),('207','Huyện Cô Tô','22',7),('213','Thành phố Bắc Giang','24',4),('215','Huyện Yên Thế','24',7),('216','Huyện Tân Yên','24',7),('217','Huyện Lạng Giang','24',7),('218','Huyện Lục Nam','24',7),('219','Huyện Lục Ngạn','24',7),('220','Huyện Sơn Động','24',7),('221','Huyện Yên Dũng','24',7),('222','Thị xã Việt Yên','24',6),('223','Huyện Hiệp Hòa','24',7),('227','Thành phố Việt Trì','25',4),('228','Thị xã Phú Thọ','25',6),('230','Huyện Đoan Hùng','25',7),('231','Huyện Hạ Hoà','25',7),('232','Huyện Thanh Ba','25',7),('233','Huyện Phù Ninh','25',7),('234','Huyện Yên Lập','25',7),('235','Huyện Cẩm Khê','25',7),('236','Huyện Tam Nông','25',7),('237','Huyện Lâm Thao','25',7),('238','Huyện Thanh Sơn','25',7),('239','Huyện Thanh Thuỷ','25',7),('240','Huyện Tân Sơn','25',7),('243','Thành phố Vĩnh Yên','26',4),('244','Thành phố Phúc Yên','26',4),('246','Huyện Lập Thạch','26',7),('247','Huyện Tam Dương','26',7),('248','Huyện Tam Đảo','26',7),('249','Huyện Bình Xuyên','26',7),('250','Huyện Mê Linh','01',7),('251','Huyện Yên Lạc','26',7),('252','Huyện Vĩnh Tường','26',7),('253','Huyện Sông Lô','26',7),('256','Thành phố Bắc Ninh','27',4),('258','Huyện Yên Phong','27',7),('259','Thị xã Quế Võ','27',6),('260','Huyện Tiên Du','27',7),('261','Thành phố Từ Sơn','27',4),('262','Thị xã Thuận Thành','27',6),('263','Huyện Gia Bình','27',7),('264','Huyện Lương Tài','27',7),('268','Quận Hà Đông','01',5),('269','Thị xã Sơn Tây','01',6),('271','Huyện Ba Vì','01',7),('272','Huyện Phúc Thọ','01',7),('273','Huyện Đan Phượng','01',7),('274','Huyện Hoài Đức','01',7),('275','Huyện Quốc Oai','01',7),('276','Huyện Thạch Thất','01',7),('277','Huyện Chương Mỹ','01',7),('278','Huyện Thanh Oai','01',7),('279','Huyện Thường Tín','01',7),('280','Huyện Phú Xuyên','01',7),('281','Huyện Ứng Hòa','01',7),('282','Huyện Mỹ Đức','01',7),('288','Thành phố Hải Dương','30',4),('290','Thành phố Chí Linh','30',4),('291','Huyện Nam Sách','30',7),('292','Thị xã Kinh Môn','30',6),('293','Huyện Kim Thành','30',7),('294','Huyện Thanh Hà','30',7),('295','Huyện Cẩm Giàng','30',7),('296','Huyện Bình Giang','30',7),('297','Huyện Gia Lộc','30',7),('298','Huyện Tứ Kỳ','30',7),('299','Huyện Ninh Giang','30',7),('300','Huyện Thanh Miện','30',7),('303','Quận Hồng Bàng','31',5),('304','Quận Ngô Quyền','31',5),('305','Quận Lê Chân','31',5),('306','Quận Hải An','31',5),('307','Quận Kiến An','31',5),('308','Quận Đồ Sơn','31',5),('309','Quận Dương Kinh','31',5),('311','Huyện Thuỷ Nguyên','31',7),('312','Huyện An Dương','31',7),('313','Huyện An Lão','31',7),('314','Huyện Kiến Thuỵ','31',7),('315','Huyện Tiên Lãng','31',7),('316','Huyện Vĩnh Bảo','31',7),('317','Huyện Cát Hải','31',7),('323','Thành phố Hưng Yên','33',4),('325','Huyện Văn Lâm','33',7),('326','Huyện Văn Giang','33',7),('327','Huyện Yên Mỹ','33',7),('328','Thị xã Mỹ Hào','33',6),('329','Huyện Ân Thi','33',7),('330','Huyện Khoái Châu','33',7),('331','Huyện Kim Động','33',7),('332','Huyện Tiên Lữ','33',7),('333','Huyện Phù Cừ','33',7),('336','Thành phố Thái Bình','34',4),('338','Huyện Quỳnh Phụ','34',7),('339','Huyện Hưng Hà','34',7),('340','Huyện Đông Hưng','34',7),('341','Huyện Thái Thụy','34',7),('342','Huyện Tiền Hải','34',7),('343','Huyện Kiến Xương','34',7),('344','Huyện Vũ Thư','34',7),('347','Thành phố Phủ Lý','35',4),('349','Thị xã Duy Tiên','35',6),('350','Huyện Kim Bảng','35',7),('351','Huyện Thanh Liêm','35',7),('352','Huyện Bình Lục','35',7),('353','Huyện Lý Nhân','35',7),('356','Thành phố Nam Định','36',4),('358','Huyện Mỹ Lộc','36',7),('359','Huyện Vụ Bản','36',7),('360','Huyện Ý Yên','36',7),('361','Huyện Nghĩa Hưng','36',7),('362','Huyện Nam Trực','36',7),('363','Huyện Trực Ninh','36',7),('364','Huyện Xuân Trường','36',7),('365','Huyện Giao Thủy','36',7),('366','Huyện Hải Hậu','36',7),('369','Thành phố Ninh Bình','37',4),('370','Thành phố Tam Điệp','37',4),('372','Huyện Nho Quan','37',7),('373','Huyện Gia Viễn','37',7),('374','Huyện Hoa Lư','37',7),('375','Huyện Yên Khánh','37',7),('376','Huyện Kim Sơn','37',7),('377','Huyện Yên Mô','37',7),('380','Thành phố Thanh Hóa','38',4),('381','Thị xã Bỉm Sơn','38',6),('382','Thành phố Sầm Sơn','38',4),('384','Huyện Mường Lát','38',7),('385','Huyện Quan Hóa','38',7),('386','Huyện Bá Thước','38',7),('387','Huyện Quan Sơn','38',7),('388','Huyện Lang Chánh','38',7),('631','Huyện Đức Cơ','64',7),('632','Huyện Chư Prông','64',7),('633','Huyện Chư Sê','64',7),('634','Huyện Đăk Pơ','64',7),('635','Huyện Ia Pa','64',7),('637','Huyện Krông Pa','64',7),('638','Huyện Phú Thiện','64',7),('639','Huyện Chư Pưh','64',7),('723','Thành phố Tân Uyên','74',4),('724','Thành phố Dĩ An','74',4),('725','Thành phố Thuận An','74',4),('726','Huyện Bắc Tân Uyên','74',7),('731','Thành phố Biên Hòa','75',4),('732','Thành phố Long Khánh','75',4),('734','Huyện Tân Phú','75',7),('735','Huyện Vĩnh Cửu','75',7),('736','Huyện Định Quán','75',7),('737','Huyện Trảng Bom','75',7),('738','Huyện Thống Nhất','75',7),('739','Huyện Cẩm Mỹ','75',7),('740','Huyện Long Thành','75',7),('741','Huyện Xuân Lộc','75',7),('742','Huyện Nhơn Trạch','75',7),('747','Thành phố Vũng Tàu','77',4),('748','Thành phố Bà Rịa','77',4),('750','Huyện Châu Đức','77',7),('751','Huyện Xuyên Mộc','77',7),('752','Huyện Long Điền','77',7),('753','Huyện Đất Đỏ','77',7),('754','Thị xã Phú Mỹ','77',6),('760','Quận 1','79',5),('761','Quận 12','79',5),('764','Quận Gò Vấp','79',5),('765','Quận Bình Thạnh','79',5),('766','Quận Tân Bình','79',5),('767','Quận Tân Phú','79',5),('768','Quận Phú Nhuận','79',5),('769','Thành phố Thủ Đức','79',3),('770','Quận 3','79',5),('771','Quận 10','79',5),('772','Quận 11','79',5),('773','Quận 4','79',5),('774','Quận 5','79',5),('775','Quận 6','79',5),('776','Quận 8','79',5),('777','Quận Bình Tân','79',5),('778','Quận 7','79',5),('783','Huyện Củ Chi','79',7),('784','Huyện Hóc Môn','79',7),('785','Huyện Bình Chánh','79',7),('786','Huyện Nhà Bè','79',7),('787','Huyện Cần Giờ','79',7),('794','Thành phố Tân An','80',4),('795','Thị xã Kiến Tường','80',6),('796','Huyện Tân Hưng','80',7),('797','Huyện Vĩnh Hưng','80',7),('798','Huyện Mộc Hóa','80',7),('799','Huyện Tân Thạnh','80',7),('800','Huyện Thạnh Hóa','80',7),('801','Huyện Đức Huệ','80',7),('802','Huyện Đức Hòa','80',7),('803','Huyện Bến Lức','80',7),('804','Huyện Thủ Thừa','80',7),('805','Huyện Tân Trụ','80',7),('806','Huyện Cần Đước','80',7),('807','Huyện Cần Giuộc','80',7),('808','Huyện Châu Thành','80',7),('815','Thành phố Mỹ Tho','82',4),('816','Thành phố Gò Công','82',4),('817','Thị xã Cai Lậy','82',6),('818','Huyện Tân Phước','82',7),('819','Huyện Cái Bè','82',7),('820','Huyện Cai Lậy','82',7),('821','Huyện Châu Thành','82',7),('822','Huyện Chợ Gạo','82',7),('823','Huyện Gò Công Tây','82',7),('824','Huyện Gò Công Đông','82',7),('825','Huyện Tân Phú Đông','82',7),('829','Thành phố Bến Tre','83',4),('831','Huyện Châu Thành','83',7),('832','Huyện Chợ Lách','83',7),('833','Huyện Mỏ Cày Nam','83',7),('834','Huyện Giồng Trôm','83',7),('835','Huyện Bình Đại','83',7),('836','Huyện Ba Tri','83',7),('837','Huyện Thạnh Phú','83',7),('838','Huyện Mỏ Cày Bắc','83',7),('842','Thành phố Trà Vinh','84',4),('844','Huyện Càng Long','84',7),('845','Huyện Cầu Kè','84',7),('846','Huyện Tiểu Cần','84',7),('847','Huyện Châu Thành','84',7),('848','Huyện Cầu Ngang','84',7),('849','Huyện Trà Cú','84',7),('850','Huyện Duyên Hải','84',7),('851','Thị xã Duyên Hải','84',6),('855','Thành phố Vĩnh Long','86',4),('857','Huyện Long Hồ','86',7),('858','Huyện Mang Thít','86',7),('859','Huyện Vũng Liêm','86',7),('860','Huyện Tam Bình','86',7),('861','Thị xã Bình Minh','86',6),('862','Huyện Trà Ôn','86',7),('863','Huyện Bình Tân','86',7),('866','Thành phố Cao Lãnh','87',4),('867','Thành phố Sa Đéc','87',4),('868','Thành phố Hồng Ngự','87',4),('869','Huyện Tân Hồng','87',7),('870','Huyện Hồng Ngự','87',7),('871','Huyện Tam Nông','87',7),('872','Huyện Tháp Mười','87',7),('873','Huyện Cao Lãnh','87',7),('874','Huyện Thanh Bình','87',7),('875','Huyện Lấp Vò','87',7),('876','Huyện Lai Vung','87',7),('877','Huyện Châu Thành','87',7),('883','Thành phố Long Xuyên','89',4),('884','Thành phố Châu Đốc','89',4),('886','Huyện An Phú','89',7),('887','Thị xã Tân Châu','89',6),('888','Huyện Phú Tân','89',7),('889','Huyện Châu Phú','89',7),('890','Thị xã Tịnh Biên','89',6),('891','Huyện Tri Tôn','89',7),('892','Huyện Châu Thành','89',7),('893','Huyện Chợ Mới','89',7),('894','Huyện Thoại Sơn','89',7),('899','Thành phố Rạch Giá','91',4),('900','Thành phố Hà Tiên','91',4),('902','Huyện Kiên Lương','91',7),('903','Huyện Hòn Đất','91',7),('904','Huyện Tân Hiệp','91',7),('905','Huyện Châu Thành','91',7),('906','Huyện Giồng Riềng','91',7),('907','Huyện Gò Quao','91',7),('908','Huyện An Biên','91',7),('909','Huyện An Minh','91',7),('910','Huyện Vĩnh Thuận','91',7),('911','Thành phố Phú Quốc','91',4),('912','Huyện Kiên Hải','91',7),('913','Huyện U Minh Thượng','91',7),('914','Huyện Giang Thành','91',7),('916','Quận Ninh Kiều','92',5),('917','Quận Ô Môn','92',5),('918','Quận Bình Thuỷ','92',5),('919','Quận Cái Răng','92',5),('923','Quận Thốt Nốt','92',5),('924','Huyện Vĩnh Thạnh','92',7),('925','Huyện Cờ Đỏ','92',7),('926','Huyện Phong Điền','92',7),('927','Huyện Thới Lai','92',7),('930','Thành phố Vị Thanh','93',4),('931','Thành phố Ngã Bảy','93',4),('932','Huyện Châu Thành A','93',7),('933','Huyện Châu Thành','93',7),('934','Huyện Phụng Hiệp','93',7),('935','Huyện Vị Thuỷ','93',7),('936','Huyện Long Mỹ','93',7),('937','Thị xã Long Mỹ','93',6),('941','Thành phố Sóc Trăng','94',4),('942','Huyện Châu Thành','94',7),('943','Huyện Kế Sách','94',7),('944','Huyện Mỹ Tú','94',7),('945','Huyện Cù Lao Dung','94',7),('946','Huyện Long Phú','94',7),('947','Huyện Mỹ Xuyên','94',7),('948','Thị xã Ngã Năm','94',6),('949','Huyện Thạnh Trị','94',7),('950','Thị xã Vĩnh Châu','94',6),('951','Huyện Trần Đề','94',7),('954','Thành phố Bạc Liêu','95',4),('956','Huyện Hồng Dân','95',7),('957','Huyện Phước Long','95',7),('958','Huyện Vĩnh Lợi','95',7),('959','Thị xã Giá Rai','95',6),('960','Huyện Đông Hải','95',7),('961','Huyện Hoà Bình','95',7),('964','Thành phố Cà Mau','96',4),('966','Huyện U Minh','96',7),('967','Huyện Thới Bình','96',7),('968','Huyện Trần Văn Thời','96',7),('969','Huyện Cái Nước','96',7),('970','Huyện Đầm Dơi','96',7),('971','Huyện Năm Căn','96',7),('972','Huyện Phú Tân','96',7),('973','Huyện Ngọc Hiển','96',7);
/*!40000 ALTER TABLE `districts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forgotpassword`
--

DROP TABLE IF EXISTS `forgotpassword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forgotpassword` (
  `id` int NOT NULL AUTO_INCREMENT,
  `otp` int NOT NULL,
  `expirationTime` datetime NOT NULL,
  `user` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user` (`user`),
  CONSTRAINT `fk_user` FOREIGN KEY (`user`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forgotpassword`
--

LOCK TABLES `forgotpassword` WRITE;
/*!40000 ALTER TABLE `forgotpassword` DISABLE KEYS */;
INSERT INTO `forgotpassword` VALUES (2,856787,'2024-08-26 18:13:41','tandat'),(3,505245,'2024-09-03 20:52:04','tandat'),(4,804652,'2024-09-03 20:54:09','tandat'),(5,533224,'2024-09-03 20:54:55','tandat'),(6,391481,'2024-09-03 21:18:30','tandat'),(7,475081,'2024-09-03 21:22:12','tandat'),(8,238978,'2024-09-03 21:22:49','tandat'),(9,814375,'2024-09-03 21:31:52','tandat'),(10,751165,'2024-09-03 21:32:27','tandat'),(11,408411,'2024-09-03 21:32:59','tandat'),(12,422215,'2024-09-03 21:35:00','tandat'),(13,586735,'2024-09-03 21:37:58','tandat'),(14,515370,'2024-09-03 21:43:21','tandat'),(15,263254,'2024-09-03 21:45:37','tandat'),(16,149946,'2024-09-03 21:45:57','tandat'),(17,916946,'2024-09-03 21:46:16','tandat'),(18,398788,'2024-09-03 21:46:16','tandat'),(19,856781,'2024-09-03 21:49:10','tandat'),(20,922761,'2024-09-03 21:51:36','tandat'),(21,625942,'2024-09-03 22:02:58','tandat'),(22,825653,'2024-09-03 22:06:23','dattan'),(23,483263,'2024-09-03 22:22:48','tandat'),(24,664651,'2024-09-03 22:25:41','tandat'),(25,542723,'2024-09-03 22:28:08','dattan'),(26,998040,'2024-09-03 22:29:29','dattan'),(27,462137,'2024-09-03 22:52:30','dattan'),(28,296123,'2024-09-03 22:56:47','dattan'),(29,708259,'2024-09-03 23:04:31','dattan'),(30,917954,'2024-09-24 06:41:40','tonghiep'),(31,921889,'2024-09-24 06:43:45','tonghiep'),(32,288056,'2024-09-24 06:43:53','tonghiep'),(33,560608,'2024-09-24 06:54:41','tonghiep'),(34,898614,'2024-09-24 06:56:31','tonghiep'),(35,946982,'2024-09-24 06:57:37','tonghiep'),(36,602679,'2024-09-24 07:10:31','tonghiep'),(37,413094,'2024-09-24 07:28:29','tonghiep'),(38,619648,'2024-09-25 20:43:01','tonghiep');
/*!40000 ALTER TABLE `forgotpassword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `google_account`
--

DROP TABLE IF EXISTS `google_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `google_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `google_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pictureUrl` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `google_account`
--

LOCK TABLES `google_account` WRITE;
/*!40000 ALTER TABLE `google_account` DISABLE KEYS */;
INSERT INTO `google_account` VALUES (16,'117900273800637003247','Hiệp Trịnh','tonghiep25@gmail.com','https://lh3.googleusercontent.com/a/ACg8ocL986ATAoPVzGEqSpCk_kk0d1ZmTvn0ViVaLqa1jGeKh6hqS_kK=s96-c'),(17,'106567848555335050572','Trịnh Hiệp','hiepdata30tb@gmail.com','https://lh3.googleusercontent.com/a/ACg8ocIvnd7P1kM02q77GxbilIDzY80k7-iXvEpgemgAAX7Enctn7A=s96-c');
/*!40000 ALTER TABLE `google_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `image_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (2,'https://res.cloudinary.com/dsbkju7j9/image/upload/v1724053716/lnespy9jjetutn8duqtv.png',2),(3,'https://res.cloudinary.com/dsbkju7j9/image/upload/v1724061573/jtnvand062gbifgbszkw.png',3),(5,'https://res.cloudinary.com/dsbkju7j9/image/upload/v1725840739/dbnit0che01vxcegmmgw.png',1),(6,'https://res.cloudinary.com/dsbkju7j9/image/upload/v1724053716/lnespy9jjetutn8duqtv.png',1);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `available_quantity` int DEFAULT NULL,
  `branch_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `branch_id` (`branch_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
  CONSTRAINT `inventory_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (7,200,1,1),(8,120,1,2),(9,250,1,3),(10,100,3,1),(11,150,3,2),(12,150,3,3);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `unit_price` decimal(10,2) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `sale_order` (`id`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,5000000.00,2,1,1),(2,18190000.00,1,2,2),(3,19990000.00,1,2,3),(4,5000000.00,1,3,1),(5,18190000.00,1,3,2),(6,19990000.00,1,3,3),(7,18190000.00,1,4,2),(8,5000000.00,2,4,1),(9,18190000.00,1,5,2),(10,5000000.00,2,5,1);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `price` decimal(10,2) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `brand_id` (`brand_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Iphone 11 64GB (Likenew 99%)','Iphone đã qua sử dụng 1 thời gian nhưng còn mới',5000000.00,'2024-09-09',2,4),(2,'MacBook Air M1','MacBook Air M1 13 inch 8GB/256GB - Chính Hãng VN',18190000.00,'2024-08-19',3,4),(3,'Laptop Gaming Acer Nitro 5','Laptop Gaming Acer Nitro 5 Tiger AN515 58 52SP I5-12500H/8GB/512GB PCIE/VGA 4GB RTX3050/15.6',19990000.00,'2024-08-19',3,7);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provinces`
--

DROP TABLE IF EXISTS `provinces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provinces` (
  `code` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `administrative_unit_id` int DEFAULT NULL,
  `administrative_region_id` int DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `idx_provinces_region` (`administrative_region_id`),
  KEY `idx_provinces_unit` (`administrative_unit_id`),
  CONSTRAINT `provinces_administrative_region_id_fkey` FOREIGN KEY (`administrative_region_id`) REFERENCES `administrative_regions` (`id`),
  CONSTRAINT `provinces_administrative_unit_id_fkey` FOREIGN KEY (`administrative_unit_id`) REFERENCES `administrative_units` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provinces`
--

LOCK TABLES `provinces` WRITE;
/*!40000 ALTER TABLE `provinces` DISABLE KEYS */;
INSERT INTO `provinces` VALUES ('01','Thành phố Hà Nội',1,3),('02','Tỉnh Hà Giang',2,1),('04','Tỉnh Cao Bằng',2,1),('06','Tỉnh Bắc Kạn',2,1),('08','Tỉnh Tuyên Quang',2,1),('10','Tỉnh Lào Cai',2,2),('11','Tỉnh Điện Biên',2,2),('12','Tỉnh Lai Châu',2,2),('14','Tỉnh Sơn La',2,2),('15','Tỉnh Yên Bái',2,2),('17','Tỉnh Hoà Bình',2,2),('19','Tỉnh Thái Nguyên',2,1),('20','Tỉnh Lạng Sơn',2,1),('22','Tỉnh Quảng Ninh',2,1),('24','Tỉnh Bắc Giang',2,1),('25','Tỉnh Phú Thọ',2,1),('26','Tỉnh Vĩnh Phúc',2,3),('27','Tỉnh Bắc Ninh',2,3),('30','Tỉnh Hải Dương',2,3),('31','Thành phố Hải Phòng',1,3),('33','Tỉnh Hưng Yên',2,3),('34','Tỉnh Thái Bình',2,3),('35','Tỉnh Hà Nam',2,3),('36','Tỉnh Nam Định',2,3),('37','Tỉnh Ninh Bình',2,3),('38','Tỉnh Thanh Hóa',2,4),('40','Tỉnh Nghệ An',2,4),('42','Tỉnh Hà Tĩnh',2,4),('44','Tỉnh Quảng Bình',2,4),('45','Tỉnh Quảng Trị',2,4),('46','Tỉnh Thừa Thiên Huế',2,4),('48','Thành phố Đà Nẵng',1,5),('49','Tỉnh Quảng Nam',2,5),('51','Tỉnh Quảng Ngãi',2,5),('52','Tỉnh Bình Định',2,5),('54','Tỉnh Phú Yên',2,5),('56','Tỉnh Khánh Hòa',2,5),('58','Tỉnh Ninh Thuận',2,5),('60','Tỉnh Bình Thuận',2,5),('62','Tỉnh Kon Tum',2,6),('64','Tỉnh Gia Lai',2,6),('66','Tỉnh Đắk Lắk',2,6),('67','Tỉnh Đắk Nông',2,6),('68','Tỉnh Lâm Đồng',2,6),('70','Tỉnh Bình Phước',2,7),('72','Tỉnh Tây Ninh',2,7),('74','Tỉnh Bình Dương',2,7),('75','Tỉnh Đồng Nai',2,7),('77','Tỉnh Bà Rịa - Vũng Tàu',2,7),('79','Thành phố Hồ Chí Minh',1,7),('80','Tỉnh Long An',2,8),('82','Tỉnh Tiền Giang',2,8),('83','Tỉnh Bến Tre',2,8),('84','Tỉnh Trà Vinh',2,8),('86','Tỉnh Vĩnh Long',2,8),('87','Tỉnh Đồng Tháp',2,8),('89','Tỉnh An Giang',2,8),('91','Tỉnh Kiên Giang',2,8),('92','Thành phố Cần Thơ',1,8),('93','Tỉnh Hậu Giang',2,8),('94','Tỉnh Sóc Trăng',2,8),('95','Tỉnh Bạc Liêu',2,8),('96','Tỉnh Cà Mau',2,8);
/*!40000 ALTER TABLE `provinces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recently_viewed`
--

DROP TABLE IF EXISTS `recently_viewed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recently_viewed` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `viewed_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_recently_viewed_product` (`product_id`),
  KEY `fk_recently_viewed_user` (`username`),
  CONSTRAINT `fk_recently_viewed_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_recently_viewed_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recently_viewed`
--

LOCK TABLES `recently_viewed` WRITE;
/*!40000 ALTER TABLE `recently_viewed` DISABLE KEYS */;
/*!40000 ALTER TABLE `recently_viewed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_CUSTOMER'),(3,'ROLE_SUPER_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_order`
--

DROP TABLE IF EXISTS `sale_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` date DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `paid` tinyint(1) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `branchId` int DEFAULT NULL,
  `shipping_address_id` bigint DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `carrier_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `fk_branch` (`branchId`),
  KEY `fk_shipping_address` (`shipping_address_id`),
  KEY `fk_carrier_id` (`carrier_id`),
  CONSTRAINT `fk_branch` FOREIGN KEY (`branchId`) REFERENCES `branch` (`id`),
  CONSTRAINT `fk_carrier_id` FOREIGN KEY (`carrier_id`) REFERENCES `carrier` (`id`),
  CONSTRAINT `fk_shipping_address` FOREIGN KEY (`shipping_address_id`) REFERENCES `shipping_address` (`id`) ON DELETE SET NULL,
  CONSTRAINT `sale_order_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_order`
--

LOCK TABLES `sale_order` WRITE;
/*!40000 ALTER TABLE `sale_order` DISABLE KEYS */;
INSERT INTO `sale_order` VALUES (1,'2024-09-07',10000000.00,1,'tandat',1,NULL,NULL,NULL),(2,'2024-08-07',38180000.00,1,'hieptestzzzz1zz',3,NULL,NULL,NULL),(3,'2024-01-07',43180000.00,1,'tandat',1,NULL,NULL,NULL),(4,'2024-10-08',28190000.00,1,'tonghiep12',1,6,'adsd',2),(5,'2024-10-08',28190000.00,1,'tonghiep12',1,6,'adsdadsada',3);
/*!40000 ALTER TABLE `sale_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shipment_date` date DEFAULT NULL,
  `expected_delivery` date DEFAULT NULL,
  `status` enum('Pending','confirmed','Shipped','Delivered','Cancelled') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sale_order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sale_order_id` (`sale_order_id`),
  CONSTRAINT `fk_sale_order_id` FOREIGN KEY (`sale_order_id`) REFERENCES `sale_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
INSERT INTO `shipment` VALUES (1,'2024-09-08','2024-09-11','Delivered',NULL),(2,'2024-08-08','2024-08-09','Delivered',NULL),(3,'2024-01-07','2024-01-13','Delivered',NULL),(4,'2024-10-08','2024-10-08','Pending',5);
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_address`
--

DROP TABLE IF EXISTS `shipping_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping_address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone_number` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `province_code` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `district_code` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ward_code` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_default` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `province_code` (`province_code`),
  KEY `district_code` (`district_code`),
  KEY `ward_code` (`ward_code`),
  CONSTRAINT `shipping_address_ibfk_1` FOREIGN KEY (`username`) REFERENCES `customer` (`username`),
  CONSTRAINT `shipping_address_ibfk_2` FOREIGN KEY (`province_code`) REFERENCES `provinces` (`code`),
  CONSTRAINT `shipping_address_ibfk_3` FOREIGN KEY (`district_code`) REFERENCES `districts` (`code`),
  CONSTRAINT `shipping_address_ibfk_4` FOREIGN KEY (`ward_code`) REFERENCES `wards` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_address`
--

LOCK TABLES `shipping_address` WRITE;
/*!40000 ALTER TABLE `shipping_address` DISABLE KEYS */;
INSERT INTO `shipping_address` VALUES (1,'tonghiep12','trinh tong hiep','0383876000','724 Cộng Hòa','01','001','00001',0,'2024-09-28 11:11:40','2024-10-03 13:12:48'),(6,'tonghiep12','Hiep Trinh','0383876000','Tan phu','01','001','00001',1,'2024-10-03 12:45:37','2024-10-03 13:12:49'),(7,'hiepabc','tonghiep trinh','0383876012','724/44 LÊ VĂN LƯƠNG','79','767','27010',1,'2024-10-07 13:42:09','2024-10-07 13:42:09');
/*!40000 ALTER TABLE `shipping_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'HOT'),(2,'NEW'),(4,'BEST SELLER');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_product`
--

DROP TABLE IF EXISTS `tag_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `tag_product_ibfk_1` (`tag_id`),
  CONSTRAINT `tag_product_ibfk_1` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `tag_product_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_product`
--

LOCK TABLES `tag_product` WRITE;
/*!40000 ALTER TABLE `tag_product` DISABLE KEYS */;
INSERT INTO `tag_product` VALUES (1,1,1),(7,2,3),(8,2,3),(9,1,2);
/*!40000 ALTER TABLE `tag_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('dattan','$2a$10$fWbVdfkLL/LjXhtu/5UowuLYIYEBWSMo/Asr5daJTrwaxN85Tx8Ru','TanLee','Dat 1','https://res.cloudinary.com/dsbkju7j9/image/upload/v1724348760/l6oyw5t9itxjuze2ttn1.jpg',1,'2024-08-23'),('hiep','$2a$12$pkkKm.s43cksNzsKLOQm/OsgGnjbai9sR/0Mtt62iN/0Bix/Vz2iO','hiep','trinh','https://res.cloudinary.com/dsbkju7j9/image/upload/v1715327227/BoardingHouse/Image_avt/fly0xp3n6dpveqxh4wpv.jpg',3,'2022-07-27'),('hiepabc','$2a$10$RDoUWrdGt84StOlokmSSk.T4jEn.R6zJOzFsiVcjifH1nx4mnC/yS','tonghiep','trinh','https://res.cloudinary.com/dsbkju7j9/image/upload/v1728308526/smmelbguxubn5skujau2.jpg',2,NULL),('hieptestzzzz1zz','$2a$12$pkkKm.s43cksNzsKLOQm/OsgGnjbai9sR/0Mtt62iN/0Bix/Vz2iO','Trịnh ','J97','https://res.cloudinary.com/dsbkju7j9/image/upload/v1723889597/nkp97zuqmjgomz3nywax.jpg',1,'2022-08-23'),('tandat','$2a$12$pkkKm.s43cksNzsKLOQm/OsgGnjbai9sR/0Mtt62iN/0Bix/Vz2iO','Lý Tấn','Đạt','https://res.cloudinary.com/dsbkju7j9/image/upload/v1724492306/gpacoyinln4zh9ubpv2h.jpg',1,'2022-07-25'),('tonghiep','$2a$10$o0MiGbJE2EMAKblMKLb1LuLJALmQzqLxPa5xXj0hJdzuIT1TRj/ci','Trịnh','Hiệp','https://res.cloudinary.com/dsbkju7j9/image/upload/v1727513656/wpucabsr177siog0gjoz.jpg',2,'2022-07-25'),('tonghiep12','$2a$12$pkkKm.s43cksNzsKLOQm/OsgGnjbai9sR/0Mtt62iN/0Bix/Vz2iO','trinh','tong hiep','https://res.cloudinary.com/dsbkju7j9/image/upload/v1727521903/n0lnkm48nas2mmddfv22.jpg',2,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wards`
--

DROP TABLE IF EXISTS `wards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wards` (
  `code` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `district_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `administrative_unit_id` int DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `idx_wards_district` (`district_code`),
  KEY `idx_wards_unit` (`administrative_unit_id`),
  CONSTRAINT `wards_administrative_unit_id_fkey` FOREIGN KEY (`administrative_unit_id`) REFERENCES `administrative_units` (`id`),
  CONSTRAINT `wards_district_code_fkey` FOREIGN KEY (`district_code`) REFERENCES `districts` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wards`
--

LOCK TABLES `wards` WRITE;
/*!40000 ALTER TABLE `wards` DISABLE KEYS */;
INSERT INTO `wards` VALUES ('00001','Phường Phúc Xá','001',8),('26053','Phường Bửu Hòa','731',8),('26056','Phường Long Bình Tân','731',8),('26059','Phường Tân Vạn','731',8),('26062','Phường Tân Hạnh','731',8),('26794','Phường Linh Xuân','769',8),('26797','Phường Bình Chiểu','769',8),('26800','Phường Linh Trung','769',8),('26803','Phường Tam Bình','769',8),('26806','Phường Tam Phú','769',8),('26809','Phường Hiệp Bình Phước','769',8),('26812','Phường Hiệp Bình Chánh','769',8),('26815','Phường Linh Chiểu','769',8),('26818','Phường Linh Tây','769',8),('26821','Phường Linh Đông','769',8),('26824','Phường Bình Thọ','769',8),('26827','Phường Trường Thọ','769',8),('26830','Phường Long Bình','769',8),('26833','Phường Long Thạnh Mỹ','769',8),('26836','Phường Tân Phú','769',8),('26839','Phường Hiệp Phú','769',8),('26842','Phường Tăng Nhơn Phú A','769',8),('26845','Phường Tăng Nhơn Phú B','769',8),('26848','Phường Phước Long B','769',8),('26851','Phường Phước Long A','769',8),('26854','Phường Trường Thạnh','769',8),('26857','Phường Long Phước','769',8),('26860','Phường Long Trường','769',8),('26863','Phường Phước Bình','769',8),('26866','Phường Phú Hữu','769',8),('26998','Phường 08','766',8),('27001','Phường 09','766',8),('27004','Phường 14','766',8),('27007','Phường 15','766',8),('27010','Phường Tân Sơn Nhì','767',8),('27013','Phường Tây Thạnh','767',8),('27016','Phường Sơn Kỳ','767',8),('27019','Phường Tân Quý','767',8),('27022','Phường Tân Thành','767',8),('27025','Phường Phú Thọ Hòa','767',8),('27028','Phường Phú Thạnh','767',8),('27031','Phường Phú Trung','767',8),('27034','Phường Hòa Thạnh','767',8),('27037','Phường Hiệp Tân','767',8),('27040','Phường Tân Thới Hòa','767',8),('27043','Phường 04','768',8),('27046','Phường 05','768',8),('27049','Phường 09','768',8),('27052','Phường 07','768',8),('27055','Phường 03','768',8),('27058','Phường 01','768',8),('27061','Phường 02','768',8),('27064','Phường 08','768',8),('27067','Phường 15','768',8),('27070','Phường 10','768',8),('27073','Phường 11','768',8),('27076','Phường 17','768',8),('27085','Phường 13','768',8),('27088','Phường Thảo Điền','769',8),('27091','Phường An Phú','769',8),('27094','Phường An Khánh','769',8),('27097','Phường Bình Trưng Đông','769',8),('27100','Phường Bình Trưng Tây','769',8),('27109','Phường Cát Lái','769',8),('27112','Phường Thạnh Mỹ Lợi','769',8),('27115','Phường An Lợi Đông','769',8),('27118','Phường Thủ Thiêm','769',8),('27127','Phường 14','770',8),('27130','Phường 12','770',8),('27133','Phường 11','770',8),('27136','Phường 13','770',8),('27139','Phường Võ Thị Sáu','770',8),('27142','Phường 09','770',8),('27145','Phường 10','770',8),('27148','Phường 04','770',8),('27151','Phường 05','770',8),('27154','Phường 03','770',8),('27157','Phường 02','770',8),('27160','Phường 01','770',8),('27163','Phường 15','771',8),('27166','Phường 13','771',8),('27169','Phường 14','771',8),('27172','Phường 12','771',8),('27175','Phường 11','771',8),('27178','Phường 10','771',8),('27181','Phường 09','771',8),('27184','Phường 01','771',8),('27187','Phường 08','771',8),('27190','Phường 02','771',8),('27193','Phường 04','771',8),('27196','Phường 07','771',8),('27199','Phường 05','771',8),('27202','Phường 06','771',8),('27208','Phường 15','772',8),('27211','Phường 05','772',8),('27214','Phường 14','772',8),('27217','Phường 11','772',8),('27220','Phường 03','772',8),('27223','Phường 10','772',8),('27226','Phường 13','772',8),('27229','Phường 08','772',8),('27232','Phường 09','772',8),('27235','Phường 12','772',8),('27238','Phường 07','772',8),('27241','Phường 06','772',8),('29506','Xã Long Hiệp','849',10);
/*!40000 ALTER TABLE `wards` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-09 15:10:29
