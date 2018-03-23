package com.stefanski.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.stefanski.entity.Movie;

@Component
public class MovieClientImpl implements MovieClient {

	/* Consuming REST service */
	public static final String API_KEY = "115fc92c";
	public static final String REST_SERVICE_URI = "http://www.omdbapi.com/?apikey=" + API_KEY + "&i=";

	@Autowired
	RestTemplate restTemplate = new RestTemplate();

	/* GET */
	@Override
	public List<Movie> fetchAllMovies(List<String> topMoviesIdList) {

		List<Movie> topMoviesList = new ArrayList<>();
		Movie movie = null;

		// movies fetched counter
		int moviesFetchedCounter = 0;

		for (String movieId : topMoviesIdList) {

			movie = restTemplate.getForObject(REST_SERVICE_URI + "{id}", Movie.class, movieId);
			System.out.println(movie.toString());
			topMoviesList.add(movie);

			if (moviesFetchedCounter == 10) {
				break;
			} else {
				moviesFetchedCounter++;
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("\n\t\t>> Added " + moviesFetchedCounter + " of " + topMoviesIdList.size() + " movies");

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
