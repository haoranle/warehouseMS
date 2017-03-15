/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : goods

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-01-10 17:27:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `price` double(8,2) DEFAULT NULL,
  `anount` varchar(100) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  `t_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_id` (`t_id`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`t_id`) REFERENCES `goodstype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('A001', '小米5', '2000.00', '20', '性价比高', 'A002');
INSERT INTO `goods` VALUES ('A003', '荣耀6X', '999.00', '231', '', 'A001');
INSERT INTO `goods` VALUES ('A004', '李宁运动库01', '180.00', '52', '', 'A002');

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES ('A001', '电子设备');
INSERT INTO `goodstype` VALUES ('A002', '服饰');
INSERT INTO `goodstype` VALUES ('A004', '拉条1');
INSERT INTO `goodstype` VALUES ('A005', '食品');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `account` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `job` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('M001', 'lee', '20160101', '123456', '654321', 'in');
INSERT INTO `manager` VALUES ('M002', 'jobs', '20160210', '123456', '654321', 'out');
INSERT INTO `manager` VALUES ('M003', 'jeckson', '20153010', '123654', '123654', 'io');
INSERT INTO `manager` VALUES ('M004', 'BAOBAO', '2014021046', '1121', '110', 'ANKANG');

-- ----------------------------
-- Table structure for shelf
-- ----------------------------
DROP TABLE IF EXISTS `shelf`;
CREATE TABLE `shelf` (
  `id` varchar(50) NOT NULL,
  `number` varchar(50) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  `w_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `w_id` (`w_id`),
  CONSTRAINT `shelf_ibfk_1` FOREIGN KEY (`w_id`) REFERENCES `warehouse` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shelf
-- ----------------------------
INSERT INTO `shelf` VALUES ('S001', '电子001', '请方晴那111', 'W001');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('A001', '小米科技', '123456', '北京');
INSERT INTO `supplier` VALUES ('A002', '锤子科技', '12345611', '北京');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `id` varchar(50) NOT NULL,
  `number` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES ('W001', '电子01', '一号仓库', '存放手机，电脑等');
INSERT INTO `warehouse` VALUES ('W002', '衣帽02', '二号仓库', '急运111');

-- ----------------------------
-- Table structure for warein
-- ----------------------------
DROP TABLE IF EXISTS `warein`;
CREATE TABLE `warein` (
  `id` varchar(50) NOT NULL,
  `number` varchar(50) DEFAULT NULL,
  `intime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `m_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `m_id` (`m_id`),
  CONSTRAINT `warein_ibfk_1` FOREIGN KEY (`m_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warein
-- ----------------------------
INSERT INTO `warein` VALUES ('A001', '1.07进货单1', '2017-01-10 14:19:43', 'M001');
INSERT INTO `warein` VALUES ('A002', '1.08进货单', '2017-02-16 01:56:36', 'M003');
INSERT INTO `warein` VALUES ('A003', '11', '2017-01-10 14:22:28', 'M002');

-- ----------------------------
-- Table structure for wareinlist
-- ----------------------------
DROP TABLE IF EXISTS `wareinlist`;
CREATE TABLE `wareinlist` (
  `id` varchar(50) NOT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `amountless` varchar(100) DEFAULT NULL,
  `g_id` varchar(50) DEFAULT NULL,
  `s_id` varchar(50) DEFAULT NULL,
  `su_id` varchar(50) DEFAULT NULL,
  `wi_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `g_id` (`g_id`),
  KEY `s_id` (`s_id`),
  KEY `su_id` (`su_id`),
  KEY `wareinlist_fk_4` (`wi_id`),
  CONSTRAINT `wareinlist_fk_4` FOREIGN KEY (`wi_id`) REFERENCES `warein` (`id`),
  CONSTRAINT `wareinlist_ibfk_1` FOREIGN KEY (`g_id`) REFERENCES `goods` (`id`),
  CONSTRAINT `wareinlist_ibfk_2` FOREIGN KEY (`s_id`) REFERENCES `shelf` (`id`),
  CONSTRAINT `wareinlist_ibfk_3` FOREIGN KEY (`su_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wareinlist
-- ----------------------------
INSERT INTO `wareinlist` VALUES (' 111', '11', '11', 'A003', 'S001', 'A002', 'A002');
INSERT INTO `wareinlist` VALUES ('12312313', '11', '123123', 'A001', 'S001', 'A001', 'A001');
INSERT INTO `wareinlist` VALUES ('A001', '200', '200', 'A003', 'S001', 'A001', 'A001');
INSERT INTO `wareinlist` VALUES ('A002', '501', '501', 'A001', 'S001', 'A001', 'A001');

-- ----------------------------
-- Table structure for wareout
-- ----------------------------
DROP TABLE IF EXISTS `wareout`;
CREATE TABLE `wareout` (
  `id` varchar(50) NOT NULL,
  `number` varchar(50) DEFAULT NULL,
  `outtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `m_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `m_id` (`m_id`),
  CONSTRAINT `wareout_ibfk_1` FOREIGN KEY (`m_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wareout
-- ----------------------------
INSERT INTO `wareout` VALUES ('111', '11', '2017-01-10 14:20:36', 'M002');
INSERT INTO `wareout` VALUES ('A001', '1.7出货单1', '2017-01-10 14:21:10', 'M002');
INSERT INTO `wareout` VALUES ('A003', '1.8出货单', '2017-01-28 17:59:12', 'M001');

-- ----------------------------
-- Table structure for wareoutlist
-- ----------------------------
DROP TABLE IF EXISTS `wareoutlist`;
CREATE TABLE `wareoutlist` (
  `id` varchar(50) NOT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `g_id` varchar(50) DEFAULT NULL,
  `wil_id` varchar(50) DEFAULT NULL,
  `wo_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `g_id` (`g_id`),
  KEY `wil_id` (`wil_id`),
  KEY `wareoutlist_fk_4` (`wo_id`),
  CONSTRAINT `wareoutlist_fk_4` FOREIGN KEY (`wo_id`) REFERENCES `wareout` (`id`),
  CONSTRAINT `wareoutlist_ibfk_1` FOREIGN KEY (`g_id`) REFERENCES `goods` (`id`),
  CONSTRAINT `wareoutlist_ibfk_2` FOREIGN KEY (`wil_id`) REFERENCES `wareinlist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wareoutlist
-- ----------------------------
INSERT INTO `wareoutlist` VALUES ('111', '11', 'A004', ' 111', 'A003');
INSERT INTO `wareoutlist` VALUES ('A001', '2001', 'A003', 'A002', 'A003');
