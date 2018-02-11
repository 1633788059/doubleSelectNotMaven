/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.1.39-community : Database - doubleselect
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`doubleselect` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `doubleselect`;

/*Table structure for table `tauth` */

DROP TABLE IF EXISTS `tauth`;

CREATE TABLE `tauth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_name` varchar(20) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8wea8xhq8xkkt1ji2csd0trkk` (`pid`),
  CONSTRAINT `FK_8wea8xhq8xkkt1ji2csd0trkk` FOREIGN KEY (`pid`) REFERENCES `tauth` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

/*Data for the table `tauth` */

insert  into `tauth`(`id`,`auth_name`,`url`,`pid`) values (0,'根结点',NULL,NULL),(1,'系统管理',NULL,0),(2,'信息管理',NULL,0),(3,'日志记录',NULL,1),(4,'角色管理','sys/roleController/open',1),(5,'用户管理','sys/userController/open',1),(6,'添加用户','sys/userController/add',5),(7,'删除用户','sys/userController/delete',5),(8,'设置角色','sys/userController/setRole',5),(9,'添加角色','sys/roleController/add',4),(10,'删除角色','sys/roleController/delete',4),(11,'学生信息管理',NULL,2),(12,'学生信息','student/studentmessage/open',11),(13,'学生信息添加','student/studentmessage/add',12),(14,'学生信息更新','student/studentmessage/update',12),(15,'学生信息删除','student/studentmessage/delete',12),(16,'教师信息管理',NULL,2),(17,'教师信息','teacher/teachermessage/open',16),(18,'教师信息添加','teacher/teachermessage/add',17),(19,'教师信息更新','teacher/teachermessage/update',17),(20,'教师信息删除','teacher/teachermessage/delete',17),(21,'学生个人信息中心',NULL,0),(22,'学生个人信息','student/studentmessage/xinxi',21),(23,'教师个人信息中心',NULL,0),(24,'教师个人信息','teacher/teachermessage/xinxi',23),(25,'志愿信息管理',NULL,0),(26,'填报志愿信息','volunteer/volunteermessage/open',25),(27,'志愿信息添加','volunteer/volunteermessage/add',26),(28,'志愿信息修改','volunteer/volunteermessage/update',26),(29,'志愿信息删除','volunteer/volunteermessage/delete',26),(30,'学生选报志愿','volunteer/volunteermessage/studentopen',25),(31,'学生志愿添加','volunteer/volunteermessage/studentadd',30),(34,'教师选报志愿','volunteer/volunteermessage/teacheropen',25),(35,'教师志愿添加','volunteer/volunteermessage/teacheraddvolunteer',34),(38,'数据字典','sys/shujuzdController/open',1),(39,'添加数据字典','sys/shujuzdController/add',38),(40,'删除数据字典','sys/shujuzdController/delete',38),(41,'查询权限','sys/roleController/queryTreeMenu',4),(42,'修改数据字典','sys/shujuzdController/update',38),(43,'查看选择教师学生','volunteer/volunteermessage/teacherselect',25),(44,'教师志愿信息修改','volunteer/volunteermessage/teachermessageupdate',25),(45,'学生志愿信息修改','volunteer/volunteermessage/studentmessageupdate',25),(46,'匹配','volunteer/volunteermessage/marry',48),(47,'双选匹配管理',NULL,0),(48,'匹配信息','match/matchmessage/open',47),(49,'匹配信息添加','match/matchmessage/add',48),(50,'匹配信息修改','match/matchmessage/update',48),(51,'匹配信息删除','match/matchmessage/delete',48),(52,'学生信息导出','student/studentmessage/export',22),(53,'学生信息导入','student/studentmessage/upload',22),(54,'教师信息导出','teacher/teachermessage/export',17),(55,'教师信息导入','teacher/teachermessage/upload',17),(56,'学生信息修改显示','student/studentmessage/updateinfomation',22),(57,'教师信息修改显示','teacher/teachermessage/updateinfomation',24),(58,'设定时间',NULL,0),(59,'时间管理','manage/managemessage/open',58),(60,'时间管理添加','manage/managemessage/add',59),(61,'时间管理修改','manage/managemessage/update',59),(62,'时间管理删除','manage/managemessage/delete',59),(63,'图片信息',NULL,59),(64,'图片管理','image/imagemessage/open',63),(65,'图片添加','image/imagemessage/add',64),(66,'图片修改','image/imagemessage/update',64),(67,'图片删除','image/imagemessage/delete',64),(68,'图片查找','image/imagemessage/searchImage',64),(69,'图片上传','teacher/teachermessage/uploadImage',64),(70,'学生图片上传','student/studentmessage/uploadImage',22),(71,'系统系能',NULL,0),(72,'性能监控','druid/index.html',71);

/*Table structure for table `tcity` */

DROP TABLE IF EXISTS `tcity`;

CREATE TABLE `tcity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(10) NOT NULL,
  `province_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`cityName`),
  KEY `province_id` (`province_id`),
  CONSTRAINT `tcity_ibfk_1` FOREIGN KEY (`province_id`) REFERENCES `tprovince` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `tcity` */

insert  into `tcity`(`id`,`cityName`,`province_id`) values (9,'开封市',1),(10,'新乡市',1),(11,'南阳市',1),(12,'朝阳区',2),(13,'武汉市',3),(15,'黄石市',3),(14,'长沙市',4);

/*Table structure for table `tcounty` */

DROP TABLE IF EXISTS `tcounty`;

CREATE TABLE `tcounty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `countyName` varchar(10) NOT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`countyName`),
  KEY `city_id` (`city_id`),
  CONSTRAINT `tcounty_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `tcity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `tcounty` */

insert  into `tcounty`(`id`,`countyName`,`city_id`) values (15,'江岸区',13),(16,'江汉区',13);

/*Table structure for table `timage` */

DROP TABLE IF EXISTS `timage`;

CREATE TABLE `timage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(11) NOT NULL,
  `imagename` varchar(255) NOT NULL,
  `imagepath` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sno` (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `timage` */

insert  into `timage`(`id`,`sno`,`imagename`,`imagepath`) values (1,'2','20160421222120741.jpg','\\tea\\1\\20160421222120741.jpg'),(3,'1','20160504162303726.jpg','\\tea\\1\\20160504162303726.jpg'),(4,'3','20160504182229982.jpg','\\tea\\3\\20160504182229982.jpg'),(5,'4','20160504183252850.jpg','\\stu\\4\\20160504183252850.jpg'),(6,'1327001','20160526150346424.jpg','\\stu\\1327001\\20160526150346424.jpg'),(7,'1300100','20160504224839022.jpg','\\tea\\1300100\\20160504224839022.jpg');

/*Table structure for table `tmanage` */

DROP TABLE IF EXISTS `tmanage`;

CREATE TABLE `tmanage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `endtime` bigint(20) NOT NULL,
  `note` varchar(255) NOT NULL,
  `starttime` bigint(20) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tnxrmcln3bmjg91w004wchlpm` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `tmanage` */

insert  into `tmanage`(`id`,`endtime`,`note`,`starttime`,`value`) values (6,1511956800,'学生选择支援时间控制',1460527398,'studentSelect'),(8,1500446407,'老师选择志愿时间控制',1459838404,'teacherSelect'),(9,1501160282,'学生修改志愿之间控制',1459947479,'studentUpdate'),(10,1514466701,'教师修改志愿时间控制',1459861898,'teacherUpdate');

/*Table structure for table `tmatch` */

DROP TABLE IF EXISTS `tmatch`;

CREATE TABLE `tmatch` (
  `studentsno` varchar(20) NOT NULL,
  `teachersno` varchar(20) NOT NULL,
  PRIMARY KEY (`studentsno`,`teachersno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tmatch` */

insert  into `tmatch`(`studentsno`,`teachersno`) values ('1','1'),('123','111');

/*Table structure for table `tprovince` */

DROP TABLE IF EXISTS `tprovince`;

CREATE TABLE `tprovince` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provinceName` varchar(10) NOT NULL,
  PRIMARY KEY (`id`,`provinceName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tprovince` */

insert  into `tprovince`(`id`,`provinceName`) values (1,'河南省'),(2,'北京市'),(3,'湖北省'),(4,'湖南省'),(5,'山西省');

/*Table structure for table `trole` */

DROP TABLE IF EXISTS `trole`;

CREATE TABLE `trole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `trole` */

insert  into `trole`(`id`,`name`) values (1,'系统管理员'),(2,'资源管理员'),(3,'导师'),(4,'研究生');

/*Table structure for table `trole_tauth` */

DROP TABLE IF EXISTS `trole_tauth`;

CREATE TABLE `trole_tauth` (
  `trole_id` int(11) NOT NULL,
  `tauth_id` int(11) NOT NULL,
  PRIMARY KEY (`tauth_id`,`trole_id`),
  KEY `FK_dkr3yl0gr6r3q02ofjsr72din` (`tauth_id`),
  KEY `FK_rotjiod037nea8c4og3sfo025` (`trole_id`),
  CONSTRAINT `FK_dkr3yl0gr6r3q02ofjsr72din` FOREIGN KEY (`tauth_id`) REFERENCES `tauth` (`id`),
  CONSTRAINT `FK_rotjiod037nea8c4og3sfo025` FOREIGN KEY (`trole_id`) REFERENCES `trole` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `trole_tauth` */

insert  into `trole_tauth`(`trole_id`,`tauth_id`) values (1,1),(1,2),(2,2),(4,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(2,11),(4,11),(1,12),(2,12),(4,12),(1,13),(2,13),(1,14),(2,14),(4,14),(1,15),(2,15),(1,16),(2,16),(1,17),(2,17),(1,18),(2,18),(1,19),(2,19),(3,19),(1,20),(2,20),(2,21),(4,21),(2,22),(4,22),(2,23),(3,23),(2,24),(3,24),(1,25),(3,25),(4,25),(1,26),(4,26),(1,27),(1,28),(4,28),(1,29),(4,29),(4,30),(4,31),(3,34),(3,35),(1,38),(1,39),(1,40),(1,41),(1,42),(3,43),(3,44),(4,45),(1,46),(1,47),(1,48),(1,49),(1,50),(1,51),(1,52),(1,53),(1,54),(1,55),(4,56),(3,57),(1,58),(1,59),(1,60),(1,61),(1,62),(1,63),(1,64),(1,65),(3,65),(4,65),(1,66),(3,66),(4,66),(1,67),(3,67),(4,67),(1,68),(3,68),(4,68),(3,69),(4,69),(4,70),(1,71),(1,72);

/*Table structure for table `tshujuzd` */

DROP TABLE IF EXISTS `tshujuzd`;

CREATE TABLE `tshujuzd` (
  `zdlb` varchar(20) DEFAULT NULL,
  `zddm` varchar(20) DEFAULT NULL,
  `zdms` varchar(30) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `tshujuzd` */

insert  into `tshujuzd`(`zdlb`,`zddm`,`zdms`,`id`) values ('民族','汉','',1),('城市','北京','',11),('城市','上海','',15),('城市','天津','',16);

/*Table structure for table `tstudent` */

DROP TABLE IF EXISTS `tstudent`;

CREATE TABLE `tstudent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentsno` varchar(20) NOT NULL COMMENT '学号',
  `studentmajor` varchar(20) NOT NULL COMMENT '专业',
  `finaldegree` varchar(20) NOT NULL COMMENT '最后学历',
  `graduation` varchar(20) NOT NULL COMMENT '毕业学校',
  `time` datetime NOT NULL COMMENT '毕业时间',
  `expertise` varchar(20) NOT NULL COMMENT '专业特长',
  `studentname` varchar(20) NOT NULL COMMENT '学生姓名',
  `studentsex` varchar(20) NOT NULL COMMENT '学生性别',
  PRIMARY KEY (`id`),
  UNIQUE KEY `studentsno` (`studentsno`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tstudent` */

insert  into `tstudent`(`id`,`studentsno`,`studentmajor`,`finaldegree`,`graduation`,`time`,`expertise`,`studentname`,`studentsex`) values (1,'1327001','软件工程','本科','河南大学','2016-04-07 23:04:32','写代码','彭辉','男'),(3,'123','123','123','13','2016-05-11 16:15:17','132','123','男'),(4,'11','1','1','1','2017-04-05 21:40:40','1','1','女');

/*Table structure for table `tteacher` */

DROP TABLE IF EXISTS `tteacher`;

CREATE TABLE `tteacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teachername` varchar(20) NOT NULL COMMENT '教师姓名',
  `teachersno` varchar(20) NOT NULL COMMENT '教师工号',
  `teachersex` varchar(20) NOT NULL COMMENT '教师性别',
  `teachermajor` varchar(20) NOT NULL COMMENT '教师专业',
  `guidemin` int(11) NOT NULL DEFAULT '1' COMMENT '指导最少学生',
  `guidemax` int(11) NOT NULL COMMENT '指导的最多学生',
  `degree` varchar(11) NOT NULL COMMENT '学位(博士/硕士/学士)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `teachersno` (`teachersno`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tteacher` */

insert  into `tteacher`(`id`,`teachername`,`teachersno`,`teachersex`,`teachermajor`,`guidemin`,`guidemax`,`degree`) values (1,'老师','1300100','男','计算机科学技术',1,5,'硕士');

/*Table structure for table `tuser` */

DROP TABLE IF EXISTS `tuser`;

CREATE TABLE `tuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdatetime` datetime DEFAULT NULL,
  `modifydatetime` datetime DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `pwd` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1t2tvox1pi3i9c3tk04q1ug1e` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tuser` */

insert  into `tuser`(`id`,`createdatetime`,`modifydatetime`,`name`,`pwd`) values (1,NULL,NULL,'admin','21232f297a57a5a743894a0e4a801fc3'),(3,'2016-05-26 18:06:04',NULL,'1300100','e10adc3949ba59abbe56e057f20f883e'),(4,'2016-05-26 18:35:50',NULL,'1327001','e10adc3949ba59abbe56e057f20f883e');

/*Table structure for table `tuser_trole` */

DROP TABLE IF EXISTS `tuser_trole`;

CREATE TABLE `tuser_trole` (
  `tuser_id` int(11) NOT NULL,
  `trole_id` int(11) NOT NULL,
  PRIMARY KEY (`tuser_id`,`trole_id`),
  KEY `FK_ibl685vb7j7k7xjnijo2jisat` (`trole_id`),
  KEY `FK_b89pcxpa2wqnub9av1icegtp8` (`tuser_id`),
  CONSTRAINT `FK_b89pcxpa2wqnub9av1icegtp8` FOREIGN KEY (`tuser_id`) REFERENCES `tuser` (`id`),
  CONSTRAINT `FK_ibl685vb7j7k7xjnijo2jisat` FOREIGN KEY (`trole_id`) REFERENCES `trole` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tuser_trole` */

insert  into `tuser_trole`(`tuser_id`,`trole_id`) values (1,1),(3,3),(4,4);

/*Table structure for table `tvolunteer` */

DROP TABLE IF EXISTS `tvolunteer`;

CREATE TABLE `tvolunteer` (
  `rank` int(11) NOT NULL,
  `studentsno` varchar(20) NOT NULL,
  `teachersno` varchar(20) NOT NULL,
  PRIMARY KEY (`rank`,`studentsno`,`teachersno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tvolunteer` */

insert  into `tvolunteer`(`rank`,`studentsno`,`teachersno`) values (1,'1327001','1300100');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
