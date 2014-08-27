-- MySQL dump 10.13  Distrib 5.6.20, for Win64 (x86_64)
--
-- Host: localhost    Database: stockforces
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `jgcg`
--

DROP TABLE IF EXISTS `jgcg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jgcg` (
  `quarter` char(10) COLLATE utf8_bin NOT NULL,
  `code` char(10) COLLATE utf8_bin NOT NULL,
  `stock_name` char(20) COLLATE utf8_bin NOT NULL,
  `inst_number` int(10) NOT NULL,
  `inst_number_increase` int(10) NOT NULL,
  `holding_ratio` double NOT NULL,
  `holding_ratio_increase` double NOT NULL,
  `a_shares_ratio` double NOT NULL,
  `a_shares_ratio_increase` double NOT NULL,
  PRIMARY KEY (`quarter`,`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='机构持股汇总';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jjzc`
--

DROP TABLE IF EXISTS `jjzc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjzc` (
  `quarter` char(10) COLLATE utf8_bin NOT NULL,
  `code` char(10) COLLATE utf8_bin NOT NULL,
  `stock_name` char(20) COLLATE utf8_bin NOT NULL,
  `inst_number` int(10) NOT NULL,
  `holding_number` double NOT NULL,
  `a_shares_ratio` double NOT NULL,
  `holding_increase` double NOT NULL,
  `holding_ratio` double NOT NULL,
  `pre_inst_number` int(10) NOT NULL,
  PRIMARY KEY (`quarter`,`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='基金重仓股';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qfii`
--

DROP TABLE IF EXISTS `qfii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qfii` (
  `quarter` char(10) COLLATE utf8_bin NOT NULL,
  `code` char(10) COLLATE utf8_bin NOT NULL,
  `stock_name` char(20) COLLATE utf8_bin NOT NULL,
  `inst_number` int(10) NOT NULL,
  `holding_number` double NOT NULL,
  `a_shares_ratio` double NOT NULL,
  `holding_increase` double NOT NULL,
  `holding_ratio` double NOT NULL,
  `pre_inst_number` int(10) NOT NULL,
  PRIMARY KEY (`quarter`,`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='QFII重仓股';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sbzc`
--

DROP TABLE IF EXISTS `sbzc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sbzc` (
  `quarter` char(10) COLLATE utf8_bin NOT NULL,
  `code` char(10) COLLATE utf8_bin NOT NULL,
  `stock_name` char(20) COLLATE utf8_bin NOT NULL,
  `inst_number` int(10) NOT NULL,
  `holding_number` double NOT NULL,
  `a_shares_ratio` double NOT NULL,
  `holding_increase` double NOT NULL,
  `holding_ratio` double NOT NULL,
  `pre_inst_number` int(10) NOT NULL,
  PRIMARY KEY (`quarter`,`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='社保重仓股';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-27 16:51:19
