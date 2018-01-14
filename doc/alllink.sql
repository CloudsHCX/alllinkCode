/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : alllink

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2018-01-14 16:48:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `seller_id` int(11) NOT NULL COMMENT '商家id',
  `longitude` decimal(16,6) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(16,6) DEFAULT NULL COMMENT '纬度',
  `activity_name` varchar(100) DEFAULT NULL COMMENT '活动名称',
  `total_number` int(5) DEFAULT '0' COMMENT '总人数',
  `total_cost` decimal(10,2) DEFAULT '0.00' COMMENT '活动结束时，总报名费用',
  `cost` decimal(10,2) DEFAULT '0.00' COMMENT '费用',
  `activity_photo` varchar(500) DEFAULT NULL COMMENT '活动照片  放文件夹',
  `activity_type` int(4) DEFAULT NULL COMMENT '活动类型',
  `begin_time` timestamp NULL DEFAULT NULL COMMENT '活动开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '活动结束时间',
  `enroll_number` int(11) NOT NULL DEFAULT '0' COMMENT '已报名人数',
  `activity_info` varchar(500) DEFAULT NULL COMMENT '活动信息',
  `activity_state` int(3) NOT NULL DEFAULT '0' COMMENT '活动状态',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '活动创建时间',
  `modified_time` timestamp NULL DEFAULT NULL COMMENT '活动修改时间',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '活动审核时间',
  `activity_notes` varchar(500) DEFAULT NULL COMMENT '活动不通过原因',
  `address` varchar(255) NOT NULL COMMENT '活动地址',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', '3', '1.000000', '1.000000', '哈哈哈告诉', '55', '0.00', '888.00', '\\pic\\5\\2\\2f50873474824add84b7ecca95785320_1514130272547.jpg', '3', '2017-12-12 00:16:00', '2019-12-12 00:16:00', '0', '差哈哈镜阿姐阿姐', '0', '2017-12-12 00:16:33', '2017-12-12 00:16:32', null, null, '纬度：29.890358经度：121.639843');
INSERT INTO `activity` VALUES ('44', '3', '1.000000', '1.000000', '游戏培训', '22', '0.00', '8888.00', '\\pic\\5\\2\\2f50873474824add84b7ecca95785320_1514130272547.jpg', '0', '2017-12-02 12:08:00', '2018-12-12 12:08:00', '1', '发干啊巴巴爸爸', '1', '2017-12-12 12:08:45', '2017-12-12 12:08:46', '2017-12-12 12:16:35', null, '纬度：29.890343经度：121.639814');
INSERT INTO `activity` VALUES ('45', '3', '1.000000', '1.000000', '测试培训', '88', '0.00', '100.00', '\\pic\\5\\2\\2f50873474824add84b7ecca95785320_1514130272547.jpg', '0', '2017-12-12 12:15:00', '2018-12-12 12:15:00', '0', '啊哈嘎嘎嘎嘎', '0', '2017-12-12 12:16:04', '2017-12-12 12:16:05', null, null, '纬度：29.890343经度：121.639814');
INSERT INTO `activity` VALUES ('46', '3', '1.000000', '1.000000', '合唱培训', '100', '0.00', '7000.00', '\\pic\\5\\2\\2f50873474824add84b7ecca95785320_1514130272547.jpg', '0', '2017-12-12 12:19:00', '2018-12-12 12:19:00', '0', '昂啊哈哈哈哈', '1', '2017-12-12 12:19:18', '2017-12-12 12:19:19', '2017-12-12 12:19:40', null, '纬度：29.890343经度：121.639814');
INSERT INTO `activity` VALUES ('47', '3', '1.000000', '1.000000', '浙江大学活动', '100', '6300.00', '1000.00', '\\pic\\5\\2\\2f50873474824add84b7ecca95785320_1514130272547.jpg', '0', '2017-12-12 12:59:00', '2017-12-12 12:59:00', '0', '啊哈哈尴尬', '3', '2017-12-12 12:59:54', '2017-12-12 12:59:54', null, null, '纬度：29.890343经度：121.639814');
INSERT INTO `activity` VALUES ('48', '3', '1.000000', '1.000000', 's405活动', '100', '0.00', '1000.00', '\\pic\\5\\2\\2f50873474824add84b7ecca95785320_1514130272547.jpg', '0', '2017-12-12 13:01:00', '2018-12-12 13:01:00', '0', '哈哈哈哈嘎嘎嘎嘎嘎v', '1', '2017-12-12 13:01:35', '2017-12-12 13:01:35', '2017-12-12 13:01:47', null, '纬度：29.890343经度：121.639814');
INSERT INTO `activity` VALUES ('102', '3', '121.647364', '29.866388', '象棋培训', '100', '0.00', '99.00', '\\pic\\5\\9\\f0c9c3672b5f4fb6ad6ccbb09dad5bab_1513958252803.jpg', '3', '2017-12-22 23:54:00', '2019-12-22 23:54:00', '0', '欢迎加入培训', '1', '2017-12-22 23:57:38', '2017-12-22 23:57:38', null, null, '浙江省宁波市鄞州区盛梅路');
INSERT INTO `activity` VALUES ('119', '3', '121.639852', '29.890366', '新东方培训', '22', '0.00', '100.00', '\\pic\\5\\2\\2f50873474824add84b7ecca95785320_1514130272547.jpg', '3', '2017-12-24 23:44:00', '2017-12-24 23:44:00', '0', '返回回合肥', '0', '2017-12-24 23:44:34', '2017-12-24 23:44:35', null, null, '中国浙江省宁波市鄞州区江南路1689');
INSERT INTO `activity` VALUES ('120', '3', '121.634912', '29.890228', '集合号', '365', '0.00', '55.00', '\\pic\\c\\9\\3e8404b026b544a2ad803576eff66987_1514130888505.jpg', '0', '2017-12-24 23:54:00', '2017-12-24 23:54:00', '0', '给哥哥', '1', '2017-12-24 23:54:58', '2017-12-24 23:54:58', null, null, '中国浙江省宁波市鄞州区聚贤路');
INSERT INTO `activity` VALUES ('131', '10', '0.000000', '0.000000', null, '0', '0.00', null, '\\pic\\5\\2\\2f50873474824add84b7ecca95785320_1514130272547.jpg', null, null, null, '0', '1', '1', '0000-00-00 00:00:00', null, null, null, '');
INSERT INTO `activity` VALUES ('132', '3', '120.096770', '30.309830', '1', '1', '0.00', '1.00', '\\pic\\3\\6\\c84dfb9808f846bb925aba427ede71be_p.jpg', '0', '2018-01-09 11:01:00', '2018-01-25 01:01:00', '0', '1', '0', '2018-01-07 23:42:18', null, null, null, '浙江大学');
INSERT INTO `activity` VALUES ('133', '4', '120.096770', '30.309830', '1', '1', '0.00', '1.00', '', '0', '2018-01-10 01:01:00', '2018-01-28 01:01:00', '0', '1', '0', '2018-01-07 23:49:47', null, null, null, '浙江大学');
INSERT INTO `activity` VALUES ('134', '4', null, null, '1', '1', '0.00', '1.00', '\\pic\\4\\d\\b133b8ded32c4548ba37643203621e79_a.jpg', '0', '2018-01-11 01:01:00', '2018-01-27 01:01:00', '0', '1', '0', '2018-01-07 23:52:22', null, null, null, '浙江大学');
INSERT INTO `activity` VALUES ('135', '3', '120.096770', '30.309830', '11', '11', '0.00', '11.00', '\\pic\\3\\6\\c99c6402be79417094951d197d885e94_p.jpg', '0', '2018-01-24 11:11:00', '2018-01-31 11:11:00', '0', '11', '0', '2018-01-07 23:57:06', null, null, null, '浙江大学');
INSERT INTO `activity` VALUES ('136', '3', '121.628572', '29.866033', '测试用例111', '11', '0.00', '11.00', '\\pic\\3\\6\\e12d1156c09344c0ae61dd0408f9af42_p.jpg', '0', '2018-01-09 11:11:00', '2018-01-23 11:11:00', '0', '测试用例111', '0', '2018-01-08 00:30:20', null, null, null, '宁波');
INSERT INTO `activity` VALUES ('137', '3', '121.628572', '29.866033', '测试3', '11', '0.00', '11.00', '\\pic\\3\\6\\7d43b025bb7a4cf1b876cdc9cb28df2b_p.jpg', '0', '2018-01-16 11:11:00', '2018-01-30 11:11:00', '0', '测试3', '0', '2018-01-08 00:42:38', null, null, null, '宁波');
INSERT INTO `activity` VALUES ('138', '3', '121.628572', '29.866033', '测试5', '11', '0.00', '11.00', '\\pic\\3\\6\\43120d74434f4fcf80a766437c66592f_p.jpg', '0', '2018-01-15 11:01:00', '2018-01-23 11:11:00', '0', '测试5', '0', '2018-01-08 00:45:27', null, null, null, '宁波');
INSERT INTO `activity` VALUES ('139', '3', '121.628572', '29.866033', 'aaa', '11', '0.00', '11.00', '\\pic\\3\\6\\54b0b9e890c8424fb16989b5717df0d6_p.jpg', '0', '2018-01-16 11:11:00', '2018-01-30 11:01:00', '0', 'aaa', '0', '2018-01-08 00:47:00', null, null, null, '宁波');
INSERT INTO `activity` VALUES ('140', '3', '121.628572', '29.866033', '11111', '11', '0.00', '11.00', '\\pic\\3\\6\\df8ad3a54ae14dfcb858064e18b3beec_p.jpg', '0', '2018-01-15 11:11:00', '2018-01-23 11:11:00', '0', '111111', '0', '2018-01-08 00:52:41', null, null, null, '宁波');
INSERT INTO `activity` VALUES ('141', '3', '121.628572', '29.866033', '测试66666', '11', '0.00', '11.00', '\\pic\\3\\6\\8a7ca27010684169ab08a8a089b96eb0_p.jpg', '0', '2018-01-09 11:11:00', '2018-01-31 11:11:00', '0', '测试66666', '0', '2018-01-08 00:58:54', null, null, null, '宁波');
INSERT INTO `activity` VALUES ('142', '3', '121.628572', '29.866033', '测试88888', '11', '0.00', '11.00', '\\pic\\3\\6\\702aa79c06ab4e79a8d3718e31220b42_p.jpg', '0', '2018-01-09 11:11:00', '2018-01-30 11:11:00', '0', '测试888', '0', '2018-01-08 01:00:31', null, null, null, '宁波');
INSERT INTO `activity` VALUES ('143', '3', '120.096770', '30.309830', '最后一次测试', '111', '0.00', '11.00', '\\pic\\3\\6\\1623f0b99f394f3882a3ad8ed6f894d7_p.jpg', '0', '2018-01-09 11:11:00', '2018-01-31 11:11:00', '0', '浙江大学', '0', '2018-01-08 01:02:12', null, null, null, '浙江大学');
INSERT INTO `activity` VALUES ('144', '3', '120.096770', '30.309830', '美术', '40', '0.00', '100.00', '\\pic\\4\\d\\f00fb452ed6a4b24a0953236a235d1f9_a.jpg', '2', '2018-01-17 12:34:00', '2018-01-25 13:45:00', '0', '美术培训', '0', '2018-01-11 15:43:02', null, null, null, '浙江大学');

-- ----------------------------
-- Table structure for enum_type
-- ----------------------------
DROP TABLE IF EXISTS `enum_type`;
CREATE TABLE `enum_type` (
  `enum_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '枚举类型id',
  `enum_type` varchar(20) DEFAULT NULL,
  `enum_item` varchar(50) DEFAULT NULL,
  `enum_value` int(3) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`enum_id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of enum_type
-- ----------------------------
INSERT INTO `enum_type` VALUES ('1', 'user_state', 'waiting_register', '0', '待注册');
INSERT INTO `enum_type` VALUES ('2', 'user_state', 'registered', '1', '已注册');
INSERT INTO `enum_type` VALUES ('3', 'seller_state', 'waiting_register', '0', '待注册');
INSERT INTO `enum_type` VALUES ('4', 'seller_state', 'registered', '1', '已注册');
INSERT INTO `enum_type` VALUES ('5', 'audit_state', 'audit_not_pass', '-1', '审核不通过');
INSERT INTO `enum_type` VALUES ('6', 'audit_state', 'audit_wait_check', '0', '未审核');
INSERT INTO `enum_type` VALUES ('7', 'audit_state', 'audit_pass', '1', '审核通过');
INSERT INTO `enum_type` VALUES ('8', 'order_state', 'order_cancel', '-1', '订单取消');
INSERT INTO `enum_type` VALUES ('9', 'order_state', 'order_not_pay', '0', '订单未支付');
INSERT INTO `enum_type` VALUES ('10', 'order_state', 'order_payed', '1', '订单已支付');
INSERT INTO `enum_type` VALUES ('11', 'activity_state', 'activity_not_pass', '-1', '活动审核不通过');
INSERT INTO `enum_type` VALUES ('12', 'activity_state', 'activity_waiting_check', '0', '活动待审核');
INSERT INTO `enum_type` VALUES ('13', 'activity_state', 'activity_checked', '1', '活动审核通过，发布状态');
INSERT INTO `enum_type` VALUES ('14', 'activity_state', 'activity_cancel', '2', '活动取消');
INSERT INTO `enum_type` VALUES ('15', 'order_eval_state', 'order_eval_delete', '-1', '评价删除');
INSERT INTO `enum_type` VALUES ('16', 'order_eval_state', 'order_waiting_eval', '0', '未评价');
INSERT INTO `enum_type` VALUES ('17', 'order_eval_state', 'order_evaled', '1', '已评价');
INSERT INTO `enum_type` VALUES ('18', 'activity_type', 'english', '0', '外语');
INSERT INTO `enum_type` VALUES ('19', 'activity_type', 'music', '1', '音乐');
INSERT INTO `enum_type` VALUES ('20', 'activity_type', 'art', '2', '美术');
INSERT INTO `enum_type` VALUES ('21', 'activity_type', 'voc_technology', '3', '职业技术');
INSERT INTO `enum_type` VALUES ('22', 'alipay_sign', 'alipay_invalid', '0', '支付无效状态');
INSERT INTO `enum_type` VALUES ('23', 'alipay_sign', 'alipay_paied', '1', '已支付');
INSERT INTO `enum_type` VALUES ('24', 'alipay_sign', 'alipay_refund', '-1', '已退款');
INSERT INTO `enum_type` VALUES ('25', 'order_state', 'order_refund', '3', '订单已退款');
INSERT INTO `enum_type` VALUES ('26', 'payment_channel', 'no_way', '0', '无');
INSERT INTO `enum_type` VALUES ('27', 'payment_channel', 'alipay', '1', '支付宝');
INSERT INTO `enum_type` VALUES ('28', 'payment_channel', 'weixin', '2', '微信');
INSERT INTO `enum_type` VALUES ('29', 'order_state', 'order_refund_check', '2', '订单退款审核中');

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `evaluate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价id',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `evaluate_level` int(3) DEFAULT '0' COMMENT '评价等级',
  `evaluate_content` varchar(200) DEFAULT '' COMMENT '评价内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '评价时间',
  PRIMARY KEY (`evaluate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evaluate
-- ----------------------------
INSERT INTO `evaluate` VALUES ('1', '47', '11', '3', '1', '一', '2018-01-05 22:24:41');
INSERT INTO `evaluate` VALUES ('2', '47', '12', '3', '1', '二', '2018-01-31 15:27:34');
INSERT INTO `evaluate` VALUES ('3', '47', '11', '3', '2', '三', '2018-01-24 15:27:39');
INSERT INTO `evaluate` VALUES ('4', '47', '12', '3', '3', '四', '2018-01-30 15:28:07');
INSERT INTO `evaluate` VALUES ('5', '47', '12', '3', '4', '四', '2018-01-31 16:49:53');
INSERT INTO `evaluate` VALUES ('6', '47', '11', '3', '5', '五', '2018-01-29 16:50:14');

-- ----------------------------
-- Table structure for orderu
-- ----------------------------
DROP TABLE IF EXISTS `orderu`;
CREATE TABLE `orderu` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_id_str` varchar(40) DEFAULT '' COMMENT '重新拼接后的order_id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `seller_id` int(11) DEFAULT NULL COMMENT '商家id',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `order_state` int(3) DEFAULT '0' COMMENT '订单状态:参见枚举表enum_type',
  `cost` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `activity_name` varchar(100) DEFAULT '' COMMENT '活动名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
  `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订单修改时间',
  `evaluate_state` int(3) NOT NULL DEFAULT '0' COMMENT '订单评价状态：参见枚举表enum_type',
  `payment_channel` int(3) DEFAULT '0' COMMENT '字符渠道：0：无，1：支付宝，2：微信',
  `refund_reason` varchar(200) DEFAULT '' COMMENT '退款原因',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderu
-- ----------------------------
INSERT INTO `orderu` VALUES ('1', '1122013600002', '2', '3', '1', '1', '0.04', '测试1', '2017-12-20 17:02:39', '2018-01-07 18:03:06', '0', '0', null);
INSERT INTO `orderu` VALUES ('2', '1122069750002', '2', '1', '1', '-2', '0.04', '测试1', '2017-12-20 17:05:44', '2017-12-22 22:36:45', '0', '0', null);
INSERT INTO `orderu` VALUES ('3', '1122271190002', '2', '1', '1', '-1', '0.04', '测试1', '2017-12-22 17:19:55', '2017-12-22 21:23:25', '0', '0', null);
INSERT INTO `orderu` VALUES ('4', '1122246350002', '2', '1', '1', '-2', '0.04', '测试1', '2017-12-22 19:08:35', '2017-12-22 22:36:38', '0', '0', '不想要了啦啦啦');
INSERT INTO `orderu` VALUES ('5', '1122273520002', '2', '1', '1', '-2', '0.04', '测试1', '2017-12-22 22:01:52', '2017-12-22 22:36:25', '0', '1', null);
INSERT INTO `orderu` VALUES ('6', '1122439440002', '2', '1', '1', '1', '0.04', '测试1', '2017-12-24 10:07:49', '2017-12-24 10:08:06', '0', '1', null);
INSERT INTO `orderu` VALUES ('7', '1122444080001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 19:54:18', '2017-12-24 19:57:55', '0', '0', null);
INSERT INTO `orderu` VALUES ('8', '1122436210001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 19:57:28', '2017-12-24 20:18:40', '0', '1', null);
INSERT INTO `orderu` VALUES ('9', '1122453650001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:08:06', '2017-12-24 20:18:45', '0', '1', null);
INSERT INTO `orderu` VALUES ('10', '1122453780001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:11:49', '2017-12-24 20:18:48', '0', '1', null);
INSERT INTO `orderu` VALUES ('11', '1122464100001', '1', '3', '1', '-2', '0.04', '测试1', '2017-12-24 20:12:50', '2018-01-05 22:23:31', '0', '0', null);
INSERT INTO `orderu` VALUES ('12', '1122428410001', '1', '3', '1', '-2', '0.04', '测试1', '2017-12-24 20:13:44', '2018-01-05 22:23:32', '0', '0', null);
INSERT INTO `orderu` VALUES ('13', '1122485870001', '1', '3', '1', '-2', '0.04', '测试1', '2017-12-24 20:19:23', '2018-01-05 22:23:34', '0', '0', null);
INSERT INTO `orderu` VALUES ('14', '1122456340001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:19:46', '2017-12-24 20:20:07', '0', '0', null);
INSERT INTO `orderu` VALUES ('15', '1122468340001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:20:09', '2017-12-24 20:22:05', '0', '1', null);
INSERT INTO `orderu` VALUES ('16', '1122451530001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:22:10', '2017-12-24 20:22:24', '0', '0', null);
INSERT INTO `orderu` VALUES ('17', '1122447550001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:22:29', '2017-12-24 20:24:16', '0', '0', null);
INSERT INTO `orderu` VALUES ('18', '1122417280001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:22:42', '2017-12-24 20:24:18', '0', '1', null);
INSERT INTO `orderu` VALUES ('19', '1122477830001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:23:29', '2017-12-24 20:24:20', '0', '1', null);
INSERT INTO `orderu` VALUES ('20', '1122484250001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:24:23', '2017-12-24 20:26:40', '0', '0', null);
INSERT INTO `orderu` VALUES ('21', '1122475220001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:24:39', '2017-12-24 20:26:44', '0', '0', null);
INSERT INTO `orderu` VALUES ('22', '1122467710001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:24:51', '2017-12-24 20:26:47', '0', '0', null);
INSERT INTO `orderu` VALUES ('23', '1122412780001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:25:09', '2017-12-24 20:26:51', '0', '0', null);
INSERT INTO `orderu` VALUES ('24', '1122453390001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:25:32', '2017-12-24 20:27:09', '0', '0', null);
INSERT INTO `orderu` VALUES ('25', '1122415800001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:27:43', '2017-12-24 20:30:31', '0', '0', null);
INSERT INTO `orderu` VALUES ('26', '1122437260001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:30:04', '2017-12-24 20:30:38', '0', '0', null);
INSERT INTO `orderu` VALUES ('27', '1122411170001', '1', '1', '1', '-2', '0.04', '测试1', '2017-12-24 20:32:16', '2017-12-24 20:32:27', '0', '0', null);
INSERT INTO `orderu` VALUES ('28', '1122445790001', '1', '1', '1', '1', '0.04', '测试1', '2017-12-24 20:33:44', '2017-12-24 20:34:12', '0', '1', null);
INSERT INTO `orderu` VALUES ('29', '1010523740003', '3', '1', '1', '-2', '0.04', '测试1', '2018-01-05 16:22:51', '2018-01-05 17:45:13', '0', '0', null);
INSERT INTO `orderu` VALUES ('30', '1010562630003', '3', '1', '1', '-2', '0.04', '测试1', '2018-01-05 16:24:51', '2018-01-05 17:45:18', '0', '0', null);
INSERT INTO `orderu` VALUES ('31', '1010587600003', '3', '1', '1', '-2', '0.04', '测试1', '2018-01-05 16:54:40', '2018-01-05 17:45:20', '0', '0', null);
INSERT INTO `orderu` VALUES ('32', '1010561340003', '3', '1', '1', '-1', '0.04', '测试1', '2018-01-05 17:26:25', '2018-01-05 18:05:03', '0', '0', null);
INSERT INTO `orderu` VALUES ('33', '2010826060003', '3', '3', '120', '0', '55.00', '集合号', '2018-01-08 02:31:58', '2018-01-08 02:31:58', '0', '0', '');
INSERT INTO `orderu` VALUES ('34', '2010854290003', '3', '3', '120', '0', '55.00', '集合号', '2018-01-08 02:41:58', '2018-01-08 02:41:58', '0', '0', '');
INSERT INTO `orderu` VALUES ('35', '2010827590003', '3', null, '120', '0', '55.00', '集合号', '2018-01-08 02:43:51', '2018-01-08 02:43:51', '0', '0', '');
INSERT INTO `orderu` VALUES ('36', '2010893770003', '3', '3', '120', '0', '55.00', '集合号', '2018-01-08 02:46:52', '2018-01-08 02:46:52', '0', '0', '');

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `seller_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `seller_name` varchar(20) DEFAULT NULL COMMENT '商家用户名',
  `state` int(3) DEFAULT '0' COMMENT '注册状态',
  `nickname` varchar(40) DEFAULT NULL COMMENT '商家名称',
  `password` varchar(150) DEFAULT NULL COMMENT '密码',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `alipay_number` varchar(20) DEFAULT NULL,
  `qq_number` varchar(20) DEFAULT NULL COMMENT 'qq',
  `wechat_number` varchar(20) DEFAULT NULL COMMENT '微信',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `photo` varchar(100) DEFAULT NULL COMMENT '头像',
  `account_balance` decimal(13,2) DEFAULT '0.00' COMMENT '余额',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `verification_code` varchar(7) DEFAULT NULL COMMENT '验证码',
  `code_creat_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '验证码生成时间',
  `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `salt` varchar(20) DEFAULT NULL,
  `bank_account` varchar(255) DEFAULT NULL COMMENT '银行账号',
  PRIMARY KEY (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seller
-- ----------------------------
INSERT INTO `seller` VALUES ('2', '1', '1', '', '071e9487bfbacd6f61b7556e4845888266dc1c31a12b5f97ab5de162e3f2e9a8', '1', '13588253872', null, '1', '1', '1', '\\pic\\4\\d\\fe5f121dc7494c6dae4b4884be087242_a.jpg', '0.00', null, '766468', '2018-01-06 15:27:48', '2018-01-06 15:28:13', '0ac', null);
INSERT INTO `seller` VALUES ('3', '白狐狸', '1', null, '9f760b40093caeb87ecd70774ed756ba218916f7a8704fb402560dd6beef82e1', '123456', '12345678901', null, null, '123456', '123456', '\\pic\\3\\5\\0ef3a455da5d4e75bf0dc6baccbd060c_1514169639560.jpg', '18900.00', null, '329617', '2018-01-06 15:38:40', '2018-01-06 15:38:55', 'f19-49f4-8', null);
INSERT INTO `seller` VALUES ('4', '白狐狸', '2', null, '194ca347d05ba271b7a254817737562b048edbbf74f4444f6fb16d1e1c32fa93', '123456', '13056773271', '123456', null, '123456', '123456', '\\pic\\b\\4\\24c833cbbae741768209527e84c99d25_1514814463247.jpg', '0.00', null, '223664', '2018-01-01 21:46:55', '2018-01-01 21:47:46', '1c1-4', null);
INSERT INTO `seller` VALUES ('5', '1234', '1', null, '882c2b5b86c9c097acc14c60e697b663156e504e39d1b7e9170ba5b1865370bd', '1342', '18538841164', '1234', null, '1243', '142', null, '0.00', null, '632638', '2018-01-01 20:23:44', '2018-01-01 20:26:11', '60a-49ca-b', null);
INSERT INTO `seller` VALUES ('6', 'han', '1', null, 'cf6f0d6e065b9d997fc9b84c67feb41fc6912ca34476e0f287e0fa24a74f9c76', '123', '12345678904', '123', null, '123', '1231', '\\pic\\3\\6\\451ac1da60f64c9791d9aa298ab10434_p.jpg', '0.00', '2017-12-22 19:17:20', '474895', '2018-01-02 13:20:57', '2018-01-02 13:21:04', '6e-79f', null);
INSERT INTO `seller` VALUES ('9', '张三三', '2', null, 'b096a7334e081e9229614daf28b33e394643bc4593e70254494b31e6b5eeec79', '张', '18538879811', '123456789', null, null, '123456789@qq.com', '\\pic\\4\\d\\6f637971d3cc4393ba8cde31cc140932_a.jpg', null, '2017-12-25 00:09:19', '474210', '2017-12-25 00:09:19', '2017-12-25 01:26:44', '654-6', null);
INSERT INTO `seller` VALUES ('10', 'han', '1', null, '36e36f8fda038689f784e0048a532943fd3ab90a228e61566463197db8d9f3dc', '1234', '12345678907', '342', null, '12431', '123@qq.com', '\\pic\\4\\d\\a37d150042274048ad96ea5c461625a0_a.jpg', null, '2017-12-25 10:24:51', '001602', '2017-12-25 10:24:51', '2017-12-25 11:11:02', '07-733', null);
INSERT INTO `seller` VALUES ('11', '123', '1', null, 'bd9d7c843e1823f8a73b10e7b4bdfccc266e74d1cff9a57923d39d2ca10d2661', '123', '12345678908', '13', null, '123', '123', '\\pic\\3\\6\\b075e8c25e4144aea205a8a2c20b0c44_p.jpg', null, '2017-12-25 10:37:18', '844837', '2017-12-25 10:37:18', '2017-12-25 10:38:08', '69a-0', null);
INSERT INTO `seller` VALUES ('12', '123', '1', null, '429ee3e574f72a85e5481b4de1cd6b1282a960bc93b81dca052dbfa4a8079360', '132', '12345678909', '132', null, '123', '123123', '\\pic\\3\\6\\168885e8935249418c76873e8eaa2dbe_p.jpg', null, '2017-12-25 10:39:53', '553727', '2017-12-25 10:39:53', '2017-12-25 10:40:25', '-97de-41', null);
INSERT INTO `seller` VALUES ('13', '韩', '1', null, '9193baa98965441f8d8f2abfa9b07b296f5396632a3844d05e298bd0283bbeed', '123456', '12345678900', '12345667', null, '12345', '123456', '\\pic\\4\\d\\5176531fb0164931ac471993a7d44a7d_a.jpg', null, '2017-12-25 10:42:28', '642810', '2017-12-25 10:42:28', '2017-12-25 10:43:12', '4840', null);
INSERT INTO `seller` VALUES ('14', '韩', '1', null, '4b2fed35ee5362adab21f4bf98407b585599195c715d30e1550d340f71fb41ae', '123', '12345678911', '123', null, '123', '123', '\\pic\\3\\6\\916a78ac64844bfab8c648913740f55a_p.jpg', null, '2017-12-25 10:44:37', '033505', '2017-12-25 10:44:37', '2017-12-25 10:45:41', 'e5c5-406f', null);
INSERT INTO `seller` VALUES ('15', '123', '1', null, '946d3d4fb7883f8f8f00d9eaa4dbb535006d50ef02ed49e6a53867357b0c02ee', '123', '12345678912', '321', null, '1231', '123', '\\pic\\4\\d\\5e0170a1184e42429c1b94cece6840c7_a.jpg', null, '2017-12-25 10:49:48', '213538', '2017-12-25 10:49:48', '2017-12-25 10:50:28', 'e65-4d70-8', null);
INSERT INTO `seller` VALUES ('16', '1', '1', null, '11be892163e059bdc9979884644e3023597185e06a1a4ebb45d2bd699de34e79', '132', '12345678913', '123', null, '132', '132', '\\pic\\3\\6\\95600ffe4f9040eebfd2a47f4a15f627_p.jpg', null, '2017-12-25 10:52:15', '255859', '2017-12-25 10:52:15', '2018-01-01 22:06:55', 'b53', null);
INSERT INTO `seller` VALUES ('24', null, '1', null, 'f707fdda7c874ff49ebfb2c88a2860c5ff4ce3d94a21efb76566ad0f92c9ad57', null, '12345678903', null, null, null, null, null, '0.00', '2018-01-05 21:38:08', '565173', '2018-01-05 21:38:08', '2018-01-05 21:38:19', 'a', null);
INSERT INTO `seller` VALUES ('25', null, '0', null, null, null, '13588253982', null, null, null, null, null, '0.00', '2018-01-07 19:44:29', '978589', '2018-01-07 19:48:50', '2018-01-07 19:48:50', '0851', null);
INSERT INTO `seller` VALUES ('26', null, '0', null, null, null, '13588253682', null, null, null, null, null, '0.00', '2018-01-07 20:00:55', '343993', '2018-01-07 20:00:55', '2018-01-07 20:00:55', 'ab8-8', null);
INSERT INTO `seller` VALUES ('27', null, '1', null, '77d1a43779da7337136488e8a721c58d2ca6e9bcbd4ff9e1bbbfb7d43bf57036', null, '18487290000', null, null, null, null, null, '0.00', '2018-01-11 15:49:30', '525273', '2018-01-11 15:49:30', '2018-01-11 15:49:46', '97', null);
INSERT INTO `seller` VALUES ('28', null, '1', null, 'd4257a68445d10286993332dd54ba814896c0f253d79c98c79a1adce9c7cb05e', null, '12345678914', null, null, null, null, null, '0.00', '2018-01-11 16:04:14', '623293', '2018-01-11 16:04:14', '2018-01-11 16:04:28', 'a828-4490', null);

-- ----------------------------
-- Table structure for seller_authinfo
-- ----------------------------
DROP TABLE IF EXISTS `seller_authinfo`;
CREATE TABLE `seller_authinfo` (
  `sauthinfo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家认证表id',
  `seller_id` int(11) DEFAULT NULL COMMENT '商家id',
  `cr_card_id` varchar(18) DEFAULT NULL COMMENT '法人身份证号',
  `cr_real_name` varchar(20) DEFAULT NULL COMMENT '法人代表',
  `cr_card_front_photo` varchar(100) DEFAULT NULL COMMENT '法人身份证正面照',
  `cr_card_back_photo` varchar(100) DEFAULT NULL COMMENT '法人身份证反面照',
  `organziation_code_certificate` char(10) DEFAULT NULL COMMENT '组织机构代码证',
  `business_licence` varchar(255) DEFAULT NULL COMMENT '营业执照',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `audit_state` int(3) DEFAULT NULL COMMENT '审核状态',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`sauthinfo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seller_authinfo
-- ----------------------------
INSERT INTO `seller_authinfo` VALUES ('31', '0', '123', '123', '\\pic\\3\\6\\34fea409ed5b4395a2b784ddf8f64546_p.jpg', '\\pic\\3\\6\\f1f7e5e341c04827acedbe51225014c0_p.jpg', '132', '\\pic\\3\\6\\34396b56d3774ec19a21eae89830bc95_p.jpg', '2017-12-20 00:18:37', '0', null, null);
INSERT INTO `seller_authinfo` VALUES ('39', '3', '123456789012345678', '张三', '\\pic\\c\\9\\c453761661414b81817adc23d236a6d7_front.jpg', '\\pic\\c\\9\\67c41d911a684bdc8f826aa8198d8961_front.jpg', '123456789', '\\pic\\9\\7\\1b3f2f362cb140ccbfa336e790096ec6_buy.jpg', '2017-12-23 01:52:08', '0', null, null);
INSERT INTO `seller_authinfo` VALUES ('54', '5', '1234587', '赵六', '\\pic\\3\\6\\17268c4be0194e90a193539d3b8a6eda_p.jpg', '\\pic\\3\\6\\7536535844ac47e595f8267d9973f9ab_p.jpg', '1234', '\\pic\\3\\6\\cb9c121501ab4d8d8c30f68eab94ab5f_p.jpg', '2017-12-24 20:56:11', '0', null, null);
INSERT INTO `seller_authinfo` VALUES ('55', '4', '331021199408073874', '胡宸瑄', '\\pic\\0\\6\\08edf0422e5d482497c167cb0a133113_15141238122782.jpg', '\\pic\\1\\4\\0bed544254094241b62c6318c1de2499_15141238211113.jpg', '12345678-4', '\\pic\\1\\6\\4bdd56bbaf2f453b9da988e13c813f48_15141237576721.jpg', '2017-12-24 21:57:04', '0', null, null);
INSERT INTO `seller_authinfo` VALUES ('56', '9', '530326199411051111', '赵六', '\\pic\\4\\d\\54dac1539c6f419c91e1f4aebf74eb3d_a.jpg', '\\pic\\3\\6\\af87adf7cace466f9ab40286b3065404_p.jpg', '31219261-1', '\\pic\\4\\d\\510b08935d1c46478e8631bb427309d7_a.jpg', '2017-12-25 00:11:16', '0', null, null);

-- ----------------------------
-- Table structure for seller_order
-- ----------------------------
DROP TABLE IF EXISTS `seller_order`;
CREATE TABLE `seller_order` (
  `seller_order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '取款订单id',
  `seller_id` int(11) DEFAULT NULL COMMENT '商家id',
  `order_state` int(3) DEFAULT '0' COMMENT '订单状态:参见枚举表0:未支付，1：已支付',
  `cost` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '订单修改时间',
  `payment_channel` int(3) DEFAULT '0' COMMENT '字符渠道：1：支付宝，2：微信',
  `admin_id` int(11) DEFAULT NULL COMMENT '管理员id',
  PRIMARY KEY (`seller_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seller_order
-- ----------------------------
INSERT INTO `seller_order` VALUES ('1', '3', '0', '0.00', '2017-12-26 15:25:49', null, '0', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) DEFAULT '' COMMENT '用户名',
  `password` varchar(150) DEFAULT '' COMMENT '密码',
  `nickname` varchar(40) DEFAULT '' COMMENT '昵称',
  `photo` varchar(100) DEFAULT '' COMMENT '头像',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别',
  `phone_number` varchar(11) DEFAULT '' COMMENT '手机号',
  `qq_number` varchar(20) DEFAULT '' COMMENT 'qq',
  `wechat_number` varchar(20) DEFAULT '' COMMENT '微信',
  `email` varchar(40) DEFAULT '' COMMENT '邮箱',
  `total_points` int(11) DEFAULT '0' COMMENT '总积分',
  `account_balance` decimal(10,2) DEFAULT '0.00' COMMENT '余额',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `identifying_code` int(6) DEFAULT NULL COMMENT '验证码',
  `code_creat_time` timestamp NULL DEFAULT NULL COMMENT '验证码生成时间',
  `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `state` int(3) DEFAULT '0' COMMENT '注册状态： 0：待注册，1：已注册',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'aaaa', '6f276e12b284aa5636aad64d659a367c721549a595df52e1602df0868ed14529', 'nickname1', '10f6beb9-8182-4d7b-a7cb-427ed760b77e.jpg', '0', '0', '15700178425', null, null, null, '7', '0.00', '2017-12-20 17:01:21', '876216', '2017-12-24 19:53:53', '2018-01-07 21:56:39', '1');
INSERT INTO `user` VALUES ('2', '不聪明的小智', '6f276e12b284aa5636aad64d659a367c721549a595df52e1602df0868ed14529', 'nickname2', null, null, null, '17326071207', null, null, null, '0', '0.00', '2017-12-20 17:02:09', '302914', '2017-12-20 17:02:09', '2018-01-07 21:56:47', '1');
INSERT INTO `user` VALUES ('3', '少聪弱鸡', 'suzi5479468', 'nickname3', '5e801baf-b023-47c5-94db-b81b1ea4fdea.jpg', '0', '0', '15638633978', null, null, null, '0', '0.00', '2018-01-05 16:22:10', '499111', '2018-01-05 16:22:10', '2018-01-07 21:56:49', '1');

-- ----------------------------
-- Table structure for user_authinfo
-- ----------------------------
DROP TABLE IF EXISTS `user_authinfo`;
CREATE TABLE `user_authinfo` (
  `uauthinfo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '认证id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `card_id` varchar(18) DEFAULT '' COMMENT '身份证号码',
  `real_name` varchar(20) DEFAULT '' COMMENT '真实姓名',
  `real_photo` varchar(100) DEFAULT '' COMMENT '本人照片',
  `card_front_photo` varchar(100) DEFAULT '' COMMENT '身份证正面照',
  `card_back_photo` varchar(100) DEFAULT '' COMMENT '身份证背面照',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `auth_state` int(3) DEFAULT '0' COMMENT '审核状态：-1：审核 不通过，0：未审核，1：审核通过，参见枚举表enum_type',
  `auth_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`uauthinfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_authinfo
-- ----------------------------
