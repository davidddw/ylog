/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : newblog

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2014-04-16 18:17:20
*/

CREATE DATABASE /*!32312 IF NOT EXISTS*/`mblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mblog`;

/*Table structure for table `article_tag` */

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user` bigint(20) unsigned NOT NULL DEFAULT '0',
  `category` bigint(20) unsigned NOT NULL,
  `created_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `content` longtext NOT NULL,
  `title` text NOT NULL,
  `tag_string` varchar(255) NOT NULL DEFAULT '',
  `excerpt` text,
  `article_status` varchar(20) NOT NULL DEFAULT 'PUBLISH',
  `comment_status` varchar(20) NOT NULL DEFAULT 'OPEN',
  `name` varchar(200) NOT NULL DEFAULT '',
  `modified_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comment_count` bigint(20) unsigned NOT NULL DEFAULT '0',
  `pv` bigint(20) unsigned NOT NULL DEFAULT '0',
  `url` varchar(255) DEFAULT NULL,
  `istop` int(255) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user` (`user`),
  KEY `category` (`category`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  CONSTRAINT `article_ibfk_2` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '1', '1', '2014-04-16 15:10:25', 'hello world', 'test1', 'test', null, 'PUBLISH', 'OPEN', '', '2014-04-16 15:10:36', '1', '11', null, '0');
INSERT INTO `article` VALUES ('2', '1', '1', '2014-04-26 15:10:25', 'hello new world', 'test2', 'test', null, 'PUBLISH', 'OPEN', '', '2014-04-26 15:10:36', '1', '11', null, '0');
INSERT INTO `article` VALUES ('3', '1', '2', '2014-04-26 15:10:25', 'hello new world', 'test3', 'test', null, 'PUBLISH', 'OPEN', '', '2014-04-26 15:10:36', '1', '11', null, '0');

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `art_id` bigint(20) unsigned NOT NULL,
  `tag_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b29au47qjlqlf4b6lu9smpa03` (`tag_id`),
  KEY `FK_svw0fr2q7h5afmbee804guac6` (`art_id`),
  CONSTRAINT `FK_svw0fr2q7h5afmbee804guac6` FOREIGN KEY (`art_id`) REFERENCES `article` (`id`),
  CONSTRAINT `FK_b29au47qjlqlf4b6lu9smpa03` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
INSERT INTO `article_tag` VALUES ('1', '1', '1');
INSERT INTO `article_tag` VALUES ('2', '2', '1');
INSERT INTO `article_tag` VALUES ('3', '2', '2');
INSERT INTO `article_tag` VALUES ('4', '1', '2');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `intro` varchar(255) NOT NULL,
  `count` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'gadget', '奇趣酷玩', '0');
INSERT INTO `category` VALUES ('2', 'home', '家居/生活', '0');
INSERT INTO `category` VALUES ('3', 'mobile', '手机/无线', '0');
INSERT INTO `category` VALUES ('4', 'digital', '数码/影音', '0');
INSERT INTO `category` VALUES ('5', 'history', '记录/历史', '0');
INSERT INTO `category` VALUES ('6', 'other', '未定义', '0');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `article` bigint(20) unsigned NOT NULL,
  `authorid` bigint(20) unsigned NOT NULL DEFAULT '0',
  `author` varchar(200) NOT NULL,
  `content` text NOT NULL,
  `email` varchar(255) NOT NULL DEFAULT '',
  `homepage` varchar(255) NOT NULL DEFAULT '',
  `posttime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ip` varchar(200) DEFAULT NULL,
  `agent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `article` (`article`),
  KEY `authorid` (`authorid`),
  CONSTRAINT `blog_comment_ibfk_1` FOREIGN KEY (`article`) REFERENCES `article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '1', 'david', 'dddw', '', '', '2014-04-16 15:13:54', null, null);
INSERT INTO `comment` VALUES ('2', '1', '1', 'dav', 'dddw阿斯大法', '', '', '2014-05-16 15:13:54', null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `level` int(255) unsigned NOT NULL DEFAULT '0',
  `name` varchar(200) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT '',
  `sex` int(255) unsigned NOT NULL DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `homepage` varchar(255) DEFAULT NULL,
  `lastvisit` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` int(255) unsigned NOT NULL DEFAULT '0',
  `postarts` bigint(255) NOT NULL DEFAULT '0',
  `postcomms` bigint(255) NOT NULL DEFAULT '0',
  `intro` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(255) DEFAULT NULL,
  `agent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_member
-- ----------------------------
INSERT INTO `user` VALUES ('1', '0', 'admin', 'password', '0', null, null, null, '2014-04-16 15:14:27', '0', '0', '0', '', null, null);

-- ----------------------------
-- Table structure for options
-- ----------------------------
DROP TABLE IF EXISTS `options`;
CREATE TABLE `options` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `value` longtext NOT NULL,
  `autoload` varchar(20) NOT NULL DEFAULT 'yes',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of options
-- ----------------------------
INSERT INTO `options` VALUES ('1', 'url', 'http://localhost:8080', 'yes');
INSERT INTO `options` VALUES ('2', 'title', '极客集', 'yes');
INSERT INTO `options` VALUES ('3', 'adminPageSize', '10', 'yes');
INSERT INTO `options` VALUES ('4', 'description', '汇聚全球科技---保持鲜活的大脑从浏览极客集开始.', 'yes');
INSERT INTO `options` VALUES ('5', 'homePageSize', '10', 'yes');
INSERT INTO `options` VALUES ('6', 'commentPageSize', '5', 'yes');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `intro` varchar(255) NOT NULL DEFAULT '',
  `count` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'test', '', '0');
INSERT INTO `tag` VALUES ('2', 'ddw', '', '0');