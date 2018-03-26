package com.stefanski.service;

import java.util.List;

import com.stefanski.entity.Movie;

/**
 * 
 * @author Frederik Stefanski
 *
 */
public interface MovieService {

	List<Movie> getMovies();

	void saveMovie(Movie theMovie);

	void saveAllMovies(List<Movie> theMoviesList);

	void deleteMovie(int theId);

	Movie getMovie(int theId);

	List<Movie> searcMovies(String theSearchedValue);

	List<Movie> searcMoviesByImdbID(String movieId);
}
