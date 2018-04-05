/*CREATE USER 'jeffrey'@'localhost' IDENTIFIED BY 'password';*/

drop user topmoviesuser@localhost;
/* flush privileges; */

CREATE USER 'topmoviesuser'@'localhost' IDENTIFIED BY 'topmovies';

GRANT ALL PRIVILEGES ON * . * TO 'topmoviesuser'@'localhost';
