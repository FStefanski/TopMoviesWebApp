
CREATE DATABASE  IF NOT EXISTS `web_customer_tracker` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `web_customer_tracker`;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

--
-- Table structure for table `movie`
--


CREATE SCHEMA IF NOT EXISTS `top_movies_collection` ;
USE `top_movies_collection` ;

-- -----------------------------------------------------
-- Table `top_movies_collection`.`table1`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `top_movies_collection`.`movie` (
  `movie_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45)  DEFAULT NULL,
  `year` VARCHAR(45)  DEFAULT NULL,
  `genre` VARCHAR(45)  DEFAULT NULL,
  `actors` VARCHAR(255)  DEFAULT NULL,
  `directors` VARCHAR(45)  DEFAULT NULL,
  `imdb_rating` VARCHAR(45)  DEFAULT NULL,
  `user_rating` VARCHAR(45)  DEFAULT NULL,
  `want_to_watch` VARCHAR(45)  DEFAULT NULL,
  PRIMARY KEY (`movie_id`))
ENGINE = InnoDB;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;

INSERT INTO `movie` VALUES 
	(1,'The Godfather','1972','Crime, Drama','Marlon Brando, Al Pacino, James Caan, Diane Keaton','Francis Ford Coppola','9.2',null, null),
	(2,'The Shawshank Redemption','1994','Crime, Drama','Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler','Frank Darabont','9.3',null, null),
	(3,'Schindler\'s List','1993','Biography, Drama, History','Liam Neeson, Ralph Fiennes, Ben Kingsley, Caroline Goodall','Steven Spielberg','8.9',null, null);

/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
