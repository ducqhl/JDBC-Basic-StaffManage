-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: qlnv
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `nhanvien` (
  `MANV` varchar(10) NOT NULL,
  `HOTEN` varchar(225) NOT NULL,
  `PHAI` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 is ''Male'' and ''Female'' for otherwise.',
  `NGAYSINH` date NOT NULL,
  `DIACHI` varchar(225) NOT NULL,
  `LUONG` int(11) unsigned NOT NULL,
  `PHONG` int(11) NOT NULL,
  PRIMARY KEY (`MANV`),
  KEY `FK_MANV_idx` (`PHONG`) /*!80000 INVISIBLE */,
  CONSTRAINT `FK_PHONGBAN` FOREIGN KEY (`PHONG`) REFERENCES `phongban` (`maphg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('NV001','Trần Anh',0,'1997-05-05','Quận 5, TPHCM',1000000,1),('NV002','Trần Thanh',1,'1997-03-01','Quận 8, TPHCM',9000000,2),('NV003','Trần Trần',0,'1997-12-24','Quận 4, TPHCM',7000000,3),('NV007','TH-TrueMilk',1,'1997-04-12','1 District, HCMC',12500000,1);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phongban`
--

DROP TABLE IF EXISTS `phongban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `phongban` (
  `MAPHG` int(11) NOT NULL AUTO_INCREMENT,
  `TENPHG` varchar(45) NOT NULL,
  PRIMARY KEY (`MAPHG`),
  UNIQUE KEY `MAPHG_UNIQUE` (`MAPHG`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phongban`
--

LOCK TABLES `phongban` WRITE;
/*!40000 ALTER TABLE `phongban` DISABLE KEYS */;
INSERT INTO `phongban` VALUES (1,'Kinh Doanh'),(2,'Kế Toán'),(3,'IT'),(4,'Chăm Sóc Khách Hàng'),(9,'Security');
/*!40000 ALTER TABLE `phongban` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-27 18:17:07
