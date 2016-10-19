# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.7.14)
# Database: bookstore
# Generation Time: 2016-10-19 04:33:10 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table auction_bid
# ------------------------------------------------------------

DROP TABLE IF EXISTS `auction_bid`;

CREATE TABLE `auction_bid` (
  `ID` bigint(20) NOT NULL,
  `BIDTIME` longblob,
  `BIDVALUE` float DEFAULT NULL,
  `MEMBERID_ID` bigint(20) DEFAULT NULL,
  `SALEID_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_auction_bid_MEMBERID_ID` (`MEMBERID_ID`),
  KEY `FK_auction_bid_SALEID_ID` (`SALEID_ID`),
  CONSTRAINT `FK_auction_bid_MEMBERID_ID` FOREIGN KEY (`MEMBERID_ID`) REFERENCES `members` (`ID`),
  CONSTRAINT `FK_auction_bid_SALEID_ID` FOREIGN KEY (`SALEID_ID`) REFERENCES `sale` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table authors
# ------------------------------------------------------------

DROP TABLE IF EXISTS `authors`;

CREATE TABLE `authors` (
  `ID` bigint(20) NOT NULL,
  `GIVENNAMES` varchar(255) DEFAULT NULL,
  `SURNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNQ_authors_0` (`GIVENNAMES`,`SURNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table book_authors
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book_authors`;

CREATE TABLE `book_authors` (
  `authoredBooks_ISBN` varchar(255) NOT NULL,
  `bookAuthors_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`authoredBooks_ISBN`,`bookAuthors_ID`),
  KEY `FK_book_authors_bookAuthors_ID` (`bookAuthors_ID`),
  CONSTRAINT `FK_book_authors_authoredBooks_ISBN` FOREIGN KEY (`authoredBooks_ISBN`) REFERENCES `books` (`ISBN`),
  CONSTRAINT `FK_book_authors_bookAuthors_ID` FOREIGN KEY (`bookAuthors_ID`) REFERENCES `authors` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table book_reviews
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book_reviews`;

CREATE TABLE `book_reviews` (
  `ID` bigint(20) NOT NULL,
  `RATING` float DEFAULT NULL,
  `REVIEW` varchar(255) DEFAULT NULL,
  `REVIEWDATE` date DEFAULT NULL,
  `REVIEWED_ISBN` varchar(255) DEFAULT NULL,
  `REVIEWER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_book_reviews_REVIEWED_ISBN` (`REVIEWED_ISBN`),
  KEY `FK_book_reviews_REVIEWER_ID` (`REVIEWER_ID`),
  CONSTRAINT `FK_book_reviews_REVIEWED_ISBN` FOREIGN KEY (`REVIEWED_ISBN`) REFERENCES `books` (`ISBN`),
  CONSTRAINT `FK_book_reviews_REVIEWER_ID` FOREIGN KEY (`REVIEWER_ID`) REFERENCES `members` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table books
# ------------------------------------------------------------

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `ISBN` varchar(255) NOT NULL,
  `COSTPRICE` double DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DESCRIPTION` text,
  `FORMAT` varchar(255) DEFAULT NULL,
  `GENRE` varchar(255) DEFAULT NULL,
  `PUBYEAR` date DEFAULT NULL,
  `PUBLISHER` varchar(255) DEFAULT NULL,
  `RETAILPRICE` double DEFAULT NULL,
  `STOCKLEVEL` int(11) DEFAULT NULL,
  `THUMBNAIL` longblob,
  `TITLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table books_wanted
# ------------------------------------------------------------

DROP TABLE IF EXISTS `books_wanted`;

CREATE TABLE `books_wanted` (
  `ID` bigint(20) NOT NULL,
  `BOOKCONDITION` varchar(255) DEFAULT NULL,
  `SHOULDNOTIFY` tinyint(1) DEFAULT '0',
  `MEMBER_ID` bigint(20) DEFAULT NULL,
  `WANTS_ISBN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_books_wanted_MEMBER_ID` (`MEMBER_ID`),
  KEY `FK_books_wanted_WANTS_ISBN` (`WANTS_ISBN`),
  CONSTRAINT `FK_books_wanted_MEMBER_ID` FOREIGN KEY (`MEMBER_ID`) REFERENCES `members` (`ID`),
  CONSTRAINT `FK_books_wanted_WANTS_ISBN` FOREIGN KEY (`WANTS_ISBN`) REFERENCES `books` (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table INTEREST
# ------------------------------------------------------------

DROP TABLE IF EXISTS `INTEREST`;

CREATE TABLE `INTEREST` (
  `GENRE` varchar(255) NOT NULL,
  PRIMARY KEY (`GENRE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table members
# ------------------------------------------------------------

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `ID` bigint(20) NOT NULL,
  `BILLINGADDRESSLINE1` varchar(255) DEFAULT NULL,
  `BILLINGADDRESSLINE2` varchar(255) DEFAULT NULL,
  `BILLINGCITY` varchar(255) DEFAULT NULL,
  `BILLINGPOSTCODE` varchar(255) DEFAULT NULL,
  `BILLINGSTATE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `JOINDATE` datetime DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PERMISSIONGROUP` varchar(255) DEFAULT NULL,
  `SHIPPINGADDRESSLINE1` varchar(255) DEFAULT NULL,
  `SHIPPINGADDRESSLINE2` varchar(255) DEFAULT NULL,
  `SHIPPINGCITY` varchar(255) DEFAULT NULL,
  `SHIPPINGPOSTCODE` varchar(255) DEFAULT NULL,
  `SHIPPINGSTATE` varchar(255) DEFAULT NULL,
  `SURNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;

INSERT INTO `members` (`ID`, `BILLINGADDRESSLINE1`, `BILLINGADDRESSLINE2`, `BILLINGCITY`, `BILLINGPOSTCODE`, `BILLINGSTATE`, `EMAIL`, `FIRSTNAME`, `JOINDATE`, `PASSWORD`, `PERMISSIONGROUP`, `SHIPPINGADDRESSLINE1`, `SHIPPINGADDRESSLINE2`, `SHIPPINGCITY`, `SHIPPINGPOSTCODE`, `SHIPPINGSTATE`, `SURNAME`)
VALUES
	(1,NULL,NULL,NULL,NULL,NULL,'admin','Administrator','2016-10-19 14:30:15','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','admin',NULL,NULL,NULL,NULL,NULL,NULL),
	(2,'100 North Road',NULL,'Darwin','7654','Northern Territory','user@dukes.com','John','2016-10-19 14:30:26','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','customer','Unit 3','123 Fake Street','Fakeville','4000','QLD','Doe');

/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table members_INTEREST
# ------------------------------------------------------------

DROP TABLE IF EXISTS `members_INTEREST`;

CREATE TABLE `members_INTEREST` (
  `Member_ID` bigint(20) NOT NULL,
  `userInterests_GENRE` varchar(255) NOT NULL,
  PRIMARY KEY (`Member_ID`,`userInterests_GENRE`),
  KEY `FK_members_INTEREST_userInterests_GENRE` (`userInterests_GENRE`),
  CONSTRAINT `FK_members_INTEREST_Member_ID` FOREIGN KEY (`Member_ID`) REFERENCES `members` (`ID`),
  CONSTRAINT `FK_members_INTEREST_userInterests_GENRE` FOREIGN KEY (`userInterests_GENRE`) REFERENCES `INTEREST` (`GENRE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table members_PREFERENCE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `members_PREFERENCE`;

CREATE TABLE `members_PREFERENCE` (
  `Member_ID` bigint(20) NOT NULL,
  `userPreferences_PREFERENCE` varchar(255) NOT NULL,
  PRIMARY KEY (`Member_ID`,`userPreferences_PREFERENCE`),
  KEY `FK_members_PREFERENCE_userPreferences_PREFERENCE` (`userPreferences_PREFERENCE`),
  CONSTRAINT `FK_members_PREFERENCE_Member_ID` FOREIGN KEY (`Member_ID`) REFERENCES `members` (`ID`),
  CONSTRAINT `FK_members_PREFERENCE_userPreferences_PREFERENCE` FOREIGN KEY (`userPreferences_PREFERENCE`) REFERENCES `PREFERENCE` (`PREFERENCE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table payment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `ID` bigint(20) NOT NULL,
  `AMOUNT` float DEFAULT NULL,
  `COMMISSION` float DEFAULT NULL,
  `PAYMENTSTATUS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table PREFERENCE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PREFERENCE`;

CREATE TABLE `PREFERENCE` (
  `PREFERENCE` varchar(255) NOT NULL,
  `PREFERENCEDESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PREFERENCE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `PREFERENCE` WRITE;
/*!40000 ALTER TABLE `PREFERENCE` DISABLE KEYS */;

INSERT INTO `PREFERENCE` (`PREFERENCE`, `PREFERENCEDESCRIPTION`)
VALUES
	('pref.notify.auction.complete','If a user has this preference they will recieve a notification when auctions they are involved in are complete'),
	('pref.notify.auction.expire','If a user has this preference they will be notified when their auction(s) expires'),
	('pref.notify.auction.outbid','If a user has this preference they will be notified when they have been outbid'),
	('pref.notify.interest.newitem.avaliable','If a user has this preference they will be notified when a new item exists in a genre they are interested'),
	('pref.notify.method.email','If a user has this preference they will be notified by email'),
	('pref.notify.wishlist.auction','If a user has this preference they will be notified when an item on their wishlist is listed for auction'),
	('pref.notify.wishlist.sale','If a user has this preference they will be notified when an item on their wishlist is listed for sale');

/*!40000 ALTER TABLE `PREFERENCE` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sale
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sale`;

CREATE TABLE `sale` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATELISTED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DURATION` int(11) DEFAULT NULL,
  `ITEMCONDITION` varchar(255) DEFAULT NULL,
  `POSTAGE` float DEFAULT NULL,
  `RESERVEPRICE` float DEFAULT NULL,
  `SALEPRICE` float DEFAULT NULL,
  `SALETYPE` varchar(255) DEFAULT NULL,
  `STARTPRICE` float DEFAULT NULL,
  `BUYERID_ID` bigint(20) DEFAULT NULL,
  `ISBN_ISBN` varchar(255) DEFAULT NULL,
  `PAYMENTID_ID` bigint(20) DEFAULT NULL,
  `SELLERID_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_sale_SELLERID_ID` (`SELLERID_ID`),
  KEY `FK_sale_BUYERID_ID` (`BUYERID_ID`),
  KEY `FK_sale_ISBN_ISBN` (`ISBN_ISBN`),
  KEY `FK_sale_PAYMENTID_ID` (`PAYMENTID_ID`),
  CONSTRAINT `FK_sale_BUYERID_ID` FOREIGN KEY (`BUYERID_ID`) REFERENCES `members` (`ID`),
  CONSTRAINT `FK_sale_ISBN_ISBN` FOREIGN KEY (`ISBN_ISBN`) REFERENCES `books` (`ISBN`),
  CONSTRAINT `FK_sale_PAYMENTID_ID` FOREIGN KEY (`PAYMENTID_ID`) REFERENCES `payment` (`ID`),
  CONSTRAINT `FK_sale_SELLERID_ID` FOREIGN KEY (`SELLERID_ID`) REFERENCES `members` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table seller_reviews
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seller_reviews`;

CREATE TABLE `seller_reviews` (
  `ID` bigint(20) NOT NULL,
  `RATING` float DEFAULT NULL,
  `REVIEW` varchar(255) DEFAULT NULL,
  `REVIEWDATE` date DEFAULT NULL,
  `REVIEWED_ID` bigint(20) DEFAULT NULL,
  `REVIEWEE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_seller_reviews_REVIEWED_ID` (`REVIEWED_ID`),
  KEY `FK_seller_reviews_REVIEWEE_ID` (`REVIEWEE_ID`),
  CONSTRAINT `FK_seller_reviews_REVIEWED_ID` FOREIGN KEY (`REVIEWED_ID`) REFERENCES `members` (`ID`),
  CONSTRAINT `FK_seller_reviews_REVIEWEE_ID` FOREIGN KEY (`REVIEWEE_ID`) REFERENCES `members` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SEQUENCE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SEQUENCE`;

CREATE TABLE `SEQUENCE` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `SEQUENCE` WRITE;
/*!40000 ALTER TABLE `SEQUENCE` DISABLE KEYS */;

INSERT INTO `SEQUENCE` (`SEQ_NAME`, `SEQ_COUNT`)
VALUES
	('SEQ_GEN',50);

/*!40000 ALTER TABLE `SEQUENCE` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
