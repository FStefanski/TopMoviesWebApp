package com.stefanski.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.stefanski.dao.MovieDAO;
import com.stefanski.entity.Movie;

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
	@Override
	public List<Movie> fetchAllMovies(List<String> theMoviesIDList) {

		List<Movie> topMoviesList = new ArrayList<>();
		Movie movie = null;

		// movies fetched counter
		int moviesFetchedCounter = 0;
		// REST server limitations counter
		int RESTServerLimitatCounter = 40;

		for (String imdbID : theMoviesIDList) {

			// restrain the number of downloaded movies - REST server limitations
			if (--RESTServerLimitatCounter > 0) {

				// download only if the movie doesn't exists in data base - checked by imdbID,
				// e.g. "tt0111161"
				if (movieDAO.searchMoviesByImdbID(imdbID).isEmpty()) {

					System.out.println("\n>> Downloading movie: " + imdbID + " ...");
					try {
						movie = restTemplate.getForObject(REST_SERVICE_URI + "{id}", Movie.class, imdbID);
					} catch (RestClientException e) {
						e.printStackTrace();
					}
					topMoviesList.add(movie);
					moviesFetchedCounter++;

					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("\t\t-- Movie: " + imdbID + " already exists!");
				}

			} else {

				break;
			}
		}
		System.out.println("\t\t-- Added " + moviesFetchedCounter + " of " + theMoviesIDList.size() + " movies");
		return topMoviesList;
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

	// public static void main(String[] args) {
	//
	// MovieClientImpl movieClientImpl = new MovieClientImpl();
	//
	// String movieId = "tt5074352";
	// Movie movie = movieClientImpl.fetchMovieById(movieId);
	// System.out.println(movie.toString());
	// }
}
