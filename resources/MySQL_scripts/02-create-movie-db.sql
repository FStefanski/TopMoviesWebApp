-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema top_movies_collection_v2
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `top_movies_collection_v2`;
-- -----------------------------------------------------
-- Schema top_movies_collection_v2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `top_movies_collection_v2` DEFAULT CHARACTER SET utf8 ;
USE `top_movies_collection_v2` ;

-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`actor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`actor` (
  `actor_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`actor_id`),
  UNIQUE INDEX `actor_id_UNIQUE` (`actor_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`production_year`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`production_year` (
  `year_id` INT(11) NOT NULL AUTO_INCREMENT,
  `year` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`year_id`),
  UNIQUE INDEX `year_id_UNIQUE` (`year_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`movie` (
  `movie_id` INT(11) NOT NULL AUTO_INCREMENT,
  `imdb_ranking_position` VARCHAR(45) NULL DEFAULT NULL,
  `imdb_id` VARCHAR(45) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `poster` VARCHAR(255) NULL DEFAULT NULL,
  `year_id` INT(11) NOT NULL,
  PRIMARY KEY (`movie_id`, `year_id`),
  UNIQUE INDEX `movie_id_UNIQUE` (`movie_id` ASC),
  INDEX `fk_movie_production_year1_idx` (`year_id` ASC),
  CONSTRAINT `fk_movie_production_year1`
    FOREIGN KEY (`year_id`)
    REFERENCES `top_movies_collection_v2`.`production_year` (`year_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`cast_mapping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`cast_mapping` (
  `cast_id` INT(11) NOT NULL AUTO_INCREMENT,
  `actor_id` INT(11) NOT NULL,
  `movie_id` INT(11) NOT NULL,
  PRIMARY KEY (`cast_id`, `actor_id`, `movie_id`),
  UNIQUE INDEX `cast_id_UNIQUE` (`cast_id` ASC),
  INDEX `fk_cast_and_director_mapping_actor1_idx` (`actor_id` ASC),
  INDEX `fk_cast_and_director_mapping_movie1_idx` (`movie_id` ASC),
  CONSTRAINT `fk_cast_and_director_mapping_actor1`
    FOREIGN KEY (`actor_id`)
    REFERENCES `top_movies_collection_v2`.`actor` (`actor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cast_and_director_mapping_movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `top_movies_collection_v2`.`movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`director`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`director` (
  `director_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`director_id`),
  UNIQUE INDEX `iddirector_id_UNIQUE` (`director_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`genre` (
  `genre_id` INT(11) NOT NULL AUTO_INCREMENT,
  `genre_type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`genre_id`),
  UNIQUE INDEX `genre_id_UNIQUE` (`genre_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`genre_mapping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`genre_mapping` (
  `genre_mapping_id` INT(11) NOT NULL AUTO_INCREMENT,
  `genre_id` INT(11) NOT NULL,
  `movie_id` INT(11) NOT NULL,
  PRIMARY KEY (`genre_mapping_id`, `genre_id`, `movie_id`),
  UNIQUE INDEX `genre_mapping_id_UNIQUE` (`genre_mapping_id` ASC),
  INDEX `fk_genre_mapping_genre1_idx` (`genre_id` ASC),
  INDEX `fk_genre_mapping_movie1_idx` (`movie_id` ASC),
  CONSTRAINT `fk_genre_mapping_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `top_movies_collection_v2`.`genre` (`genre_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_genre_mapping_movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `top_movies_collection_v2`.`movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`imdb_rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`imdb_rating` (
  `imdb_rating_id` INT(11) NOT NULL AUTO_INCREMENT,
  `imdb_rating` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`imdb_rating_id`),
  UNIQUE INDEX `idimdb_rating_id_UNIQUE` (`imdb_rating_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`user_rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`user_rating` (
  `user_rating_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_rating` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`user_rating_id`),
  UNIQUE INDEX `iduser_rating_id_UNIQUE` (`user_rating_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`watch_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`watch_list` (
  `watch_list_id` INT(11) NOT NULL AUTO_INCREMENT,
  `watch_list` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`watch_list_id`),
  UNIQUE INDEX `idwatch_list_UNIQUE` (`watch_list_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`ratings_and_settings_mapping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`ratings_and_settings_mapping` (
  `ratings_and_settings_mapping_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_rating_id` INT(11) NOT NULL,
  `watch_list_id` INT(11) NOT NULL,
  `imdb_rating_id` INT(11) NOT NULL,
  `movie_id` INT(11) NOT NULL,
  PRIMARY KEY (`ratings_and_settings_mapping_id`, `user_rating_id`, `watch_list_id`, `imdb_rating_id`, `movie_id`),
  UNIQUE INDEX `ratings_and_settings_mapping_id_UNIQUE` (`ratings_and_settings_mapping_id` ASC),
  INDEX `fk_ratings_and_settings_mapping_user_rating1_idx` (`user_rating_id` ASC),
  INDEX `fk_ratings_and_settings_mapping_watch_list1_idx` (`watch_list_id` ASC),
  INDEX `fk_ratings_and_settings_mapping_imdb_rating1_idx` (`imdb_rating_id` ASC),
  INDEX `fk_ratings_and_settings_mapping_movie1_idx` (`movie_id` ASC),
  CONSTRAINT `fk_ratings_and_settings_mapping_imdb_rating1`
    FOREIGN KEY (`imdb_rating_id`)
    REFERENCES `top_movies_collection_v2`.`imdb_rating` (`imdb_rating_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ratings_and_settings_mapping_movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `top_movies_collection_v2`.`movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ratings_and_settings_mapping_user_rating1`
    FOREIGN KEY (`user_rating_id`)
    REFERENCES `top_movies_collection_v2`.`user_rating` (`user_rating_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ratings_and_settings_mapping_watch_list1`
    FOREIGN KEY (`watch_list_id`)
    REFERENCES `top_movies_collection_v2`.`watch_list` (`watch_list_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `top_movies_collection_v2`.`director_mapping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `top_movies_collection_v2`.`director_mapping` (
  `director_mapping_id` INT NOT NULL AUTO_INCREMENT,
  `director_id` INT(11) NOT NULL,
  `movie_id` INT(11) NOT NULL,
  PRIMARY KEY (`director_mapping_id`, `director_id`, `movie_id`),
  UNIQUE INDEX `director_mapping_id_UNIQUE` (`director_mapping_id` ASC),
  INDEX `fk_director_mapping_director1_idx` (`director_id` ASC),
  INDEX `fk_director_mapping_movie1_idx` (`movie_id` ASC),
  CONSTRAINT `fk_director_mapping_director1`
    FOREIGN KEY (`director_id`)
    REFERENCES `top_movies_collection_v2`.`director` (`director_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_director_mapping_movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `top_movies_collection_v2`.`movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
