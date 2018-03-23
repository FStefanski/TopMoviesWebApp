package com.stefanski.dao;

import java.util.List;

import com.stefanski.entity.Movie;

/**
 * 
 * @author Frederik Stefanski
 *
 */
public interface MovieDAO {

	List<Movie> getMovies();

	void saveMovie(Movie theMovie);

	void saveAllMovies(List<Movie> theMoviesList);

	void deleteMovie(int theId);

	Movie getMovie(int theId);

	List<Movie> searcMovies(String theSearchValue);

	List<Movie> searcMoviesByImdbID(String movieId);
}
