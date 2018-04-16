
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
    
INSERT IGNORE INTO `genre_mapping` VALUES 
	(1, 1, 1),
	(2, 2, 1),
	(3, 3, 2),
	(4, 4, 2),
	(5, 1, 3),
	(6, 2, 3),
	(7, 3, 3),
	(8, 4, 4);
    
INSERT IGNORE INTO `actor` VALUES 
	(1,'actor 1'),
	(2,'actor 2'),
	(3,'actor 3'),
	(4,'actor 4'),
	(5,'actor 5'),
	(6,'actor 6'),
	(7,'actor 7');
    
INSERT IGNORE INTO `cast_mapping` VALUES 
	(1, 1, 1),
    (2, 2, 1),
    (3, 3, 1),
    (4, 4, 1),
    (5, 1, 2),
    (6, 1, 3),
    (7, 1, 4),
    (8, 4, 4);
    
INSERT IGNORE INTO `director` VALUES 
	(1,'director 1'),
	(2,'director 2'),
	(3,'director 3'),
	(4,'director 4'),
	(5,'director 5'),
	(6,'director 6');
    
INSERT IGNORE INTO `director_mapping` VALUES 
	(1, 1, 1),
    (2, 4, 1),
    (3, 3, 2),
    (4, 1, 3),
    (5, 2, 3),
    (6, 1, 4);
    
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
	(1, "yes"),
	(2, "no"),
	(3, "in two weeks"),
	(4, null);
    
INSERT IGNORE INTO `ratings_and_settings_mapping` VALUES 
	(1, 1, 1, 1, 1),
	(2, 1, 2, 1, 2),
	(3, 1, 3, 2, 3),
	(4, 1, 2, 4, 4);
    
INSERT IGNORE INTO `movie` VALUES 
	(1, 1,'imdb_id 1','title 1', 'poster 1', 1),
	(2, 2,'imdb_id 2','title 2', 'poster 2', 2),
	(3, 3,'imdb_id 3','title 3', 'poster 3', 3),
	(4, 4,'imdb_id 4','title 4', 'poster 4', 4);
-- -----------------------------------------------------
-- TEST DB
-- -----------------------------------------------------
/* add new movie, if actor exists use actor_id from actor table in cast table */
/* -> auto incrementation of Primary Key - In order to take advantage of the auto-incrementing capability of the column, 
	  do not supply a value for that column when inserting rows. The database will supply a value for you.*/
INSERT INTO movie (movie_id, imdb_ranking_position, imdb_id, title, poster, year_id) 
VALUES (NULL, 5, 'imdb_id 5', 'title 5', 'poster 5', 1);

/* test data */
/*SHOW tables;*/
SELECT * FROM movie;
SELECT * FROM ratings_and_settings_mapping;
SELECT * FROM actor;
SELECT * FROM genre;

/* Test LEFT join */
/* LEFT JOIN to show all movies even if the associated values are null */
SELECT movie.imdb_ranking_position, movie.imdb_id, movie.title, movie.poster,
	   production_year.year,
       GROUP_CONCAT(genre.genre_type) as genre,
       GROUP_CONCAT(actor.name) as actor,
       GROUP_CONCAT(director.name) as director,
	   imdb_rating.imdb_rating,
	   user_rating.user_rating,
	   watch_list.watch_list
       
FROM movie  
LEFT JOIN production_year ON movie.year_id = production_year.year_id

/* joining tables via junction table */
LEFT JOIN genre_mapping ON movie.movie_id = genre_mapping.movie_id
LEFT JOIN genre ON genre_mapping.genre_id = genre.genre_id

/* joining tables via junction table */
LEFT JOIN cast_mapping ON movie.movie_id = cast_mapping.movie_id
LEFT JOIN actor ON cast_mapping.actor_id = actor.actor_id

/* joining tables via junction table */
LEFT JOIN director_mapping ON movie.movie_id = director_mapping.movie_id
LEFT JOIN director ON director_mapping.director_id = director.director_id

/* joining tables via junction table */
LEFT JOIN ratings_and_settings_mapping ON movie.movie_id = ratings_and_settings_mapping.movie_id
LEFT JOIN imdb_rating ON ratings_and_settings_mapping.imdb_rating_id = imdb_rating.imdb_rating_id
LEFT JOIN user_rating ON ratings_and_settings_mapping.user_rating_id = user_rating.user_rating_id
LEFT JOIN watch_list ON ratings_and_settings_mapping.watch_list_id = watch_list.watch_list_id

group by movie.movie_id;
