package com.stefanski.service;

import java.util.Map;

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
	 * Fetch from REST server a movie from the map and save it. The REST client side
	 * is done concurrently.
	 * 
	 * @param topMoviesMap
	 *            - Map<Integer, String> - imdb top movie position (key) with the
	 *            imbd id (value)
	 */
	void fetchAndSaveAllMoviesConcurrently(Map<Integer, String> topMoviesMap);
}
