-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.20 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 stockdata 的数据库结构
CREATE DATABASE IF NOT EXISTS `stockdata` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `stockdata`;


-- 导出  表 stockdata.jgcg 结构
CREATE TABLE IF NOT EXISTS `jgcg` (
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

-- 数据导出被取消选择。


-- 导出  表 stockdata.jjzc 结构
CREATE TABLE IF NOT EXISTS `jjzc` (
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

-- 数据导出被取消选择。


-- 导出  表 stockdata.qfii 结构
CREATE TABLE IF NOT EXISTS `qfii` (
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

-- 数据导出被取消选择。


-- 导出  表 stockdata.sbzc 结构
CREATE TABLE IF NOT EXISTS `sbzc` (
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

-- 数据导出被取消选择。


-- 导出  表 stockdata.stock 结构
CREATE TABLE IF NOT EXISTS `stock` (
  `code` char(10) COLLATE utf8_bin NOT NULL,
  `stock_name` char(20) COLLATE utf8_bin NOT NULL,
  `open` double NOT NULL,
  `high` double NOT NULL,
  `low` double NOT NULL,
  `close` double NOT NULL,
  `volume` double NOT NULL,
  `volume_value` double NOT NULL,
  `market_cap` double NOT NULL,
  `market_cap_free` double NOT NULL,
  `amplitude` double NOT NULL,
  `turnover_rate` double NOT NULL,
  `pb` double NOT NULL,
  `pe` double NOT NULL,
  `time` char(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='股票信息概览';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
