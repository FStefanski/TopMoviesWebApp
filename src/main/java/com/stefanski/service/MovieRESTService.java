package com.stefanski.service;

import java.util.List;
import java.util.Map;

import com.stefanski.entity.Movie;

public interface MovieRESTService {

	/**
	 * Find a current list of top movies from imdb
	 * 
	 * @return Map<Integer, String> - imdb top movie position (key) with the imbd id
	 *         (value)
	 */
	Map<Integer, String> findAllTopMovies();

	/**
	 * Fetch from REST server a movie from the map and save it one by one.
	 * 
	 * @param topMoviesMap
	 *            - Map<Integer, String> - imdb top movie position (key) with the
	 *            imbd id (value)
	 */
	void fetchAndSaveAllMoviesOneByOne(Map<Integer, String> topMoviesMap);

	/**
	 * Fetch from REST server all movies from the list
	 * 
	 * @param theMoviesIDList
	 * @return
	 */
	List<Movie> fetchAllMovies(List<String> theMoviesIDList);

	/**
	 * Save all movies from the list to the data base
	 * 
	 * @param theMoviesList
	 */
	void saveAllMovies(List<Movie> theMoviesList);
}
