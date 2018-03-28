package com.stefanski.bgjobs;

import java.util.Map;

public interface TopMoviesIdFinder {

	/**
	 * Find a current list of top movies from imdb
	 * 
	 * @return Map<Integer, String> - imdb top movie position (key) with the imbd id
	 *         (value)
	 */
	Map<Integer, String> findAllTopMovies();
}
