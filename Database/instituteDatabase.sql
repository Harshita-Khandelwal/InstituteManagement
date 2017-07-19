/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.5.21 : Database - theinstitutedb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`theinstitutedb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `theinstitutedb`;

/*Table structure for table `batch` */

DROP TABLE IF EXISTS `batch`;

CREATE TABLE `batch` (
  `course_id` smallint(6) NOT NULL DEFAULT '0',
  `batch_id` smallint(6) NOT NULL DEFAULT '0',
  `batch_name` varchar(20) DEFAULT NULL,
  `batch_start_time` time DEFAULT NULL,
  `start_am_pm` varchar(5) DEFAULT NULL,
  `batch_end_time` time DEFAULT NULL,
  `end_am_pm` varchar(5) DEFAULT NULL,
  `batch_duration` time DEFAULT NULL,
  `batch_start_date` date DEFAULT NULL,
  `faculty_id` smallint(6) DEFAULT NULL,
  `add_by` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`batch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `batch` */

insert  into `batch`(`course_id`,`batch_id`,`batch_name`,`batch_start_time`,`start_am_pm`,`batch_end_time`,`end_am_pm`,`batch_duration`,`batch_start_date`,`faculty_id`,`add_by`) values (0,1,'Pinnacle','05:30:00','pm','06:30:00','pm','01:00:00','2015-05-03',2,'myself'),(0,2,'ZenithC','08:30:00','am','09:30:00','am','01:00:00','2015-07-18',2,'myself'),(0,3,'cdoom','06:30:00','pm','09:00:00','pm','02:30:00','2015-07-09',2,'myself'),(1,1,'CppRock','05:30:00','pm','06:30:00','pm','01:00:00','2015-06-10',1,'myself'),(1,2,'Cppjockey','05:30:00','am','06:30:00','am','01:00:00','2015-06-13',1,'myself'),(1,3,'CppDemoZ','02:30:00','pm','03:30:00','pm','01:00:00','2015-06-18',1,'myself'),(2,1,'java Rocks','09:30:00','am','01:00:00','pm','03:30:00','2015-09-15',3,'myself'),(2,2,'demo','12:30:00','pm','01:30:00','pm','01:00:00','2015-06-15',4,'myself'),(2,3,'Yes','09:30:00','am','10:30:00','am','01:00:00','2015-06-15',4,'myself'),(2,4,'javaunagi','06:30:00','pm','08:00:00','pm','01:30:00','2015-07-03',3,'myself');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `course_id` smallint(6) NOT NULL DEFAULT '0',
  `course_name` varchar(10) DEFAULT NULL,
  `course_fees` smallint(6) DEFAULT NULL,
  `approx_duration` varchar(10) DEFAULT NULL,
  `add_by` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `course_name` (`course_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `course` */

insert  into `course`(`course_id`,`course_name`,`course_fees`,`approx_duration`,`add_by`) values (0,'C',4000,'3Months','myself'),(1,'C++',4500,'3Months','myself'),(2,'java',5500,'3.5Months','myself'),(3,'dsa',2250,'65Days','myself'),(4,'vh',6,'64Days','myself'),(5,'klnkl',646,'65Days','myself'),(6,'lml',5,'5Days','myself'),(7,'mklln',54,'4Days','myself'),(8,' m ,',5,'54Days','myself'),(9,'lknkl',55,'646Days','myself'),(10,'klnl',45,'6Days','myself'),(11,'mnk',454,'546Days','myself'),(12,'bj',6,'646Days','myself'),(13,'igijb',56,'595Days','myself'),(14,'kguib',4,'54Days','myself'),(15,'kljn',646,'65Days','myself'),(16,'jkb',54,'5Days','myself'),(17,'lkl',64,'64Days','myself'),(18,'kjjg',4,'35Days','myself'),(19,'k',45,'45Days','myself'),(20,'lhlk',5,'5Days','myself'),(21,'hjk',4,'4Days','myself'),(22,',bj',56,'65Days','myself');

/*Table structure for table `faculty` */

DROP TABLE IF EXISTS `faculty`;

CREATE TABLE `faculty` (
  `faculty_id` smallint(6) NOT NULL DEFAULT '0',
  `faculty_name` varchar(20) DEFAULT NULL,
  `faculty_address` varchar(50) DEFAULT NULL,
  `faculty_mobile_no` varchar(10) DEFAULT NULL,
  `fac_qualification` varchar(10) DEFAULT NULL,
  `course_id` smallint(6) DEFAULT NULL,
  `add_date` date DEFAULT NULL,
  `add_by` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`faculty_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `faculty` */

insert  into `faculty`(`faculty_id`,`faculty_name`,`faculty_address`,`faculty_mobile_no`,`fac_qualification`,`course_id`,`add_date`,`add_by`) values (1,'P.K.Tiwari','66,Murlipura,jaipur','2587413690','M.tech',1,'2013-05-12','myself'),(2,'S.K.Mittal','vaishali nagar  jaipur','1245789630','Phd',0,'2015-06-13','myself'),(3,'arijit','murlipura','1254632132','M.Tech',2,'2015-06-13','myself'),(4,'dadadala','mumbai','1236523111','B.Tech',2,'2015-06-14','myself');

/*Table structure for table `fee` */

DROP TABLE IF EXISTS `fee`;

CREATE TABLE `fee` (
  `receipt_no` smallint(6) DEFAULT NULL,
  `stu_id` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fee` */

insert  into `fee`(`receipt_no`,`stu_id`) values (1,0),(2,1),(3,1),(4,1),(5,2),(6,4);

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` smallint(6) NOT NULL DEFAULT '0',
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `MobileNo` varchar(10) DEFAULT NULL,
  `designation` varchar(20) DEFAULT NULL,
  `add_by` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`login_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`name`,`MobileNo`,`designation`,`add_by`) values (0,'myself','admin12','Harshita','9782590536','admin','bydefault'),(1,'manageinst','manager12','Lakshya','9829244263','manager','bydefault'),(2,'manageacc','account12','Shipra','8769003391','accounts_manager','myself'),(3,'khharshu94','harshita94','harshu','1236547895','admin','myself');

/*Table structure for table `query` */

DROP TABLE IF EXISTS `query`;

CREATE TABLE `query` (
  `query_id` smallint(6) NOT NULL DEFAULT '0',
  `query_name` varchar(30) DEFAULT NULL,
  `query_mobile_no` varchar(10) DEFAULT NULL,
  `query_father_name` varchar(30) DEFAULT NULL,
  `query_father_mobile_no` varchar(10) DEFAULT NULL,
  `query_address` varchar(50) DEFAULT NULL,
  `query_clg` varchar(20) DEFAULT NULL,
  `course_id` smallint(6) DEFAULT NULL,
  `add_by` varchar(10) DEFAULT NULL,
  `add_date` date DEFAULT NULL,
  PRIMARY KEY (`query_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `query` */

insert  into `query`(`query_id`,`query_name`,`query_mobile_no`,`query_father_name`,`query_father_mobile_no`,`query_address`,`query_clg`,`course_id`,`add_by`,`add_date`) values (0,'Lakshya Khandelwal','9782590536','Ajay Khandelwal','9829244263','63,shivpuri,murlipura,jaipur','Jaipur school',1,'myself','2015-03-15'),(1,'sunita','1254789544','radhe','7896546233','switzerland','pareek clg',2,'myself','2015-06-13'),(2,'lh','6447797979','kl;','6469797979',';k;','lkk',3,'myself','2015-07-28');

/*Table structure for table `receipt` */

DROP TABLE IF EXISTS `receipt`;

CREATE TABLE `receipt` (
  `receipt_no` smallint(6) DEFAULT NULL,
  `fee_deposit` smallint(6) DEFAULT NULL,
  `receipt_generated_by` varchar(10) DEFAULT NULL,
  `rec_generation_date` date DEFAULT NULL,
  `rec_generation_time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `receipt` */

insert  into `receipt`(`receipt_no`,`fee_deposit`,`receipt_generated_by`,`rec_generation_date`,`rec_generation_time`) values (1,3000,'myself','2015-12-03','03:10:12'),(2,1000,'myself','2015-06-13','13:56:08'),(3,900,'myself','2015-06-13','14:01:17'),(4,500,'myself','2015-06-13','15:58:04'),(5,1000,'myself','2015-06-13','17:06:39'),(6,500,'myself','2015-07-28','19:55:41');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `stu_id` smallint(6) NOT NULL DEFAULT '0',
  `stu_name` varchar(30) DEFAULT NULL,
  `stu_mobile_no` varchar(10) DEFAULT NULL,
  `stu_father_name` varchar(30) DEFAULT NULL,
  `stu_father_mobile_no` varchar(10) DEFAULT NULL,
  `stu_address` varchar(50) DEFAULT NULL,
  `stu_clg` varchar(20) DEFAULT NULL,
  `course_id` smallint(6) DEFAULT NULL,
  `batch_id` smallint(6) DEFAULT NULL,
  `add_by` varchar(10) DEFAULT NULL,
  `add_date` date DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`stu_id`,`stu_name`,`stu_mobile_no`,`stu_father_name`,`stu_father_mobile_no`,`stu_address`,`stu_clg`,`course_id`,`batch_id`,`add_by`,`add_date`) values (0,'Harshita Khandelwal','9782590536','Ajay Khandelwal','9829244263','63,shivpuri,murlipura,jaipur','Jecrc',0,1,'myself','2015-03-15'),(1,'Shipra','8769003391','Ajay Khandelwal','9829244263','63,shivpuri','Banasthali',0,1,'myself','2015-06-01'),(2,'LAkshya','2215362422','Ajay','9829244263','MURLIPURA','jaipur school',0,2,'myself','2015-06-01'),(3,'aastha','7877641176','Ashok Gambhir','1236547891','shastri nagar','jecrc',0,1,'myself','2015-06-01'),(4,'JustDemo','2312321254','DemoFather','2312546546','DisneyLand Paris\r\n','WaltDisney School',1,1,'myself','2015-06-12'),(5,'abc','1236541255','hga','2365231236','jjjj','aaaa',1,2,'myself','2015-06-12'),(6,'rte','9652365231','swsd','7895512365','fe','ef',1,3,'myself','2015-06-24'),(7,'hello','9632126358','hoo','9874563212','hh','kjjhjk',1,3,'manageinst','2015-07-05'),(8,'query','2563123652','querymentor','9874563210','queryCity			','abc',0,2,'myself','2015-07-28');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
