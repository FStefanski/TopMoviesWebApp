package com.stefanski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanski.bgjobs.MovieRESTClient;
import com.stefanski.bgjobs.TopMoviesIdFinder;
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
	@Autowired
	private MovieService movieService;

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

	@Transactional // defining transactions at Service layer
	@Override
	public void fetchAndSaveAllMoviesConcurrently(Map<Integer, String> topMoviesMap) {

		List<Future<Movie>> theFutureMoviesList = new ArrayList<>();
		String imdbID = null;

		for (int key = 0; key <= topMoviesMap.size(); key++) {

			imdbID = topMoviesMap.get(key);

			if (imdbID != null) {
				// download only if the movie doesn't exists in data base - checked by imdbID,
				// e.g. "tt0111161"
				if (movieDAO.searchMoviesByImdbID(imdbID).isEmpty()) {

					System.out.println(">> Fetching movie: " + imdbID);
					theFutureMoviesList.add(movieRESTClient.fetchMovieByIdConcurrently(key, imdbID));
				} else {
					// System.out.println(">> Movie: " + imdbID + " already exists!");
				}
			}
		}

		// extract all Movies form Future List if task is done
		System.out.println(">> Fetched: " + theFutureMoviesList.size() + " movies of " + topMoviesMap.size() + "!");

		List<Movie> theMoviesList = new ArrayList<>();
		Movie movie = null;

		while (!theFutureMoviesList.isEmpty()) {
			for (int futureCounter = 0; futureCounter < theFutureMoviesList.size(); futureCounter++) {

				// get Movie only if the Future task is already done
				if (theFutureMoviesList.get(futureCounter).isDone()) {
					try {
						movie = theFutureMoviesList.get(futureCounter).get();
						theMoviesList.add(movie);
						// save movie using Movie Service
						movieService.saveMovie(movie);
						// remove saved movie from the list
						theFutureMoviesList.remove(futureCounter);
						break;
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
