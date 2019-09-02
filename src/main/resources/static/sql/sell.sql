/*
Navicat MySQL Data Transfer

Source Server         : localhost-3306
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : sell

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-09-02 17:33:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(512) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(512) NOT NULL COMMENT '商品小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`) COMMENT '索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1567415410129', '1567415462294', '1567413594396', '微星(msi)冲锋坦克Ⅱ GL65 15.6英寸窄边框游戏本(i7-9750H 8G 512G SSD GTX1660Ti 6G独显 赛睿 IPS电竞屏)', '5555.00', '2', 'https://img14.360buyimg.com/n0/jfs/t1/48929/16/8503/290446/5d5fc53aE64f6a08f/b43c3e543c84aebd.jpg', '2019-09-02 17:03:27', '2019-09-02 17:03:27');
INSERT INTO `order_detail` VALUES ('1567415691353', '1567415617010', '1567413594396', '微星(msi)冲锋坦克Ⅱ GL65 15.6英寸窄边框游戏本(i7-9750H 8G 512G SSD GTX1660Ti 6G独显 赛睿 IPS电竞屏)', '5555.00', '2', 'https://img14.360buyimg.com/n0/jfs/t1/48929/16/8503/290446/5d5fc53aE64f6a08f/b43c3e543c84aebd.jpg', '2019-09-02 17:09:39', '2019-09-02 17:09:39');
INSERT INTO `order_detail` VALUES ('1567415929957', '1567416044832', '1567413594396', '微星(msi)冲锋坦克Ⅱ GL65 15.6英寸窄边框游戏本(i7-9750H 8G 512G SSD GTX1660Ti 6G独显 赛睿 IPS电竞屏)', '5555.00', '2', 'https://img14.360buyimg.com/n0/jfs/t1/48929/16/8503/290446/5d5fc53aE64f6a08f/b43c3e543c84aebd.jpg', '2019-09-02 17:15:56', '2019-09-02 17:15:56');
INSERT INTO `order_detail` VALUES ('1567415930536', '1567415944451', '1567413594396', '微星(msi)冲锋坦克Ⅱ GL65 15.6英寸窄边框游戏本(i7-9750H 8G 512G SSD GTX1660Ti 6G独显 赛睿 IPS电竞屏)', '5555.00', '2', 'https://img14.360buyimg.com/n0/jfs/t1/48929/16/8503/290446/5d5fc53aE64f6a08f/b43c3e543c84aebd.jpg', '2019-09-02 17:13:58', '2019-09-02 17:13:58');
INSERT INTO `order_detail` VALUES ('1567416054643', '1567415992494', '1567413594396', '微星(msi)冲锋坦克Ⅱ GL65 15.6英寸窄边框游戏本(i7-9750H 8G 512G SSD GTX1660Ti 6G独显 赛睿 IPS电竞屏)', '5555.00', '2', 'https://img14.360buyimg.com/n0/jfs/t1/48929/16/8503/290446/5d5fc53aE64f6a08f/b43c3e543c84aebd.jpg', '2019-09-02 17:05:57', '2019-09-02 17:05:57');
INSERT INTO `order_detail` VALUES ('1567416316440', '1567416121336', '1567413594396', '微星(msi)冲锋坦克Ⅱ GL65 15.6英寸窄边框游戏本(i7-9750H 8G 512G SSD GTX1660Ti 6G独显 赛睿 IPS电竞屏)', '5555.00', '2', 'https://img14.360buyimg.com/n0/jfs/t1/48929/16/8503/290446/5d5fc53aE64f6a08f/b43c3e543c84aebd.jpg', '2019-09-02 17:15:25', '2019-09-02 17:15:25');
INSERT INTO `order_detail` VALUES ('1567416448985', '1567415954255', '1567413594396', '微星(msi)冲锋坦克Ⅱ GL65 15.6英寸窄边框游戏本(i7-9750H 8G 512G SSD GTX1660Ti 6G独显 赛睿 IPS电竞屏)', '5555.00', '2', 'https://img14.360buyimg.com/n0/jfs/t1/48929/16/8503/290446/5d5fc53aE64f6a08f/b43c3e543c84aebd.jpg', '2019-09-02 17:15:50', '2019-09-02 17:15:50');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认0新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('1567415462294', '石中君子', '8888888', '市局村', 'szjz', '11110.00', '1', '0', '2019-09-02 17:03:27', '2019-09-02 17:10:51');
INSERT INTO `order_master` VALUES ('1567415617010', '石中君子', '8888888', '市局村', 'szjz', '11110.00', '2', '0', '2019-09-02 17:09:39', '2019-09-02 17:10:21');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类目名称',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uqe_category_type` (`category_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='类目表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('1', '儿童', '1', '2019-09-02 15:57:17', '2019-09-02 15:57:17');
INSERT INTO `product_category` VALUES ('2', '男生最爱', '2', '2019-09-02 16:09:59', '2019-09-02 16:09:59');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(512) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(512) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `product_status` int(11) NOT NULL DEFAULT '0' COMMENT '上架 0 下架 1',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('1567413594396', '微星(msi)冲锋坦克Ⅱ GL65 15.6英寸窄边框游戏本(i7-9750H 8G 512G SSD GTX1660Ti 6G独显 赛睿 IPS电竞屏)', '5555.00', '47', '【微星开学季狂欢】新品GL65搭载九代酷睿i7/1660Ti显卡,赛睿游戏键盘,双风扇三出风口七铜管强大散热，游戏更畅爽开学季专场戳我', 'https://img14.360buyimg.com/n0/jfs/t1/48929/16/8503/290446/5d5fc53aE64f6a08f/b43c3e543c84aebd.jpg', '0', '2', '2019-09-02 16:24:53', '2019-09-02 17:15:56');
INSERT INTO `product_info` VALUES ('1567414355633', ' 五谷磨房 红豆薏米粉代餐粉 薏仁红枣杂粮粉600g', '44.00', '55', '左手咖啡麦片蜂蜜要健康，右手泡面粉丝自嗨锅要快乐，这个中秋，冲饮速食为您献礼，更有满199减100助你放肆购戳我戳我~', 'https://img14.360buyimg.com/n0/jfs/t1/79334/31/8364/156577/5d64ba28E5e959ff4/291393a136f183df.jpg', '0', '1', '2019-09-02 16:38:47', '2019-09-02 16:38:47');

-- ----------------------------
-- Table structure for seller_info
-- ----------------------------
DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info` (
  `seller_id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(64) NOT NULL COMMENT '微信openID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卖家信息表';

-- ----------------------------
-- Records of seller_info
-- ----------------------------
INSERT INTO `seller_info` VALUES ('1', 'admin', '111', 'szjz', '2019-09-03 14:53:04', '2019-09-02 16:51:00');
