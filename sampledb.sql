/*
SQLyog v10.2 
MySQL - 5.7.20 : Database - sampledb
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sampledb` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `stock_bill` */

DROP TABLE IF EXISTS `stock_bill`;

CREATE TABLE `stock_bill` (
  `trade_id` varchar(20) DEFAULT NULL,
  `tradeuser_id` varchar(20) DEFAULT NULL,
  `stock_id` varchar(20) DEFAULT NULL,
  `stock_price` varchar(20) DEFAULT NULL,
  `stock_number` varchar(20) DEFAULT NULL,
  `untrade_number` varchar(20) DEFAULT NULL,
  `trade_type` int(11) DEFAULT NULL,
  `trade_status` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `stock_info` */

DROP TABLE IF EXISTS `stock_info`;

CREATE TABLE `stock_info` (
  `stock_id` varchar(20) DEFAULT NULL,
  `stock_name` varchar(20) DEFAULT NULL,
  `stock_py` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `stock_price` */

DROP TABLE IF EXISTS `stock_price`;

CREATE TABLE `stock_price` (
  `stock_id` varchar(20) DEFAULT NULL,
  `last_price` decimal(10,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `stock_record` */

DROP TABLE IF EXISTS `stock_record`;

CREATE TABLE `stock_record` (
  `stock_id` varchar(20) DEFAULT NULL,
  `stock_price` varchar(20) DEFAULT NULL,
  `buyuser_id` varchar(20) DEFAULT NULL,
  `selluser_id` varchar(20) DEFAULT NULL,
  `trade_number` varchar(20) DEFAULT NULL,
  `trade_time` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` varchar(50) DEFAULT NULL,
  `passwd` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
