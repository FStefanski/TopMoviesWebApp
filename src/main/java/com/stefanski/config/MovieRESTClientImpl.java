package com.stefanski.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.stefanski.dao.MovieDAO;
import com.stefanski.entity.Movie;

@Component
public class MovieRESTClientImpl implements MovieRESTClient {

	/* Consuming REST service */
	public static final String API_KEY = "115fc92c";
	public static final String REST_SERVICE_URI = "http://www.omdbapi.com/?apikey=" + API_KEY + "&i=";

	@Autowired
	RestTemplate restTemplate = new RestTemplate();
	// need to inject the movie dao
	@Autowired
	private MovieDAO movieDAO;

	/* GET */
	@Override
	public List<Movie> fetchAllMovies(List<String> topMoviesIdList) {

		List<Movie> topMoviesList = new ArrayList<>();
		Movie movie = null;

		// movies fetched counter
		int moviesFetchedCounter = 0;
		// REST server limitations counter
		int RESTServerLimitatCounter = 100;

		for (String movieId : topMoviesIdList) {

			// restrain the number of downloaded movies - REST server limitations
			if (--RESTServerLimitatCounter > 0) {

				// download only if the movie doesn't exists in data base - checked by imdbID,
				// e.g. "tt0111161"
				if (movieDAO.searcMoviesByImdbID(movieId).isEmpty()) {

					System.out.println("\t\t>> Downloading movie: " + movieId + " ...");
					movie = restTemplate.getForObject(REST_SERVICE_URI + "{id}", Movie.class, movieId);
					topMoviesList.add(movie);
					moviesFetchedCounter++;

					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("\t\t>> Movie: " + movieId + " already exists!");
				}

			} else {

				break;
			}
		}
		System.out.println("\t>> Added " + moviesFetchedCounter + " of " + topMoviesIdList.size() + " movies");
		return topMoviesList;
	}

	/* GET */
	@Override
	public Movie fetchMovieById(String movieId) {

		Movie movie = restTemplate.getForObject(REST_SERVICE_URI + "{id}", Movie.class, movieId);

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
