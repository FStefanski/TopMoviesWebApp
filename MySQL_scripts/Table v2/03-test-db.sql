
USE `top_movies_collection_v2`;

-- -----------------------------------------------------
-- Populate the tables
-- -----------------------------------------------------
INSERT IGNORE INTO `production_year` VALUES 
	(1,'production_year 1'),
	(2,'production_year 2'),
	(3,'production_year 3'),
	(4,'production_year 4');
    
INSERT IGNORE INTO `genre` VALUES 
	(1,'genre 1'),
	(2,'genre 2'),
	(3,'genre 3'),
	(4,'genre 4');
    
INSERT IGNORE INTO `actor` VALUES 
	(1,'actor 1'),
	(2,'actor 2'),
	(3,'actor 3'),
	(4,'actor 4');

INSERT IGNORE INTO `director` VALUES 
	(1,'director 1'),
	(2,'director 2'),
	(3,'director 3'),
	(4,'director 1');
    
INSERT IGNORE INTO `imdb_rating` VALUES 
	(1,'imdb_rating 1'),
	(2,'imdb_rating 2'),
	(3,'imdb_rating 3'),
	(4,'imdb_rating 1');
    
INSERT IGNORE INTO `user_rating` VALUES 
	(1,'user_rating 1'),
	(2,'user_rating 2'),
	(3,'user_rating 3'),
	(4,'user_rating 1');
    
INSERT IGNORE INTO `watch_list` VALUES 
	(1, default),
	(2, default),
	(3, true),
	(4, default);
    
INSERT IGNORE INTO `movie` VALUES 
	(1, 1,'imdb_id 1','title 1', 'poster 1', 1, 1, 1, 1, 1, 1, 1),
	(2, 2,'imdb_id 2','title 2', 'poster 2', 2, 2, 2, 2, 2, 2, 2),
	(3, 3,'imdb_id 3','title 3', 'poster 3', 3, 3, 3, 3, 3, 3, 3),
	(4, 4,'imdb_id 4','title 4', 'poster 4', 4, 4, 4, 4, 4, 4, 4);
-- -----------------------------------------------------
-- TEST DB
-- -----------------------------------------------------
/* test data */
/*SHOW tables;*/
SELECT * FROM movie;
/* SELECT * FROM production_year;
SELECT * FROM genre;
SELECT * FROM actor;*/

/* test join */
/* INNER JOIN to fill the values in table movie by foreign key */
SELECT * FROM movie
INNER JOIN actor
ON movie.actor_id = actor.id;

SELECT movie.id, movie.imdb_position, movie.imdb_id, movie.title, movie.poster,
		production_year.year, genre.genre, actor.actor, director.director,
        imdb_rating.imdb_rating, user_rating.user_rating, watch_list.is_on_watch_list
FROM movie
INNER JOIN production_year
ON movie.year_id = production_year.id
INNER JOIN genre
ON movie.genre_id = genre.id
INNER JOIN actor
ON movie.actor_id = actor.id

INNER JOIN director
ON movie.director_id = director.id
INNER JOIN imdb_rating
ON movie.imdb_rating_id = imdb_rating.id

INNER JOIN user_rating
ON movie.user_rating_id = user_rating.id
INNER JOIN watch_list
ON movie.watch_list_id = watch_list.id;



