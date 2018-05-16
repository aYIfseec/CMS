/*
Navicat MySQL Data Transfer

Source Database       : CMS

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-12 19:49:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `nickname` varchar(20) DEFAULT NULL COMMENT '管理员昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '管理员');

-- ----------------------------
-- Table structure for t_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(255) NOT NULL COMMENT '系统后台接口',
  `admin_auth` tinyint(4) NOT NULL DEFAULT '1',
  `teacher_auth` tinyint(4) NOT NULL DEFAULT '0',
  `student_auth` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth
-- ----------------------------
INSERT INTO `t_auth` VALUES ('10', '主界面', '/CMS/main/', '1', '1', '1');
INSERT INTO `t_auth` VALUES ('11', '学生信息管理', '/CMS/student/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('12', '教师信息管理', '/CMS/teacher/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('13', '课程信息管理', '/CMS/course/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('14', '系统权限', '/CMS/auth/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('15', '公告管理', '/CMS/notice/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('16', '查看公告', '/CMS/notice/look', '1', '1', '1');
INSERT INTO `t_auth` VALUES ('17', '公告列表', '/CMS/notice/list', '1', '1', '1');
INSERT INTO `t_auth` VALUES ('18', '教师课程', '/CMS/course/getMyCourse', '1', '1', '0');
INSERT INTO `t_auth` VALUES ('19', '可选课程列表', '/CMS/course/choiceList', '1', '0', '1');
INSERT INTO `t_auth` VALUES ('20', '结束课程', '/CMS/course/complete', '1', '1', '0');
INSERT INTO `t_auth` VALUES ('21', '学生列表', '/CMS/student/stulist', '1', '1', '0');
INSERT INTO `t_auth` VALUES ('22', '录入成绩', '/CMS/score/update', '1', '1', '0');
INSERT INTO `t_auth` VALUES ('23', '学生选课', '/CMS/score/choiceCourse', '1', '0', '1');
INSERT INTO `t_auth` VALUES ('24', '取消选课', '/CMS/score/delete', '1', '0', '1');
INSERT INTO `t_auth` VALUES ('26', '查看成绩', '/CMS/score/stuScore', '1', '0', '1');
INSERT INTO `t_auth` VALUES ('27', '密码管理', '/CMS/pswd/', '1', '1', '1');
INSERT INTO `t_auth` VALUES ('28', '基本课程管理', '/CMS/basecourse/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('29', '成绩报表', '/CMS/score/', '1', '0', '0');

-- ----------------------------
-- Table structure for t_base_course
-- ----------------------------
DROP TABLE IF EXISTS `t_base_course`;
CREATE TABLE `t_base_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `name` varchar(100) NOT NULL COMMENT '课程名',
  `synopsis` varchar(255) DEFAULT NULL COMMENT '课程简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_course
-- ----------------------------
INSERT INTO `t_base_course` VALUES ('1', 'C语言', '本课程介绍计算机结构化程序设计的思想、方法和技巧；C语言的基本知识和概念；Ｃ语言丰富的运算符和数据类型，以及C语言的结构控制语句；在本课程中，函数的概念和指针的使用是课程重点和难点。');
INSERT INTO `t_base_course` VALUES ('2', '数据结构与算法', '数据结构是计算机科学的一门非常重要的专业基础课，内容丰富，涉及面广，我校计算机专业的本科主干基础课程，也是非计算机类本科生和研究生学习计算机的选修课。');
INSERT INTO `t_base_course` VALUES ('3', '操作系统', '操作系统是计算机科学与技术领域中最为活跃的学科之一,因而操作系统课程也自然是该专业的一门核心的专业基础课。操作系统课程内容综合了基础理论教学、课程实践教学、最新技术追踪等多项内容。');
INSERT INTO `t_base_course` VALUES ('4', '计算机网络', '计算机网络是计算机专业学生必修的一门专业基础课和核心课程，它是后续课程《计算机系统安全》、《网络管理技术》、《TCP/IP与网络互联》等理论课程，以及《网络课程设计》等实践教学环节的先行课。');
INSERT INTO `t_base_course` VALUES ('5', '软件工程', '该课程主要介绍软件工程的基本概念、原理和典型的技术方法。该课程的主要目的是通过教学，使学生了解工程学原理在软件开发中的应用，对计算机科学专业和信息工程专业学生理解软件开发过程和软件工程学具有重要意义。');
INSERT INTO `t_base_course` VALUES ('6', 'Linux入门', 'Linux是适用于多种平台的计算机操作系统，也是免费的非商品化的操作系统。本课程以Redhat Linux系统为基础，试图对Linux系统一个简洁而全面的介绍，使学生在较短时间内对该操作系统有个大概的了解。');
INSERT INTO `t_base_course` VALUES ('93', '课程1', '课程简介1');
INSERT INTO `t_base_course` VALUES ('94', '课程2', '课程简介2');
INSERT INTO `t_base_course` VALUES ('95', '课程3', '课程简介3');
INSERT INTO `t_base_course` VALUES ('96', '课程4', '课程简介4');
INSERT INTO `t_base_course` VALUES ('97', '课程5', '课程简介5');
INSERT INTO `t_base_course` VALUES ('98', '课程6', '课程简介6');
INSERT INTO `t_base_course` VALUES ('99', '课程7', '课程简介7');
INSERT INTO `t_base_course` VALUES ('100', '课程8', '课程简介8');
INSERT INTO `t_base_course` VALUES ('101', '课程9', '课程简介9');
INSERT INTO `t_base_course` VALUES ('102', '课程10', '课程简介10');
INSERT INTO `t_base_course` VALUES ('103', '课程11', '课程简介11');
INSERT INTO `t_base_course` VALUES ('104', '课程12', '课程简介12');
INSERT INTO `t_base_course` VALUES ('105', '课程13', '课程简介13');
INSERT INTO `t_base_course` VALUES ('106', '课程14', '课程简介14');
INSERT INTO `t_base_course` VALUES ('107', '课程15', '课程简介15');
INSERT INTO `t_base_course` VALUES ('108', '课程16', '课程简介16');
INSERT INTO `t_base_course` VALUES ('109', '课程17', '课程简介17');
INSERT INTO `t_base_course` VALUES ('110', '课程18', '课程简介18');
INSERT INTO `t_base_course` VALUES ('111', '课程19', '课程简介19');
INSERT INTO `t_base_course` VALUES ('112', '课程20', '课程简介20');
INSERT INTO `t_base_course` VALUES ('113', '课程21', '课程简介21');
INSERT INTO `t_base_course` VALUES ('114', '课程22', '课程简介22');
INSERT INTO `t_base_course` VALUES ('115', '课程23', '课程简介23');
INSERT INTO `t_base_course` VALUES ('116', '课程24', '课程简介24');
INSERT INTO `t_base_course` VALUES ('117', '课程25', '课程简介25');
INSERT INTO `t_base_course` VALUES ('118', '课程26', '课程简介26');
INSERT INTO `t_base_course` VALUES ('119', '课程27', '课程简介27');
INSERT INTO `t_base_course` VALUES ('120', '课程28', '课程简介28');
INSERT INTO `t_base_course` VALUES ('121', '课程29', '课程简介29');
INSERT INTO `t_base_course` VALUES ('122', '课程30', '课程简介30');
INSERT INTO `t_base_course` VALUES ('123', '课程31', '课程简介31');
INSERT INTO `t_base_course` VALUES ('124', '课程32', '课程简介32');
INSERT INTO `t_base_course` VALUES ('125', '课程33', '课程简介33');
INSERT INTO `t_base_course` VALUES ('126', '课程34', '课程简介34');
INSERT INTO `t_base_course` VALUES ('127', '课程35', '课程简介35');
INSERT INTO `t_base_course` VALUES ('128', '课程36', '课程简介36');
INSERT INTO `t_base_course` VALUES ('129', '课程37', '课程简介37');
INSERT INTO `t_base_course` VALUES ('130', '课程38', '课程简介38');
INSERT INTO `t_base_course` VALUES ('131', '课程39', '课程简介39');
INSERT INTO `t_base_course` VALUES ('132', '课程40', '课程简介40');
INSERT INTO `t_base_course` VALUES ('133', '课程41', '课程简介41');

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` date DEFAULT NULL COMMENT '开设日期',
  `end_date` date DEFAULT NULL COMMENT '结束日期',
  `class_hour` smallint(6) DEFAULT NULL COMMENT '总课时',
  `test_mode` varchar(255) DEFAULT NULL COMMENT '考核方式',
  `student_num` int(11) DEFAULT NULL,
  `choice_num` int(11) DEFAULT '0',
  `complete` int(11) NOT NULL DEFAULT '0' COMMENT '是否是完成的课程',
  `t_id` varchar(255) NOT NULL COMMENT '外键-教师号',
  `base_course_id` int(11) NOT NULL COMMENT '外键-课程号',
  PRIMARY KEY (`id`),
  KEY `teacher_course` (`t_id`),
  KEY `course` (`base_course_id`),
  CONSTRAINT `course` FOREIGN KEY (`base_course_id`) REFERENCES `t_base_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_course` FOREIGN KEY (`t_id`) REFERENCES `t_teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('7', '2018-09-01', '2019-02-01', '54', '考试', '40', '1', '0', '1560310', '1');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(30) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `auth` int(11) NOT NULL DEFAULT '3',
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('5', '全体可见公告-测试', '管理员', '<p>全体可见公告-测试</p><p><img src=\"/CMS/images/.png1524704315616\" alt=\"undefined\"><br>图片测试<br></p><p><img src=\"/CMS/images/.png1524704337195\" alt=\"undefined\"><br></p>', '1', '2018-04-26');
INSERT INTO `t_notice` VALUES ('6', '仅教师可见公告-测试', '管理员', '<p>仅教师可见公告-测试</p>', '2', '2018-04-26');
INSERT INTO `t_notice` VALUES ('7', '管理员可见公告-测试', '管理员', '<p><b>管理员可见公告-测试</b></p>', '3', '2018-04-26');

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '考试成绩',
  `result` varchar(255) NOT NULL DEFAULT '' COMMENT '考察结果',
  `c_id` int(11) NOT NULL COMMENT '外键-课程id',
  `s_id` varchar(255) NOT NULL COMMENT '外键-学号',
  PRIMARY KEY (`id`),
  KEY `score_stu` (`s_id`),
  KEY `score_course` (`c_id`),
  CONSTRAINT `score_course` FOREIGN KEY (`c_id`) REFERENCES `t_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_stu` FOREIGN KEY (`s_id`) REFERENCES `t_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_score
-- ----------------------------
INSERT INTO `t_score` VALUES ('20', '0', '', '7', '15603102221');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` varchar(20) NOT NULL COMMENT '学号',
  `password` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sex` varchar(10) NOT NULL COMMENT '性别',
  `admission_date` date NOT NULL COMMENT '入学日期',
  `major` varchar(50) NOT NULL COMMENT '专业',
  `grade` varchar(50) NOT NULL COMMENT '班级',
  `education` varchar(20) NOT NULL COMMENT '学历',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('15603102221', 'E93A222EF152B262747586EC72C43485', '赵某某', '男', '2015-09-07', '计算机', '15软件工程4班', '本科');
INSERT INTO `t_student` VALUES ('15603102222', 'DEFCFECC2B71DD52891ACF2D21C32F50', '李某某', '女', '2015-09-07', '计算机', '15网络工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102223', '0F50C438D6AF5FC0368F36B9B838BF74', '吴某', '男', '2015-09-07', '计算机', '15软件工程5班', '专科');
INSERT INTO `t_student` VALUES ('15603102224', 'BAC6D913584512AAC959C223EDD96DCA', '郑某', '女', '2015-09-07', '计算机', '15网络工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102225', '07B00464CEF2AC6C81D1C93CDDA11004', '王某', '男', '2015-09-07', '计算机', '15软件工程4班', '本科');
INSERT INTO `t_student` VALUES ('15603102226', '82FEBCE7CFAA009C51814AA23B1E14B7', '陈某', '女', '2015-09-07', '计算机', '15网络工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102227', '35C7F9B01CD2571BCDB658FFF69EB62E', '黄某', '男', '2015-09-07', '计算机', '15软件工程5班', '专科');
INSERT INTO `t_student` VALUES ('15603102228', 'BAC2DC1FC2E04E78402E418827957738', '周某', '女', '2015-09-07', '计算机', '15网络工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102229', '91C125CD2FA882722C383B29CD91DEB9', '赵某某', '男', '2015-09-07', '计算机', '15软件工程4班', '本科');
INSERT INTO `t_student` VALUES ('15603102230', '528813C8034BF289E3788AF4F76963D5', '李某某', '女', '2015-09-07', '计算机', '15网络工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102231', 'C75B37D205DD2213927F2BD5A25CCF63', '吴某', '男', '2015-09-07', '计算机', '15软件工程5班', '专科');
INSERT INTO `t_student` VALUES ('15603102232', '39F7A3A462D8F8C6BFE759BDB8C47C85', '郑某', '女', '2015-09-07', '计算机', '15网络工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102233', 'AA22AF7B06F601B869CAB7E9A192DF51', '王某', '男', '2015-09-07', '计算机', '15软件工程4班', '本科');
INSERT INTO `t_student` VALUES ('15603102234', '8D09E6E1F2B46481D0607E97082BCD7F', '陈某', '女', '2015-09-07', '计算机', '15网络工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102235', '000A0C38BA71EBEFAE8A5DBEA4EF0301', '黄某', '男', '2015-09-07', '计算机', '15软件工程5班', '专科');
INSERT INTO `t_student` VALUES ('15603102236', '704E8383E7A6A9D5874572B5E26F6385', '周某', '女', '2015-09-07', '计算机', '15网络工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102237', '4E258C6B7BECC850F9D21DEF4BC8A76A', '赵某某', '男', '2015-09-07', '计算机', '15软件工程4班', '本科');
INSERT INTO `t_student` VALUES ('15603102238', 'F948B3B39680D0745EC2B750014D7986', '李某某', '女', '2015-09-07', '计算机', '15网络工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102239', 'CDFB1269C94C32668A79187AAE1C21DE', '吴某', '男', '2015-09-07', '计算机', '15软件工程5班', '专科');
INSERT INTO `t_student` VALUES ('15603102240', 'A0ABDC06C285B29A240543B49F12C77F', '郑某', '女', '2015-09-07', '计算机', '15网络工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102241', '48C70CB67802C0186849E2D1465C0D56', '王某', '男', '2015-09-07', '计算机', '15软件工程4班', '本科');
INSERT INTO `t_student` VALUES ('15603102242', '08128881F75563FA23D5B87D005552D9', '陈某', '女', '2015-09-07', '计算机', '15网络工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102243', '88CC88AF1700601757E92ECA545C25FC', '黄某', '男', '2015-09-07', '计算机', '15软件工程5班', '专科');
INSERT INTO `t_student` VALUES ('15603102244', '7B1B320D3FC2F6C2FBE9F29BA0E27530', '周某', '女', '2015-09-07', '计算机', '15网络工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102245', 'EE58A58092544B1721CF267D27DDAAC8', '赵某某', '男', '2015-09-07', '计算机', '15软件工程4班', '本科');
INSERT INTO `t_student` VALUES ('15603102246', 'EF25304930E9590FB56146F6953D8CEA', '李某某', '女', '2015-09-07', '计算机', '15网络工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102247', '73EE929A017701026A89F6F06B2CEA21', '吴某', '男', '2015-09-07', '计算机', '15软件工程5班', '专科');
INSERT INTO `t_student` VALUES ('15603102248', 'E5BCA9AA1BD6069790764F91EE68340C', '郑某', '女', '2015-09-07', '计算机', '15网络工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102249', 'EF0545957396DD30CBC5D424FD8E738E', '王某', '男', '2015-09-07', '计算机', '15软件工程4班', '本科');
INSERT INTO `t_student` VALUES ('15603102250', '938ECEA713DB67DDA68E21D423CA1B45', '陈某', '女', '2015-09-07', '计算机', '15网络工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102251', 'A1C1411276DE83CF445293EDA6D83479', '黄某', '男', '2015-09-07', '计算机', '15软件工程5班', '专科');
INSERT INTO `t_student` VALUES ('15603102252', 'B8DA45EB161FBB204ED9D4997FC6F1E5', '周某', '女', '2015-09-07', '计算机', '15网络工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102253', '55E32CB6FC7B4AEEE24AA8A2B34B13A2', '赵某某', '男', '2015-09-07', '计算机', '15软件工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102254', '0D20E5298EE7257DDDC76CFACCA125D0', '李某某', '女', '2015-09-07', '计算机', '15网络工程6班', '本科');
INSERT INTO `t_student` VALUES ('15603102255', 'AA7D710F39A87272A76D00C24F0C1612', '吴某', '男', '2015-09-07', '计算机', '15软件工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102256', '4AA2FF0C2857E2920217D5C12BF0D222', '郑某', '女', '2015-09-07', '计算机', '15网络工程7班', '专科');
INSERT INTO `t_student` VALUES ('15603102257', '2D992FFC41C0FDBB65508817DD2B8FBC', '王某', '男', '2015-09-07', '计算机', '15软件工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102258', '0C14CB46D858A2363F8D7D439D03835F', '陈某', '女', '2015-09-07', '计算机', '15网络工程6班', '本科');
INSERT INTO `t_student` VALUES ('15603102259', '3F3F0A7D9AD7AA870B9AD0C35708E9E8', '黄某', '男', '2015-09-07', '计算机', '15软件工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102260', 'E691E16C18F6517370C223BBEE5329A9', '周某', '女', '2015-09-07', '计算机', '15网络工程7班', '专科');
INSERT INTO `t_student` VALUES ('15603102261', '1C5429C0048D7B23049E4A79A4FA19E3', '赵某某', '男', '2015-09-07', '计算机', '15软件工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102262', '72816E48A3024C101B8267A706A42F09', '李某某', '女', '2015-09-07', '计算机', '15网络工程6班', '本科');
INSERT INTO `t_student` VALUES ('15603102263', '2BA73964FC9957D7091DFAB19FEDBCD9', '吴某', '男', '2015-09-07', '计算机', '15软件工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102264', '252C791D903AEF933BD96DE009CD9CF8', '郑某', '女', '2015-09-07', '计算机', '15网络工程7班', '专科');
INSERT INTO `t_student` VALUES ('15603102265', '6FF236A84432E984C3FE269E072E0B9A', '王某', '男', '2015-09-07', '计算机', '15软件工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102266', '0F37984BA5C9CC13C586C274CDE21E36', '陈某', '女', '2015-09-07', '计算机', '15网络工程6班', '本科');
INSERT INTO `t_student` VALUES ('15603102267', 'F61850D38B8697A3280E1C13AAE0042A', '黄某', '男', '2015-09-07', '计算机', '15软件工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102268', 'B24C44CF07903168F76612779D7DC3A7', '周某', '女', '2015-09-07', '计算机', '15网络工程7班', '专科');
INSERT INTO `t_student` VALUES ('15603102269', '7268B5DAB2E348071A61896427EBCBAF', '赵某某', '男', '2015-09-07', '计算机', '15软件工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102270', 'E77CBA48EA8B47188AC0A314D638AC9D', '李某某', '女', '2015-09-07', '计算机', '15网络工程6班', '本科');
INSERT INTO `t_student` VALUES ('15603102271', '08C4D6968D584E2B949DD99D9741D0A1', '吴某', '男', '2015-09-07', '计算机', '15软件工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102272', '89089DE5134267C7847EC112C4D037B0', '郑某', '女', '2015-09-07', '计算机', '15网络工程7班', '专科');
INSERT INTO `t_student` VALUES ('15603102273', 'EB33C258E0D1D7C0AE88AACC3B8E97A1', '王某', '男', '2015-09-07', '计算机', '15软件工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102274', '45D6413ED1FF1342E82AD4625683D691', '陈某', '女', '2015-09-07', '计算机', '15网络工程6班', '本科');
INSERT INTO `t_student` VALUES ('15603102275', 'E9B3EFBAC8898F621CB83EB934E3611A', '黄某', '男', '2015-09-07', '计算机', '15软件工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102276', '072344C45F18118ED213FB514312DADC', '周某', '女', '2015-09-07', '计算机', '15网络工程7班', '专科');
INSERT INTO `t_student` VALUES ('15603102277', 'F791321C13AAF448D2E167FDDD77FCB4', '赵某某', '男', '2015-09-07', '计算机', '15软件工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102278', '829A2A1D1AC197552795BF54AD72604E', '李某某', '女', '2015-09-07', '计算机', '15网络工程6班', '本科');
INSERT INTO `t_student` VALUES ('15603102279', '3E59126852B76C6167E0505F2771A558', '吴某', '男', '2015-09-07', '计算机', '15软件工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102280', '487F3E87E72A49BE6916D46E5F166DC9', '郑某', '女', '2015-09-07', '计算机', '15网络工程7班', '专科');
INSERT INTO `t_student` VALUES ('15603102281', 'CDB83B8384E01D22C9BC0BC566B513FE', '王某', '男', '2015-09-07', '计算机', '15软件工程5班', '本科');
INSERT INTO `t_student` VALUES ('15603102282', 'D3C5BF849BCC2F02E17E5351894365FA', '陈某', '女', '2015-09-07', '计算机', '15网络工程6班', '本科');
INSERT INTO `t_student` VALUES ('15603102283', '338A2FA6BABB01C2B994422EF3B0840B', '黄某', '男', '2015-09-07', '计算机', '15软件工程6班', '专科');
INSERT INTO `t_student` VALUES ('15603102284', '180140875CB34F1A52A5F65095536AB7', '周某', '女', '2015-09-07', '计算机', '15网络工程7班', '专科');
INSERT INTO `t_student` VALUES ('15603102285', 'E07037AD91D12C94B43FFD512A3379D9', '赵某某', '男', '2015-09-07', '计算机', '15软件工程6班', '本科');
INSERT INTO `t_student` VALUES ('15603102286', '5331D62DC81A2FD4A4CC670C58BED482', '李某某', '女', '2015-09-07', '计算机', '15网络工程7班', '本科');
INSERT INTO `t_student` VALUES ('15603102287', '7D3329F0E416276AD6B18F90598F81F3', '吴某', '男', '2015-09-07', '计算机', '15软件工程7班', '专科');
INSERT INTO `t_student` VALUES ('15603102288', '7B69B87B9BBFA6DE5C0E1A06225C5A1A', '郑某', '女', '2015-09-07', '计算机', '15网络工程8班', '专科');
INSERT INTO `t_student` VALUES ('15603102289', 'F3ADC631EE8A178C1B5CEA039CF2A549', '王某', '男', '2015-09-07', '计算机', '15软件工程6班', '本科');
INSERT INTO `t_student` VALUES ('15603102290', 'A46515124188B76AC561E8EE12A466F9', '陈某', '女', '2015-09-07', '计算机', '15网络工程7班', '本科');
INSERT INTO `t_student` VALUES ('15603102291', '5BB9963D5BBAFEF4CEAC6917335AFC5D', '黄某', '男', '2015-09-07', '计算机', '15软件工程7班', '专科');
INSERT INTO `t_student` VALUES ('15603102292', '24CE3F7C4002AD2E6475461231B12045', '周某', '女', '2015-09-07', '计算机', '15网络工程8班', '专科');

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` varchar(20) NOT NULL COMMENT '教师职工号',
  `password` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `synopsis` varchar(255) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('1560310', '80F5407A5CD59BB453A7963085661654', '蔡元培', '1916年至1927年任北京大学校长，革新北大，开“学术”与“自由”之风；1920年至1930年，蔡元培同时兼任中法大学校长。他早年参加反清朝帝制的斗争，民国初年主持制定了中国近代高等教育的第一个法令——《大学令');
INSERT INTO `t_teacher` VALUES ('1560321', '030E659A783687F8FB6CE38D5BCB5563', '教师12', null);
INSERT INTO `t_teacher` VALUES ('1560322', 'BBC0A1E0880CC4CAFF4EDAFF8FAC824B', '教师13', null);
INSERT INTO `t_teacher` VALUES ('1560323', '41A8C6420786BD1F0CCD89E1C7E3EF18', '教师14', null);
INSERT INTO `t_teacher` VALUES ('1560324', 'F0BD754B3F4AF9126BA39F76ED94D7AB', '教师15', null);
INSERT INTO `t_teacher` VALUES ('1560325', '0BA44C31237E0476FEB6862FF1C19071', '教师16', null);
INSERT INTO `t_teacher` VALUES ('1560326', 'A5955C019287F51DE5F37925729EA05A', '教师17', null);
INSERT INTO `t_teacher` VALUES ('1560327', 'EC9FC52645B9972A6E5B0C96CE58063C', '教师18', null);
INSERT INTO `t_teacher` VALUES ('1560328', 'E0E173E0A786D274A1E664F32D307C53', '教师19', null);
INSERT INTO `t_teacher` VALUES ('1560329', '70A3952F6098A2BB7AAE9EC15A8FB313', '教师20', null);
INSERT INTO `t_teacher` VALUES ('1560330', '67155C818A62C1236AFD2D2B3AF44041', '教师21', null);
INSERT INTO `t_teacher` VALUES ('1560331', '3387E546B63E6F042E2E55E0A2B1E7CB', '教师22', null);
INSERT INTO `t_teacher` VALUES ('1560332', '37A863FF2EE0C0A7CEBCC514B027A2AE', '教师23', null);
INSERT INTO `t_teacher` VALUES ('1560333', '899BA480041D13DDCA2E3EA069062985', '教师24', null);
INSERT INTO `t_teacher` VALUES ('1560334', 'D95182B67DDC34D4D74FE211A0942C4A', '教师25', null);
INSERT INTO `t_teacher` VALUES ('1560335', '41BD566857C2D297E44B5A4DB52F77E8', '教师26', null);
INSERT INTO `t_teacher` VALUES ('1560336', '79DCE88F7CF66161232C0AF6FBED6900', '教师27', null);
INSERT INTO `t_teacher` VALUES ('1560337', 'A000AD85BF45BFDF183FACDA230EF7A9', '教师28', null);
INSERT INTO `t_teacher` VALUES ('1560338', 'DD57797C2C524F5BF7647D1823E130F9', '教师29', null);
INSERT INTO `t_teacher` VALUES ('1560339', '256BFFD16365074DAE5584BB3D4BD0C3', '教师30', null);
INSERT INTO `t_teacher` VALUES ('1560340', 'FDD8EE65BA54678A886FEFAD7FDEB7FC', '教师31', null);
INSERT INTO `t_teacher` VALUES ('1560341', 'C4403B600FD58D7C215E32BAD1C142F5', '教师32', null);
INSERT INTO `t_teacher` VALUES ('1560342', 'DD6E56F574F14544EDD9D3C1554594BE', '教师33', null);
INSERT INTO `t_teacher` VALUES ('1560343', '62242DF0A3685D5807E12A788111212E', '教师34', null);
INSERT INTO `t_teacher` VALUES ('1560344', 'F0ABCE9F5AD92FEEC91A34E69B92E28D', '教师35', null);
INSERT INTO `t_teacher` VALUES ('1560345', 'C0B49F492B3ADAA544965B36EF068928', '教师36', null);
INSERT INTO `t_teacher` VALUES ('1560346', '6FC0CADF381244FFC479F7B779005413', '教师37', null);
INSERT INTO `t_teacher` VALUES ('1560347', '0A0D386158A6CA4B4EFB9BD35963A065', '教师38', null);
INSERT INTO `t_teacher` VALUES ('1560348', '266DEBB824DD7BB30110773F1F2E14AD', '教师39', null);
INSERT INTO `t_teacher` VALUES ('1560349', '94972563C468C540921E043BC8F8FCED', '教师40', null);
INSERT INTO `t_teacher` VALUES ('1560350', '834761DA50A1D9B91F3E24584E613FC6', '教师41', null);
INSERT INTO `t_teacher` VALUES ('1560351', '5100FADB25BCB574D9C5CE7BD4E48515', '教师42', null);
INSERT INTO `t_teacher` VALUES ('1560352', '0C0D24D094282BDE30FC7DA677013264', '教师43', null);
INSERT INTO `t_teacher` VALUES ('1560353', 'D8C82026F164E9C26552821B269F5030', '教师44', null);
INSERT INTO `t_teacher` VALUES ('1560354', '80A0F5C11A0205BB4A84E9487178095C', '教师45', null);
INSERT INTO `t_teacher` VALUES ('1560355', '7DCE36A69FF31F3AD58EEBC59D7C860C', '教师46', null);
INSERT INTO `t_teacher` VALUES ('1560356', '9A3C0DF7CF78C6B95D2DC66297D6565B', '教师47', null);
INSERT INTO `t_teacher` VALUES ('1560357', '17096D7C07EC1A26634904AAA63C53C5', '教师48', null);
INSERT INTO `t_teacher` VALUES ('1560358', '5D1991E742AAF3F667E35599E8FF0F06', '教师49', null);
INSERT INTO `t_teacher` VALUES ('1560359', 'BCE2ACE1DB1223ADA6D789EEEB1F36A8', '教师50', null);
INSERT INTO `t_teacher` VALUES ('1560360', 'D896F9198342DB8AF9EE881DD15EC900', '教师51', null);
INSERT INTO `t_teacher` VALUES ('1560361', 'B2678B32D49497DD1F793DA0DEBB6573', '教师52', null);
INSERT INTO `t_teacher` VALUES ('1560362', '2E675EDF1722ED5D4EC39A7334982C88', '教师53', null);
INSERT INTO `t_teacher` VALUES ('1560363', '1399CA855C393DDEDACC32FC78046C23', '教师54', null);
INSERT INTO `t_teacher` VALUES ('1560364', '0DE0B117FFA94F03151025E7C4780E56', '教师55', null);
INSERT INTO `t_teacher` VALUES ('1560365', '2DD3D84506D049F719A53DB5F305F7EE', '教师56', null);
INSERT INTO `t_teacher` VALUES ('1560366', '00F2A3E1DC802A570B547DE70B01080E', '教师57', null);
INSERT INTO `t_teacher` VALUES ('1560367', 'E3AACF1652087F798E72D06F72EE9D7F', '教师58', null);
INSERT INTO `t_teacher` VALUES ('1560368', '2F873A4B39357ACC47AA73DEC7842A99', '教师59', null);
INSERT INTO `t_teacher` VALUES ('1560369', '266E44DD15992E9A763B019158395AF9', '教师60', null);
INSERT INTO `t_teacher` VALUES ('1560370', '83A0624B6E108C961A109FA2201E9908', '教师61', null);
INSERT INTO `t_teacher` VALUES ('1560371', '0D22AA44DD595160F0102D7E4BDA6953', '教师62', null);
INSERT INTO `t_teacher` VALUES ('1560372', '9778974B7C73A42403CA71D143B86F9A', '教师63', null);
INSERT INTO `t_teacher` VALUES ('1560373', '4D88B6C84A19997110A1840AEE0EB94C', '教师64', null);
INSERT INTO `t_teacher` VALUES ('1560374', '3FB0FE1FEDD585BB899230ADFFDC7FCD', '教师65', null);
INSERT INTO `t_teacher` VALUES ('1560375', '09B8DD268A749187EFB6082328A28CF9', '教师66', null);
INSERT INTO `t_teacher` VALUES ('1560376', '981939EF4BED1584191342B61FBDDA34', '教师67', null);
INSERT INTO `t_teacher` VALUES ('1560377', '2537E3F3DC13D7F23ED9DBA382735466', '教师68', null);
INSERT INTO `t_teacher` VALUES ('1560378', '907CCA83A81010CB49332338E8D6EE95', '教师69', null);
INSERT INTO `t_teacher` VALUES ('1560379', '4092864F56C0EB374B1326B916B053A0', '教师70', null);
INSERT INTO `t_teacher` VALUES ('1560380', '235873C21980FF90C640DD012566011D', '教师71', null);
INSERT INTO `t_teacher` VALUES ('1560381', '62FE61CC57C3C3853189CDC0A0E7938B', '教师72', null);
INSERT INTO `t_teacher` VALUES ('1560382', 'C04DC41E39A8AB219FCE1B2531B6D7DF', '教师73', null);
INSERT INTO `t_teacher` VALUES ('1560383', 'D1EA710E82945FAB7F0B120701633443', '教师74', null);
INSERT INTO `t_teacher` VALUES ('1560384', '0C41296AE10135B7F88938BED838F7E3', '教师75', null);
INSERT INTO `t_teacher` VALUES ('1560385', '66494D255397F252D968E345042807BD', '教师76', null);
INSERT INTO `t_teacher` VALUES ('1560386', 'F9BFEF8FB63FB5B403DCAA82DCB59AFD', '教师77', null);
INSERT INTO `t_teacher` VALUES ('1560387', '6163A36B86F8CD5CF6843C847BDD9B6A', '教师78', null);
INSERT INTO `t_teacher` VALUES ('1560388', '8B9A28EA47A46DD7E4F52F732772E667', '教师79', null);
INSERT INTO `t_teacher` VALUES ('1560389', '6B658EEC4619A018ECB1D4A2677C1EA2', '教师80', null);
INSERT INTO `t_teacher` VALUES ('1560390', '3957C9295C8A4C1AD1781BCD8A2E6046', '教师81', null);
INSERT INTO `t_teacher` VALUES ('1560391', 'C24563FA97954BF827E17BB5C691F15B', '教师82', null);
INSERT INTO `t_teacher` VALUES ('1560392', '5860852A368344802E5F99803247DD3C', '教师83', null);
INSERT INTO `t_teacher` VALUES ('1560393', 'CBDDBEF4F3C58C4520C7A16C162C8838', '教师84', null);
INSERT INTO `t_teacher` VALUES ('1560394', '348A50FEDCD24757FBDACFFA2FDB933E', '教师85', null);
INSERT INTO `t_teacher` VALUES ('1560395', '4998033A989289F0DC1443D6E85C5186', '教师86', null);
INSERT INTO `t_teacher` VALUES ('1560396', 'A573A2C4C050BE574146362D9D8F56BB', '教师87', null);
INSERT INTO `t_teacher` VALUES ('1560397', 'F3710FAC797C45F217DB0A638CD3E370', '教师88', null);
INSERT INTO `t_teacher` VALUES ('1560398', '70C8D38D2F3164F14CCF084DA4B43CB5', '教师89', null);
INSERT INTO `t_teacher` VALUES ('1560399', '1AFA55CEC5644FF030A6E76DBEE69604', '教师90', null);
INSERT INTO `t_teacher` VALUES ('1560400', 'F41D5D7FF09D9E759FFC8F8AF1DDEC61', '教师91', null);
INSERT INTO `t_teacher` VALUES ('1560401', '77861F034F02D3CB09463253423BCBCC', '教师92', null);
INSERT INTO `t_teacher` VALUES ('1560402', 'E6A9CA8C54BE5B94B0B7AB4281D0FFB0', '教师93', null);
INSERT INTO `t_teacher` VALUES ('1560403', 'EAA9486EDC70A920028FB13F25125ED2', '教师94', null);
INSERT INTO `t_teacher` VALUES ('1560404', 'D7E7C829033AB6051C0C59FFA726B0CB', '教师95', null);
INSERT INTO `t_teacher` VALUES ('1560405', '27BD814906F06FCBDE169E5E12BF43EC', '教师96', null);
INSERT INTO `t_teacher` VALUES ('1560406', '86E349F47DC3AB5A237C31779FD8BF1B', '教师97', null);
INSERT INTO `t_teacher` VALUES ('1560407', '087A76C703C036BB1BA35F6247973CC8', '教师98', null);
INSERT INTO `t_teacher` VALUES ('1560408', '27970E08FB9FC1A52FFCD120A835ED5D', '教师99', null);
INSERT INTO `t_teacher` VALUES ('1560409', '7845A008C1B1CBF5009AE17F66898009', '教师100', null);
INSERT INTO `t_teacher` VALUES ('1560410', '03BB9AF943A6672E1A3DB57F8165A872', '教师101', null);
INSERT INTO `t_teacher` VALUES ('1560411', 'ABD3A9CC882BA7581213746AF1D977D3', '教师102', null);
INSERT INTO `t_teacher` VALUES ('1560412', '5F67BA7AB1537BDDF6DF6249CDA9575C', '教师103', null);
INSERT INTO `t_teacher` VALUES ('1560413', '04CA6A8D624EAD93AC5A308BBDCE5038', '教师104', null);
INSERT INTO `t_teacher` VALUES ('1560414', '682016CE94295FFD23EEFDC23CEBEE38', '教师105', null);
INSERT INTO `t_teacher` VALUES ('1560415', 'FDE579C0A7AD2707F510AC914D6EE097', '教师106', null);
INSERT INTO `t_teacher` VALUES ('1560416', '4AD72989415B43DE9848BE4BDF328FA0', '教师107', null);
INSERT INTO `t_teacher` VALUES ('1560417', '4A866CB8A49C4BA46C9B813FFBC18D4A', '教师108', null);
INSERT INTO `t_teacher` VALUES ('1560418', 'D9AE2C13B336A7E83FAE7CC065FF870A', '教师109', null);
INSERT INTO `t_teacher` VALUES ('1560419', '924B6AAE33AFFB8F8CD02D4F3DA43FB6', '教师110', null);
INSERT INTO `t_teacher` VALUES ('1560420', '7061985BB4D0592F8B7F9D03BC440091', '教师111', null);
INSERT INTO `t_teacher` VALUES ('1560421', '990BD733779DC90E8E59BB30DA30F7E7', '教师112', null);
INSERT INTO `t_teacher` VALUES ('1560422', '54AD85AAEB5B918832344A5A00C5F750', '教师113', null);
INSERT INTO `t_teacher` VALUES ('1560423', 'F616CC5FB7A7B2B9B57A628E4516A7E0', '教师114', null);
INSERT INTO `t_teacher` VALUES ('1560424', '0744B059BF21E2BBA7BB104365AD0E93', '教师115', null);
INSERT INTO `t_teacher` VALUES ('1560425', 'A3F19A4C07122DA492E21A101C63A5C3', '教师116', null);
INSERT INTO `t_teacher` VALUES ('1560426', '6EFD5B3DA334A601824ED7B7275423BD', '教师117', null);
INSERT INTO `t_teacher` VALUES ('1560427', 'F3539E1259B70BD08FCD07334A0A8260', '教师118', null);
INSERT INTO `t_teacher` VALUES ('1560428', 'F4E4DD3E2D1BCC23A49D730C96DEE86F', '教师119', null);
INSERT INTO `t_teacher` VALUES ('1560429', '5F029A45093EBE3C87F8E8AE8C162EE5', '教师120', null);
INSERT INTO `t_teacher` VALUES ('1560430', 'BBAA430797D57E865DC96D651736A3BC', '教师121', null);
INSERT INTO `t_teacher` VALUES ('1560431', '7FD9CB59C5C1B19E430475B70F7F642D', '教师122', null);
INSERT INTO `t_teacher` VALUES ('1560432', '48B9D4135CE3155A139F38E38291E767', '教师123', null);
INSERT INTO `t_teacher` VALUES ('1560433', 'DC4161E07479A7B4AF21E73A3C643F2D', '教师124', null);
INSERT INTO `t_teacher` VALUES ('1560434', 'A375F989F95DBD90CB2C78D881C06113', '教师125', null);
INSERT INTO `t_teacher` VALUES ('1560435', 'D974F9840212C299F108422AD24A1E14', '教师126', null);
INSERT INTO `t_teacher` VALUES ('1560436', '0C6E8517CF84582E8997BF6FDDB94FB5', '教师127', null);
INSERT INTO `t_teacher` VALUES ('1560437', '4651EB1325B53845E1AFA45673E6F85A', '教师128', null);
INSERT INTO `t_teacher` VALUES ('1560438', '6E6299AE8A5E5ED0456CFE203D4AFBCC', '教师129', null);
INSERT INTO `t_teacher` VALUES ('1560439', '901DC6A69198B687B83E7535382B4B73', '教师130', null);
INSERT INTO `t_teacher` VALUES ('1560440', '6AE4F0B99AFDFED6B5B3C15B4BBF3E31', '教师131', null);
INSERT INTO `t_teacher` VALUES ('1560441', 'EB4A03483D44496E85E1F426DD4DEB5F', '教师132', null);
INSERT INTO `t_teacher` VALUES ('1560442', '45C5D399278E62A711E7AE032CC3054F', '教师133', null);
INSERT INTO `t_teacher` VALUES ('1560443', '62E540229F33F437FC2FF2A599D995E5', '教师134', null);
INSERT INTO `t_teacher` VALUES ('1560444', 'F97FF397E5830BD6CB49DE905BAD2918', '教师135', null);
INSERT INTO `t_teacher` VALUES ('1560445', '90A4CAE3D95A744B65AD1A395312AFA0', '教师136', null);
INSERT INTO `t_teacher` VALUES ('1560446', '3D58869D966228079DF93D7477A8982B', '教师137', null);
INSERT INTO `t_teacher` VALUES ('1560447', '558E15BAE9868D671A5B5D80525F0F03', '教师138', null);
INSERT INTO `t_teacher` VALUES ('1560448', '67FC84F7852312E9EF344FE4261B5571', '教师139', null);
INSERT INTO `t_teacher` VALUES ('1560449', 'C8BED2807A13B618301A96E8589B91CC', '教师140', null);
INSERT INTO `t_teacher` VALUES ('1560450', '332B9CD04ED26FD83BAFDFA6046296C9', '教师141', null);
INSERT INTO `t_teacher` VALUES ('1560451', '01FFA8B50AE9C6CD827B6671EC72A945', '教师142', null);
INSERT INTO `t_teacher` VALUES ('1560452', 'AB5E8CB422A5AE61C3FAFC5630291D5D', '教师143', null);
INSERT INTO `t_teacher` VALUES ('1560453', '5FF3E9D0A49646FC916586AC15BE354E', '教师144', null);
INSERT INTO `t_teacher` VALUES ('1560454', '3C087E0732DB8F5A611071D32944092F', '教师145', null);
INSERT INTO `t_teacher` VALUES ('1560455', 'FCFC24006EC4DFF1C4E8392C55E7FD5E', '教师146', null);
INSERT INTO `t_teacher` VALUES ('1560456', 'A8C5A6CC02D65019DD7AAFC075E1FF83', '教师147', null);
INSERT INTO `t_teacher` VALUES ('1560457', '10BF5F4D8F54B2B8BB0D0A00C1B6C935', '教师148', null);
INSERT INTO `t_teacher` VALUES ('1560458', 'AA20EDA2C6E0A50B8FC517AEFB449597', '教师149', null);
INSERT INTO `t_teacher` VALUES ('1560459', '562C3E047FFEF9530DD820A302A24E41', '教师150', null);
INSERT INTO `t_teacher` VALUES ('1560460', '4E2D74759AA08ABDC142D2F756F6A18B', '教师151', null);
INSERT INTO `t_teacher` VALUES ('1560461', 'A0F639B4BB214FE209CCFDD038140749', '教师152', null);
INSERT INTO `t_teacher` VALUES ('1560462', 'BBFFB4991A3921ED332C0F9347DECD4E', '教师153', null);
INSERT INTO `t_teacher` VALUES ('1560463', 'CDCDB10CBD77D35857B67DF3B3B1180B', '教师154', null);
INSERT INTO `t_teacher` VALUES ('1560464', '8F42589209415E24830148D911A1A8CA', '教师155', null);
INSERT INTO `t_teacher` VALUES ('1560465', '584980E3B32412D90CC9081B4CC9FF8A', '教师156', null);
INSERT INTO `t_teacher` VALUES ('1560466', 'B96437D28ACF43D237E6182B23C1D20D', '教师157', null);
INSERT INTO `t_teacher` VALUES ('1560467', 'A77FBAAC7BB1F97EB4BD0A9EC971300E', '教师158', null);
INSERT INTO `t_teacher` VALUES ('1560468', '6F380285D9FB384E27310BB655D58A8A', '教师159', null);
INSERT INTO `t_teacher` VALUES ('1560469', '08A5BF0F0EEFF0B31BC1AECF585E71E2', '教师160', null);
INSERT INTO `t_teacher` VALUES ('1560470', '0F27FC1178FAAF194F3CD23E1B16CC54', '教师161', null);
INSERT INTO `t_teacher` VALUES ('1560471', '519B403B500114795FA34C7119C00D71', '教师162', null);
INSERT INTO `t_teacher` VALUES ('1560472', 'C0BD73D3447A83FC258A13F7F83D2D56', '教师163', null);
INSERT INTO `t_teacher` VALUES ('1560473', 'F2475B9296D86B645BD587DFB4FE8914', '教师164', null);
INSERT INTO `t_teacher` VALUES ('1560474', 'AC2423EFDB844001E4D0E7C4B1D6AC7E', '教师165', null);
INSERT INTO `t_teacher` VALUES ('1560475', '44C57438AF80AC69D5DE56431BFCB320', '教师166', null);
INSERT INTO `t_teacher` VALUES ('1560476', '754EC262D4C99A11FD668EFC06A7C622', '教师167', null);
INSERT INTO `t_teacher` VALUES ('1560477', '6264AF2442301C23C69D4F12F5E7EA6F', '教师168', null);
INSERT INTO `t_teacher` VALUES ('1560478', 'B1EFA1B440627440D3DFAF0053F28B5C', '教师169', null);
INSERT INTO `t_teacher` VALUES ('1560479', '7876E13A17A01391D30CC39E879C96B4', '教师170', null);
INSERT INTO `t_teacher` VALUES ('1560480', 'A46966699FDB618C9B58975EB99A33CA', '教师171', null);
INSERT INTO `t_teacher` VALUES ('1560481', 'EF96075A60856A101DCF813EDA0BC132', '教师172', null);
INSERT INTO `t_teacher` VALUES ('1560482', '156535BFF733B862B9375E34656064D2', '教师173', null);
INSERT INTO `t_teacher` VALUES ('1560483', '04A0F31839747FF835B0413F581DD983', '教师174', null);
INSERT INTO `t_teacher` VALUES ('1560484', 'BDFDA0D8F5B832D2C69BD3B281B4D764', '教师175', null);
INSERT INTO `t_teacher` VALUES ('1560485', 'DF5B093C20350E77FBF54779B6FBA36F', '教师176', null);
INSERT INTO `t_teacher` VALUES ('1560486', '526ADAE87869D98D55CDAC8E43D297D8', '教师177', null);
INSERT INTO `t_teacher` VALUES ('1560487', '53A11BF387817FF80F62D6CEA30D5887', '教师178', null);
INSERT INTO `t_teacher` VALUES ('1560488', '4ECC436C5E97A3B636A5BF134ECF14A4', '教师179', null);
INSERT INTO `t_teacher` VALUES ('1560489', 'D15F8ECD9013901BCE2649E1382B43F0', '教师180', null);
INSERT INTO `t_teacher` VALUES ('1560490', '4D4C7BECF7BAF659785969ECFC3E0170', '教师181', null);
INSERT INTO `t_teacher` VALUES ('1560491', '7FB5405666B7AB5E07877E570B1FA3CF', '教师182', null);
INSERT INTO `t_teacher` VALUES ('1560492', '1FC0B52984A6D62474D7778AB3CB68B6', '教师183', null);
INSERT INTO `t_teacher` VALUES ('1560493', 'E475B7B385CE281AEBF333857FF1F96B', '教师184', 'sdf ');
INSERT INTO `t_teacher` VALUES ('1560494', '864E4717924314D215B0AFAFDB55ADF4', '教师185', null);
INSERT INTO `t_teacher` VALUES ('1560495', '250B85C50E492E467A617A7B11310BF8', '教师186', null);
INSERT INTO `t_teacher` VALUES ('1560497', '5C5DB17EF48D2D360381F6C2296B234D', '教师188', null);
INSERT INTO `t_teacher` VALUES ('1560498', '8D059B96F45BEA0595E081D02A9C2FF0', '教师189', null);
INSERT INTO `t_teacher` VALUES ('1560499', 'ABA9244AA3F2A1721F17610736B5A7CA', '教师190', null);
