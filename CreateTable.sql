CREATE DATABASE  IF NOT EXISTS `db_operator` 
USE `db_operator`;

DROP TABLE IF EXISTS `inquiry`;

DROP TABLE IF EXISTS `attribute_of_inquiry`;
CREATE TABLE `attribute_of_inquiry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(450) DEFAULT NULL,
  `adress` varchar(450) DEFAULT NULL,
  `city` varchar(450) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `inquiry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL,
  `attribute_of_inquiry_id` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `customer_name` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_topic_idx` (`topic_id`),
  KEY `fk_attribute_of_inquiry_idx` (`attribute_of_inquiry_id`),
  CONSTRAINT `fk_topic` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_attribute_of_inquiry` FOREIGN KEY (`attribute_of_inquiry_id`) REFERENCES `attribute_of_inquiry` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;


CREATE DATABASE  IF NOT EXISTS `db_operator_test` 
USE `db_operator_test`;

DROP TABLE IF EXISTS `inquiry`;

DROP TABLE IF EXISTS `attribute_of_inquiry`;
CREATE TABLE `attribute_of_inquiry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(450) DEFAULT NULL,
  `adress` varchar(450) DEFAULT NULL,
  `city` varchar(450) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `inquiry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL,
  `attribute_of_inquiry_id` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `customer_name` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_topic_idx` (`topic_id`),
  KEY `fk_attribute_of_inquiry_idx` (`attribute_of_inquiry_id`),
  CONSTRAINT `fk_topic` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_attribute_of_inquiry` FOREIGN KEY (`attribute_of_inquiry_id`) REFERENCES `attribute_of_inquiry` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;




