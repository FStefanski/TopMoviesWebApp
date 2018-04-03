package com.stefanski.bgjobs;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.stefanski.dao.MovieDAO;
import com.stefanski.entity.Movie;

@EnableAsync
@Component
public class MovieRESTClientImpl implements MovieRESTClient {

	/* Consuming REST service */
	public static final String API_KEY = "115fc92c";
	public static final String REST_SERVICE_URI = "http://www.omdbapi.com/?apikey=" + API_KEY + "&i=";

	// need to inject the movie dao, rest template
	@Autowired
	RestTemplate restTemplate = new RestTemplate();
	@Autowired
	private MovieDAO movieDAO;

	/* GET */
	@Async // for more see http://www.baeldung.com/spring-async
	@Override
	public Future<Movie> fetchMovieByIdConcurrently(Integer imdbPostion, String imdbID) {

		System.out.println("\t\t-- Execute method asynchronously - " + Thread.currentThread().getName());

		Movie movie = null;

		try {
			movie = restTemplate.getForObject(REST_SERVICE_URI + "{id}", Movie.class, imdbID);
			// add imdb position to movie
			movie.setImdbPosition(imdbPostion);
			// return async result
			return new AsyncResult<Movie>(movie);
		} catch (ResourceAccessException e) {

			// java.net.ConnectException: Connection timed out: connect
			System.out.println("\t\too Failed to fetch Movie " + imdbID);
			// e.printStackTrace();

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		return null;
	}

	/* GET */
	@Override
	public Movie fetchMovieById(String imdbID) {

		Movie movie = null;

		// download only if the movie doesn't exists in data base - checked by imdbID,
		// e.g. "tt0111161"
		if (movieDAO.searchMoviesByImdbID(imdbID).isEmpty()) {

			System.out.println("\n>> Downloading movie: " + imdbID + " ...");
			try {
				movie = restTemplate.getForObject(REST_SERVICE_URI + "{id}", Movie.class, imdbID);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("\t\t-- Movie: " + imdbID + " already exists!");
		}

		return movie;
	}

	/* GET */
	@Override
	public Movie fetchMovieById(Integer imdbPostion, String imdbID) {

		Movie movie = null;

		// download only if the movie doesn't exists in data base - checked by imdbID,
		// e.g. "tt0111161"
		if (movieDAO.searchMoviesByImdbID(imdbID).isEmpty()) {

			System.out.println("\n>> Downloading movie: " + imdbID + " ...");
			try {
				movie = restTemplate.getForObject(REST_SERVICE_URI + "{id}", Movie.class, imdbID);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			// add imdb position to movie
			movie.setImdbPosition(imdbPostion);

		} else {
			System.out.println("\t\t-- Movie: " + imdbID + " already exists!");
		}

		return movie;
	}
}
