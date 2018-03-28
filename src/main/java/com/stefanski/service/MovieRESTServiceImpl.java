package com.stefanski.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanski.bgjobs.TopMoviesIdFinder;
import com.stefanski.config.MovieRESTClient;
import com.stefanski.dao.MovieDAO;
import com.stefanski.entity.Movie;

/**
 * 
 * @author Frederik Stefanski
 *
 */
@Service
public class MovieRESTServiceImpl implements MovieRESTService {

	// need to inject the movie DAO, finder, REST Client
	@Autowired
	private MovieDAO movieDAO;
	@Autowired
	private TopMoviesIdFinder topMoviesIdFinder;
	@Autowired
	private MovieRESTClient movieRESTClient;

	@Override
	public Map<Integer, String> findAllTopMovies() {

		return topMoviesIdFinder.findAllTopMovies();
	}

	@Transactional // defining transactions at Service layer
	@Override
	public void fetchAndSaveAllMoviesOneByOne(Map<Integer, String> topMoviesMap) {

		topMoviesMap.forEach(
				(imdbPosition, imdbID) -> movieDAO.saveMovie(movieRESTClient.fetchMovieById(imdbPosition, imdbID)));
	}

	@Override
	public List<Movie> fetchAllMovies(List<String> theMoviesIDList) {

		return movieRESTClient.fetchAllMovies(theMoviesIDList);
	}

	@Transactional // defining transactions at Service layer
	@Override
	public void saveAllMovies(List<Movie> theMoviesList) {

		if (!theMoviesList.isEmpty()) {
			for (Movie movie : theMoviesList) {
				movieDAO.saveMovie(movie);
			}
		}
	}
}
