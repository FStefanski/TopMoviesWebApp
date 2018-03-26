package com.stefanski.config;

import java.util.List;

import com.stefanski.entity.Movie;

public interface MovieRESTClient {

	List<Movie> fetchAllMovies(List<String> topMoviesIdList);

	Movie fetchMovieById(String id);

}
