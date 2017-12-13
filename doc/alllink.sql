# Host: localhost  (Version: 5.5.47)
# Date: 2017-12-08 21:27:55
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "activity"
#

DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `seller_id` int(11) DEFAULT NULL COMMENT '商家id',
  `longitude` decimal(10,2) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,2) DEFAULT NULL COMMENT '纬度',
  `activity_name` varchar(100) DEFAULT NULL COMMENT '活动名称',
  `total_number` int(5) DEFAULT '0' COMMENT '总人数',
  `cost` decimal(10,2) DEFAULT NULL COMMENT '费用',
  `activity_photo` varchar(500) DEFAULT NULL COMMENT '活动照片  放文件夹',
  `activity_type` int(4) DEFAULT NULL COMMENT '活动类型',
  `begin_time` timestamp NULL DEFAULT NULL COMMENT '活动开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '活动结束时间',
  `enroll_mumber` int(11) DEFAULT NULL COMMENT '已报名人数',
  `activity_info` varchar(500) DEFAULT NULL COMMENT '活动信息',
  `activity_state` int(3) DEFAULT NULL COMMENT '活动状态：参见枚举表enum_type',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '活动创建时间',
  `modified_time` timestamp NULL DEFAULT NULL COMMENT '活动修改时间',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '活动审核时间',
  `address` varchar(255) DEFAULT NULL COMMENT '活动地址',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "activity"
#


#
# Structure for table "enum_type"
#

DROP TABLE IF EXISTS `enum_type`;
CREATE TABLE `enum_type` (
  `enum_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '枚举类型id',
  `enum_type` varchar(20) DEFAULT NULL,
  `enum_item` varchar(50) DEFAULT NULL,
  `enum_value` int(3) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`enum_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

#
# Data for table "enum_type"
#

/*!40000 ALTER TABLE `enum_type` DISABLE KEYS */;
INSERT INTO `enum_type` VALUES (1,'user_state','waiting_register',0,'待注册'),(2,'user_state','registered',1,'已注册'),(3,'seller_state','waiting_register',0,'待注册'),(4,'seller_state','registered',1,'已注册'),(5,'audit_state','audit_not_pass',-1,'审核不通过'),(6,'audit_state','audit_wait_check',0,'未审核'),(7,'audit_state','audit_pass',1,'审核通过'),(8,'order_state','order_cancel',-1,'订单取消'),(9,'order_state','order_not_pay',0,'订单未支付'),(10,'order_state','order_payed',1,'订单已支付'),(11,'activity_state','activity_not_pass',-1,'活动审核不通过'),(12,'activity_state','activity_waiting_check',0,'活动待审核'),(13,'activity_state','activity_checked',1,'活动审核通过，发布状态'),(14,'activity_state','activity_cancel',2,'活动取消'),(15,'order_eval_state','order_eval_delete',-1,'评价删除'),(16,'order_eval_state','order_waiting_eval',0,'未评价'),(17,'order_eval_state','order_evaled',1,'已评价'),(18,'activity_type','english',0,'外语'),(19,'activity_type','music',1,'音乐'),(20,'activity_type','art',2,'美术'),(21,'activity_type','voc_technology',3,'职业技术');
/*!40000 ALTER TABLE `enum_type` ENABLE KEYS */;

#
# Structure for table "order"
#

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `seller_id` int(11) DEFAULT NULL COMMENT '商家id',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `order_state` int(3) DEFAULT '0' COMMENT '订单状态:参见枚举表enum_type',
  `cost` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `activity_name` varchar(100) DEFAULT NULL COMMENT '活动名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
  `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订单修改时间',
  `evaluate_state` int(3) NOT NULL DEFAULT '0' COMMENT '订单评价状态：参见枚举表enum_type',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order"
#


#
# Structure for table "seller"
#

DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `seller_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `seller_name` varchar(20) DEFAULT NULL COMMENT '商家用户名',
  `state` int(3) DEFAULT NULL COMMENT '注册状态：参见枚举表enum_type:user_state',
  `nickname` varchar(40) DEFAULT NULL COMMENT '商家名称',
  `password` varchar(150) DEFAULT NULL COMMENT '密码',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `qq_number` varchar(20) DEFAULT NULL COMMENT 'qq',
  `wechat_number` varchar(20) DEFAULT NULL COMMENT '微信',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `photo` varchar(100) DEFAULT NULL COMMENT '头像',
  `account_balance` decimal(13,2) DEFAULT NULL COMMENT '余额',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `verification_code` varchar(7) DEFAULT NULL COMMENT '验证码',
  `code_creat_time` timestamp NULL DEFAULT NULL COMMENT '验证码生成时间',
  `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `salt` varchar(20) DEFAULT NULL,
  `bank_account` varchar(255) DEFAULT NULL COMMENT '银行账号',
  PRIMARY KEY (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "seller"
#


#
# Structure for table "seller_authinfo"
#

DROP TABLE IF EXISTS `seller_authinfo`;
CREATE TABLE `seller_authinfo` (
  `sauthinfo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家认证表id',
  `seller_id` int(11) DEFAULT NULL COMMENT '商家id',
  `cr_card_id` varchar(18) DEFAULT NULL COMMENT '法人身份证号',
  `cr_real_name` varchar(20) DEFAULT NULL COMMENT '法人代表',
  `cr_real_photo` varchar(100) DEFAULT NULL COMMENT '法人照片',
  `cr_card_front_photo` varchar(100) DEFAULT NULL COMMENT '法人身份证正面照',
  `cr_card_back_photo` varchar(100) DEFAULT NULL COMMENT '法人身份证反面照',
  `organziation_code_certificate` char(10) DEFAULT NULL COMMENT '组织机构代码证',
  `business_licence` varchar(255) DEFAULT NULL COMMENT '营业执照',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `audit_state` int(3) DEFAULT NULL COMMENT '审核状态：参见枚举表enum_type',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`sauthinfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "seller_authinfo"
#


#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(150) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(40) DEFAULT NULL COMMENT '昵称',
  `photo` varchar(100) DEFAULT NULL COMMENT '头像',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `qq_number` varchar(20) DEFAULT NULL COMMENT 'qq',
  `wechat_number` varchar(20) DEFAULT NULL COMMENT '微信',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `total_points` int(11) DEFAULT '0' COMMENT '总积分',
  `account_balance` decimal(10,2) DEFAULT '0.00' COMMENT '余额',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `identifying_code` int(6) DEFAULT NULL COMMENT '验证码',
  `code_creat_time` timestamp NULL DEFAULT NULL COMMENT '验证码生成时间',
  `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `state` int(3) DEFAULT '0' COMMENT '注册状态： 0：待注册，1：已注册',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user"
#


#
# Structure for table "user_authinfo"
#

DROP TABLE IF EXISTS `user_authinfo`;
CREATE TABLE `user_authinfo` (
  `uauthinfo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '认证id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `card_id` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `real_photo` varchar(100) DEFAULT NULL COMMENT '本人照片',
  `card_front_photo` varchar(100) DEFAULT NULL COMMENT '身份证正面照',
  `card_back_photo` varchar(100) DEFAULT NULL COMMENT '身份证背面照',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `auth_state` int(3) DEFAULT NULL COMMENT '审核状态：-1：审核 不通过，0：未审核，1：审核通过，参见枚举表enum_type',
  `auth_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`uauthinfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_authinfo"
#

