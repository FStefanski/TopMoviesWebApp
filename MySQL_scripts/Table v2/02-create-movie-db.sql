--
-- Creating DB top_movies_collection version 2
--
DROP SCHEMA IF EXISTS `top_movies_collection_v2`;
CREATE SCHEMA IF NOT EXISTS `top_movies_collection_v2` ;
USE `top_movies_collection_v2` ;

-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`production_year`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `production_year`;
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`production_year` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `year` VARCHAR(45)  DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
 
-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `genre`;
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `genre` VARCHAR(45)  DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `actor`;
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`actor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `actor` VARCHAR(45)  DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `director`;
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`director` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `director` VARCHAR(45)  DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `imdb_rating`;
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`imdb_rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `imdb_rating` VARCHAR(45)  DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_rating`;
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`user_rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_rating` VARCHAR(45)  DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `watch_list`;
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`watch_list` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `is_on_watch_list` boolean DEFAULT FALSE,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`movie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `imdb_position` INT  DEFAULT NULL,
  `imdb_id` VARCHAR(45)  DEFAULT NULL,
  `title` VARCHAR(255)  DEFAULT NULL,
  `poster` VARCHAR(255)  DEFAULT NULL,
  
  `year_id` INT,
  `genre_id` INT,
  `actor_id` INT,
  
  `director_id` INT,
  `imdb_rating_id` INT,
  `user_rating_id` INT,
  `watch_list_id` INT,
  
  PRIMARY KEY (`id`),
  FOREIGN KEY (year_id) REFERENCES production_year(id),
  FOREIGN KEY (actor_id) REFERENCES actor(id),
  FOREIGN KEY (genre_id) REFERENCES genre(id)
)ENGINE = InnoDB;

